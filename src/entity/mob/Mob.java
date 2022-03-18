package entity.mob;

import entity.Entity;
import myGame.graphics.Sprite;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	protected int dir = 0;
	protected boolean moving = false;

	public void move(int xa, int ya) {
		if (xa > 0)
			dir = 1; // go east
		if (xa < 0)
			dir = 3; // go west
		if (ya > 0)
			dir = 2; // go south
		if (ya > 0)
			dir = 0; // go north

		if (!collision()) {
			x += xa; // x = -1, 0, 1
			y += ya; // y = -1, 0, 1
		}
	}

	public void updtae() {

	}

	private boolean collision() {
		return false;
	}

	public void render() {

	}

}
