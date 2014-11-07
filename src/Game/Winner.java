package Game;

import AI.AI;

public class Winner {
	public String name;
	public String[] authors;
	public int score;
	
	public Winner(String name, String[] authors, int score) {
		this.name = name;
		this.authors = authors;
		this.score = score;
	}

	public Winner(AI playerAI, Snake playerSnake) {
		this.name = playerAI.getName();
		this.authors = playerAI.getAuthors();
		this.score = playerSnake.score;
	}
}
