package f2;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

public abstract class Rectangle {
	int x;
	int y;
	int width;
	int height;
	
	public Rectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	abstract public void draw(Graphics2D g);
	
	public Double getRectangle() {
		return new Rectangle2D.Double(x, y, width, height);
	}
}