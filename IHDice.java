

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 * Simulate an N sides die in which 2 sides are 0, N/2-1 are 1 and the rest are 2
 * @author gtowell
 * Created: October 13, 2020
 */
public class IHDice {
    /** A random number generator */
    private Random rand;
    /** The number of sides of the die */
    private int sides;
    
    /**
     * Constructor to make a die with the given number of sides
     * @param sides the number of sides in the die
     */
    public IHDice(int sides) {
        this.sides = sides;
        File f = new File("seed.txt");
        if (f.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(f));) {
                String line = br.readLine();
                this.rand = new Random(Integer.parseInt(line));
            } catch (FileNotFoundException fnfe) {
                // this is fine so be silent
            } catch (IOException ioe) {
                // again, silence
            } catch (NumberFormatException nfe) {
                System.err.println("Could not parse file contents to integer");
            }
        }
        if (rand==null)
            this.rand = new Random();
    }

    /**
     * One roll of teh die.
     * @return 0, 1 or 2
     */
    public int nextHop() {
        int nextHop = rand.nextInt(sides);
        if (nextHop>(sides-3))
            return 0;
        if (nextHop<(sides/2-1)) 
            return 1;
        return 2;
    }

    public static void main(String[] args) {
        IHDice ihd = new IHDice(26);
        int roll = ihd.nextHop();
        while (roll!=0) {
            System.out.print(roll + " ");
            roll=ihd.nextHop();
        }
        System.out.println();
    }
}
