package myGame.level.tile;

import myGame.graphics.Screen;
import myGame.graphics.Sprite;

public class voidTile extends Tile {

	public voidTile(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x, y, this);

	}

}
