import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

// author Linrong Chen

public class Main {
	
	static double executionTime;

	public static void main(String[] args) {
		
		Ranges r = new Ranges(0x0fffffff);
		
		if(args.length == 0){
			System.err.println("No fileName argument.");
			System.exit(1);
		}
		String fileName = args[0];
		
		Scanner inputStream;
		try {
			inputStream = new Scanner(new FileInputStream(fileName));
			final long start = System.currentTimeMillis();
			
			while (inputStream.hasNextLine()){
				String s = inputStream.nextLine();
				String[] input = s.split(" ");
				if (Character.isDigit(s.charAt(0))){
					r.add(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
				}
				else if (s.charAt(0) == 'f'){
					// treat single item query as a special case of range query
					int res = r.query(Integer.parseInt(input[1]), Integer.parseInt(input[1])+1);
					System.out.println("query: " + s + "     output: " + res);
				}
				else if (s.charAt(0) == 'r'){
					int a = Integer.parseInt(input[1]), b = Integer.parseInt(input[2]);
					int res = r.query(a, b);
					System.out.println("query: " + s + "    output: " + res);
				}
				else{
					System.err.println("wrong input!");
					System.exit(1);
				}
			}
			
			inputStream.close();
			final long end = System.currentTimeMillis();
			executionTime = end - start;
		}
		catch (FileNotFoundException e){
			System.err.println("Input error!");
		}
		
		// calculate file reading time
		try {
			inputStream = new Scanner(new FileInputStream(fileName));
			final long start = System.currentTimeMillis();
			
			while (inputStream.hasNextLine()){
				String s = inputStream.nextLine();
				String[] junk = s.split(" ");
			}
			
			inputStream.close();
			final long end = System.currentTimeMillis();
			long readingTime = end -start;
			executionTime -= readingTime;
		}
		catch (FileNotFoundException e){
			System.err.println("Input error!");
		}
		
		long memory = (long) ((Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory())
				/ (double)(1024 * 1024));
		System.out.println("The execution time is: " + executionTime + "ms");
		System.out.println("Memory usage: " + memory);
	}

}
