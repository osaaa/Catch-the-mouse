import java.util.Arrays;
import java.util.Random;

public class GameState {

  // static final variables public
  public static final int FREE_CUBE = 0;
  public static final int SELECTED = 1;
  public static final int RED_CUBE = 2;
  public static final int MAX_SELECTED = 5;

  // non-final variables private
  private int boardSize;
  private Point redCube;
  // YOUR INSTANCE VARIABLES HERE
  private int[][] gameBoard;

  /**
   * Constructor initializes the state to the size of board The parameter size is
   * the size of the board
   */
  public GameState(int size) {
    this.boardSize = size;

    //initializing ame board
    gameBoard = new int[size][size];

    //setting all squares to freecube
    for (int i = 0; i < this.boardSize; i++) {
      for (int j = 0; j < this.boardSize; j++) {
        gameBoard[i][j] = FREE_CUBE;
      }
    }

    // randomly selecting snakes
    setSnakes();

    // Sets Red Cube
    setRedCube();


  }

  //

  public int getSize() {
    // YOUR CODE HERE
    return this.boardSize;
  }

  // public int[][] test(){
  //   return this.gameBoard;
  // }

  /**
   * returns the current status (FREE_CUBE, SELECTED or RED_CUBE) of a given cube
   * in the game
   * 
   * i is the x coordinate of the cube j is the y coordinate of the cube return
   * the status of the cube at location (i,j)
   */
  public int getCurrentStatus(int i, int j) {
    return this.gameBoard[i][j];
  }

  /**
   * Sets the status of the cube at coordinate (i,j) to SELECTED i is the x
   * coordinate of the cube j is the y coordinate of the cube
   */
  public void select(int i, int j) {
   this.gameBoard[i][j] = SELECTED;
  }

  /**
   * Puts the red cube at coordinate (i,j). Clears the previous location of the
   * red cube.
   *
   * i is the x coordinate of the cube j is the y coordinate of the cube
   */
  public void setCube(int i, int j) {
    this.gameBoard[i][j] = RED_CUBE;
  }

  /*
   * Getter method for the current red cube return the location of the curent red
   * cube
   */
  public Point getCurrentCube() {

    for (int i = 0; i < this.boardSize; i++) {
      for (int j = 0; j < this.boardSize; j++) {
        if (gameBoard[i][j] == RED_CUBE) {
          redCube = new Point(i, j);

        }

      }
    }
    return redCube;
  }
// makes a cube a free_cube
  public void selectFreeCube(int i,int j){
    this.gameBoard[i][j] = FREE_CUBE;
  }

  // sets position of the rat at the starts of the game
  private void setRedCube(){
    int cubePos[] = randomRedCube();

    setCube(cubePos[0], cubePos[1]);
  }

  //sets position oof the snakes at the start of the game
  private void setSnakes(){
    Point randomPoints[] = randomSnake();

    for(int i = 0;i < randomPoints.length;i++){
      select(randomPoints[i].getX(), randomPoints[i].getY());
    }
  }

  // assigns random positions for the snakles
  private Point[] randomSnake(){

   Point randomPoints[] = new Point[MAX_SELECTED];
    int max = this.getSize();
    int x = 0;
    int y = 0;
    
    for(int i = 0;i < MAX_SELECTED;i++){
        x = randomInt(max);
        y = randomInt(max);

        Point point = new Point(x,y);
        randomPoints[i] = point;

    }

    return randomPoints;
  }


  //assigns random postion for the RED_cube
  private int[] randomRedCube(){
    int cubePos[] = new int[2];
    int positions[];
    int x1 = 0;
    int x2 = 0;
    if(getSize()%2 == 0){
      x1 = getSize()/2;
      x2 = x1 - 1;

      positions = new int[2];
      positions[0] = x1;
      positions[1] = x2;

      cubePos[0] = positions[randomInt(positions.length)];
      cubePos[1] = positions[randomInt(positions.length)];

      

    }else{
      x1 = (int)(getSize()/2);
      x2 = x1 -1;
     int x3 = x1 + 1;

     positions = new int[3];
      positions[0] = x1;
      positions[1] = x2;
      positions[2] = x3;

      cubePos[0] = positions[randomInt(positions.length)];
      cubePos[1] = positions[randomInt(positions.length)];

    }

    return cubePos;
  }
  
  //generates random int
  private int randomInt(int bound){
    Random rand = new Random();
    return rand.nextInt(bound);

  }

  

}


