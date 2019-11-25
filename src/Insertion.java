/**
 * @author Asa Marshall
 * Insertion Sort
 */
public class Insertion {

	public int[] insertionSort(int[] arr){
		int[] a = arr;
	    int n = a.length;
		
		for(int i = 1; i < n; ++i){
			int key = a[i];
			int j = i -1;
			
			while(j >= 0 && a[j] > key){
				a[j+1] = a[j];
				j--;
			}
			a[j+1] = key;
		}

		return a;
	}
}
