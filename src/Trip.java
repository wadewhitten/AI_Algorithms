import java.util.ArrayList;

public class Trip {

    //An ArrayList to hold all of our CapitalCities
    private ArrayList trip;

    //A variable to hold the entire distance of a trip,
    // initialized to 0 because all the trips start empty
    private double totalTripDistance = 0;

    //Trip Constructor
    //Constructs a Trip from a given Trip
    public Trip(ArrayList trip){

        this.trip = (ArrayList) trip.clone();

    }   //End of Trip Constructor

    //A Method to return the objects(current) Trip in ArrayList form
    public ArrayList getTrip(){

        return trip;

    }   //End of getTrip Method

    public void addCapitalCityToTrip( CapitalCity capitalCity){
        trip.add(capitalCity);
    }

    //A Method to return the CapitalCity at a specific index
    public CapitalCity getCapitalCity(int indexPosition){

        return (CapitalCity) trip.get(indexPosition);

    }   //End of getCapitalCity Method

    //A Method to set a specific index of the Trip to be a specific CapitalCity
    public void setCapitalCity(int indexPosition, CapitalCity capitalCity){

        trip.set(indexPosition, capitalCity);

        //Resets the heuristic distance since the distance(s) have changed
        totalTripDistance = 0.0;

    }   //End of setCapitalCity Method

    //A Method to return the number of CapitalCities in a trip
    public int getNumberOfCapitalCities(){

        return trip.size();

    }   //End of getNumberOfCapitalCities Method

    //A Method to find the total distance between each CapitalCity of a Trip
    public double calculateTripDistance(){

        if(totalTripDistance == 0){

            //Total Distance for this calculation is reset
            double totalCalculationDistance = 0;

            for (int index = 0; index < getNumberOfCapitalCities(); index++){

                //Create a new CapitalCity the same as the one at given index of Trip
                CapitalCity startCapitalCity = getCapitalCity(index);
                CapitalCity endCapitalCity;

                //If the next CapitalCity is not the last stop of the Trip
                if (index + 1 < getNumberOfCapitalCities() ){

                    //Create a new CapitalCity that will be the next stop on the trip
                    endCapitalCity = getCapitalCity(index + 1);

                }   //End of last stop If Statement

                //If this CapitalCity is the last stop
                else{

                    //Create a new CapitalCity that is the starting CapitalCity
                    endCapitalCity = getCapitalCity(0);

                }   //End of last stop Else Statement

                //Calculate distance from final CapitalCity to initial CapitalCity
                totalCalculationDistance = totalCalculationDistance + startCapitalCity.getDistanceToCapitalCity(endCapitalCity );

            }   //End of For Loop

            //Set the distance of this calculation to the distance of this Trip
            totalTripDistance = totalCalculationDistance;

        }   //End of If Statement

        return totalTripDistance;

    }   //End of calculateTripDistance Method

    //A Method to return the String of abbreviations for the Trip
    public String printTripAbbreviations(){

        String tripString = "";

        for (int index = 0; index < getNumberOfCapitalCities(); index++){

            String tempString = getCapitalCity(index).getCapitalCityAbbreviation();

            tripString = tripString + tempString + " --> ";
        }

        //Add last Capital City abbreviation to string
        tripString = tripString + getCapitalCity(0).getCapitalCityAbbreviation();

        return tripString;

    }   //End of printTripAbbreviations Method



    //A Method to return a String of names for the Trip
    public String entireTripNameString(){

        String tripString = "";

        for (int index = 0; index < getNumberOfCapitalCities(); index++){

            String tempString = getCapitalCity(index).getCapitalCityName();

            tripString = tripString + tempString + " --> ";

            if (index % 6 == 0 && index != 0) {
                tripString = tripString + "\n";
            }
        }

        //Add last Capital City name to string
        tripString = tripString + "and back to " + getCapitalCity(0).getCapitalCityName();

        return tripString;

    }   //End of entireTripNameString

}   //End of Trip Class
