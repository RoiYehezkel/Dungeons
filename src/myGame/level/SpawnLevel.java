package myGame.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import entity.mob.Chaser;
import entity.mob.Dummy;
import entity.mob.Star;

public class SpawnLevel extends Level {

	public SpawnLevel(String path) {
		super(path);
	}

	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			int w = width = image.getWidth(); // get the size of the platform
			int h = height = image.getHeight(); // get the size of the platform
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception! Could not load level file");
		}
		// create enemy
		add(new Chaser(20, 55));
		add(new Star(17, 35));
		for (int i = 0; i < 5; i++) {
			add(new Dummy(20, 55));
		}
	}

	protected void generateLevel() {

	}

}
