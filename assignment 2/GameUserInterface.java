import javax.swing.*;
import java.awt.*;

 /*the class GameUserInterface provides the user interface of the Game. It extends
 JFrame and lays out an instance of BoardUserInterface and  two instances of JButton: one to reset and the second the quit the game at any time.*/

public class GameUserInterface extends JFrame {

	private JButton reset;
	private JButton quit;
	// ADD YOUR INSTANCE VARIABLES HERE
	//ALL PRIVATE
	private GameState state;
	private GameLogic gameLogic;
	private BoardUserInterface boardUserInterface;
    /* Constructor 
	 * initializes the board
     * takes as parameters the state of the game and the gameLogic */

    public GameUserInterface( GameState state,GameLogic gameLogic) {
		this.state = state;
		this.gameLogic = gameLogic;
		this.boardUserInterface = new BoardUserInterface(this.state,this.gameLogic);
		
		this.reset = new JButton("Reset");
		this.quit = new JButton("Quit");

		this.reset.addActionListener(this.gameLogic);
		this.quit.addActionListener(this.gameLogic);
		JPanel panel = new JPanel();

		add(this.boardUserInterface);
		add(panel,BorderLayout.AFTER_LAST_LINE);

		panel.add(this.reset);
		panel.add(this.quit);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Catch the Mouse Game");
		
		pack();
		setVisible(true);
    }

	


	public JButton getReset(){
		return this.reset;
	}
	public JButton getQuit(){
		
		return this.quit;
	}
    public BoardUserInterface getBoardUserInterface(){
		return this.boardUserInterface;
   }


}
