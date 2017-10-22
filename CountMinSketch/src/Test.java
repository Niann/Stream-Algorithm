
public class Test {

	public static void main(String[] args) {
		int n = 0x0fffffff;
		Ranges r = new Ranges(n);
		for (int i = 0; i < 1000; i++){
			r.add(i, 100);
			r.add(i, -10);
		}
		for (int i = 583; i < 783; i += 7){
			System.out.println(r.query(i, i+1));
		}
	}

}
