package myGame.graphics;

import java.util.Random;

import entity.mob.Player;
import myGame.level.tile.Tile;

public class Screen {

	public int width, height;
	public int[] pixels;
	public final int MAP_SIZE = 64;
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;
	public int xOffset, yOffset;
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

	public void renderTile(int xp, int yp, Tile tile) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < tile.sprite.SIZE; y++) {
			int ya = yp + y; // setting the offset of y
			for (int x = 0; x < tile.sprite.SIZE; x++) {
				int xa = xp + x; // setting the offset of x
				if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height)
					break; // out of bounds
				if (xa < 0)
					xa = 0;
				pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
			}
		}
	}

	public void renderPlayer(int xp, int yp, Sprite sprite) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < 16; y++) {
			int ya = yp + y; // setting the offset of y
			for (int x = 0; x < 16; x++) {
				int xa = xp + x; // setting the offset of x
				if (xa < -16 || xa >= width || ya < 0 || ya >= height)
					break; // out of bounds
				if (xa < 0)
					xa = 0;
				int col = sprite.pixels[x + y * 16]; // get the color of the pixel
				if (col != 0xffff00ff) // delete the pink pixel from the picture
					pixels[xa + ya * width] = col;
			}
		}

	}

	public void setOffset(int xOffset, int yOffset) {
		// set the offset of the screen
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

}
