import java.awt.Color;
import java.awt.Graphics;

public class snakeSprite {
	private int x;
	private int y;
	private int width;
	private int height;
	
	public snakeSprite(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.width = size;
		this.height = size;
		
	}
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x*width,y*height,width,height);
		g.setColor(Color.GREEN);
		g.fillRect(x*width+2, y*height+2, width-4, height-4);
	}
	public int getX() {
		return x;
	}
	int getY() {
		return y;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}

}
