import java.util.Random;

public class ReservoirSampling {
	
	private int k; // number of items to sample
	private int m;
	private Object[] S;
	private Random random;
	
	public ReservoirSampling(int k){
		this.k = k;
		this.m = 0;
		S = new Object[k];
		random = new Random();
	}
	
	public void addItem(Object x){
		int r = random.nextInt(m+1);
		if (r < k)
			S[r] = x;
		m++;
	}
	
	public Object[] getSample(){
		if (m < k){
			System.err.println("Stream length less than sample number");
			System.exit(0);
		}
		Object[] copy = new Object[k];
		for (int i = 0; i < k; i++){
			copy[i] = S[i];
		}
		// return a shallow copy
		return copy;
	}
}
