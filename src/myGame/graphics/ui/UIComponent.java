package myGame.graphics.ui;

import java.awt.Color;
import java.awt.Graphics;
import util.Vector2i;

public class UIComponent {

	public Vector2i position, size;
	protected Vector2i offset;
	public Color color;
	protected UIPanel panel;
	protected boolean active = true;

	public UIComponent(Vector2i position) {
		this.position = position;
		offset = new Vector2i();
	}

	public UIComponent(Vector2i position, Vector2i size) {
		this.position = position;
		offset = new Vector2i();
		this.size = size;
	}

	public void init(UIPanel panel) {
		this.panel = panel;
	}

	public UIComponent setColor(int color) {
		this.color = new Color(color);
		return this;
	}

	public void update() {

	}

	public void render(Graphics g) {

	}

	public Vector2i getAbsolutePositin() {
		return new Vector2i(position).add(offset);
	}

	public void setOffset(Vector2i offset) {
		this.offset = offset;
	}

}
