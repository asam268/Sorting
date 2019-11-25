/**
 * @author Asa Marshall
 * Selection Sort
 */
public class Selection {

	public int[] selectionSort(int[] arr){
		int[] a = arr;
		int n = a.length;
		
		for(int i = 0; i < n-1; i++){
			int min = i;
			for(int j = i+1; j < n; j++){
				if(a[j] < a[min])
					min = j;
			}
			
			int t = a[min];
			a[min] = a[i];
			a[i] = t;
		}
		return a;
	}
}
