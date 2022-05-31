package entity.mob;

import entity.projectile.Projectile;
import entity.projectile.WizardProjectile;
import myGame.Game;
import myGame.graphics.AnimatedSprite;
import myGame.graphics.Screen;
import myGame.graphics.Sprite;
import myGame.graphics.SpriteSheet;
import myGame.graphics.ui.UIButton;
import myGame.graphics.ui.UIButtonListener;
import myGame.graphics.ui.UILabel;
import myGame.graphics.ui.UIManager;
import myGame.graphics.ui.UIPanel;
import myGame.graphics.ui.UIProgressBar;
import myGame.graphics.ui.UIActionListener;
import myGame.input.Keyboard;
import myGame.input.Mouse;
import util.ImageUtil;
import util.Vector2i;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Mob {

	private String name;
	private Keyboard input;
	private Sprite sprite;
	// private int anim = 0;
	// private boolean walking = false;
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 32, 32, 3);

	private AnimatedSprite animSprite = down; // default sprite

	private int fireRate = 0;

	private UIManager ui;
	private UIProgressBar uiHealthBar;
	private UIButton button;
	private BufferedImage image;

	public Player(String name, Keyboard input) {
		this.name = name;
		this.input = input;
		health = 100;
		// sprite = Sprite.player_forward;
	}

	public Player(String name, int x, int y, Keyboard input) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.input = input;
		// sprite = Sprite.player_forward;
		fireRate = WizardProjectile.FIRE_RATE;
		// create the ui for the game
		ui = Game.getUIManager();
		UIPanel panel = (UIPanel) new UIPanel(new Vector2i((300 - 80) * 3, 0), new Vector2i(80 * 3, 168 * 3))
				.setColor(0x4f4f4f);
		ui.addPanel(panel);
		UILabel nameLabel = new UILabel(new Vector2i(40, 200), name);
		nameLabel.setColor(0xbbbbbb);
		nameLabel.setFont(new Font("Verdana", Font.PLAIN, 24));
		nameLabel.shadow = true;
		panel.addComponent(nameLabel);
		uiHealthBar = new UIProgressBar(new Vector2i(10, 215), new Vector2i(80 * 3 - 20, 20));
		uiHealthBar.setColor(0x6a6a6a);
		uiHealthBar.setForegroundColor(0xee3030);
		panel.addComponent(uiHealthBar);
		UILabel hpLabel = new UILabel(new Vector2i(uiHealthBar.position).add(new Vector2i(2, 16)), "HP");
		hpLabel.setColor(0xffffff);
		hpLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
		panel.addComponent(hpLabel);
		// player default health rate
		health = 100;
//		button = new UIButton(new Vector2i(10, 260), new Vector2i(100, 30), new UIActionListener() {
//			public void perform() {
//				System.out.println("pressed");
//				// System.exit(0);
//			}
//		});
//		button.setButtonListener(new UIButtonListener() {
//			public void pressed(UIButton button) {
//				super.pressed(button);
//				button.performAction();
//				button.ignoreNextPress();
//			}
//		});
//		button.setText("hello");
//		panel.addComponent(button);
		try {
			image = ImageIO.read(new File("res/textures/sheets/home.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// imageHover = new BufferedImage(image.getWidth(), image.getHeight(),
		// BufferedImage.TYPE_INT_ARGB);
		// int[] newPixels = ((DataBufferInt)
		// imageHover.getRaster().getDataBuffer()).getData();
		// for (int yyy = 0; yyy < image.getHeight(); yyy++) {
		// for (int xxx = 0; xxx < image.getWidth(); xxx++) {
		// newPixels[xxx + yyy * image.getWidth()] = image.getRGB(xxx, yyy);
		// }
		// }
		// for (int yy = 0; yy < image.getHeight(); yy++) {
		// for (int xx = 0; xx < image.getWidth(); xx++) {
		// int color = newPixels[xx + yy * image.getWidth()];
		// int r = (color & 0xff0000) >> 16;
		// int g = (color & 0xff00) >> 8;
		// int b = color & 0xff;
		// r += 100;
		// g += 100;
		// b += 100;
		// color &= 0xff000000;
		// newPixels[xx + yy * image.getWidth()] = color | r << 16 | g << 8 | b;
		// }
		// }
		UIButton imageButton = new UIButton(new Vector2i(10, 360), image, new UIActionListener() {
			public void perform() {
				System.out.println("pressed");
			}
		});
		imageButton.setButtonListener(new UIButtonListener() {
			public void entered(UIButton button) {
				button.setImage(ImageUtil.changeBrightness(image, -50));
			}

			public void exited(UIButton button) {
				button.setImage(image);
			}

			public void pressed(UIButton button) {
				button.setImage(ImageUtil.changeBrightness(image, 50));
			}

			public void released(UIButton button) {
				button.setImage(image);
			}
		});
		panel.addComponent(imageButton);

	}

	public void updateHealth() {
		if (health > 0)
			health--;
		else {
			this.x = 320;
			this.y = 300;
			health = 100;
		}
	}

	public String getName() {
		return name;
	}

	@Override
	public void update(Player player) {
		if (walking)
			animSprite.update(); // player walk
		else
			animSprite.setFrame(0); // player stop and reset to default
		if (fireRate > 0) // count for shooting
			fireRate--;
		double xa = 0, ya = 0;
		double speed = 1.4;
		if (input.up) {
			animSprite = up;
			ya -= speed;
		} else if (input.down) {
			animSprite = down;
			ya += speed;
		}
		if (input.left) {
			animSprite = left;
			xa -= speed;
		} else if (input.right) {
			animSprite = right;
			xa += speed;
		}
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
		clear();
		updateShooting();
		uiHealthBar.setProgress(health / 100.0);
	}

	private void clear() {
		for (int i = 0; i < level.getProjectiles().size(); i++) {
			Projectile p = level.getProjectiles().get(i);
			if (p.isRemoved())
				level.getProjectiles().remove(i);
		}

	}

	private void updateShooting() {
		// check if we on ui
		if (Mouse.getX() > 660)
			return;
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
		screen.renderMob((int) (x - 16), (int) (y - 16), sprite);
	}

}
