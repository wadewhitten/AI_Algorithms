//Wade Whitten


/*
This class performs the Simulated Annealing Algorithm on sufficient objects

Simulated Annealing is a a way for an algorithm to find it's way around local
minima and maxima. There are multiple simulated annealing solutions. This
solution will randomly pick another starting point if a local minmima/maxima
is found.

 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SimulatedAnnealing {

    //This method calculates the acceptance probability
    public static double CalculateAcceptanceProbability(double initialEnergy, double newEnergy, double temperatureValue) {

        //If the new energy is better than initial energy, accept the new energy
        //Here we are trying to minimize, so smaller values are more preferred
        //If you are trying to maximize, you want if (newEnergy > Energy)
        if (newEnergy < initialEnergy) {

            return 1.0;

        }   //End of CalculateAcceptanceProbability Method

        //If the new energy is not better than the initial energy, Calculate the Acceptance Probability
        return Math.exp((initialEnergy - newEnergy) / temperatureValue);

        /*

        exp((- delta) / temperatureValue) > random[0,1)

        When the temperature value is large, the calculation is close to e^(0) = 1
            So, for large temperature values, you can go anywhere

        When the temperature value is small, the calculation is close to e^( - infinity ) = 0
            So, for small temperature values, avoid most bad moves

        After the temperature value becomes 0, other Hill-Climbing algorithms should be used

        */

    }   //End of CalculateAcceptanceProbability method

    //Main method
    public static void main(String[] args) {


        int decision = 1;
        int numberReps;
        double temperatureFound;

        while ((decision == 1 || decision == 2)) {
            Scanner scan = new Scanner(System.in);

            System.out.println("\n\nPress 1:\t 8 Tiles Puzzle\n\n\tOR\n\nPress 2:\t Traveling Salesman Problem\n\n\n\n\n");
            decision = scan.nextInt();

            //If 8 Tiles Puzzle is chosen
            if (decision == 1) {

                //Initial starting temperature value
                //This value should be changed to match the parameters of our problem
                int temperatureValue = 999;

                //Cooling rate of the algorithm
                //This should be gradual, as it is more likely to find a better solution
                //However, this comes at the cost of performance
                double coolingRate = 0.003;

                //Reset the repetition counter
                numberReps = 0;

                temperatureFound = 0;

                //Create and Initialize all your Objects
            /*
            Object object1 = new Object(parameter1, parameter2, ...);
                            ...
            Object objectN = new Object(parameterN1, parameterN2, ...);

             */
                int[][] initialState = { {2, 8, 3}, {1, 6, 4}, {7, 0, 5} };
//                int[][] initialState = { {2, 8, 3}, {0, 6, 4}, {1, 7, 5} };

                TileBoard tileBoard = new TileBoard(initialState);




                //Initialize an initial state
        /*
            Object currentSolution = new Object();
            currentSolution.generateAllStates();
        *
        * */

                System.out.println("Your initial board state is:\n");
                TileBoardMoves currentSolution = new TileBoardMoves(tileBoard);
                System.out.println(tileBoard.toStringTileBoard());

                //Print statement for initial state calculated f(n)
                System.out.println("Heuristic =  Manhattan Distance + Move Count = (" + currentSolution.calculateHeuristic() +
                        ") + (" +  (currentSolution.getSize() - 1) +") = " + (currentSolution.calculateHeuristic() + currentSolution.getSize()-1));

                //Set the current calculated solution to the be the best solution
        /*
            Object bestSolution = new Object(currentSolution.returnObjectMethod());
        */

                TileBoardMoves bestSolution = new TileBoardMoves(currentSolution.getTileBoardMoves());

                //Loop through possible solutions until the system has cooled'
                //After the temperature value becomes 0, other Hill-Climbing algorithms should be used
                while (temperatureValue > 1) {

                    //Create a new Object to compare to the best Object
            /*
                Object newPossibleSolution = new Object(currentSolution.returnObjectMethod());
            */

                    TileBoardMoves newPossibleSolution = new TileBoardMoves(currentSolution.getTileBoardMoves());

                    System.out.println("\nCurrent Solution:");
                    currentSolution.addMove();

                    System.out.println("New Possible Solution:");

                    newPossibleSolution.addMove();

                    //Find energies of the possible solution states
                    int currentEnergy = currentSolution.calculateHeuristic(); // = currentSolution.getHeuristicScore;
                    int newEnergy = newPossibleSolution.calculateHeuristic();  // = newPossibleSolution.getHeuristicScore;


                    //Acceptance Probability Calculation
                    //Decides if we should keep the new solution
                    //Keep if: exp( -(delta) / temperatureValue) > random[0,1) )
                    if(CalculateAcceptanceProbability(currentEnergy, newEnergy, temperatureValue) > Math.random()){
                        currentSolution = new TileBoardMoves(newPossibleSolution.getTileBoardMoves());
                    }

                    //Keep the better solution state
                    if(currentSolution.calculateHeuristic() < bestSolution.calculateHeuristic()){
                        bestSolution = new TileBoardMoves(currentSolution.getTileBoardMoves());

                        temperatureFound = temperatureValue;

                    }

                    //Temperature cooling rate function
                    temperatureValue = (int) (temperatureValue * (1 - coolingRate));

                    //Increase the repetition count
                    numberReps++;
                    System.out.println(numberReps);



                }   //End of temperature cooling while loop

                //Print statement for best found
                System.out.println("\nThe best found:" + currentSolution.printAllMoves());

            }   //End of 8 Tiles Puzzle If Statement

            //If the Traveling Salesman Problem is chosen
            if (decision == 2) {

                //Reset the repetition counter
                numberReps = 0;

                //An double to see when the best solution was found
                temperatureFound = 0;

                //Initial starting temperature value
                //This value should be changed to match the parameters of our problem
                double temperatureValue = 9999.9;

                //Cooling rate of the algorithm
                //This should be gradual, as it is more likely to find a better solution
                //However, this comes at the cost of performance
                double coolingRate = 0.003;

                //Create and Initialize all your Objects
                //Top 30 most populated cities in the U.S. according to Infoplease.com
                //Coordinates according the Google Maps
                CapitalCity newYorkCityNY = new CapitalCity("New York City, New York", "NYC", 40.7128, 74.0060);
                CapitalCity losAngelesCA = new CapitalCity("Los Angeles, California", "LA", 34.0522, 118.2437);
                CapitalCity chicagoIL = new CapitalCity("Chicago, Illinois", "CHI", 41.8781, 87.6298);
                CapitalCity houstonTX = new CapitalCity("Houston, Texas", "HOU", 29.7604, 95.3698);
                CapitalCity philadelphiaPA = new CapitalCity("Philadelphia, Pennsylvania", "PHIL", 39.9526, 75.1652);
                CapitalCity phoenixAZ = new CapitalCity("Phoenix, Arizona", "PHX", 33.4484, 73.979681);
                CapitalCity sanAntonioTX = new CapitalCity("San Antonio, Texas", "SA", 40.6974881, 112.0740);
                CapitalCity sanDiegoCA = new CapitalCity("San Diego, California", "SD", 32.7157, 117.1611);
                CapitalCity dallasTX = new CapitalCity("Dallas, Texas", "DAL", 32.7767, 96.7970);
                CapitalCity sanJoseCA = new CapitalCity("San Jose, California", "SJ", 37.3382, 121.8863);
                CapitalCity austinTX = new CapitalCity("Austin, Texas", "AUS", 30.2672, 97.7431);
                CapitalCity jacksonvilleFL = new CapitalCity("Jacksonville, Florida", "JACK", 30.3322, 30.3322);
                CapitalCity sanFranciscoCA = new CapitalCity("San Francisco, California", "SF", 37.7749, 122.4194);
                CapitalCity indianapolisID = new CapitalCity("Indianapolis, Indiana", "IND", 39.7684, 86.1581);
                CapitalCity columbusOH = new CapitalCity("Columbus, Ohio", "COL", 39.9612, 82.9988);
                CapitalCity fortWorthTX = new CapitalCity("Fort Worth, Texas", "FT W", 32.7555, 97.3308);
                CapitalCity charlotteNC = new CapitalCity("Charlotte, North Carolina", "CHRL", 35.2271, 80.8431);
                CapitalCity detroitMI = new CapitalCity("Detroit, Michigan", "DET", 42.3314, 83.0458);
                CapitalCity elPasoTX = new CapitalCity("El Paso, Texas", "EL P", 31.7619, 106.4850);
                CapitalCity seattleWA = new CapitalCity("Seattle, Washington", "SEA", 47.6062, 122.3321);
                CapitalCity denverCO = new CapitalCity("Denver, Colorado", "DEN", 39.7392, 104.9903);
                CapitalCity washingtonDC = new CapitalCity("Washington D.C.", "WDC", 38.9072, 77.0369);
                CapitalCity memphisTN = new CapitalCity("Memphis, Tennessee", "MEM", 42.3601, 71.0589);
                CapitalCity bostonMA = new CapitalCity("Boston, Massachusetts", "BOS", 42.3601, 71.0589);
                CapitalCity nashvilleTN = new CapitalCity("Nashville, Tennessee", "NSH", 36.1627, 86.7816);
                CapitalCity baltimoreMD = new CapitalCity("Baltimore, Maryland", "BAL", 39.2904, 76.6122);
                CapitalCity oklahomaCityOK = new CapitalCity("Oklahoma City, Oklahoma", "OKC", 35.4676, 97.5164);
                CapitalCity portlandOR = new CapitalCity("Portland, Oregon", "PORT", 45.5122, 122.6587);
                CapitalCity lasVegasNV = new CapitalCity("Las Vegas, Nevada", "LV", 36.1699, 115.1398);
                CapitalCity milwaukeeWI = new CapitalCity("Milwaukee, Wisconsin", "MIL", 43.0389, 87.9065);

                //Add the CapitalCities to an ArrayList
                ArrayList capitalCitiesArrayList = new ArrayList<CapitalCity>();
                capitalCitiesArrayList.add(newYorkCityNY);
                capitalCitiesArrayList.add(losAngelesCA);
                capitalCitiesArrayList.add(chicagoIL);
                capitalCitiesArrayList.add(houstonTX);
                capitalCitiesArrayList.add(philadelphiaPA);
                capitalCitiesArrayList.add(phoenixAZ);
                capitalCitiesArrayList.add(sanAntonioTX);
                capitalCitiesArrayList.add(sanDiegoCA);
                capitalCitiesArrayList.add(dallasTX);
                capitalCitiesArrayList.add(sanJoseCA);
                capitalCitiesArrayList.add(austinTX);
                capitalCitiesArrayList.add(jacksonvilleFL);
                capitalCitiesArrayList.add(sanFranciscoCA);
                capitalCitiesArrayList.add(indianapolisID);
                capitalCitiesArrayList.add(columbusOH);
                capitalCitiesArrayList.add(fortWorthTX);
                capitalCitiesArrayList.add(charlotteNC);
                capitalCitiesArrayList.add(detroitMI);
                capitalCitiesArrayList.add(elPasoTX);
                capitalCitiesArrayList.add(seattleWA);
                capitalCitiesArrayList.add(denverCO);
                capitalCitiesArrayList.add(washingtonDC);
                capitalCitiesArrayList.add(memphisTN);
                capitalCitiesArrayList.add(bostonMA);
                capitalCitiesArrayList.add(nashvilleTN);
                capitalCitiesArrayList.add(baltimoreMD);
                capitalCitiesArrayList.add(oklahomaCityOK);
                capitalCitiesArrayList.add(portlandOR);
                capitalCitiesArrayList.add(lasVegasNV);
                capitalCitiesArrayList.add(milwaukeeWI);

                //Shuffle the order of the ArrayList
                Collections.shuffle(capitalCitiesArrayList);

                //Initialize an initial state
                Trip currentSolution = new Trip(capitalCitiesArrayList);

                //Print statement for initial state calculated f(n)
                System.out.println("\nInitial Trip:\n\n" + currentSolution.entireTripNameString() +
                        "\n\n\t" + "Distance: " + currentSolution.calculateTripDistance() + "\n\n\n");

                //Set the current calculated solution to the be the best solution
                Trip bestSolution = new Trip(currentSolution.getTrip());

                //Loop through possible solutions until the system has cooled
                //After the temperature value becomes 0, other Hill-Climbing algorithms should be used
                while (temperatureValue > 1) {

                    //Create a new Object to compare to the best Object
                    Trip newSolution = new Trip(currentSolution.getTrip());

                    //Get random indexes of the Trip
                    int randomTripPosition1 = (int) (newSolution.getNumberOfCapitalCities() * Math.random());
                    int randomTripPosition2 = (int) (newSolution.getNumberOfCapitalCities() * Math.random());

                    //Get the CapitalCities at these random indexes
                    CapitalCity capitalCity1 = newSolution.getCapitalCity(randomTripPosition1);
                    CapitalCity capitalCity2 = newSolution.getCapitalCity(randomTripPosition2);

                    //Swap the 2 CapitalCities at the random indexes in the Trip
                    newSolution.setCapitalCity(randomTripPosition2, capitalCity1);
                    newSolution.setCapitalCity(randomTripPosition1, capitalCity2);


                    //Find energies of the possible solution states
                    double currentEnergy = currentSolution.calculateTripDistance();
                    double newEnergy = newSolution.calculateTripDistance();


                    //A string representing the current state of the solution
                    String currentSolutionString = "\n--------\nCurrent Solution:\n" +
                            currentSolution.printTripAbbreviations() + "\n" +
                            "Distance = " + currentEnergy + "\n";

                    //A string representing a possible new state of the solution
                    String newSolutionString = "\nPossible Solution:\n" +
                            newSolution.printTripAbbreviations() + "\n" +
                            "Distance = " + newEnergy + "\n\n" + "Number " +
                            "of Repetitions: " + numberReps + "\n--------\n";


                    //Acceptance Probability Calculation
                    //Decides if we should keep the new solution
                    //Keep if: exp( -(delta) / temperatureValue) > random[0,1) )
                    if (CalculateAcceptanceProbability(currentEnergy, newEnergy, temperatureValue) > Math.random()) {

                        currentSolution = new Trip(newSolution.getTrip());

                        //Undo the comment below to print the 2 solution states every time a new best solution state is found
                        //System.out.println(currentSolutionString + newSolutionString);

                    }   //End of CalculateAcceptanceProbability If Statement

                    //Keep the better solution state
                    if (currentSolution.calculateTripDistance() < bestSolution.calculateTripDistance()) {

                        //The best solution state is now the current solution state
                        bestSolution = new Trip(currentSolution.getTrip());

                        //Record the repetition where the best solution state was found
                        temperatureFound = temperatureValue;

                    }   //End of better solution state If statement


                    //Temperature cooling rate function
                    temperatureValue = temperatureValue - coolingRate;

                    //Increase the count of repetition
                    numberReps++;

                }   //End of temperature cooling while loop

                //Print statement for best found
                System.out.print("\n\nBest Solution found in : " + numberReps + " repetitions was " +
                        bestSolution.calculateTripDistance() + "\n\n" +
                        bestSolution.entireTripNameString() + "\n\nAt temperature \t" +
                        temperatureFound + "\n\n");

            }   //End Traveling Salesman Problem If Statement

        }   //End of decision While Loop

    }   //End of main Method

}   //End of SimulatedAnnealing Class
