package myGame.graphics.ui;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.Image;
import java.awt.image.BufferedImage;
import myGame.input.Mouse;
import util.Vector2i;

public class UIButton extends UIComponent {

	private UIButtonListener buttonListener;
	private UIActionListener actionListener;
	private UILabel label;

	private Image image;

	private boolean inside = false;
	private boolean pressed = false;
	private boolean ignorePressed = false;
	private boolean ignoreAction = false;

	public UIButton(Vector2i position, Vector2i size, UIActionListener actionListener) {
		super(position, size);
		this.actionListener = actionListener;
		Vector2i lp = new Vector2i(position);
		lp.x += 4;
		lp.y += size.y - 2;
		label = new UILabel(lp, "");
		label.active = false;
		label.setColor(0x444444);
		init();
	}

	public UIButton(Vector2i position, BufferedImage image, UIActionListener actionListener) {
		super(position, new Vector2i(image.getWidth(), image.getHeight()));
		setImage(image);
		this.actionListener = actionListener;
		init();
	}

	private void init() {
		setColor(0xaaaaaa);
		buttonListener = new UIButtonListener();
	}

	public void init(UIPanel panel) {
		super.init(panel);
		if (label != null)
			panel.addComponent(label);
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void update() {
		Rectangle rect = new Rectangle(getAbsolutePositin().x, getAbsolutePositin().y, size.x, size.y);
		boolean leftMouseButton = Mouse.getButton() == MouseEvent.BUTTON1;
		// check if inside the boundary of button
		if (rect.contains(new Point(Mouse.getX(), Mouse.getY()))) {
			if (!inside) {
				if (leftMouseButton)
					ignorePressed = true;
				else
					ignorePressed = false;
				buttonListener.entered(this);
			}
			inside = true;
			if (!pressed && !ignorePressed && leftMouseButton) {
				buttonListener.pressed(this);
				pressed = true;
			} else if (Mouse.getButton() == MouseEvent.NOBUTTON) {
				if (pressed) {
					buttonListener.released(this);
					pressed = false;
					if (!ignoreAction)
						actionListener.perform();
					else
						ignoreAction = false;
				}
				ignorePressed = false;
			}
		} else {
			if (inside) {
				buttonListener.exited(this);
				pressed = false;
			}
			inside = false;
		}
	}

	public void setButtonListener(UIButtonListener buttonListener) {
		this.buttonListener = buttonListener;
	}

	public void setText(String text) {
		if (text == "")
			label.active = false;
		else
			label.text = text;
	}

	public void performAction() {
		actionListener.perform();
	}

	public void ignoreNextPress() {
		ignoreAction = true;
	}

	public void render(Graphics g) {
		int x = position.x + offset.x;
		int y = position.y + offset.y;
		if (image != null) {
			g.drawImage(image, x, y, null);
		} else {
			g.setColor(color);
			g.fillRect(x, y, size.x, size.y);
			if (label != null)
				label.render(g);
		}
	}

}
