import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class TileBoardMoves {

    //Holds all of our TileBoard states that we are working with
    private ArrayList moves = new ArrayList<TileBoard>();

    //A calculation of the ManhattanDistance for the entire Manhattan Distance of all the moves
    private int totalManhattanDistance = 0;

    public TileBoardMoves(TileBoard tileBoard){
        moves.add(tileBoard);
//        solve();
    }


    public TileBoardMoves(ArrayList moves){
        this.moves = (ArrayList) moves.clone();
//        solve();
    }

    public ArrayList getTileBoardMoves(){
        return moves;
    }

    public void addTileBoardMove(TileBoard tileBoard){
        moves.add(tileBoard);
    }

    public void setTileBoardMove(TileBoardMoves tileBoardMove){

        moves = (ArrayList) tileBoardMove.getTileBoardMoves().clone();
    }

    public void remove(int index){
        moves.remove(index);
    }

    public void clearUpToThisPoint(int newMoveSize){
        for(int index = moves.size() - 1; index >= newMoveSize + 1; index--){
            moves.remove(index);
        }
    }


    public int getSize(){
        return moves.size();
    }

    public int getNumberOfMoves(){
        return moves.size();
    }


    public int calculateHeuristic(){

        TileBoard tileBoard = (TileBoard) moves.get(moves.size() - 1);

        int manhattanDistance = tileBoard.calculateManhattanDistance();

        int numberOfMoves = moves.size() - 1 ;

        return manhattanDistance + numberOfMoves;

    }   //End of calculateHeuristic Method

    public int calculateNextHeuristic(){

        TileBoard tileBoard = (TileBoard) moves.get(moves.size() - 1);

        int manhattanDistance = tileBoard.calculateManhattanDistance();

        int numberOfMoves = moves.size()  ;

        return manhattanDistance + numberOfMoves;

    }   //End of calculateHeuristic Method





    public String printAllMoves() {

        String tempString = "";

        for(int index = 0; index < moves.size(); index++){
            TileBoard tileBoard =(TileBoard) moves.get(index);
            tempString = tempString + "\nMove: " + index +"\n" + tileBoard.toStringTileBoard() + "\n";

        }

        return tempString;


    }   //End of printAllMoves Method

    public ArrayList getNextPossibleMoves(){
        TileBoard tileBoard = (TileBoard) moves.get(moves.size() - 1);
        return tileBoard.getPossibleMoves();

    }

    public int pickNextMove(ArrayList nextPossibleMoves){
        int lowestManhattanDistance = 100;

        ArrayList manhattanDistances = new ArrayList<Integer>();


        for(int index = 0; index < nextPossibleMoves.size(); index++) {

            TileBoard tileBoard = (TileBoard) nextPossibleMoves.get(index);

            int manhattanDistance = tileBoard.calculateManhattanDistance();

            if(manhattanDistance <= lowestManhattanDistance){

                if(manhattanDistance == lowestManhattanDistance){
                    manhattanDistances.add(index);
                }
                if(manhattanDistance < lowestManhattanDistance){
                    manhattanDistances.clear();
                    manhattanDistances.add(index);
                }

                lowestManhattanDistance = manhattanDistance;
            }
        }   //End of for loop


        Collections.shuffle(manhattanDistances);

        return (int) manhattanDistances.get(0);


    }   //End of pickNextMove Method

    public boolean checkIfSolved(){
        TileBoard tileBoard = (TileBoard) moves.get(moves.size() - 1);

        return tileBoard.isEqual();

    }

    public void addMove(){

        if(!checkIfSolved()) {
            ArrayList nextPossibleMoves = getNextPossibleMoves();

            TileBoard nextMove = (TileBoard) nextPossibleMoves.get(pickNextMove(nextPossibleMoves));


            System.out.println("****************\nAdding Move:");
            nextMove.printTileBoard();
            System.out.println("\nEqual to Goal State? " + nextMove.isEqual() + "\n*****************\n\n");


            moves.add(nextMove);
        }

    }

    //Un-Used Methods

    public void addThisMove(TileBoard tileBoard){

        if(!checkIfSolved()) {
            System.out.println("\n****************\nAdding Move:");
            tileBoard.printTileBoard();
            System.out.println("\nEqual to Goal State? " + tileBoard.isEqual() + "\n*****************");


            moves.add(tileBoard);
        }
    }


    public void solve() {
        while (!checkIfSolved()) {

            addMove();


        }   //End of solved While Loop

        System.out.println("The 8 Tile Puzzle has been solved!");

    }   //End of solve method


}   //End of TileBoardMoves Class
