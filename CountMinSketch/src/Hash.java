// Hash.java
// Hash class

public class Hash {
	private int p = 1073741789; //smaller than 2^30
	private int a,b;		
	private int domain,range;
	
	public Hash(int domain, int range){
		a=StdRandom.uniform(p-1)+1;
		b=StdRandom.uniform(p+1);
		this.domain = domain;
		this.range = range;
	}
	
	public int hash(Object key){
		int x = key.hashCode() & domain;
		long prod = (long)a*(long)x;
		prod += (long)b;
		long y = prod % (long) p;
		int r = (int) y % range;
		return r;
	}
}
