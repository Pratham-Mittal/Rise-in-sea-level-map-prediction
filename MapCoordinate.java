package Assignment;


public class MapCoordinate implements Comparable<MapCoordinate>{
    double longitude;
    double latitude;
    double altitude;
    //empty constructor defined which helps in calling in main
    public MapCoordinate(){}
  // constructor to make variables immutable
    public MapCoordinate(double longitude, double latitude, double altitude)
    {
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;

    }
    //method to calculate distance
    public double distanceto(double longitude1, double latitude1, double longitude2, double latitude2)
    {

        double Dlatitude = Math.toRadians(latitude2 - latitude1);
        double Dlongitude = Math.toRadians(longitude2-longitude1);

        latitude1 = Math.toRadians(latitude1);
        latitude2 = Math.toRadians(latitude2);

        double v = Math.pow(Math.sin(Dlatitude / 2), 2) + Math.pow(Math.sin(Dlongitude / 2), 2) * Math.cos(latitude1) * Math.cos(latitude2);

        double rad = 6371;
        double b = 2 * Math.asin(Math.sqrt(v));
        double d = rad * b ;
        System.out.println(d);
        return d;


    }
    //overriding equals method done to compare memory allocation
    @Override
    public boolean equals(Object obj) {

        if(obj == this )
        {   //will be true if both objects point to same memory allocation
            return true;
        }
        if(!(obj instanceof MapCoordinate))
        {
            return false;
        }
        MapCoordinate map = (MapCoordinate) obj;

        return Double.compare(altitude, map.altitude) == 0 && Double.compare(latitude, map.latitude) == 0 && Double.compare(longitude, map.longitude) == 0;
    }

    //sorts elements in array list
    @Override
    public int compareTo(MapCoordinate map) {
        if(altitude == map.altitude && latitude==map.latitude && longitude==map.longitude) {
            return 0;
        }
        else if(altitude > map.altitude && latitude > map.latitude && longitude > map.longitude)
        {
            return 1;
        }
        else
        return -1;
        }
        //gives a string representation of the class

    public String toString()
    {
        return String.valueOf(altitude +" "+longitude +" "+ latitude);
    }

}

