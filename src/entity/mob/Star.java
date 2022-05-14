package entity.mob;

import java.util.List;

import entity.mob.Mob.Direction;
import myGame.graphics.AnimatedSprite;
import myGame.graphics.Screen;
import myGame.graphics.Sprite;
import myGame.graphics.SpriteSheet;
import myGame.level.Node;
import util.Vector2i;

public class Star extends Mob {
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.chaser_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.chaser_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.chaser_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.chaser_right, 32, 32, 3);

	private AnimatedSprite animSprite = down; // default sprite

	private double xa = 0;
	private double ya = 0;
	private double speed = 0.8;
	private List<Node> path = null;
	private int time = 0;

	public Star(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.chaser;

	}

	private void move() {
		xa = 0;
		ya = 0;
		int px = (int) level.getPlayerAt(0).getX();
		int py = (int) level.getPlayerAt(0).getY();
		Vector2i start = new Vector2i((int) getX() >> 4, (int) getY() >> 4);
		Vector2i destination = new Vector2i(px >> 4, py >> 4);
		if (time % 3 == 0)
			path = level.findPath(start, destination);
		if (path != null) {
			//			List<Player> players = level.getPlayer(this, 100);
			//			if (players.size() > 0) {
			//				Player player = players.get(0);
			if (path.size() > 0) {
				Vector2i vec = path.get(path.size() - 1).tile;
				if (x < vec.getX() << 4)
					xa++;
				if (x > vec.getX() << 4)
					xa--;
				if (y < vec.getY() << 4)
					ya++;
				if (y > vec.getY() << 4)
					ya--;
			}
			// prevent chaser to follow player when he reach him
			//				if (Math.floor(x) == Math.floor(player.getX()))
			//					xa = 0;
			//				if (Math.floor(y) == Math.floor(player.getY()))
			//					ya = 0;
			//			}
		}
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}

	}

	@Override
	public void update() {
		time++;
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
