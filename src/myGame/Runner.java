package myGame;

import javax.swing.JFrame;

public class Runner {

	public static void main(String[] args) {
		Game game = new Game();
		game.getFrame().setResizable(true);
		game.getFrame().setTitle(Game.title); // set the title of the window
		game.getFrame().add(game); // adding the game to the frame
		game.getFrame().pack(); // setting the game size by the definition of dimension size
		game.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // terminate the process if it has been closed by the X button
		game.getFrame().setLocationRelativeTo(null); // set the default location of the window
		game.getFrame().setVisible(true); // set the window as visible
		game.start();
	}

}
