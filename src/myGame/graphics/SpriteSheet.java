package myGame.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path; // path to image
	public final int SIZE;
	public final int WIDTH, HEIGHT;
	private int width, height;
	public int[] pixels;

	public static SpriteSheet tiles = new SpriteSheet("/textures/sheets/spritesheet.png", 256);
	public static SpriteSheet spawn_level = new SpriteSheet("/textures/sheets/spawn_level.png", 48);
	public static SpriteSheet projectile_wizard = new SpriteSheet("/textures/sheets/projectiles/wizard.png", 48);

	// player sprties
	public static SpriteSheet player = new SpriteSheet("/textures/sheets/player1.png", 96, 128);
	public static SpriteSheet player_down = new SpriteSheet(player, 0, 0, 3, 1, 32);
	public static SpriteSheet player_up = new SpriteSheet(player, 0, 3, 3, 1, 32);
	public static SpriteSheet player_left = new SpriteSheet(player, 0, 1, 3, 1, 32);
	public static SpriteSheet player_right = new SpriteSheet(player, 0, 2, 3, 1, 32);

	// NPC sprites
	public static SpriteSheet dummy = new SpriteSheet("/textures/sheets/player2.png", 96, 128);
	public static SpriteSheet dummy_down = new SpriteSheet(dummy, 0, 0, 3, 1, 32);
	public static SpriteSheet dummy_up = new SpriteSheet(dummy, 0, 3, 3, 1, 32);
	public static SpriteSheet dummy_left = new SpriteSheet(dummy, 0, 1, 3, 1, 32);
	public static SpriteSheet dummy_right = new SpriteSheet(dummy, 0, 2, 3, 1, 32);

	public static SpriteSheet chaser = new SpriteSheet("/textures/sheets/player3.png", 96, 128);
	public static SpriteSheet chaser_down = new SpriteSheet(chaser, 0, 0, 3, 1, 32);
	public static SpriteSheet chaser_up = new SpriteSheet(chaser, 0, 3, 3, 1, 32);
	public static SpriteSheet chaser_left = new SpriteSheet(chaser, 0, 1, 3, 1, 32);
	public static SpriteSheet chaser_right = new SpriteSheet(chaser, 0, 2, 3, 1, 32);

	private Sprite[] sprites;

	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
		int xx = x * spriteSize;
		int yy = y * spriteSize;
		int w = width * spriteSize;
		int h = height * spriteSize;
		if (width == height)
			SIZE = width;
		else
			SIZE = -1;
		WIDTH = w;
		HEIGHT = h;
		pixels = new int[w * h];
		// initialize sprite player in some direction
		for (int y0 = 0; y0 < h; y0++) {
			int yp = yy + y0;
			for (int x0 = 0; x0 < w; x0++) {
				int xp = xx + x0;
				pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.WIDTH];
			}
		}
		int frame = 0;
		sprites = new Sprite[width * height];
		// create array of player sprites
		for (int ya = 0; ya < height; ya++) {
			for (int xa = 0; xa < width; xa++) {
				int[] spritePixels = new int[spriteSize * spriteSize];
				for (int y0 = 0; y0 < spriteSize; y0++) {
					for (int x0 = 0; x0 < spriteSize; x0++) {
						spritePixels[x0 + y0 * spriteSize] = pixels[(x0 + xa * spriteSize) + (y0 + ya * spriteSize) * WIDTH];
					}
				}
				Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);
				sprites[frame++] = sprite;
			}
		}
		// put the default player sprite in the first cell
		Sprite temp = sprites[1];
		sprites[1] = sprites[0];
		sprites[0] = temp;

	}

	public SpriteSheet(String path, int size) {
		this.path = path;
		WIDTH = size;
		HEIGHT = size;
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		load(); // set spritesheet to pixels
	}

	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		SIZE = -1;
		WIDTH = width;
		HEIGHT = height;
		pixels = new int[WIDTH * HEIGHT];
		load(); // set spritesheet to pixels
	}

	public Sprite[] getSprites() {
		return sprites;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int[] getPixels() {
		return pixels;
	}

	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path)); // import spritesheet.png
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width); // set spritesheet to pixels
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(" failed!");
		}
	}
}
