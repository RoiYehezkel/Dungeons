package entity.mob;

import entity.projectile.Projectile;
import entity.projectile.WizardProjectile;
import myGame.Game;
import myGame.graphics.AnimatedSprite;
import myGame.graphics.Screen;
import myGame.graphics.Sprite;
import myGame.graphics.SpriteSheet;
import myGame.input.Keyboard;
import myGame.input.Mouse;

public class Player extends Mob {

	private Keyboard input;
	private Sprite sprite;
	//private int anim = 0;
	//private boolean walking = false;
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 32, 32, 3);

	private AnimatedSprite animSprite = down; // default sprite

	private int fireRate = 0;

	public Player(Keyboard input) {
		this.input = input;
		//sprite = Sprite.player_forward;
	}

	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		//sprite = Sprite.player_forward;
		fireRate = WizardProjectile.FIRE_RATE;
	}

	@Override
	public void update() {
		if (walking)
			animSprite.update(); // player walk
		else
			animSprite.setFrame(0); // player stop and reset to default
		if (fireRate > 0) // count for shooting
			fireRate--;
		int xa = 0, ya = 0;
		if (input.up) {
			animSprite = up;
			ya -= 2;
		} else if (input.down) {
			animSprite = down;
			ya += 2;
		}
		if (input.left) {
			animSprite = left;
			xa -= 2;
		} else if (input.right) {
			animSprite = right;
			xa += 2;
		}
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
		clear();
		updateShooting();
	}

	private void clear() {
		for (int i = 0; i < level.getProjectiles().size(); i++) {
			Projectile p = level.getProjectiles().get(i);
			if (p.isRemoved())
				level.getProjectiles().remove(i);
		}

	}

	private void updateShooting() {
		if (Mouse.getButton() == 1 && fireRate <= 0) {
			double dx = Mouse.getX() - (Game.getWindowWidth() / 2);
			double dy = Mouse.getY() - (Game.getWindowHeight() / 2);
			double dir = Math.atan2(dy, dx);
			shoot(x, y, dir);
			fireRate = WizardProjectile.FIRE_RATE;
		}
	}

	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		// put player on the screen
		screen.renderMob(x - 16, y - 16, sprite);
	}

}
