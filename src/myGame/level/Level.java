package myGame.level;

import myGame.graphics.Screen;

public class Level {

	protected int width, height;
	protected int[] tiles;

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
		generateLevel(); // generate random level
	}

	public Level(String path) {
		loadLevel(path); // generate level from file
	}

	protected void generateLevel() {
	}

	private void loadLevel(String path) {
	}

	public void update() {

	}
	
	private void time() {
		
	}

	public void render(int xScroll, int yScroll, Screen screen) {

	}

}
