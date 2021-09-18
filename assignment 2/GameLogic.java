import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.util.*;

//This class implements the interface ActionListener so that it is called when the player makes a move. 
//It calculates the next step of the game
//It updates state and userInterface.

public class GameLogic implements ActionListener {

    // ADD YOUR INSTANCE VARIABLES HERE
    private int size;
    private GameUserInterface gUserInterface;
    private GameState gameState;

    public GameLogic(int size) { // The parameter size is the size of the board on which the game will be played
        // YOUR CODE HERE
        // It creates the game's userInterface and the game's state instances
        this.size = size;
        this.gameState = new GameState(size);
        this.gUserInterface = null;
        //
    }

    /**
     * Starts the game
     */
    public void start() {
        this.gUserInterface = new GameUserInterface(this.gameState, this);
    }

    // Debugging
    // public int[][] test() {
    //     return this.gameState.test();
    // }

    /**
     * resets the game
     */
    public void reset() {
        // YOUR CODE HERE
        this.gameState = new GameState(size);
        this.gUserInterface = new GameUserInterface(this.gameState, this);
    }

    public void actionPerformed(ActionEvent e) {

        // the logic of the game goes in this method
        // YOUR CODE HERE
        Cube clicked;

        //checks if what was clicked was an instance of cube
        if (e.getSource() instanceof Cube) {
            //assigns the clicked cube
            clicked = (Cube) e.getSource();

            //checks if it has neigbours and its not the rat
            if (hasNeighbor(clicked) && gameState.getCurrentStatus(clicked.getRow(), clicked.getColumn()) != 2) {

                // makes clicked cube a snake or Selected
                gameState.select(clicked.getRow(), clicked.getColumn());
                //get the cordinated of the current rat // RED_CUBE
                Point p = gameState.getCurrentCube();

                // makes previous location of rat a free cube
                gameState.selectFreeCube(p.getX(), p.getY());

                //moves rat into a random position in the board
                int posX = randomInt(this.size);
                int posY = randomInt(this.size);
                gameState.setCube(posX,posY);

                // // Debugging
                // System.out.println(Arrays.deepToString(this.test()));

                //Updates the Ui
                this.gUserInterface.getBoardUserInterface().update();

                // Checks if it was the rat that was clicked and has a neigbour
            } else if (hasNeighbor(clicked) && gameState.getCurrentStatus(clicked.getRow(), clicked.getColumn()) == 2) {
                //sores the OK_OPTION value for processing
                int result = messageDisplay("You Won");

                // if "okay" was clicked it resets the game
                if (result == JOptionPane.OK_OPTION) {
                    this.gUserInterface.dispose();
                    reset();

                }else{
                    this.gUserInterface.dispose();
                }
            }

        }
        // checks if the rat is at th edge of the board
        if ((this.gameState.getCurrentCube().getX() == 0 || this.gameState.getCurrentCube().getX() == this.size - 1)) {

            int result = messageDisplay("You Lost!");

            if (result == JOptionPane.OK_OPTION) {
                this.gUserInterface.dispose();
                reset();

            }else{
                this.gUserInterface.dispose();
            }

        }

        //quits the game if quit btn was clicked
        if (e.getSource() == this.gUserInterface.getQuit()) {
            this.gUserInterface.dispose();
        }
        //resets the game if reset btn was clicked
        if (e.getSource() == this.gUserInterface.getReset()) {
            this.gUserInterface.dispose();
            reset();
        }

    }

    //private func to help display message dialog
    private int messageDisplay(String msg) {
        return JOptionPane.showConfirmDialog(null, msg, "Result", JOptionPane.OK_CANCEL_OPTION);

    }

    //return random int
    private int randomInt(int bound) {
        Random rand = new Random();
        return rand.nextInt(bound);

    }
    //checks if a clicked cube has a snake has a neigbour
    private boolean hasNeighbor(Cube cube) {

        int posX = cube.getRow();
        int posY = cube.getColumn();
        int lastIndex = this.size - 1;

        if (!(posX < 1 || posY < 1 || posX == lastIndex || posY == lastIndex)) {
            if (gameState.getCurrentStatus(posX - 1, posY) == 1) {
                return true;
            } else if (gameState.getCurrentStatus(posX, posY - 1) == 1) {
                return true;
            } else if (gameState.getCurrentStatus(posX, posY + 1) == 1) {
                return true;

            } else if (gameState.getCurrentStatus(posX + 1, posY) == 1) {
                return true;
            }
        } else if (posX < 1 && posY < 1) {
            if (gameState.getCurrentStatus(posX, posY + 1) == 1) {
                return true;

            } else if (gameState.getCurrentStatus(posX + 1, posY) == 1) {
                return true;
            }
        } else if (posX == lastIndex && posY == lastIndex) {
            if (gameState.getCurrentStatus(posX - 1, posY) == 1) {
                return true;
            } else if (gameState.getCurrentStatus(posX, posY - 1) == 1) {
                return true;
            }
        } else if (posX < 1 && posY == lastIndex) {
            if (gameState.getCurrentStatus(posX, posY - 1) == 1) {
                return true;
            } else if (gameState.getCurrentStatus(posX + 1, posY) == 1) {
                return true;
            }

        } else if (posX == lastIndex && posY < 1) {
            if (gameState.getCurrentStatus(posX - 1, posY) == 1) {
                return true;
            } else if (gameState.getCurrentStatus(posX, posY + 1) == 1) {
                return true;
            }
        } else if (posX < 1) {
            if (gameState.getCurrentStatus(posX, posY - 1) == 1) {
                return true;
            } else if (gameState.getCurrentStatus(posX, posY + 1) == 1) {
                return true;

            } else if (gameState.getCurrentStatus(posX + 1, posY) == 1) {
                return true;
            }
        } else if (posY < 1) {
            if (gameState.getCurrentStatus(posX - 1, posY) == 1) {
                return true;
            } else if (gameState.getCurrentStatus(posX, posY + 1) == 1) {
                return true;

            } else if (gameState.getCurrentStatus(posX + 1, posY) == 1) {
                return true;
            }
        } else if (posX == lastIndex) {
            if (gameState.getCurrentStatus(posX - 1, posY) == 1) {
                return true;
            } else if (gameState.getCurrentStatus(posX, posY - 1) == 1) {
                return true;
            } else if (gameState.getCurrentStatus(posX, posY + 1) == 1) {
                return true;

            }
        } else if (posY == lastIndex) {
            if (gameState.getCurrentStatus(posX - 1, posY) == 1) {
                return true;
            } else if (gameState.getCurrentStatus(posX, posY - 1) == 1) {
                return true;
            } else if (gameState.getCurrentStatus(posX + 1, posY) == 1) {
                return true;
            }
        }

        return false;

    }

   
}
