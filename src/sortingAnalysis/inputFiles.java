package sortingAnalysis;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class inputFiles {
	private static final String inputFile = "C:\\test\\inputFile50000Random.txt";

	public static void main(String[] args) {

		BufferedWriter bw = null;
		FileWriter fw = null;
		int number = 50000;
        String s = "";
		try {
			 int line;
	            Random random = new Random();
	            while (number > 0) {
	                // Randomize an integer and write it to the output file
	                line = random.nextInt(50000);
	                s += Integer.toString(line) + System.lineSeparator();
	                number--;
	            }
			
			fw = new FileWriter(inputFile);
			bw = new BufferedWriter(fw);
			bw.write(s);
			
			System.out.println("Done");
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
