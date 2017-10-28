import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class MakeTestFiles {

	public static void main(String[] args) {
		
		PrintWriter outputStream;
		
		try{
			outputStream = new PrintWriter(new FileOutputStream("test-stream.txt"));
			
			for(int i = 1; i < 10; i++){
				for(int j = 0; j < i*100; j++){
					outputStream.println(i);
				}
			}
			
			outputStream.close();
		}
		catch (FileNotFoundException e) {
			System.err.println("unable to make the file!");
		}
		
		System.out.println("test file generated!");
	}

}
