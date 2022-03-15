package myGame.level;

import myGame.graphics.Screen;
import myGame.level.tile.Tile;

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
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height) >> 4;
	}

	public Tile getTile(int x, int y) {
		if (tiles[x + y * width] == 0)
			return Tile.grass;
		return Tile.voidTile;
	}

}
