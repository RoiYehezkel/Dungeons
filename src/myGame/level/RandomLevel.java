package myGame.level;

import java.util.Random;

public class RandomLevel extends Level {

	private static final Random rand = new Random();

	public RandomLevel(int width, int height) {
		super(width, height);

	}

	protected void generateLevel() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[x + y * width] = rand.nextInt(4); // choose random tile
				
			}
		}
	}

}
