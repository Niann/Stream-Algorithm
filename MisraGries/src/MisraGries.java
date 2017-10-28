import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

// Misra-Gries algorithm
// identifying items occur more than m/k times

public class MisraGries {
	
	private HashMap<Object, Integer> counter;
	private int k;
	
	public MisraGries(int k){
		// keep track of k-1 items
		counter = new HashMap<>(k-1);
		this.k = k;
	}
	
	public void add(Object x){
		if (counter.containsKey(x)){
			// if x is tracked, increment counter
			counter.put(x, counter.get(x)+1);
		}
		else if(counter.size() < k-1){
			// if less than k-1 items are tracked, add to tracked items
			counter.put(x, 1);
		}
		else{
			// decrement every tracked item
			// remove if count == 0
			counter.replaceAll((key, value) -> value-1);
			Iterator<Map.Entry<Object, Integer>> iter = counter.entrySet().iterator();
			while(iter.hasNext()){
				HashMap.Entry<Object, Integer> e = iter.next();
				if (e.getValue() == 0){
					iter.remove();
				}
			}
		}
	}
	
	public HashMap<Object, Integer> getResult(){
		return counter;
	}

}
