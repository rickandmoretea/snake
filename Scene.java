import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;


public class Scene extends JPanel implements Runnable {
	public static int WIDTH = 800;
	public static int HEIGHT = 600;
	private Thread th;
	private boolean isRunning = false; // keeping track of running status
	private boolean isGameOver = false;
	// Snake
	private snakeSprite s = 10;
	private int y = 10;

	// Control
	private boolean right = false;
	private boolean left = false;
	private boolean up = false;
	private boolean down = true;
	private int count = 0;

	// Food
	private Food f;
	private ArrayList<Food> foodArr;
	private boolean isFoodEaten = false;

	public Scene() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		snakeArr = new ArrayList<>();
		foodArr = new ArrayList<>();
		addKeyListener(new KeyInner());
		setFocusable(true);
	}
	public void startGame() {
		isRunning = true;
		th = new Thread(this);
		th.start();
	}
	@Override
	public void run() {
		
		while (isRunning) {
			tick();
			repaint();
		}
	}

	// default settings
	private void tick() {
		if (snakeArr.size() == 0) {
			s = new snakeSprite(x, y, 10);
			snakeArr.add(s);
		}
		if (foodArr.size() == 0) {
			// generate food for snake - add them to arrayList
			int xfood = new Random().nextInt(60);
			int yfood = new Random().nextInt(60);
			f = new Food(xfood, yfood, 10);
			foodArr.add(f);
		}
		// Check collision
		for (int i = 0; i < foodArr.size(); i++) {
			if (x == foodArr.get(i).getX() && y == foodArr.get(i).getY()) {
				System.out.println("You have eaten the food... now it's time to getting longer");
				isFoodEaten = true;
				size++;
				foodArr.remove(i);
				i--;
			}

		}
		//Snake eat their tail and die 
		for (int j = 0; j < snakeArr.size(); j++) {
			if (x == snakeArr.get(j).getX() && y == snakeArr.get(j).getY()) {
				if (j != snakeArr.size() - 1) {
					stop();
				}
			}

		}
		//Check if the snake smash itself into wall
		if (x < 0 || x > 79 || y < 0 || y > 59) {
			stop();
		}
		count++;
		if (count > 60000) {
			if (right) {
				x++;
			}
			if (left) {
				x--;
			}
			if (up) {
				y--;
			}
			if (down) {
				y++;
			}
			count = 0;
			s = new snakeSprite(x, y, 10);
			snakeArr.add(s);
			if (snakeArr.size() > size) {
				snakeArr.remove(0);
			}
		}

	}

	private void stop() {
		isRunning = false;
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	@Override
	public void paint(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.darkGray);
		if (!isRunning) {
			g.setColor(Color.GREEN);
			g.setFont(new Font("Tahoma", Font.BOLD, 40));
			g.drawString("HARDCORE SNAKE GAME", WIDTH/2 - 200, HEIGHT / 2);
			g.setColor(Color.black);
			g.setFont(new Font("Tahoma", Font.BOLD ,15));
			g.drawString("Please Enter SpaceBar To Start Game", WIDTH/2 -130, HEIGHT / 2 + 15);
		} else {
			for (int i = 0; i < WIDTH; i++) {
				g.drawLine(i * 10, 0, i * 10, HEIGHT);
			}
			for (int i = 0; i < HEIGHT; i++) {
				g.drawLine(0, i * 10, WIDTH, i * 10);
			}
			for (int i = 0; i < snakeArr.size(); i++) {
				snakeArr.get(i).draw(g);
			}
			for (int j = 0; j < foodArr.size(); j++) {
				foodArr.get(j).draw(g);
			}
		}

	}

	class KeyInner implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_RIGHT) {
				System.out.println("Snake is moving right from its position");
				up = false;
				down = false;
				left = false;
				right = true;
			}
			if (key == KeyEvent.VK_LEFT) {
				System.out.println("Snake is moving left from its position");
				up = false;
				down = false;
				left = true;
				right = false;
			}
			if (key == KeyEvent.VK_UP) {
				System.out.println("Snake is moving upward from its position");
				up = true;
				down = false;
				left = false;
				right = false;
			}
			if (key == KeyEvent.VK_DOWN) {
				System.out.println("Snake is moving downward from its position");
				up = false;
				down = true;
				left = false;
				right = false;
			}
			if (key == KeyEvent.VK_ENTER) {

				startGame();
			}
			if (key == KeyEvent.VK_W) {
				up = true;
				down = false;
				left = false;
				right = false;
			}
			if (key == KeyEvent.VK_A) {
				up = false;
				down = false;
				right = false;
				left = true;
			}
			if (key == KeyEvent.VK_S) {
				up = false;
				down = true;
				left = false;
				right = false;	
			}
			if (key == KeyEvent.VK_D) {
				up = false;
				down = false;
				right = true;
				down = false;
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyReleased(KeyEvent e) {

		}

	}
}
