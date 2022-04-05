package myGame.level.tile;

import myGame.graphics.Screen;
import myGame.graphics.Sprite;
import myGame.level.tile.spawn_level.SpawnFloorTile;
import myGame.level.tile.spawn_level.SpawnGrassTile;
import myGame.level.tile.spawn_level.SpawnHedgeTile;
import myGame.level.tile.spawn_level.SpawnWallTile;
import myGame.level.tile.spawn_level.SpawnWaterTile;

public class Tile {

	public int x, y;
	public Sprite sprite;

	public static Tile grass = new GrassTile(Sprite.grass); // tile of grass
	public static Tile flower = new FlowerTile(Sprite.flower); // tile of flower
	public static Tile rock = new RockTile(Sprite.rock); // tile of rock
	public static Tile voidTile = new voidTile(Sprite.voidSprite); // black tile

	// spawn tile
	public static Tile spawn_grass = new SpawnGrassTile(Sprite.spawn_grass);
	public static Tile spawn_hedge = new SpawnHedgeTile(Sprite.spawn_hedge);
	public static Tile spawn_water = new SpawnWaterTile(Sprite.spawn_water);
	public static Tile spawn_wall1 = new SpawnWallTile(Sprite.spawn_wall1);
	public static Tile spawn_wall2 = new SpawnWallTile(Sprite.spawn_wall2);
	public static Tile spawn_floor = new SpawnFloorTile(Sprite.spawn_floor);

	// hexa code for every spawn tile
	public final static int col_spawn_grass = 0xff00ff00;
	public final static int col_spawn_hedge = 0; // unused
	public final static int col_spawn_water = 0; // unused
	public final static int col_spawn_wall1 = 0xff808080;
	public final static int col_spawn_wall2 = 0xff303030;
	public final static int col_spawn_floor = 0xff7F0000;

	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	public void render(int x, int y, Screen screen) {

	}

	public boolean solid() {
		return false;
	}

}
