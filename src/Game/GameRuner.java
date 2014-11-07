package Game;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import AI.BasicAI;

public class GameRuner {

	public static void main(String[] args) {
		GameRuner gameRunner = new GameRuner();
	}

	Game game;

	public GameRuner() {
		game = new Game(11, 11);
		Renderer renderer = new Renderer();

		game.setPlayer1AI(new BasicAI(Direction.DOWN));
		game.setPlayer2AI(new BasicAI(Direction.UP));

		long startTime, endTime;
		
		while (!game.gameOver) {
			startTime = System.nanoTime();
			
			String output = renderer.renderAsText(game);
			System.out.println(output);
			
			game.step();
			
			endTime = System.nanoTime();
			
			System.out.println("Took about: " + TimeUnit.NANOSECONDS.toMillis(endTime - startTime));
		}

		printWinner();
	}

	private void printWinner() {
		List<Winner> winners = game.getWinner();

		System.out.println("Winner: ");
		for (Winner winner : winners) {
			System.out.println("AI: " + winner.name);
			System.out.println("Score: " + winner.score);
			System.out
					.println("Written by: " + Arrays.toString(winner.authors));
		}
	}
}