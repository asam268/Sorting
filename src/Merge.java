/**
 * @author Zhyere Ducksworth
 * Merge Sort
 */
public class Merge {


	//TODO: Change mergesort to iterative version
	void mergesort(int arr[], int n)
	{
		int size; 
		
		int leftStart;
		
		for(size=1; size<=n-1; size=2*size)
		{
			
			for(leftStart=0; leftStart< n-1; leftStart+= 2*size )
			{
				int mid=Math.min(leftStart+size-1, n-1); 
				int right=Math.min(leftStart+2*size-1,n-1 );
				
				merge(arr, leftStart, mid, right); 
				
			}
			
		}
	}
	
	void merge(int arr[], int a, int b, int c)
	{
		int i,j,k; 
		int n1=b-a+1;
		int n2=c-b; 
		
		int L[]=new int[n1];
		int R[]=new int[n2];
		
		for(i =0; i < n1; i++)
		{
			L[i]=arr[a+i]; 
			
		}
		for(j=0; j< n2; j++)
		{
			R[j]=arr[b+1+j]; 
		}
		
		i =0;
		j=0;
		k=a; 
		
		while(i< n1 && j < n2)
		{
			if(L[i]<=R[j])
			{
				arr[k]=L[i]; 
				i++;
			}else
		{
			arr[k]= R[j]; 
			j++; 
		}
		k++; 
	}
		
		while(i < n1)
		{
			arr[k]=L[i];
			i++;
			k++; 
		}
		
		while(j < n2)
		{
			arr[k]=R[j];
			j++;
			k++; 
		}
	}

}
