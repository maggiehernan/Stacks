

import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;

/**
* Small class to generate a crude histogram plot.
* 
* @author geoffreytowell
* Created: October 2019
* Modified: Oct 13, 2020
*/
public class Histogrammer
{
	/** The histogram marker */
	private static final String MARK = "X";
	
	/** The maximum height of the bars in the histogram */
	private static final int HISTO_MAX = 150;

	/**
	* Write a histogram to System.out
	* @param hdata the data to be plotted
	*/
	public void fauxHistogram(int[] hdata)
	{
		fauxHistogram(hdata, System.out);
	}
	
	/**
	* Write a histogram to the named file
	* @param hdata the data to be plotted
	* @param filename the file to be written to
	*/
	public void fauxHistogram(int[] hdata, String filename)
	{
		try
		{
			PrintStream fileWriter = new PrintStream(filename);
			fauxHistogram(hdata, fileWriter);
			fileWriter.close();
		}
		catch (IOException ioe)
		{
			System.err.println(ioe.toString());
		}
	}
	
	/**
	* Write a histogram to the PrintWriter
	* @param hdata the data to be plotted.  This data mush be as following.  
	* Each location in the array corresponds to a recorded value.  Each value in 
	* The array corresponses to the number of times that value occurred.  So, if the array
	* is 10 long, this implies that the only values observed are in the range 0..9 inclusive
	* Futher, if the value store at location 7 in the array is 42, then this means that
	* 7 occurred 42 times.
	* @param ps the PrintWriter to which to write
	*/
	private void fauxHistogram(int[] hdata, PrintStream ps)
	{
		// first get the highest and lowest non-zero data points
		int ff=-1; 
		int ll=-1;
		for (int i=0; i<hdata.length; i++)
		{
			if (hdata[i]>0 && ff<0)
			ff=i;
			if (hdata[i]>0)
			ll=i;
		}
		if (ff<0)
		ps.println("No data to Histogram");
		
		// Now plot the data, showing only from lowest to highest
		for (int i=ff; i<=ll; i++)
		{
			// use String.format to make things look nice
			ps.print(String.format("%2d (%4d)", i, hdata[i]));
			// ternary operator limits printing to 70 marks
			for (int j=0; j<(hdata[i]<=HISTO_MAX?hdata[i]:HISTO_MAX); j++)
				ps.print(MARK);
			ps.println();
		}
	}
	
	/**
	 * Demo of the use of the class
	 * @param args unused
	 */
	public static void main(String[] args)
	{
		Random r = new Random();
		// The range of values in the histogram is 1-100
		int[] data = new int[100];
		for (int i=0; i<1000; i++) {
			// v will be a number between 1 and 100.  By building it this way
			// I get a roughly bell-shaped histogram
			int v = 0;
			for (int j=0; j<10; j++)
				v += r.nextInt(10);
			data[v]++;
		}
		// Make a histogram twice.  This first time just put it on the screen; 
		// the second time write it into a file. 
		new Histogrammer().fauxHistogram(data);
		new Histogrammer().fauxHistogram(data, "histo.txt");
	}
}
