/**
 * @author Asa Marshall
 * Bubble Sort
 */
public class Bubble {

	public int[] bubbleSort(int[] arr){
		int[] a = arr;
	    int n = a.length;
		for(int i = 0; i < n-1; i++){
			for(int j = 0; j < n-i-1; j++){
				if(a[j] > a[j+1]){
					int t = a[j];
					a[j] = a[j+1];
					a[j+1] = t;
				}
			}
		}
		return a;
	}

}
