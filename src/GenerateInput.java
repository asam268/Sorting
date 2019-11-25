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

	//TODO: Different types of input: random, sorted, backwards sorted, few unique
	public GenerateInput() throws IOException{
		generate();
	}


	/**
	 * Loop for random array input sizes 100 - 500000000
	 * @throws IOException Throws for FileNotFoundException
	 */
	public void generate() throws IOException{
		for(int i = 100; i <= 100000000; i=i*10){
			populate(i);
			if(i*5 < 500000000)
				populate(i*5);
		}
	}

	/**
	 * Populates random arrays and sorts them
	 * @param i	input size
	 * @throws IOException Throws for FileNotFoundException
	 */
	public void populate(int i) throws IOException {
		b = new Bubble();
		is = new Insertion();
		s = new Selection();
		q = new Quick();
		m = new Merge();
		int[] arr, arr_b, arr_is, arr_s, arr_q, arr_m;

		arr = new int[i];
		System.out.println("Running input size: " + i);
		for(int j = 0; j < i; j++){
			arr[j] = (int) (Math.random() * i) + 1;
		}

		//TODO: Insert System.nanotime() in between sorting algorithms, faster algorithms first.
        //System.currentTimeMillis could be useful for converting
//		arr_b = b.bubbleSort(arr);
//		writeCustomFile("bubble", arr_b, i);
//		arr_is = is.insertionSort(arr);
//		writeCustomFile("insertion", arr_is, i);
//		arr_s = s.selectionSort(arr);
//		writeCustomFile("selection", arr_s, i);
//		arr_q = arr;
//		q.Qsort(arr_q, 0, i-1);
//		writeCustomFile("quick", arr_q, i);

		arr_m = arr;
		long start = System.nanoTime();
		m.mergesort(arr_m, i);
		long end = System.nanoTime();
		long elapsedTime = end - start;
		double elapsedTimeInSeconds = (double) elapsedTime / 1000000000;
		System.out.println("Elapsed time in seconds: " + elapsedTimeInSeconds);
		writeCustomFile("merge", arr_m, i, elapsedTimeInSeconds);
	}

	//TODO: based on length of time in nano/milliseconds, convert the time to a more readable format
	public void convertTime(){
        /*
        millisecond = 0.001 seconds
        nanosecond = 0.000000001 seconds
        nanosecond = 0.000001 milliseconds
         */
    }

    public int[] getRandomArray(int i){
		return null;
	}

	public int[] getSortedArray(int i){
		return null;
	}

	public int[] getBackwardsArray(int i){
		return null;
	}

	public int[] getFewUniqueArray(int i){
		return null;
	}

	/**
	 * Writes an output file unique to every algorithm and input size
	 * TODO: Write the time in seconds at the top of each file
	 * TODO: Will have to differentiate different types of test
	 * @param algo	Name of the algorithm used to sort the array
	 * @param arr	Sorted array
	 * @param n		Input size
	 * @throws IOException Throws for FileNotFoundException
	 */
	public void writeCustomFile(String algo, int[] arr, int n, double runtime) throws IOException {
		String filename = algo + n + ".txt";
		//File file = new File(filename);
		FileWriter fr = new FileWriter(filename);
		fr.write("Runtime: " + runtime + "seconds");
		fr.write(System.lineSeparator());
		for(int i = 0; i < n; i++){
			fr.write("" + arr[i]);
			fr.write(System.lineSeparator());
		}
		fr.close();
	}

	/**
	 * Drives code
	 * @param args	none
	 * @throws IOException Throws for FileNotFoundException
	 */
	public static void main(String[] args) throws IOException{
		new GenerateInput();
	}

}
