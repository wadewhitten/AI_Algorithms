public class CapitalCity {

    //The name of the CapitalCity
    String name;

    //Abbreviation for the CapitalCity
    String abbreviation;

    //The North-Coordinate and West-Coordinate of the Capital City according to Google Maps
    double northCoordinate;
    double westCoordinate;


    //CapitalCity Constructor 2
    public CapitalCity(String name, String abbreviation, double northCoordinate, double westCoordinate){
        this.name = name;
        this.abbreviation = abbreviation;
        this.northCoordinate = northCoordinate;
        this. westCoordinate = westCoordinate;

    }   //End of CapitalCity Constructor 2

    //A method to return the name of a CapitalCity
    public String getCapitalCityName() {

        return name;

    }   //End of getCapitalName

    //A method to return the abbreviation of a CapitalCity
    public String getCapitalCityAbbreviation() {

        return abbreviation;

    }   //End of getCapitalCityAbbreviation Method


    //A Method to return the North-Coordinate of the CapitalCity
    public double getNorthCoordinate() {

        return northCoordinate;

    }   //End of getNorthCoordinate Method


    //A Method to return the West-Coordinate of a CapitalCity
    public double getWestCoordinate() {

        return this.westCoordinate;

    }   //End of getWestCoordinate Method

    //A Method that calculates the distance between the object(current) CapitalCity and another CapitalCity
    public  double getDistanceToCapitalCity(CapitalCity capitalCity){

        double xDistance = Math.abs(getNorthCoordinate() - capitalCity.getNorthCoordinate());
        double yDistance = Math.abs(getWestCoordinate() - capitalCity.getWestCoordinate());

        //totalDistance = SquareRoot(xDistance^2 + yDistance^2)
        double totalDistance = Math.sqrt( (xDistance * xDistance) + (yDistance * yDistance) );

        return totalDistance;

    }   //End of getDistanceToCity Method

    //A Method that calculates the distance between 2 given CapitalCities
    public double getDistanceBetween2Cities(CapitalCity capitalCity1, CapitalCity capitalCity2){

        double xDistance = Math.abs(capitalCity1.getNorthCoordinate() - capitalCity2.getNorthCoordinate());
        double yDistance = Math.abs(capitalCity1.getWestCoordinate() - capitalCity2.getWestCoordinate());

        //totalDistance = SquareRoot(xDistance^2 + yDistance^2)
        double totalDistance = Math.sqrt( (xDistance * xDistance) + (yDistance * yDistance) );

        return totalDistance;

    }   //End of getDistanceBetween2Cities Method
















    //Un-used Methods


    //CapitalCity Constructor 1
    //Constructs a CapitalCity to "Null" Values
    public CapitalCity(){

        name = "Null City";
        abbreviation = "N/A";
        northCoordinate = 0.0;
        westCoordinate = 0.0;
        System.out.println("Null City Created");

    }   //End of CapitalCity Constructor 1

    //A method to print the object(current) CapitalCity
    public String printCapitalCity(){
        return getCapitalCityName();
    }

    public String printCapitalCityAbbreviation(){
        return getCapitalCityAbbreviation();
    }

    public static void printCapitalAbbreviation(CapitalCity capitalCity){

        System.out.print(capitalCity.getCapitalCityAbbreviation());
    }


    //A Method that prints the distance between the object(current) CapitalCity and a given CapitalCity
    public void printDistanceToCapitalCity(CapitalCity capitalCity){

        System.out.println("The Distance between " + getCapitalCityName() + "(Current Capital City) and " + capitalCity
                + " is: " + getDistanceToCapitalCity(capitalCity));

    }   //End of printDistanceToCapitalCity Method

    //A Method that prints the distance between 2 given CapitalCities
    public void printDistanceBetween2CapitalCities(CapitalCity capitalCity1, CapitalCity capitalCity2){

        System.out.println("The Distance between " + capitalCity1.getCapitalCityName() + " and " + capitalCity2
                + " is: " + getDistanceBetween2Cities(capitalCity1, capitalCity2));

    }   //End of printDistanceBetween2CapitalCities Method





}   //End of CapitalCity Class
