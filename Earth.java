package Assignment;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Earth {

    //creating variable to read coordinates
    double[][] arrayofEarth;
    //creating a map
    Map<Double, TreeMap<Double, Double>> mapOfEarth;
    public static final int MAX_DIM = 5;
    protected double shift;

    //method to read data
    public void readDataArray(String Filename) throws FileNotFoundException {
        File File = new File(Filename);
        Scanner scan = new Scanner(File);
        int ln = 0;
        int count = 0;
        String line;
        //reading the file using while loop go through each line
        //boolean will be true as long as there are more lines in the file
        while (scan.hasNextLine()) {
            count++;
            scan.nextLine();
        }
        scan.close();
        scan = new Scanner(new File(Filename));
        arrayofEarth = new double[count][3];
        double longitude, latitude, altitude;


        while (scan.hasNextLine()) {
            String[] w = scan.nextLine().split("\t");
            longitude = Double.parseDouble(w[0]);
            latitude = Double.parseDouble(w[1]);
            altitude = Double.parseDouble(w[2]);

            arrayofEarth[ln][0] = longitude;
            arrayofEarth[ln][1] = latitude;
            arrayofEarth[ln][2] = altitude;
            ln++;

        }
    }

    //method to get all the coordinates which are above a certain altitude
    public List<Double> coordinatesAbove(double altitude) {
        List<Double> coordinatesAbove = new ArrayList<>();
        for (int i = 0; i < arrayofEarth.length; i++) {
            if (arrayofEarth[i][2] > altitude)
                coordinatesAbove.add(arrayofEarth[i][2]);
        }

        return coordinatesAbove;
    }

    //method to get all the coordinates below a certain altitude
    public List<Double> coordinatesBelow(double altitude) {
        List<Double> coordinatesBelow = new ArrayList<>();
        for (int i = 0; i < arrayofEarth.length; i++) {
            if (arrayofEarth[i][2] < altitude)
                coordinatesBelow.add(arrayofEarth[i][2]);
        }
        System.out.println(coordinatesBelow);
        return coordinatesBelow;
    }

    //method to return percentage of coordinates above a certain altitude
    public void percentageAbove(double altitude) {
        double sizeofearth = arrayofEarth.length;
        double result;
        result = coordinatesAbove(altitude).size(); //this gives all the number of perc coordinnates above the altitude parameter
        result = (result / sizeofearth) * 100;
        System.out.printf("propotion of coordinates above %.1f meters: %.1f%% %n", altitude, result);

    }

    //method to return percentage of coordinates below a certain altitude
    public void percentageBelow(double altitude) {
        double sizeofearth = arrayofEarth.length;
        double result;
        result = coordinatesBelow(altitude).size(); //this gives all the number of coordinnates below the altitude parameter
        result = (result / sizeofearth) * 100;
        System.out.printf("propotion of coordinates below %.1f meters: %.1f%% %n", altitude, result);

    }

    //method which reads altitude data into map
    public void readDataMap(String Filename) throws FileNotFoundException {
        File file = new File(Filename);
        Scanner input = new Scanner(file);
        mapOfEarth = new TreeMap<>();
        String l;
        double longitude, latitude, altitude;
        //boolean will be true as long as there are more lines in the file
        while (input.hasNextLine()) {
            l = input.nextLine();
            String[] data = l.split("\t");
            longitude = Double.parseDouble(data[0]);
            latitude = Double.parseDouble(data[1]);
            altitude = Double.parseDouble(data[2]);
            if (mapOfEarth.get(longitude) == null) {
                mapOfEarth.put(longitude, new TreeMap<>());
                mapOfEarth.get(longitude).put(latitude, altitude);
            } else {
                mapOfEarth.get(longitude).put(latitude, altitude);
            }

        }
        input.close();
    }

    //method to print a random map if file isnt being read
    void generateMap(double resolution) {

        mapOfEarth = new TreeMap<>();
        double longitude = 360;
        double latitude = 90;
        double x = -1;
        double y = -90 * resolution;
        longitude = longitude * resolution;
        latitude = latitude * resolution;
        for (int i = 0; i != longitude; i++) {
            x += 1 * resolution;
            for (int j = 0; j != latitude; j++) {
                double z = (Math.random() * (10001)) - 5000; // creating random values between -5000 and 5000
                //creating a empty list to store the values
                List<Double> key = new ArrayList<>();
                key.add(x);
                key.add(y);
                y += 1 * resolution;
                mapOfEarth.get(x).put(y, z);

            }

        }
        System.out.println(mapOfEarth);
    }

    //function to get altitude from longitude and latitude
    public double getAltitude(double longitude, double latitude) {

        String lon = Double.toString(longitude);
        String lat = Double.toString(latitude);
        String lonlat = lon + lat;
        double altitude = 0;


       /* for (Map.Entry<String, Double> entry : mapOfEarth.entrySet()) {
            if (lonlat == entry.getKey()) {
                altitude = entry.getValue();
            }
        }*/
        System.out.println(altitude);
        return altitude;

    }
   //shifts the sea level in the map
    public void Sealevel(double sealevel) throws FileNotFoundException {
        this.shift = sealevel;
        for (int i = 0; i < arrayofEarth.length; i++) {
            arrayofEarth[i][2] -= sealevel;
        }
        JFrame jf = new JFrame("Earth");
        jf.getContentPane().setPreferredSize(new Dimension(700,700));
        PlotEarth pe = new PlotEarth("C:\\Users\\Pratham Mittal\\IdeaProjects\\OOP\\src\\Assignment\\earth.xyz");
        jf.add(pe);
        jf.pack();
    }
    // method to take in altitude and print out percentage of coordinates above
    public double useralt()
    {
        while(true)
        {
            Scanner input = new Scanner((System.in));
            String q;
            System.out.println("enter altitude");
            q= input.nextLine();
            boolean n = true;

            try
            {
                Double altitude = Double.parseDouble(q);
            }
            catch (NumberFormatException e)
            {
                n = false;
            }
            if(n)
            {
                double altitude = Double.parseDouble((q));
                percentageAbove(altitude);
            }
            else if(q.equals("quit"))
            {
                System.out.println("bye");
                break;
            }
            else
            {
                System.out.println("Invalid altitude. Please enter an altitude in meter");
            }

        }
        return 0;
    }
    //method to take longitude and latitude and return altitude
    public double userlonlat()
    {
        while(true)
        {
            Scanner input = new Scanner((System.in));
            String q;
            System.out.println("enter longitude ( 0 -360) and latitude (-90-90)");
            q= input.nextLine();
            boolean n = true;
            try
            {
                Double longitude= Double.parseDouble(q);
                Double latitude= Double.parseDouble(q);
            }
            catch(NumberFormatException e)
            {
                n = false;
                if(n)
                {   Double longitude= Double.parseDouble(q);
                    Double latitude= Double.parseDouble(q);
                    double x = getAltitude(longitude,latitude);
                    System.out.println(x);
                }
                else if(q.equals("quit"))
                {
                    System.out.println("bye");
                    break;
                }
                else
                {
                    System.out.println("Invalid longitude and latitude");
                }
            }

        }
        return 0;
    }


     }























