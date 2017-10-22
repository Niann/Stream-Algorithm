import java.security.NoSuchAlgorithmException;

/*
 * author  Linrong
 */

public class Hyperloglog {

	private int p, m, threshhold1;  // p - bit mask, m - number of substream
	private int[] M;  // M[i] keeps maximum leading zero number in substream Si
	private final int threshhold2 = 143165576;
	private final long twoTo32 = 4294967296L; // 2^32
	
	public Hyperloglog(int p){
		
		if (p < 4 || p > 16){
			System.err.println("p must be within [4, 16]");
			System.exit(0);
		}
		this.p = p;
		m = (int)Math.pow(2, p);
		M = new int[m];
		threshhold1 = 5 * m / 2;
	}
	
	public void add(Object obj){
		
		// hash function 1, simply using Object.hashCode()
		//int hash = obj.hashCode();
		
		// hash function 2, use md5
		String md5 = MD5(Integer.toString(obj.hashCode()));
		int hash = md5.hashCode();
		
		String binary = Integer.toBinaryString(hash);
		// pad left most 0s if binary rep is less than 32 bits
		int len = binary.length();
		for (int i = 0; i < 32-len; i++)
			binary = "0" + binary;

		// the first p bits decide which substream it is
		int substream = Integer.parseInt(binary.substring(0, p), 2);
		// count number of leading zero of last 32-p bits
		String s = binary.substring(p);
		int i = 0;
		for (i = 0; i < s.length() && s.charAt(i) == '0'; i++){
		}
		
		M[substream] = Math.max(M[substream], i+1);
	}
	
	public int count(){
		
		// return the estimated cardinality
		double sum = 0;
		for (int i: M)
			sum += 1 / Math.pow(2.0, i);
			//sum += 1 / (1 << i);
		double alphaM = getAlphaM();
		double rawE = (1 / sum) * m * m * alphaM;
		return (int)Math.round(correction(rawE));
	}
	
	private double correction(double E){
		
		// correction for small and large range
		double Estar;
		if (E <= threshhold1){
			int V = 0; // number of zero registers
			for (int i = 0; i < m; i++)
				if (M[i] == 0)
					V++;
			if (V != 0)
				Estar = linearCounting(V);
			else
				Estar = E;
		}
		else if(E <= threshhold2){
			Estar = E;
		}
		else{
			Estar = -twoTo32 * Math.log(1 - E / twoTo32);
		}
		return Estar;
	}
	
	private double linearCounting(int V){
		double d = Math.log((double)m / (double)V);
		return m * d;
	}
	
	private double getAlphaM(){
		switch(p){
		case 4:
			return 0.673;
		case 5:
			return 0.697;
		case 6:
			return 0.709;
		default:
			return 0.7213/(1 + 1.079/m);
		}
	}
	
	private String MD5(String md5){
		java.security.MessageDigest md;
		try {
			md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < array.length; ++i)
	          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
	        return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			System.err.println("No such algorithm!");
		}
		return null;
	}

}
