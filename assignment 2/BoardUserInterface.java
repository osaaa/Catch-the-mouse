import javax.swing.*;

import java.awt.*;

public class BoardUserInterface extends JPanel {

  // YOUR INSTANCE VARIABLES HERE
  private GameLogic gameLogic;
  private GameState gameState;
  private int boardSize;

  public BoardUserInterface(GameState gameState, GameLogic gameLogic) {
    this.gameState = gameState;
    this.gameLogic = gameLogic;

    this.boardSize = this.gameState.getSize();

    //set panel layout
    setLayout(new GridLayout(boardSize, boardSize));
    Cube cube;
    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        setBackground(Color.white);
        cube = new Cube(i, j, this.gameState.getCurrentStatus(i, j));
        // sets cube type
        cube.setType(this.gameState.getCurrentStatus(i, j));
        //add an event listener on the cube btn
        cube.addActionListener(this.gameLogic);
        add(cube);
      }
    }
    

  }

  // updates the status of the board's cubes instances following the game state
  // calls setType() from the class Cube, as needed.
  public void update() {
    //revomes every element
    removeAll();
    
    setLayout(new GridLayout(boardSize, boardSize));
    Cube cube;
    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        setBackground(Color.white);
        cube = new Cube(i, j, this.gameState.getCurrentStatus(i, j));
        cube.setType(this.gameState.getCurrentStatus(i, j));
        cube.addActionListener(this.gameLogic);
        add(cube);
      }
    }
    revalidate();
    repaint();
    
  }

}
