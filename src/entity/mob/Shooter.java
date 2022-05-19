package entity.mob;

import java.util.List;

import entity.Entity;
import myGame.graphics.AnimatedSprite;
import myGame.graphics.Screen;
import myGame.graphics.Sprite;
import myGame.graphics.SpriteSheet;
import util.Vector2i;

public class Shooter extends Mob {

	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 32, 32, 3);

	private AnimatedSprite animSprite = down; // default sprite

	private int time = 0;
	private int xa = 0;
	private int ya = 0;
	private Entity random = null;

	public Shooter(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.dummy;
	}

	@Override
	public void update() {
		time++;
		// set the direction of dummy
		if (time % (rand.nextInt(50) + 30) == 0) {
			xa = rand.nextInt(3) - 1;
			ya = rand.nextInt(3) - 1;
			if (rand.nextInt(4) == 0) {
				xa = 0;
				ya = 0;
			}
		}
		if (walking)
			animSprite.update();
		else
			animSprite.setFrame(0);
		if (ya < 0) {
			animSprite = up;
			dir = Direction.UP;
		} else if (ya > 0) {
			animSprite = down;
			dir = Direction.DOWN;
		}
		if (xa < 0) {
			animSprite = left;
			dir = Direction.LEFT;
		} else if (xa > 0) {
			animSprite = right;
			dir = Direction.RIGHT;
		}
		if (xa != 0 || ya != 0) {
			//move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
		shootRandom();

	}

	private void shootRandom() {
		if (time % (30 + rand.nextInt(91)) == 0) {
			List<Entity> entities = level.getEntities(this, 500);
			entities.add(level.getClientPlayer());
			int index = rand.nextInt(entities.size());
			random = entities.get(index);
		}
		if (random != null) {
			double dx = random.getX() - x;
			double dy = random.getY() - y;
			double dir = Math.atan2(dy, dx);
			shoot(x, y, dir);
		}

	}

	private void shootClosest() {
		List<Entity> entities = level.getEntities(this, 500);
		entities.add(level.getClientPlayer());

		double min = 0;
		Entity closest = null;
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			double distance = Vector2i.getDistance(new Vector2i((int) x, (int) y), new Vector2i((int) e.getX(), (int) e.getY()));
			if (i == 0 || distance < min) {
				min = distance;
				closest = e;
			}
		}
		if (closest != null) {
			double dx = closest.getX() - x;
			double dy = closest.getY() - y;
			double dir = Math.atan2(dy, dx);
			shoot(x, y, dir);
		}
	}

	@Override
	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob((int) (x - 16), (int) (y - 16), sprite);
	}

}
