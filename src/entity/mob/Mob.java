package entity.mob;

import java.util.ArrayList;
import java.util.List;

import entity.Entity;
import entity.projectile.Projectile;
import entity.projectile.WizardProjectile;
import myGame.graphics.Sprite;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	protected int dir = 0;
	protected boolean moving = false;

	public void move(int xa, int ya) {
		//System.out.println("size: " + level.getProjectiles().size());
		if (xa != 0 && ya != 0) {
			move(xa, 0); // to check collision
			move(0, ya); // to check collision
			return;
		}
		if (xa > 0)
			dir = 1; // go east
		if (xa < 0)
			dir = 3; // go west
		if (ya > 0)
			dir = 2; // go south
		if (ya < 0)
			dir = 0; // go north
		if (!collision(xa, ya)) // check if there is a collision
		{
			x += xa; // x = -1, 0, 1
			y += ya; // y = -1, 0, 1
		}

	}

	public void updtae() {

	}

	protected void shoot(int x, int y, double dir) {
		//dir = dir * (180 / Math.PI);
		Projectile p = new WizardProjectile(x, y, dir);
		level.addProjectile(p);

	}

	private boolean collision(int xa, int ya) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) // to check all 4 corners of player if there is collision
		{
			int xt = ((x + xa) + c % 2 * 14 - 8) / 16;
			int yt = ((y + ya) + c / 2 * 12 + 3) / 16;
			if (level.getTile(xt, yt).solid())
				solid = true; // there is a collision
		}

		return solid;
	}

	public void render() {

	}

}
