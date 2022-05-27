package myGame.graphics;

import java.util.Random;

import entity.mob.Mob;
import entity.projectile.Projectile;
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
			tiles[i] = rand.nextInt(0xffffff); // choose any color from the 16.9 million available randomly(ffffff is
												// white)
		}

	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void renderSheet(int xp, int yp, SpriteSheet sheet, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		for (int y = 0; y < sheet.HEIGHT; y++) {
			int ya = y + yp; // move to the right position instead of the upper right corner
			for (int x = 0; x < sheet.WIDTH; x++) {
				int xa = x + xp; // move to the right position instead of the upper right corner
				if (xa < 0 || xa >= width || ya < 0 || ya >= height)
					continue;
				pixels[xa + ya * width] = sheet.pixels[x + y * sheet.WIDTH];
			}
		}
	}

	public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		for (int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp; // move to the right position instead of the upper right corner
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp; // move to the right position instead of the upper right corner
				if (xa < 0 || xa >= width || ya < 0 || ya >= height)
					continue;
				int col = sprite.pixels[x + y * sprite.getWidth()];
				if (col != 0xffff00ff && col != 0xff7f007f) // delete the pink pixel from the picture
					pixels[xa + ya * width] = col;
			}
		}
	}

	public void renderTextCharacter(int xp, int yp, Sprite sprite, int color, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		for (int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp; // move to the right position instead of the upper right corner
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp; // move to the right position instead of the upper right corner
				if (xa < 0 || xa >= width || ya < 0 || ya >= height)
					continue;
				int col = sprite.pixels[x + y * sprite.getWidth()];
				if (col != 0xffff00ff && col != 0xff7f007f) // delete the pink pixel from the picture
					pixels[xa + ya * width] = color;
			}
		}
	}

	public void renderProjectile(int xp, int yp, Projectile p) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < p.getSpriteSize(); y++) {
			int ya = y + yp; // move to the right position instead of the upper right corner
			for (int x = 0; x < p.getSpriteSize(); x++) {
				int xa = x + xp; // move to the right position instead of the upper right corner
				if (xa < -p.getSpriteSize() || xa >= width || ya < 0 || ya >= height)
					break;
				if (xa < 0)
					xa = 0;
				int col = p.getSprite().pixels[x + y * p.getSpriteSize()];
				if (col != 0xffff00ff) // delete the pink pixel from the picture
					pixels[xa + ya * width] = col;
			}
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

	public void renderMob(int xp, int yp, Sprite sprite) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < 32; y++) {
			int ya = yp + y; // setting the offset of y
			int ys = y;
			for (int x = 0; x < 32; x++) {
				int xa = xp + x; // setting the offset of x
				int xs = x;
				if (xa < -32 || xa >= width || ya < 0 || ya >= height)
					break; // out of bounds
				if (xa < 0)
					xa = 0;
				int col = sprite.pixels[xs + ys * 32]; // get the color of the pixel
				if (col != 0xffff00ff) // delete the pink pixel from the picture
					pixels[xa + ya * width] = col;
			}
		}
	}

	public void drawRect(int xp, int yp, int width, int height, int color, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		for (int x = xp; x < xp + width; x++) {
			if (x < 0 || x >= this.width || yp >= this.height)
				continue;
			if (yp > 0)
				pixels[x + yp * this.width] = color;
			if (yp + height >= this.height)
				continue;
			if (yp + height > 0)
				pixels[x + (yp + height) * this.width] = color;
		}
		for (int y = yp; y <= yp + height; y++) {
			if (xp >= this.width || y < 0 || y >= this.height)
				continue;
			if (xp > 0)
				pixels[xp + y * this.width] = color;
			if (xp + width >= this.width)
				continue;
			if (xp + width > 0)
				pixels[(xp + width) + y * this.width] = color;
		}

	}

	public void setOffset(int xOffset, int yOffset) {
		// set the offset of the screen
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

}
