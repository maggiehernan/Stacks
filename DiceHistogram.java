

import java.util.Arrays;
import java.util.Scanner;

/**
 * Simulates an Infinite Hopscotch game and prints the data 
 * into a histogram
 * Uses methods from IHDice.java and Histogrammer.java
 * 
 * @author mhernan
 * Created: Oct 20, 2020
 */
public class DiceHistogram {

    /**
     * Run to perform the simulation Dice Histogram
     * Asks for user input to enter an even number for the number 
     * of dice sides 
     * Second number is asked for how many times to run the simulation
     * Creates a histogram based off of the plays 
     * 
     * @param args
     */
    public static void main(String[] args) {
        int numSides;
        int numRuns;
        int i = 1;
        Scanner scnr = new Scanner(System.in);

        System.out.print("Enter an even number: "); //number of sides on dice
        numSides = scnr.nextInt();
        System.out.print("Enter number: "); //number of times code will run
        numRuns = scnr.nextInt();

        int[] histogramData = new int[1000]; 
        IHDice sides = new IHDice(numSides);
        int roll = sides.nextHop();
        histogramData[0] = roll;
        while (i < numRuns) {
            if (roll != 0) {
                System.out.print(roll + " ");
                roll = sides.nextHop();
                histogramData[i] = roll;
            }
            i++;
        }
        System.out.println("");
        Histogrammer histogram = new Histogrammer();
        histogram.fauxHistogram(histogramData);
        histogram.fauxHistogram(histogramData, "histogram.txt");

        scnr.close();
    }
}
