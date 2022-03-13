package myGame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import myGame.graphics.Screen;
import myGame.input.Keyboard;

public class Game extends Canvas implements Runnable {
	// static variables
	private static final long serialVersionUID = 1L; // remove a warning in the class name
	public static int width = 300;
	public static int height = width / 16 * 9; // giving aspect ration of 16:9
	public static int scale = 3; // scaling the window size, while the render stay the same
	public static String title = "Rain";
	// object variables
	private Thread thread;
	private JFrame frame; // creating the window for the game
	private Keyboard key; // control key
	private boolean running = false;

	private Screen screen; // screen for dealing with the pixels

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); // each image will be sized by original width and height and later will be scaled
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData(); // get pixels from the image and register them into an array to modify(access the image itself)

	public Game() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);

		screen = new Screen(width, height);
		frame = new JFrame();
		setFocusable(true);
		key = new Keyboard();
		addKeyListener(key);
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime(); // counting the CPU cycle in nano seconds before the loop
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0; // 1 nano second divided by the 60 fps target
		double delta = 0;
		int frames = 0; // fps counter VAR
		int updates = 0;
		while (running) {
			long now = System.nanoTime(); // current loop CPU cycle
			delta += (now - lastTime) / ns; // adding to delta the time difference
			lastTime = now; // updating each loop the last time as the last loop
			while (delta >= 1) // will happened 60 times a second
			{
				update(); // method for updating the frame each time, aiming for 60 fps
				updates++; //every time an update made for the pixels, Inc the updates VAR
				delta--;
			}
			render(); // method for generating the frames in speed limited by user hardware
			frames++;

			if (System.currentTimeMillis() - timer > 1000) // as long as the difference between the start and now is greater then 1 second
			{
				timer += 1000;
				System.out.println(updates + " ups, " + frames + " fps");
				frame.setTitle(title + " | " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0; // setting them back to zero each 60 renders per second
			}
		}
		stop();
	}

	int x = 0, y = 0;

	public void update() {
		key.update();
		if (key.up)
			y--;
		if (key.down)
			y++;
		if (key.right)
			x++;
		if (key.left)
			x--;

	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3); // buffer with triple buffering(= having faster render, storing 2 images in ram)
			return;
		}

		screen.clear(); // clearing the previous pixels
		screen.render(x, y); // from screen class, calling the graphic render for each pixel

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose(); // empty resources of the irrelevant graphics
		bs.show();
	}

}