package f2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.ArrayList;
import javax.swing.Timer;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;

	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private ArrayList<Shoot> shoot = new ArrayList<Shoot>();
	private SpaceShip v;
	private Timer timer;
	private long score = 0;
	private double difficulty = 0.1;

	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;	

		gp.sprites.add(v);
	
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
			}
		});
		timer.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();
	}

	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		enemies.add(e);
	}

	public void generateBullet(int x, int y){
		Shoot ss = new Shoot(v.x, v.y);
		gp.sprites.add(ss);
		shoot.add(ss);
	}

	private void process(){
		
		if(Math.random() < difficulty){
			generateEnemy();
		}

		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();

			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				//score += 100;
			}
		}

		Iterator<Shoot> s_iter = shoot.iterator();
		while(s_iter.hasNext()){
			Shoot sh = s_iter.next();
			sh.proceed();

			if(!sh.isAlive()){
				s_iter.remove();
				gp.sprites.remove(sh);
				score += 100;
			}			
		}

		gp.updateGameUI(this);

		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		Rectangle2D.Double sr;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				die();
				return;
			}

			else{
				for(Shoot s : shoot){
					sr = s.getRectangle();
					if(sr.intersects(er)){
							gp.sprites.remove(s);
							gp.sprites.remove(e);	
							score += 100;				
					}
				}
			}
		}
	}
	
	public void die(){
		timer.stop();
	}
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			v.move(-1);
			break;
		case KeyEvent.VK_RIGHT:
			v.move(1);
			break;
		case KeyEvent.VK_SPACE:
			generateBullet(v.x, v.y);
			break;
		case KeyEvent.VK_D:
			difficulty += 0.1;
			break;
		}
	}


	public long getScore(){
		return score;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}