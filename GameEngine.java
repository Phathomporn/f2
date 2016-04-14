package f2;

import java.awt.geom.Rectangle2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.ArrayList;
import javax.swing.Timer;


public class GameEngine{
	GamePanel gp;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	
	private Timer timer;
	
	public GameEngine(GamePanel gp) {
		this.gp = gp;	
	
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

	private void process(){
		gp.updateGameUI();
		generateEnemy();

		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();

			if(!e.isAlive()){
				e_iter.remove();
			}
		}
	}
	
	public void die(){
		timer.stop();
	}
}