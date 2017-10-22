import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;

// author Linrong Chen

public class MakeTestFiles {

	public static void main(String args[]){
		PrintWriter outputStream;
		int itemNum = 10000;
		try{
			outputStream = new PrintWriter(new FileOutputStream("test-stream.txt"));
			for (int i = 0; i < itemNum; i++){
				outputStream.println(Integer.toString(i) + " 10");
			}
			// single query test
			for (int i = 0; i < 5; i++){
				int x = StdRandom.uniform(itemNum);
				outputStream.println("f " + x);
			}
			// range query test
			int[] arr = new int[10];
			for (int i = 0; i < 10; i++){
				arr[i] = StdRandom.uniform(itemNum);
			}
			Arrays.sort(arr);
			for (int i = 0; i < 10; i += 2){
				outputStream.println("r " + arr[i] + " " + arr[i+1]);
			}
			outputStream.close();
		}
		catch (FileNotFoundException e) {
			System.err.println("unable to make the file!");
		}
		System.out.println("test file generated!");
	}
}
