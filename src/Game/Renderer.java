package Game;

import java.awt.Image;

public class Renderer {
	public static Image render(Game game) {
		return null;
	}

	public static String renderAsText(Game game) {
		String[][] map = new String[game.worldWidth][game.worldHeight];

		for (int y = 0; y < game.worldHeight; y++) {
			for (int x = 0; x < game.worldWidth; x++) {
				map[x][y] = "_";
			}
		}

		map[game.apple.x][game.apple.y] = "A";

		for (SnakePart part : game.player1.parts)
			map[part.x][part.y] = "+";

		for (SnakePart part : game.player2.parts)
			map[part.x][part.y] = "-";

		try {
			map[game.player1.head.x][game.player1.head.y] = "1";
		} catch (Exception e) {
			System.out.println("this is expected, dont worry, but there is still a game over");
			e.printStackTrace();
		}

		try {
			map[game.player2.head.x][game.player2.head.y] = "2";
		} catch (Exception e) {
			System.out.println("this is expected, dont worry, but there is still a game over");
			e.printStackTrace();
		}

		StringBuilder out = new StringBuilder();

		for (int y = 0; y < game.worldHeight; y++) {
			for (int x = 0; x < game.worldWidth; x++) {
				out.append(map[x][y]);
			}
			out.append("\n");
		}

		return out.toString();
	}
}
