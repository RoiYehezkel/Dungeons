package myGame.graphics;

import java.util.Random;

import myGame.level.tile.Tile;

public class Screen {

	private int width, height;
	public int[] pixels;
	public final int MAP_SIZE = 64;
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE]; // making the screen as 64*64 tiles
	private Random rand = new Random();

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height]; // 50,400

		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			tiles[i] = rand.nextInt(0xffffff); // choose any color from the 16.9 million available randomly(ffffff is white)
		}

	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void render(int xOffset, int yOffset) {
		for (int y = 0; y < height; y++) {
			int yp = y + yOffset;
			if (yp < 0 || yp >= height)
				continue; // out of bounds
			for (int x = 0; x < width; x++) {
				int xp = x + xOffset;
				if (xp < 0 || xp >= width)
					continue; // out of bounds
				pixels[xp + yp * width] = Sprite.grass.pixels[(x & 15) + (y & 15) * Sprite.grass.SIZE];

			}
		}
	}

	public void renderTile(int xp, int yp, Tile tile) {
		for (int y = 0; y < tile.sprite.SIZE; y++) {
			int ya = yp + y; // setting the offset of y
			for (int x = 0; x < tile.sprite.SIZE; x++) {
				int xa = xp + x; // setting the offset of x
				if (xa < 0 || xa >= width || ya < 0 || ya >= width)
					break; // out of bounds
				pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
			}
		}

	}

}
