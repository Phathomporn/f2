package f2;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Shoot extends Sprite{
	public static final int Y_TO_FADE = 400;
	public static final int Y_TO_DIE = 600;	

	private int step = 12;
	private boolean alive = true;
	private Image pic;

	public Shoot(int x, int y) {
		super(x, y, 25, 25);

		try{
			pic = ImageIO.read(new File("f2/pic/the-bullet.png"));
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(pic, x, y, width, height, null);
	}
	public void proceed(){
		y -= step;
		if(y > Y_TO_DIE){
			alive = false;
		}
	}
	
	public boolean isAlive(){
		return alive;
	}
} 