package Assignment;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.TreeMap;

public class PlotEarth extends Plot
{    //object created for class earth
    Earth e;
    double shift;
    int size = 1;
    double resolution = 1;
    public Map<Double, Double> mapOfEarth;
    //constructor to read the array
    public PlotEarth(String filename) throws FileNotFoundException
    {
        e = new Earth();
        e.readDataArray("C:\\Users\\Pratham Mittal\\IdeaProjects\\OOP\\src\\Assignment\\earth.xyz");


        setScaleX(-1, 240);
        setScaleY(-90, 90);
    }


    @Override
    //method to print and color the map
    public void paintComponent(Graphics g)
    {   //supers helps in refering to the parent class
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D)g;

        for(int i = 0; i< e.arrayofEarth.length; i++)
        {
            double longitude = e.arrayofEarth[i][0];
            double latitude = e.arrayofEarth[i][1];
            double altitude = e.arrayofEarth[i][2];


            if(altitude <= -4000) {
                gd.setColor(new Color(32,3,251));
            }
            else if (altitude > -4000 && altitude < -3000){
                gd.setColor(new Color(3,40,252));
            }
            else if (altitude >-3000  && altitude < -2000){
                gd.setColor(new Color(3,80,252));
            }
            else if (altitude >-2000 && altitude < -1000){
                gd.setColor(new Color(3,150,252));
            }
            else if (altitude >-1000 && altitude < 0){
                gd.setColor(new Color(3,200,252));
            }
            else if (altitude >0 && altitude < 250){
                gd.setColor(new Color(2,230,48));
            }
            else if (altitude >250 && altitude < 350){
                gd.setColor(new Color(0,130,30));
            }
            else if (altitude >350 && altitude < 2000){
                gd.setColor(new Color(175,131,10));
            }
            else if (altitude >2000 && altitude < 3000){
                gd.setColor(new Color(138,111,38));
            }
            else if (altitude >3000 && altitude < 4000){
                gd.setColor(new Color(255,255,255));
            }
            else if  (altitude > 4000){
                gd.setColor(new Color(180,177,167));
            }
            //centers the map in the frame
            longitude = longitude + 168;
            if(longitude > 360)
            {
                longitude = longitude - 360;
            }
            gd.fillRect(scaleX(longitude),scaleY(latitude), 1 , 1);
            int sealevel = (int) e.shift;
            String l = "The Earth after sea level rose by" + sealevel +"meters";
            if(sealevel !=0)
            {
                gd.drawString(l, 110, 220);
            }
        }
    }
}





