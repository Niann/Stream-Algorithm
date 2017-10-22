import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * author  Linrong
 */

public class TestDistinct{
	
	static double executionTime;
	
	public static void main(String args[]){
		
		int p = 8;
		Hyperloglog c = new Hyperloglog(p);
		
		if(args.length == 0){
			System.err.println("No fileName argument.");
			System.exit(1);
		}
		String fileName = args[0];
		
		Scanner inputStream;
		try{
			inputStream = new Scanner(new FileInputStream(fileName));
			String s;
			final long start = System.currentTimeMillis();
			while(inputStream.hasNextLine()){
				s = inputStream.nextLine();
				c.add(s);
			}
			inputStream.close();
			int E = c.count();
			final long end = System.currentTimeMillis();
			executionTime = end - start;
			System.out.println("Estimate cardinality: " + E);
		} catch (FileNotFoundException ex) {
			System.err.println("No file: "+fileName);
		}

		// subtract file reading time
		try{
			inputStream = new Scanner(new FileInputStream(fileName));
			final long start = System.currentTimeMillis();
			while(inputStream.hasNextLine()){
				String s = inputStream.nextLine();
			}
			inputStream.close();
			final long end = System.currentTimeMillis();
			long readingTime = end - start;
			executionTime -= readingTime;
		}
		catch (FileNotFoundException ex) {
			System.err.println("No file: "+fileName);
		}
		long memory = (long) ((Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory())
				/ (double)(1024 * 1024));
		System.out.println("The execution time of algorithm is: " + executionTime + "ms");
		System.out.println("Memory usage: " + memory);
	}
}