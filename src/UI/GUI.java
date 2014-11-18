package UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import AI.Zach.Seeker;
import Game.Game;
import Game.Loser;
import Game.Renderer;
import Game.Winner;

public class GUI extends JApplet {

	private JTextArea txtrn;
	private JButton btnGamestep;
	private Game game;
	private Renderer renderer;

	/**
	 * Create the applet.
	 */
	public GUI() {
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		btnGamestep = new JButton("game.step();");
		btnGamestep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				gameStepped();
			}
		});
		panel_1.add(btnGamestep, BorderLayout.NORTH);

		txtrn = new JTextArea();
		panel_1.add(txtrn, BorderLayout.SOUTH);
		txtrn.setEditable(false);
		txtrn.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

		setupGame();
	}

	private void setupGame() {
		game = new Game(100,30);
		renderer = new Renderer();

		game.setPlayer1AI(new Seeker(" 1"));
		game.setPlayer2AI(new Seeker(" 2"));

		txtrn.setText(renderer.renderAsText(game));
	}

	protected void gameStepped() {
		if (!game.gameOver()) {
			game.step();
			if (!game.gameOver()) {
				txtrn.setText(renderer.renderAsText(game));
			}
		}
		
		 if (game.gameOver()) {
			 btnGamestep.setEnabled(false);
			 txtrn.append(printWinners());
			 txtrn.append(printLosers());
		 }

	}

	private String printWinners() {
		List<Winner> winners = game.getWinner();
		StringBuilder sb = new StringBuilder();

		sb.append("Winner\n");
		for (Winner winner : winners) {
			sb.append("AI: " + winner.name + "\n");
			sb.append("Direction: " + winner.direction + "\n");
			sb.append("Score: " + winner.score + "\n");
			sb.append("Written by: " + Arrays.toString(winner.authors) + "\n");
		}
		
		return sb.toString();
	}
	
	private String printLosers() {
		List<Loser> losers = game.getLoser();
		StringBuilder sb = new StringBuilder();

		sb.append("Loser\n");
		for (Loser loser : losers) {
			sb.append("AI: " + loser.name + "\n");
			sb.append("Direction: " + loser.direction + "\n");
			sb.append("Score: " + loser.score + "\n");
			sb.append("Written by: " + Arrays.toString(loser.authors) + "\n");
		}
		
		return sb.toString();
	}
	
}
