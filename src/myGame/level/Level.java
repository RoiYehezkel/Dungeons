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
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4; // left map bound
		int x1 = (xScroll + screen.width + 16) >> 4; // right map bound
		int y0 = yScroll >> 4; // upper map bound
		int y1 = (yScroll + screen.height+16) >> 4; // lower map bound

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen); // render every tile

			}
		}
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return Tile.voidTile;
		if (tiles[x + y * width] == 0)
			return Tile.grass;
		return Tile.voidTile;
	}

}
