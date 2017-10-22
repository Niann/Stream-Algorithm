// class for range query using count-min sketch
// author Linrong Chen

public class Ranges {
	int[] power2; // this array records the size of interval
	public CountMin[] cms;
	int intervalNum = 32;
	int d = 10, w = 5000; // size of cms
	
	public Ranges(int n){
		// initialize power2 and cms
		power2 = new int[intervalNum];
		power2[0] = 1;
		for (int i = 1; i < intervalNum; i++){
			power2[i] = power2[i-1] * 2;
		}
		
		cms = new CountMin[intervalNum];
		for (int i = 0; i < intervalNum; i++){
			cms[i] = new CountMin(d, w, n);
		}
	}
	
	public void add(int item, int update){
		// update for each cms
		for (int i = 0; i < intervalNum; i++){
			cms[i].add(item, update);
			item /= 2;
		}
	}
	
	public int query(int start, int end){
		// find the largest applicable interval
		// accumulate result and recursively call this function till start == end
		if (start == end)
			return 0;
		for (int i = intervalNum-1; i >= 0; i--){
			if (start % power2[i] == 0 && start + power2[i] <= end){
				return cms[i].query(start / power2[i]) + query(start + power2[i], end);
			}
		}
		return 0;
	}
}
