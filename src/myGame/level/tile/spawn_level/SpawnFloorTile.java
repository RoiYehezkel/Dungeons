package myGame.level.tile.spawn_level;

import myGame.graphics.Screen;
import myGame.graphics.Sprite;
import myGame.level.tile.Tile;

public class SpawnFloorTile extends Tile {

	public SpawnFloorTile(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);

	}

}
