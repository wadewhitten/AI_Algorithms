import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GeneticAlgorithm {

    public static void main (String[] args) {

        int numberOfMutations = 0;

        int mutationNumberFound = 0;


        int decision = 1;

        while ((decision == 1 || decision == 2)) {
            Scanner scan = new Scanner(System.in);

            System.out.println("\n\nPress 1:\t 8 Tiles Puzzle\n\n\tOR\n\nPress 2:\t Traveling Salesman Problem\n\n\n\n\n");
            decision = scan.nextInt();

            //If 8 Tiles Puzzle is chosen
            if (decision == 1) {


                int[][] initialState = {{2, 8, 3}, {1, 6, 4}, {7, 0, 5}};


                System.out.println("Your initial board state is:\n");
                TileBoard bestSolution = new TileBoard(initialState);


                bestSolution.printTileBoard();

                TileBoard currentState = new TileBoard(bestSolution.getTileBoardArray());


                while (!bestSolution.isEqual()) {

                    currentState = new TileBoard(bestSolution.shuffleTileBoard());

                    if(currentState.calculateManhattanDistance() < bestSolution.calculateManhattanDistance()){
                        bestSolution = new TileBoard(currentState.getTileBoardArray());
                        mutationNumberFound = numberOfMutations;
                    }

                    System.out.println("# Mutations: " + numberOfMutations);
                    currentState.printTileBoard();
                    numberOfMutations++;

                }   //End of number of mutations while loop

                    //Print statement for best found
                    System.out.print("\n\nBest Solution found in " + numberOfMutations + " mutations was at mutation # " + mutationNumberFound);
                            currentState.printTileBoard();

                }   //End of 8 Tiles if statement

                //If the Traveling Salesman Problem is chosen
                if (decision == 2) {

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

                    //Initial an initial state
                    Trip currentSolution = new Trip(capitalCitiesArrayList);

                    //Print the initial trip and distance
                    System.out.println("\nInitial Trip:\n\n" + currentSolution.entireTripNameString() +
                            "\n\n\t" + "Fitness Value: " + currentSolution.calculateTripDistance() + "\n\n\n");

                    //Set the current calculated solution to the be the best solution
                    Trip bestSolution = new Trip(currentSolution.getTrip());

                    numberOfMutations = 0;

                    mutationNumberFound = 0;

                    while (numberOfMutations < 9999) {

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

                        if (newSolution.calculateTripDistance() < currentSolution.calculateTripDistance()) {

                            currentSolution = new Trip(newSolution.getTrip());

                        }   //End of keep the better mutation

                        //Keep the best mutation
                        if (currentSolution.calculateTripDistance() < bestSolution.calculateTripDistance()) {

                            bestSolution = new Trip(currentSolution.getTrip());

                            bestSolution.printTripAbbreviations();
                            mutationNumberFound = numberOfMutations;


                            System.out.println("New best found at mutation #" + mutationNumberFound + ":\n");

                        }   //End of better than if statement


                        //Increase the number of mutations
                        numberOfMutations++;

                    }   //End of mutation while loop

                    //Print statement for best found
                    System.out.print("\n\nBest Solution found in : " + numberOfMutations + " mutations was " +
                            bestSolution.calculateTripDistance() + "\n\n" +
                            bestSolution.entireTripNameString() + "\n\nAt mutation number \t" +
                            mutationNumberFound + "\n\n");

                }   // End of Traveling Salesman if statement

            }   //End of while statement

        }   //End of main Method

    }   //End of GeneticAlgorithm Class
