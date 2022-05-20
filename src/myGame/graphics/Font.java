package myGame.graphics;

public class Font {

	private static SpriteSheet font = new SpriteSheet("/fonts/arial.png", 16);
	private static Sprite[] characters = Sprite.split(font);

	private static String charIndex = "ABCDEFGHIJKLM" + //
			"NOPQRSTUVWXYZ" + //
			"abcdefghijklm" + //
			"nopqrstuvwxyz" + //
			"0123456789.,'" + //
			"'\"\";:!@$%()-+";

	public Font() {

	}

	public void render(String text, Screen screen) {
		int x = 50;
		int y = 50;
		for (int i = 0; i < text.length(); i++) {
			char currentChar = text.charAt(i);
			int index = charIndex.indexOf(currentChar);
			if (index == -1)
				continue;
			screen.renderSprite(x + i * 16, y, characters[index], false);
		}

	}
}
