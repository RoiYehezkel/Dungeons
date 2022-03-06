package com.thecherno.rain.graphics;

import java.util.Random;

public class Screen {

	private int width, height;
	public int[] pixels;
	public int[] tiles = new int[64 * 64];
	private Random rand = new Random();

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height]; // 50,400

		for (int i = 0; i < 64 * 64; i++) {
			tiles[i] = rand.nextInt(0xffffff);
		}

	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void render() {

		for (int y = 0; y < height; y++) {
			if (y < 0 || y >= height)
				break;
			for (int x = 0; x < width; x++) {
				if (x < 0 || x > width)
					break;
				int tileIndex = (x >> 2) + (y >> 2) * 64;
				pixels[x + y * width] = tiles[tileIndex];

			}
		}
	}

}
