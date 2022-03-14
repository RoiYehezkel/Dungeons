package myGame.level.tile;

import myGame.graphics.Screen;
import myGame.graphics.Sprite;

public class GrassTile extends Tile {

	public GrassTile(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x, y, this);

	}

}
