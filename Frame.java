import java.awt.GridLayout;


import javax.swing.JFrame;


public class Frame extends JFrame {

	public Frame() {
		// Creating Frame
		setTitle("HARDCORE SNAKE GAME");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(1, 1, 0, 0));
		start();
		setVisible(true);
	}
	public void start() {
		Scene s = new Scene();
		add(s);
		pack();
		setLocationRelativeTo(null);
		
	}
	public static void main(String[] args) {
		new Frame();

	}

}
