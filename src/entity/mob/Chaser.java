package entity.mob;

import java.util.List;

import myGame.graphics.AnimatedSprite;
import myGame.graphics.Screen;
import myGame.graphics.Sprite;
import myGame.graphics.SpriteSheet;

public class Chaser extends Mob {
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.chaser_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.chaser_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.chaser_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.chaser_right, 32, 32, 3);

	private AnimatedSprite animSprite = down; // default sprite

	private double xa = 0;
	private double ya = 0;
	private double speed = 0.8;

	public Chaser(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.chaser;

	}

	private void move() {
		xa = 0;
		ya = 0;

		// check the player position and chase after him
		List<Player> players = level.getPlayer(this, 80);
		if (players.size() > 0) {
			Player player = players.get(0);
			if (x < player.getX())
				xa += speed;
			if (x > player.getX())
				xa -= speed;
			if (y < player.getY())
				ya += speed;
			if (y > player.getY())
				ya -= speed;
			// prevent chaser to follow player when he reach him
			if (Math.floor(x) == Math.floor(player.getX()))
				xa = 0;
			if (Math.floor(y) == Math.floor(player.getY()))
				ya = 0;
		}
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}

	}

	@Override
	public void update(Player player) {
		move();
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

	}

	@Override
	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob((int) (x - 16), (int) (y - 16), sprite);

	}

}
