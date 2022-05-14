package entity.mob;

import entity.Entity;
import entity.projectile.Projectile;
import entity.projectile.WizardProjectile;
import myGame.graphics.Screen;

public abstract class Mob extends Entity {

	protected boolean moving = false;
	protected boolean walking = false;

	protected enum Direction {
		UP, DOWN, RIGHT, LEFT
	}

	protected Direction dir;

	public void move(double xa, double ya) {
		if (xa != 0 && ya != 0) {
			move(xa, 0); // to check collision
			move(0, ya); // to check collision
			return;
		}
		if (xa > 0)
			dir = Direction.RIGHT; // go east
		if (xa < 0)
			dir = Direction.LEFT; // go west
		if (ya > 0)
			dir = Direction.DOWN; // go south
		if (ya < 0)
			dir = Direction.UP; // go north
		while (xa != 0) {
			if (Math.abs(xa) > 1) {
				if (!collision(abs(xa), ya)) // check if there is a collision
					this.x += abs(xa); // x = -1, 0, 1
				xa -= abs(xa);
			} else {
				if (!collision(abs(xa), ya)) // check if there is a collision
					this.x += xa; // x = -1, 0, 1
				xa = 0;
			}
		}
		while (ya != 0) {
			if (Math.abs(ya) > 1) {
				if (!collision(xa, abs(ya))) // check if there is a collision
					this.y += abs(ya); // x = -1, 0, 1
				ya -= abs(ya);
			} else {
				if (!collision(xa, abs(ya))) // check if there is a collision
					this.y += ya; // x = -1, 0, 1
				ya = 0;
			}
		}

	}

	private int abs(double value) {
		if (value < 0)
			return -1;
		return 1;
	}

	public abstract void update();

	public abstract void render(Screen screen);

	protected void shoot(double x, double y, double dir) {
		//dir = dir * (180 / Math.PI);
		Projectile p = new WizardProjectile(x, y, dir);
		level.add(p);

	}

	private boolean collision(double xa, double ya) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) // to check all 4 corners of player if there is collision
		{
			double xt = ((x + xa) - c % 2 * 15) / 16;
			double yt = ((y + ya) - c / 2 * 15) / 16;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if (c % 2 == 0) // check for the left bound
				ix = (int) Math.floor(xt);
			if (c / 2 == 0) // check for the lower bound
				iy = (int) Math.floor(yt);
			if (level.getTile(ix, iy).solid())
				solid = true; // there is a collision
		}
		return solid;
	}

}
