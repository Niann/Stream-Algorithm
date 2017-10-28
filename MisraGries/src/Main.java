import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		int k = 6;
		MisraGries c = new MisraGries(k);
		
		if(args.length == 0){
			System.err.println("No fileName argument.");
			System.exit(1);
		}
		String fileName = args[0];
		Scanner inputStream;
		try {
			inputStream = new Scanner(new FileInputStream(fileName));
			
			while (inputStream.hasNextLine()){
				String s = inputStream.nextLine();
				c.add(s);
			}
		}
		catch (FileNotFoundException e){
			System.err.println("Input error!");
		}
		
		HashMap<Object, Integer> counter = c.getResult();
		System.out.println(counter);
	}

}
