package entity;

import java.util.Random;

import myGame.graphics.Screen;
import myGame.level.Level;

public abstract class Entity {
	public int x, y;
	private boolean removed = false;
	protected Level level;
	protected final Random rand = new Random();

	public void update() {

	}

	public void render(Screen screen) {

	}

	public void remove() {
		// remove from level
		removed = true;
	}

	public boolean isRemoved() {
		return removed;
	}

}
