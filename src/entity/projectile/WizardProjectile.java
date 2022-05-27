package entity.projectile;

import entity.spawner.ParticleSpawner;
import myGame.graphics.Screen;
import myGame.graphics.Sprite;

public class WizardProjectile extends Projectile {
	public static final int FIRE_RATE = 10; // how much time between shoot

	public WizardProjectile(double x, double y, double dir) {
		super(x, y, dir);
		range = 200;
		speed = 4;
		damage = 20;
		sprite = Sprite.rotate(Sprite.projectile_arrow, angle); // weapon
		// create vector for direction of projectile
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}

	// private int time = 0;

	public void update() {
		if (level.tileCollision((int) (x + nx), (int) (y + ny), 7, 4, 4)) // remove projectile when he touch wall
		{
			level.add(new ParticleSpawner((int) x, (int) y, 44, 50, level)); // create the particles
			remove();
		}
		// time++;
		// if (time % 2 == 0) {
		// sprite = Sprite.rotate(sprite, Math.PI / 20.0);
		// }
		move();
	}

	protected void move() {
		x += nx;
		y += ny;
		if (distance() > range) // delete projectiles from list
			remove();
	}

	private double distance() {
		// calculate the distance from the player for projectile to remove them from
		// list
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
		return dist;

	}

	public void render(Screen screen) {
		screen.renderProjectile((int) x - 12, (int) y - 2, this);
	}

}
