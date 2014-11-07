package Game;

import java.awt.Image;

public class Renderer {
	public Image render(Game game) {
		return null;
	}
	
	public String renderAsText(Game game) {
		String[][] map = new String[game.worldWidth][game.worldHeight];
		
		for (int y = 0; y < game.worldHeight; y++) {
			for (int x = 0; x < game.worldWidth; x++) {
				map[x][y] = "_";
			}
		}
		
		map[game.apple.x][game.apple.y] = "A";
		
		map[game.player1.head.x][game.player1.head.y] = "1";
		
		for (SnakePart part : game.player1.parts)
			map[part.x][part.y] = "+";

		map[game.player2.head.x][game.player2.head.y] = "2";
		
		for (SnakePart part : game.player2.parts)
			map[part.x][part.y] = "x";

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
