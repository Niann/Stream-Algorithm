// count-min sketch with conservative update
// author Linrong Chen

public class CountMin {
	
	private int[][] table;
	private Hash[] hashFamily;

	public CountMin(int d, int w, int n){
		// create a family of pairwise independent hash function
		hashFamily = new Hash[d];
		for (int i = 0; i < hashFamily.length; i++){
			hashFamily[i] = new Hash(n, w);
		}
		// table is initialized to all zero
		table = new int[d][w];
	}
	
	public void add(int item, int update){
		int c = getMin(item);
		for (int i = 0; i < hashFamily.length; i++){
			// conservative update for positive updates
			if (update > 0)
				table[i][hashFamily[i].hash(item)] = Math.max(table[i][hashFamily[i].hash(item)], c + update);
			else
				table[i][hashFamily[i].hash(item)] += update;
		}
	}
	
	private int getMin(int item){
		// get minimum value for a item across all hash tables
		int r = Integer.MAX_VALUE;
		for (int i = 0; i < hashFamily.length; i++){
			r = Math.min(r, table[i][hashFamily[i].hash(item)]);
		}
		return r;
	}
	
	public int query(int item){
		int c = getMin(item);
		return c;
	}
}