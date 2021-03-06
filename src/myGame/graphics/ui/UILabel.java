package myGame.graphics.ui;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import util.Vector2i;

public class UILabel extends UIComponent {
	public String text;
	private Font font;
	public boolean shadow;

	public UILabel(Vector2i position, String text) {
		super(position);
		font = new Font("Helvetica", Font.PLAIN, 32);
		this.text = text;
		color = new Color(0xff00ff);
	}

	public UILabel setFont(Font font) {
		this.font = font;
		return this;
	}

	public void render(Graphics g) {
		if (shadow) {
			g.setColor(Color.BLACK);
			g.setFont(font);
			g.drawString(text, position.x + offset.x + 2, position.y + offset.y + 2);
		}
		g.setColor(color);
		g.setFont(font);
		g.drawString(text, position.x + offset.x, position.y + offset.y);
	}

}
