import java.util.ArrayList;

public class TileBoard {

    //Dimensions of the TileBoard
    //Dimension of 8 Tile puzzle is 3
    private final int dimension = 3;

    /*The initial state, in 2D array form given to us in the Assignment
        [2] [8] [3]
        [1] [6] [4]
        [7] [0] [5]
     */
    private final int[][] initialState = { {2, 8, 3}, {1, 6, 4}, {7, 0, 5} };

    /*The goal state, in 2D array form given to us in the Assignment
        [1] [2] [3]
        [8] [0] [4]
        [7] [6] [5]
     */
    private int[][] goalState = { {1, 2, 3}, {8, 0, 4}, {7, 6, 5} };


    //Initialing a workable tiles array to match the initial state of our assignment
    private int[][] tiles;

    //The Manhattan Distance of a TileBoard
    int manhattanDistance;




    //TileBoard Constructor 2 - 2D array as an argument
    public TileBoard(int[][] tileArray){

        tiles = tileArray.clone();
        manhattanDistance = calculateManhattanDistance();

    }   //End of TileBoard Constructor 2

    //A Method to return the 2D array of a TileBoard
    public int[][] getTileBoardArray(){
        return tiles;
    }   //End of getTileBoardArray Method

    //A Method that will return the Goal State in TileBoard form
    public TileBoard getGoalStateTileBoard(){

        //Create new TileBoard and initialize it to the Goal State
        TileBoard goalStateTileBoard = new TileBoard(goalState);

        return goalStateTileBoard;

    }   //End of getGoalStateTileBoard Method

    //A Method to find the column(x-dimension) of the blank(0) tile
    public int getColumn(int desired, int[][] tileBoardArray){

        for(int row = 0; row < dimension; row++){
            for(int col = 0; col < dimension; col++){

                if (tileBoardArray[row][col] == desired){

                    //Return the column(x) of the desired tile
                    return col;
                }
            }
        }

        return -1;
    }   //End of getColumn Method

    //A Method to randomly shuffle the tiles of a TileBoard
    public int[][] shuffleTileBoard(){

        int[][] newArray = copyArray(tiles);



        for(int i = 0; i < 1; i++){
            int random1 = (int) (Math.random() * newArray.length);
            int random2 = (int) (Math.random() * newArray[0].length);
            int random3 = (int) (Math.random() * newArray.length);
            int random4 = (int) (Math.random() * newArray[0].length);


           int temp = newArray[random1][random2];
           newArray[random1][random2] = newArray[random3][random4];
           newArray[random3][random4] = temp;
//            System.out.println("Swapping [" + newArray[random1][random2] + "] at (" + + random1 + "," + random2+") with ["+newArray[random3][random4]+"] at ("+ random3 + ", " + random4 + ")");

        }

        return newArray;
    }   //End of shuffleTileBoard Method

    //A Method to find the row(y-dimension) of the desired tile
    public int getRow(int desired, int[][] tileBoardArray){

        for(int row = 0; row < dimension; row++){
            for(int col = 0; col < dimension; col++){

                if (tileBoardArray[row][col] == desired){

                    //Return the row(y) of the desired tile
                    return row;
                }
            }
        }

        return -1;
    }   //End of getRow Method

    //A Print Method showing the current state of the TileBoard
    public void printTileBoard(){

        System.out.println("\n-----------");

        System.out.println("[" + tiles[0][0] + "] " + "[" + tiles[0][1] + "] " +
                "[" + tiles[0][2] + "]");
        System.out.println("[" + tiles[1][0] + "] " + "[" + tiles[1][1] + "] " +
                "[" + tiles[1][2] + "]");
        System.out.println("[" + tiles[2][0] + "] " + "[" + tiles[2][1] + "] " +
                "[" + tiles[2][2] + "]");


        System.out.println("-----------");

    }   //End of PrintTileBoard

    //A Print Method showing the current state of the TileBoard
    public String toStringTileBoard(){

        String tempString = "-----------\n[" + tiles[0][0] + "] " + "[" + tiles[0][1] + "] " +
                "[" + tiles[0][2] + "]\n[" + tiles[1][0] + "] " + "[" + tiles[1][1] + "] " +
                "[" + tiles[1][2] + "]\n[" + tiles[2][0] + "] " + "[" + tiles[2][1] + "] " +
                "[" + tiles[2][2] + "]\n-----------\n";

        return tempString;

    }   //End of PrintTileBoard


    public Boolean areEqual(TileBoard tileboard){
        boolean areEqual = true;

        int[][] currentArray = tileboard.getTileBoardArray();
        int[][] goalArray = tileboard.getGoalStateArray();

        for(int row = 0; row < dimension; row++){
            for(int col = 0; col < dimension; col++) {
                if(currentArray[row][col] != goalArray[row][col] ){
                    areEqual = false;
                }


            }
        }

        return areEqual;

    }   //End of areEqual Method

    public Boolean isEqual(){
        boolean isEqual = true;

        int[][] currentArray = getTileBoardArray();
        int[][] goalArray = getGoalStateArray();

        for(int row = 0; row < dimension; row++){
            for(int col = 0; col < dimension; col++) {
                if(currentArray[row][col] != goalArray[row][col] ){
                    isEqual = false;
                }


            }
        }

        return isEqual;

    }   //End of isEqual Method


    //A method to find the Manhattan Distance of a TileBoard
    //This is the heuristic used
    public int calculateManhattanDistance(){

        int totalManhattanDistance = 0;

        for(int row = 0; row < goalState.length; row++) {
            for (int col = 0; col < goalState[0].length; col++) {

                int currentManhattanDistance = 0;

                int desired = goalState[row][col];

                if (desired != 0) {
                    int desiredX = getColumn(desired, getTileBoardArray());
                    int desiredY = getRow(desired, getTileBoardArray());

                    int goalStateX = getColumn(desired, goalState);
                    int goalStateY = getRow(desired, goalState);

                    int differenceX = (Math.abs(desiredX - goalStateX));
                    int differenceY = (Math.abs(desiredY - goalStateY));


                    currentManhattanDistance = differenceX + differenceY;
                }
                totalManhattanDistance = totalManhattanDistance + currentManhattanDistance;

            }

        }

        return totalManhattanDistance;

    }   //End of calculateManhattanDistance Method



    public int[][] copyArray(int[][] array){

        int[][] newArray = new int[dimension][dimension];
        for(int row = 0; row < dimension; row++){
            for(int col = 0; col < dimension; col++){
                newArray[row][col] = array[row][col];
            }
        }
        return newArray;

    }   //End of copyArray method

    public ArrayList<TileBoard> getPossibleMoves() {
        ArrayList possibleMoves = new ArrayList<TileBoard>();

//        System.out.println("\n---\nGetting possible moves from board:\n");

        final int[][] currentBoard = getTileBoardArray();

        int[][] upMove = copyArray(currentBoard);
        int[][] downMove =copyArray(currentBoard);
        int[][] leftMove = copyArray(currentBoard);
        int[][] rightMove = copyArray(currentBoard);

        int zeroX, zeroY;

        zeroX = getColumn(0, upMove);
        zeroY = getRow(0, upMove);

        //If the blank(0) tile is not on the top edge of the board - Up Move
        if(zeroY != 0){

            upMove[zeroY][zeroX] = upMove[zeroY - 1][zeroX];
            upMove[zeroY - 1][zeroX] = 0;

            TileBoard upMoveTileBoard = new TileBoard(upMove);

            possibleMoves.add(upMoveTileBoard);
        }


        zeroX = getColumn(0, downMove);
        zeroY = getRow(0, downMove);

        //If the blank(0) tile is not on the bottom edge of the board - Down Move
        if(zeroY != dimension - 1){

            downMove[zeroY][zeroX] = downMove[zeroY + 1][zeroX];
            downMove[zeroY + 1][zeroX] = 0;

            TileBoard downMoveTileBoard = new TileBoard(downMove);

            possibleMoves.add(downMoveTileBoard);
        }

        zeroX = getColumn(0, leftMove);
        zeroY = getRow(0, leftMove);

        //If the blank(0) tile is not on the left edge of the board - Left Move
        if(zeroX != 0){

            leftMove[zeroY][zeroX] = leftMove[zeroY][zeroX - 1];
            leftMove[zeroY][zeroX - 1] = 0;

            TileBoard leftMoveTileBoard = new TileBoard(leftMove);

            possibleMoves.add(leftMoveTileBoard);
        }

        zeroX = getColumn(0, rightMove);
        zeroY = getRow(0, rightMove);

        //If the blank(0) tile is not on the right edge of the board - Right Move
        if ((zeroX != dimension - 1)){

            rightMove[zeroY][zeroX] = rightMove[zeroY][zeroX + 1];
            rightMove[zeroY][zeroX + 1] = 0;

            TileBoard rightMoveTileBoard = new TileBoard(rightMove);

            possibleMoves.add(rightMoveTileBoard);
        }

        return possibleMoves;

    }   //End of getPossibleMoves Method




    //Un-Used Methods



    //TileBoard Constructor 1 - no arguments
    public TileBoard(){

        tiles = initialState.clone();
        manhattanDistance = calculateManhattanDistance();

    }   //End of TileBoard Constructor 1

    //A Method ro return a TileBoard given a TileBoard
    public TileBoard getTileBoard(TileBoard tileBoard){

        return tileBoard;

    }   //End of getTileBoard Method

    //
    public void setTileBoardArray(int[][] newTiles) {
        tiles = newTiles.clone();
        return;
    }

    public int[][] getInitialStateArray() {
        return initialState;
    }

    //A Method that will return the Goal State in 2D array form
    public int[][] getGoalStateArray() {

        return goalState;

    }   //End of getGoalStateArray

    public void setTiles(int[][] newTiles){
        this.tiles = newTiles.clone();
    }

    //A Print Method showing the goal state of the TileBoard
    public void printGoalBoard(){

        System.out.println("\n\nThe Goal to reach is:\n");

        System.out.println("-----------");

        System.out.println("[" + goalState[0][0] + "] " + "[" + goalState[0][1] + "] " +
                "[" + goalState[0][2] + "]");
        System.out.println("[" + goalState[1][0] + "] " + "[" + goalState[1][1] + "] " +
                "[" + goalState[1][2] + "]");
        System.out.println("[" + goalState[2][0] + "] " + "[" + goalState[2][1] + "] " +
                "[" + goalState[2][2] + "]");

        System.out.println("-----------");

    }   //End of PrintGoalBoard

    //A Method that returns the row position of a desired value
    public int getRowPosition(int[][] board, int desiredValue){

        for(int row = 0; row < board.length - 1; row++){
            for( int column = 0; column < board[column].length - 1; column++){
                if (board[row][column] == desiredValue){
                    return row;
                }
            }
        }

        //Return -1 if desired value was not found
        System.out.println("Error: Row Position not found");
        return -1;

    }   //End of getRowPosition Method

    //A Method that returns the column position of a desired value
    public int getColumnPosition(int[][] board, int desiredValue){

        for(int row = 0; row < board.length - 1; row++){
            for( int column = 0; column < board[column].length -1; column++){
                if (board[row][column] == desiredValue){
                    return column;
                }
            }
        }

        //Return -1 if desired value was not found
        System.out.println("Error: Column Position not found");
        return -1;

    }   //End of getColumnPosition Method

}   //End of TileBoard Class
