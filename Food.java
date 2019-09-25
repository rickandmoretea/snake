import java.awt.Color;
import java.awt.Graphics;

public class Food {
	private int x;
	private int y;
	private int width;
	private int height;
	
	public Food(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.width = size;
		this.height = size;
		
	}
	public void draw(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x*width, y*height, width, height);
		
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
}
