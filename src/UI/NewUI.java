package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import AI.AI;
import AI.AIList;
import Game.Game;
import Game.Renderer;

public class NewUI {

	private JFrame frmSnakeGame;
	private JButton btnSpeedUp;
	private JCheckBox chckbxAutoStep;
	private JButton btnStep;
	private JButton btnStop;
	private JButton btnStart;
	private JLabel player2_score;
	private JComboBox<String> player2_comboBox;
	private JLabel player1_score;
	private JComboBox<String> player1_comboBox;
	private JButton btnSlowDown;
	private JSpinner spinner_gameWidth;
	private JSpinner spinner_gameHeight;
	private Game game;
	private JTextPane gamePlayArea;
	private JTextPane status;

	private int initialTimerDelay = 500;
	private int timerDelay = initialTimerDelay;
	private Timer timer = new Timer(initialTimerDelay, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			stepGame();
			
		}
	});

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewUI window = new NewUI();
					window.frmSnakeGame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NewUI() {
		initialize();
		disableControlButtons();
		btnStop.setEnabled(false);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSnakeGame = new JFrame();
		frmSnakeGame.setTitle("Snake Game");
		frmSnakeGame.setBounds(100, 100, 500, 300);
		frmSnakeGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel top_panel = new JPanel();
		frmSnakeGame.getContentPane().add(top_panel, BorderLayout.NORTH);
		top_panel.setLayout(new BorderLayout(0, 0));

		JPanel beforegame_panel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) beforegame_panel.getLayout();
		top_panel.add(beforegame_panel, BorderLayout.NORTH);

		btnStart = new JButton("Start Game");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startGameClicked(e);
			}
		});

		JLabel lblWidth = new JLabel("width");
		beforegame_panel.add(lblWidth);

		spinner_gameWidth = new JSpinner();
		spinner_gameWidth.setModel(new SpinnerNumberModel(new Integer(100),
				new Integer(0), null, new Integer(1)));
		((JSpinner.DefaultEditor) spinner_gameWidth.getEditor()).getTextField()
				.setColumns(3);
		beforegame_panel.add(spinner_gameWidth);

		JLabel lblHeight = new JLabel("height");
		beforegame_panel.add(lblHeight);

		spinner_gameHeight = new JSpinner();
		spinner_gameHeight.setModel(new SpinnerNumberModel(new Integer(100),
				new Integer(0), null, new Integer(1)));
		((JSpinner.DefaultEditor) spinner_gameHeight.getEditor())
				.getTextField().setColumns(3);
		beforegame_panel.add(spinner_gameHeight);
		beforegame_panel.add(btnStart);

		btnStop = new JButton("Stop Game");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopGameClicked(e);
			}
		});
		beforegame_panel.add(btnStop);

		JPanel players_panel = new JPanel();
		top_panel.add(players_panel);
		players_panel.setLayout(new GridLayout(1, 2, 5, 5));
		players_panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel player1_panel = new JPanel();
		players_panel.add(player1_panel);
		player1_panel.setLayout(new GridLayout(3, 1, 0, 0));

		player1_comboBox = new JComboBox<String>(
				new DefaultComboBoxModel<String>(AIList.asArray()));
		player1_panel.add(player1_comboBox);

		JLabel player1_score_lbl = new JLabel("Player 1 score");
		player1_score_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		player1_panel.add(player1_score_lbl);

		player1_score = new JLabel("0");
		player1_score.setHorizontalAlignment(SwingConstants.CENTER);
		player1_panel.add(player1_score);

		JPanel player2_panel = new JPanel();
		players_panel.add(player2_panel);
		player2_panel.setLayout(new GridLayout(3, 1, 0, 0));

		player2_comboBox = new JComboBox<String>(
				new DefaultComboBoxModel<String>(AIList.asArray()));
		player2_panel.add(player2_comboBox);

		JLabel player2_score_lbl = new JLabel("Player 2 score");
		player2_score_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		player2_panel.add(player2_score_lbl);

		player2_score = new JLabel("0");
		player2_score.setHorizontalAlignment(SwingConstants.CENTER);
		player2_panel.add(player2_score);

		JPanel game_panel = new JPanel();
		frmSnakeGame.getContentPane().add(game_panel, BorderLayout.CENTER);
		game_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		game_panel.setLayout(new BoxLayout(game_panel, BoxLayout.Y_AXIS));

		JPanel duringgame_panel = new JPanel();
		game_panel.add(duringgame_panel);

		btnStep = new JButton("Step");
		btnStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stepClicked(e);
			}
		});
		duringgame_panel.add(btnStep);

		chckbxAutoStep = new JCheckBox("auto step");
		chckbxAutoStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				autoStepClicked(e);
			}
		});
		duringgame_panel.add(chckbxAutoStep);
		chckbxAutoStep.setHorizontalAlignment(SwingConstants.CENTER);

		btnSlowDown = new JButton("<< slow down");
		btnSlowDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				slowDownClicked(e);
			}
		});
		duringgame_panel.add(btnSlowDown);

		btnSpeedUp = new JButton("speed up >>");
		btnSpeedUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				speedUpClicked(e);
			}
		});
		duringgame_panel.add(btnSpeedUp);

		JPanel status_panel = new JPanel();
		game_panel.add(status_panel);

		status = new JTextPane();
		status.setEditable(false);
		status.setText("Status");
		status_panel.add(status);

		gamePlayArea = new JTextPane();
		gamePlayArea.setText("GamePlayArea");
		game_panel.add(gamePlayArea);
	}

	protected void autoStepClicked(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (chckbxAutoStep.isSelected()) {
			timerDelay = initialTimerDelay ;
			timer.setInitialDelay(timerDelay);
			timer.setDelay(timerDelay);
			timer.restart();
			timer.start();
		} else {
			timer.stop();
		}
	}

	protected void speedUpClicked(ActionEvent e) {
		timerDelay -= 350;
		if (timerDelay < 0)
			timerDelay = 100;
		timer.setDelay(timerDelay);
	}

	protected void slowDownClicked(ActionEvent e) {
		timerDelay += 350;
		timer.setDelay(timerDelay);
	}

	protected void stepClicked(ActionEvent e) {
		chckbxAutoStep.setSelected(false);
		timer.stop();
		stepGame();
	}
	
	protected void stepGame() {
		if (!game.gameOver()) {
			game.step();
			renderGame();
			updateScores();
		}
		
		if (game.gameOver()){
			disableControlButtons();
			timer.stop();
		}
	}

	protected void updateScores() {
		player1_score.setText("" + game.getPlayer1Score());
		player2_score.setText("" + game.getPlayer2Score());

	}

	protected void renderGameAsText() {
		String gameAsText = Renderer.renderAsText(game);
		gamePlayArea.setText(gameAsText);
	}

	protected void stopGameClicked(ActionEvent e) {
		disableControlButtons();
		enablePlayerAIComboBoxes();
		btnStart.setEnabled(true);
		btnStop.setEnabled(false);
		spinner_gameHeight.setEnabled(true);
		spinner_gameWidth.setEnabled(true);
	}

	protected void startGameClicked(ActionEvent e) {
		enableControlButtons();
		disablePlayerAIComboBoxes();
		btnStart.setEnabled(false);
		btnStop.setEnabled(true);
		spinner_gameHeight.setEnabled(false);
		spinner_gameWidth.setEnabled(false);
		configureGame();
	}

	private void enablePlayerAIComboBoxes() {
		player1_comboBox.setEnabled(true);
		player2_comboBox.setEnabled(true);
	}

	private void disablePlayerAIComboBoxes() {
		player1_comboBox.setEnabled(false);
		player2_comboBox.setEnabled(false);
	}

	private void disableControlButtons() {
		btnSpeedUp.setEnabled(false);
		btnSlowDown.setEnabled(false);
		btnStep.setEnabled(false);
		chckbxAutoStep.setSelected(false);
		chckbxAutoStep.setEnabled(false);
	}

	private void enableControlButtons() {
		btnSpeedUp.setEnabled(true);
		btnSlowDown.setEnabled(true);
		btnStep.setEnabled(true);
		chckbxAutoStep.setSelected(false);
		chckbxAutoStep.setEnabled(true);
	}

	private void configureGame() {
		int width = (int) spinner_gameWidth.getValue();
		int height = (int) spinner_gameHeight.getValue();

		game = new Game(width, height);

		try {
			AI player1AI = AIList.list[player1_comboBox.getSelectedIndex()]
					.getClass().newInstance();
			AI player2AI = AIList.list[player2_comboBox.getSelectedIndex()]
					.getClass().newInstance();

			game.setPlayer1AI(player1AI);
			game.setPlayer2AI(player2AI);
		} catch (InstantiationException | IllegalAccessException e) {
			System.out.println("Could not make new instance of an AI");
			e.printStackTrace();
		}

		updateScores();
		renderGame();
	}

	protected void renderGame() {
		renderGameAsText();
		
	}
}