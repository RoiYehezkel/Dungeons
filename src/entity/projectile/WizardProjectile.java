package entity.projectile;

import myGame.graphics.Screen;
import myGame.graphics.Sprite;

public class WizardProjectile extends Projectile {
	public static final int FIRE_RATE = 10; // how much time between shoot

	public WizardProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = 200;
		speed = 4;
		damage = 20;
		sprite = Sprite.projectile_wizard; // weapon
		// create vector for direction of projectile
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}

	public void update() {
		if(level.tileCollision(x, y, nx, ny, 7)) // remove projectile when he touch wall
			remove();
		move();
	}
	
	protected void move() {
		x += nx;
		y += ny;
		if (distance() > range) // delete projectiles from list
			remove();
	}

	private double distance() {
		// calculate the distance from the player for projectile to remove them from list
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
		return dist;

	}

	public void render(Screen screen) {
		screen.renderProjectile((int) x - 12, (int) y - 2, this);
	}

}
