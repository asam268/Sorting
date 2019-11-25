import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Asa Marshall
 * @author Zhyere Ducksworth
 * This program generates randomly assorted integer arrays with different input sizes and sorts them using 5 different
 * sorting methods. The program outputs each sorted array to a text file.
 */
public class GenerateInput {

	private Bubble b;
	private Insertion is;
	private Selection s;
	private Quick q;
	private Merge m;

	public GenerateInput() throws IOException{
		generate();
	}


	/**
	 * Loop for random array input sizes 100 - 500000000
	 * @throws IOException
	 */
	public void generate() throws IOException{
		for(int i = 100; i <= 100000000; i=i*10){
			populate(i);
			populate(i*5);
		}
	}

	/**
	 * Populates random arrays and sorts them
	 * @param i	input size
	 * @throws IOException
	 */
	public void populate(int i) throws IOException {
		b = new Bubble();
		is = new Insertion();
		s = new Selection();
		q = new Quick();
		m = new Merge();
		int[] arr, arr_b, arr_is, arr_s, arr_q, arr_m;

		arr = new int[i];
		System.out.println("Why isn't this printing: " + i + "=====================================");
		for(int j = 0; j < i; j++){
			arr[j] = (int) (Math.random() * i) + 1;
		}

		//TODO: Insert System.nanotime() in between sorting algorithms, faster algorithms first.
        //System.currentTimeMillis could be useful for converting
		arr_b = b.bubbleSort(arr);
		writeCustomFile("bubble", arr_b, i);
		arr_is = is.insertionSort(arr);
		writeCustomFile("insertion", arr_is, i);
		arr_s = s.selectionSort(arr);
		writeCustomFile("selection", arr_s, i);
		arr_q = arr;
		q.Qsort(arr_q, 0, i-1);
		writeCustomFile("quick", arr_q, i);
		arr_m = arr;
		m.mergesort(arr_m, i);
		writeCustomFile("merge", arr_m, i);
	}

	//TODO: based on length of time in nano/milliseconds, convert the time to a more readable format
	public void convertTime(){
        /*
        millisecond = 0.001 seconds
        nanosecond = 0.000000001 seconds
        nanosecond = 0.000001 milliseconds
         */
    }

	/**
	 * Writes an output file unique to every algorithm and input size
	 * @param algo	Name of the algorithm used to sort the array
	 * @param arr	Sorted array
	 * @param n		Input size
	 * @throws IOException
	 */
	public void writeCustomFile(String algo, int[] arr, int n) throws IOException {
		String filename = algo + n + ".txt";
		//File file = new File(filename);
		FileWriter fr = new FileWriter(filename);
		
		for(int i = 0; i < n; i++){
			fr.write("" + arr[i]);
			fr.write(System.lineSeparator());
		}
		fr.close();
	}

	/**
	 * Drives code
	 * @param args	none
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{
		new GenerateInput();
	}

}
