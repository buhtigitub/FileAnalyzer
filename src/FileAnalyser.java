import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.io.BufferedReader;
import java.io.File;

/**
 * @author Tony
 *
 */
public class FileAnalyser {

	/**
	 * Holds a file and performs operations on it.
	 */
	
	private File file;
	private int length;
	
	public FileAnalyser(String path) throws IOException {
		file = new File(path);
		if (!file.exists()) {
			createFile(100);
		}
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			
			updateLength(bufferedReader);
			//for length, repeat and pick out top element (instead of turning into array)
			// Get the length of the file on the first pass
		}
		finally {
			if (fileReader != null) {
				fileReader.close();
			}
		}
	}
	
	public void printLargestNumbers(int count) throws IOException {
		FileReader fileReader = null;
		
		try {
			String line;
			
			// Array keeps track of the printed lines.
			boolean[] printed = new boolean[length+1];
			Arrays.fill(printed, Boolean.FALSE);
			// Make as many passes as the file length or the values
			
			for (int i = 0; i < length && i < count; i++) {
				int lineNumber = 0;
				int largest = 0;
				int largestsLineNumber = 0;
				fileReader = new FileReader(file);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				while ((line = bufferedReader.readLine()) != null) {
					int lineValue = Integer.parseInt(line);
					if (printed[lineNumber]) {
						//System.out.println("already printed line " + lineNumber + ", current largest value is: " + largest);
					}
					else if (lineValue > largest) {
						largest = lineValue;
						largestsLineNumber = lineNumber;
						//System.out.println("setting largest val to " + largest);
					}
					lineNumber ++;
				}
				System.out.println("largest val: " + largest);
				printed[largestsLineNumber] = true;
			}
		}
		finally {
			if (fileReader != null) {
				fileReader.close();
			}
		}
	}
	
	public void printLargestNumbersArray(int count) throws IOException {
		FileReader fileReader = null;
		
		try {
			String line;
			
			// Array keeps track of the printed lines.
			boolean[] printed = new boolean[length+1];
			Arrays.fill(printed, Boolean.FALSE);
			// Make as many passes as the file length or the values
			
			// Put the values into an array
			int[] values = new int[length];
			fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			for (int i = 0; (line = bufferedReader.readLine()) != null; i++) {
				values[i] = Integer.parseInt(line);
			}
			
			// Iterate through array and pick out top values
			for (int i = 0; i < count; i++) {
				int largest = 0; 
				int index = 0;
				int largestLineNumber = 0;
				for (int j = 0; j < length; j++) {
//					System.out.println("index: " + j);
//					System.out.println("value: " + values[j]);
//					System.out.println("printed?: " + printed[j]);
					
					if (printed[j]) {
//						System.out.println("already printed line " + j + ", current largest value is: " + largest);
					}
					else if (values[j] > largest) {
						largest = values[j];
						largestLineNumber = j;
//						System.out.println("setting largest val to " + largest);
					}

				}
				System.out.println("largest: " + largest);
				printed[largestLineNumber] = true;
			}
		}
		finally {
			if (fileReader != null) {
				fileReader.close();
			}
		}
	}
	
	public void createFile(int size) throws IOException {
		file.delete();
		FileWriter fileWriter = null;
		fileWriter = new FileWriter(file);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		String line = null;
		Random r = new Random();
		for (; size > 0; size --) {
			printWriter.println(r.nextInt(1000000));
		}
		printWriter.close();

		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		updateLength(bufferedReader);
	}
	
	public void updateLength(BufferedReader bufferedReader) throws IOException {
		length = 0;
		String line = "";
		while ((line = bufferedReader.readLine()) != null) {
			//System.out.println(line);
			length ++;
		}
		this.length = length;
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileAnalyser fileAnalyser = new FileAnalyser("res/input.txt");
		fileAnalyser.createFile(1000000);
		
		long startTime = System.nanoTime();
		fileAnalyser.printLargestNumbers(5);
		long endTime = System.nanoTime();
		System.out.println("Without arrays took " + (endTime - startTime) + " nanoseconds");
		
		startTime = System.nanoTime();
		fileAnalyser.printLargestNumbersArray(5);
		endTime = System.nanoTime();
		System.out.println("With arrays took " + (endTime - startTime) + " nanoseconds");
		
	}

}
