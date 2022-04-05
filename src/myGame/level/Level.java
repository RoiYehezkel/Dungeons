package myGame.level;

import myGame.graphics.Screen;
import myGame.level.tile.Tile;

public class Level {

	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles; // symbol for every tiles
	public static Level spwan = new SpawnLevel("/levels/spawn.png");

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel(); // generate random level
	}

	public Level(String path) {
		loadLevel(path); // generate level from file
		generateLevel(); // generate random level
	}

	protected void generateLevel() {

	}

	protected void loadLevel(String path) {

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
		int y1 = (yScroll + screen.height + 16) >> 4; // lower map bound

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen); // render every tile

			}
		}
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return Tile.spawn_water;
		if (tiles[x + y * width] == Tile.col_spawn_floor)
			return Tile.spawn_floor; // return sprite of floor
		if (tiles[x + y * width] == Tile.col_spawn_grass)
			return Tile.spawn_grass; // return sprite of grass
		if (tiles[x + y * width] == Tile.col_spawn_hedge)
			return Tile.spawn_hedge; // return sprite of hedge
		if (tiles[x + y * width] == Tile.col_spawn_wall1)
			return Tile.spawn_wall1; // return sprite of wall1
		if (tiles[x + y * width] == Tile.col_spawn_wall2)
			return Tile.spawn_wall2; // return sprite of wall2
		if (tiles[x + y * width] == Tile.col_spawn_water)
			return Tile.spawn_water; // return sprite of water
		return Tile.spawn_water;
	}

}
