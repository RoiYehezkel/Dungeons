package myGame.graphics;

public class Sprite {

	public final int SIZE;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;

	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles); // create new single sprite
	public static Sprite voidSprite = new Sprite(16, 0x1B87E0); // empty sprite(blue)

	// create new player
	public static Sprite player_forward = new Sprite(32, 0, 5, SpriteSheet.tiles);
	public static Sprite player_back = new Sprite(32, 2, 5, SpriteSheet.tiles);
	public static Sprite player_left = new Sprite(32, 3, 5, SpriteSheet.tiles);
	public static Sprite player_right = new Sprite(32, 1, 5, SpriteSheet.tiles);

	// walking forward
	public static Sprite player_forward_1 = new Sprite(32, 0, 6, SpriteSheet.tiles);
	public static Sprite player_forward_2 = new Sprite(32, 0, 7, SpriteSheet.tiles);

	// walking backward
	public static Sprite player_back_1 = new Sprite(32, 2, 6, SpriteSheet.tiles);
	public static Sprite player_back_2 = new Sprite(32, 2, 7, SpriteSheet.tiles);

	// walking left
	public static Sprite player_left_1 = new Sprite(32, 3, 6, SpriteSheet.tiles);
	public static Sprite player_left_2 = new Sprite(32, 3, 7, SpriteSheet.tiles);

	// walking right
	public static Sprite player_right_1 = new Sprite(32, 1, 6, SpriteSheet.tiles);
	public static Sprite player_right_2 = new Sprite(32, 1, 7, SpriteSheet.tiles);

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load(); // set a single sprite to the screen
	}

	public Sprite(int size, int colour) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		setColour(colour);
	}

	private void setColour(int colour) {
		for (int i = 0; i < SIZE * SIZE; i++) {
			pixels[i] = colour;

		}

	}

	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE]; // set every single spritein SpriteSheet
			}
		}
	}

}
