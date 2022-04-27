package entity.particle;

import entity.Entity;
import myGame.graphics.Screen;
import myGame.graphics.Sprite;

public class Particle extends Entity {

	private Sprite sprite;
	private int life;

	protected double xx, yy, xa, ya;

	public Particle(int x, int y, int life) {
		this.x = x;
		this.y = y;
		this.xx = x;
		this.yy = y;
		this.life = life;
		sprite = Sprite.particle_normal;
		this.xa = rand.nextGaussian();
		this.ya = rand.nextGaussian();
	}

	public void update() {
		this.xx += xa;
		this.yy += ya;

	}

	public void render(Screen screen) {
		screen.renderSprite((int) xx, (int) yy, sprite, true);
	}

}
