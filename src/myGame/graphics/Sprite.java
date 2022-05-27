package myGame.graphics;

public class Sprite {

	public final int SIZE;
	private int x, y;
	public int[] pixels;
	protected SpriteSheet sheet;
	private int width, height;

	public static Sprite voidSprite = new Sprite(16, 0x1B87E0); // empty sprite(blue)

	// SpawnLevel Sprites
	public static Sprite spawn_grass = new Sprite(16, 0, 0, SpriteSheet.spawn_level);
	public static Sprite spawn_hedge = new Sprite(16, 1, 0, SpriteSheet.spawn_level);
	public static Sprite spawn_water = new Sprite(16, 2, 0, SpriteSheet.spawn_level);
	public static Sprite spawn_wall1 = new Sprite(16, 0, 1, SpriteSheet.spawn_level);
	public static Sprite spawn_wall2 = new Sprite(16, 0, 2, SpriteSheet.spawn_level);
	public static Sprite spawn_floor = new Sprite(16, 1, 1, SpriteSheet.spawn_level);

	// Projectile Sprites
	public static Sprite projectile_wizard = new Sprite(16, 0, 0, SpriteSheet.projectile_wizard);
	public static Sprite projectile_arrow = new Sprite(16, 1, 0, SpriteSheet.projectile_wizard);

	// Particles Sprites
	public static Sprite particle_normal = new Sprite(2, 0xAAAAAA);

	// Player Sprites
	public static Sprite player_forward = new Sprite(32, 1, 7, SpriteSheet.tiles);
	public static Sprite player_back = new Sprite(32, 1, 4, SpriteSheet.tiles);
	public static Sprite player_left = new Sprite(32, 1, 5, SpriteSheet.tiles);
	public static Sprite player_right = new Sprite(32, 1, 6, SpriteSheet.tiles);

	// walking forward
	public static Sprite player_forward_1 = new Sprite(32, 0, 7, SpriteSheet.tiles);
	public static Sprite player_forward_2 = new Sprite(32, 2, 7, SpriteSheet.tiles);

	// walking backward
	public static Sprite player_back_1 = new Sprite(32, 0, 4, SpriteSheet.tiles);
	public static Sprite player_back_2 = new Sprite(32, 2, 4, SpriteSheet.tiles);

	// walking left
	public static Sprite player_left_1 = new Sprite(32, 0, 5, SpriteSheet.tiles);
	public static Sprite player_left_2 = new Sprite(32, 2, 5, SpriteSheet.tiles);

	// walking right
	public static Sprite player_right_1 = new Sprite(32, 0, 6, SpriteSheet.tiles);
	public static Sprite player_right_2 = new Sprite(32, 2, 6, SpriteSheet.tiles);

	public static Sprite dummy = new Sprite(32, 0, 0, SpriteSheet.dummy_down);
	public static Sprite chaser = new Sprite(32, 0, 0, SpriteSheet.chaser_down);

	protected Sprite(SpriteSheet sheet, int width, int height) {
		SIZE = width == height ? width : -1;
		this.width = width;
		this.height = height;
		this.sheet = sheet;
	}

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load(); // set a single sprite to the screen
	}

	public Sprite(int width, int height, int colour) {
		SIZE = -1;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		setColour(colour);
	}

	public Sprite(int size, int colour) // constructor fo particle of projectile
	{
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		setColour(colour);
	}

	public Sprite(int[] pixels, int width, int height) {
		SIZE = width == height ? width : -1;
		this.width = width;
		this.height = height;
		// copy the array instead of copy the reference
		this.pixels = new int[pixels.length];
		for (int i = 0; i < pixels.length; i++) {
			this.pixels[i] = pixels[i];
		}
	}

	public static Sprite rotate(Sprite sprite, double angle) {
		return new Sprite(rotate(sprite.pixels, sprite.width, sprite.height, angle), sprite.width, sprite.height);
	}

	// rotate projectile by angle
	private static int[] rotate(int[] pixels, int width, int height, double angle) {
		int[] result = new int[width * height];
		// calculate angles to rotate
		double nx_x = rot_x(-angle, 1.0, 0.0);
		double nx_y = rot_y(-angle, 1.0, 0.0);
		double ny_x = rot_x(-angle, 0.0, 1.0);
		double ny_y = rot_y(-angle, 0.0, 1.0);
		double x0 = rot_x(-angle, -width / 2.0, -height / 2) + width / 2.0;
		double y0 = rot_y(-angle, -width / 2.0, -height / 2) + height / 2.0;
		for (int y = 0; y < height; y++) {
			double x1 = x0;
			double y1 = y0;
			for (int x = 0; x < width; x++) {
				int xx = (int) x1;
				int yy = (int) y1;
				// default color
				int col = 0;
				if (xx < 0 || xx >= width || yy < 0 || yy >= height)
					col = 0xffff00ff;
				else
					col = pixels[xx + yy * width];
				result[x + y * width] = col;
				x1 += nx_x;
				y1 += nx_y;
			}
			x0 += ny_x;
			y0 += ny_y;
		}
		return result;
	}

	private static double rot_x(double angle, double x, double y) {
		double cos = Math.cos(angle - Math.PI / 2);
		double sin = Math.sin(angle - Math.PI / 2);
		return x * cos + y * -sin;
	}

	private static double rot_y(double angle, double x, double y) {
		double cos = Math.cos(angle - Math.PI / 2);
		double sin = Math.sin(angle - Math.PI / 2);
		return x * sin + y * cos;
	}

	public static Sprite[] split(SpriteSheet sheet) {
		int amount = (sheet.getWidth() * sheet.getHeight()) / (sheet.WIDTH * sheet.HEIGHT); // amout of sprites
		Sprite[] sprites = new Sprite[amount];
		int current = 0;
		int[] pixels = new int[sheet.HEIGHT * sheet.WIDTH];
		for (int yp = 0; yp < sheet.getHeight() / sheet.HEIGHT; yp++) {
			// 6 times
			for (int xp = 0; xp < sheet.getWidth() / sheet.WIDTH; xp++) {
				// 13 times
				for (int y = 0; y < sheet.HEIGHT; y++) {
					for (int x = 0; x < sheet.WIDTH; x++) {
						// Separate every sprite to single sprite
						int xo = x + xp * sheet.WIDTH;
						int yo = y + yp * sheet.HEIGHT;
						pixels[x + y * sheet.WIDTH] = sheet.getPixels()[xo + yo * sheet.getWidth()];
					}
				}
				// add the single sprite to the array
				sprites[current++] = new Sprite(pixels, sheet.WIDTH, sheet.HEIGHT);
			}
		}

		return sprites;
	}

	private void setColour(int colour) {
		for (int i = 0; i < width * height; i++) {
			pixels[i] = colour;

		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	private void load() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.WIDTH]; // set every single
																									// sprite in
																									// SpriteSheet
			}
		}
	}

}
