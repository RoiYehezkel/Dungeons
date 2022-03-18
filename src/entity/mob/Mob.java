package entity.mob;

import entity.Entity;
import myGame.graphics.Sprite;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	protected int dir = 0;
	protected boolean moving = false;

	public void nove() {

	}

	public void updtae() {

	}

	private boolean collision() {
		return false;
	}

	public void render() {

	}

}
