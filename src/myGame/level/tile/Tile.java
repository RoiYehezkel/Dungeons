package myGame.level.tile;

import myGame.graphics.Screen;
import myGame.graphics.Sprite;

public class Tile {

	public int x, y;
	public Sprite sprite;

	public static Tile grass = new GrassTile(Sprite.grass); // tile of grass
	public static Tile voidTile = new voidTile(Sprite.voidSprite); // black tile

	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	public void render(int x, int y, Screen screen) {

	}

	public boolean solid() {
		return false;
	}

}
