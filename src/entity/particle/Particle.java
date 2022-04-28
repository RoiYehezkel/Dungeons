package entity.particle;

import entity.Entity;
import myGame.graphics.Screen;
import myGame.graphics.Sprite;

public class Particle extends Entity {

	private Sprite sprite;
	private int life;
	private int time = 0;

	protected double xx, yy, zz;
	protected double xa, ya, za;

	public Particle(int x, int y, int life) {
		this.x = x;
		this.y = y;
		this.xx = x;
		this.yy = y;
		this.life = life + (rand.nextInt(50) - 25); // every particle will disapeard in diffrent time
		sprite = Sprite.particle_normal;
		this.xa = rand.nextGaussian() + 1.8;
		this.ya = rand.nextGaussian();
		this.zz = rand.nextFloat() + 2.0;
	}

	public void update() {
		time++;
		if (time >= 7400)
			time = 0;
		if (time > life) // delete particle from list
			remove();
		za -= 0.1;
		if (zz < 0) // change the direction of particles
		{
			zz = 0;
			za *= -0.55;
			xa *= 0.4;
			ya *= 0.4;
		}
		move(xx + xa, (yy + ya) + (zz + za));
	}

	private void move(double x, double y) {
		if (collision(x, y)) {
			this.xa *= -0.5;
			this.ya *= -0.5;
			this.za *= -0.5;
		}
		this.xx += xa;
		this.yy += ya;
		this.zz += za;
	}

	public boolean collision(double x, double y) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) // to check all 4 corners of player if there is collision
		{
			double xt = (x - c % 2 * 16) / 16; // check right and left
			double yt = (y - c / 2 * 16) / 16; // check upper and lower
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

	public void render(Screen screen) {
		screen.renderSprite((int) xx - 1, (int) yy - (int) zz - 1, sprite, true);
	}

}
