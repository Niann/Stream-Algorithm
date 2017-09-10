import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/*
 * author  Linrong
 */

public class MakeRandomTestFiles{
	public static void main(String args[]){
		if(args.length != 1){
			System.err.println("Usage: MakeRandomTestFiles <number>");
			System.exit(1);
		}
		
		// set the cardinality of test stream
		int cardinality = 100000;
		long[] items = new long[cardinality];
		for (int i = 0; i < cardinality; i++){
			double d = StdRandom.uniform();
			items[i] = (long) (d * 58342924141839L);
		}
		
		// create test stream with given cardinality
		long N = Long.parseLong(args[0]);
		PrintWriter outputStream;
		try{
			outputStream = new PrintWriter(new FileOutputStream("test.txt"));
			for(long i=0;i<N;i++){
				double d = StdRandom.uniform();
				d*=(cardinality-1);
				int x = (int)Math.round(d);
				outputStream.println(Long.toString(items[x]));
			}
			outputStream.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("Something wrong with output");
		}
		finally {
			System.out.println("Done!");
		}
	}
}