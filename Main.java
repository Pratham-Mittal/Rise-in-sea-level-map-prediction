package Assignment;

import javax.swing.*;
import java.awt.*;

import java.lang.*;



public class Main {

    public static void main(String[] args) throws Exception {
        Earth et = new Earth();

        et.readDataArray("C:\\Users\\Pratham Mittal\\IdeaProjects\\OOP\\src\\Assignment\\earth.xyz");
        et.readDataMap("C:\\Users\\Pratham Mittal\\IdeaProjects\\OOP\\src\\Assignment\\earth.xyz");
        et.useralt();
        et.userlonlat();

        JFrame jf = new JFrame("Earth");
        jf.getContentPane().setPreferredSize(new Dimension(700,700));
        PlotEarth pe = new PlotEarth("C:\\Users\\Pratham Mittal\\IdeaProjects\\OOP\\src\\Assignment\\earth.xyz");
        jf.add(pe);
        jf.pack();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);

        MapCoordinate mp = new MapCoordinate();
        et.Sealevel(60);
        mp.distanceto(22,303, 30,250);


    }
}