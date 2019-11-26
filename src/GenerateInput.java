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
	private Analysis analysis;

	//TODO: Different types of input: random, sorted, backwards sorted, few unique
	public GenerateInput() throws IOException{
		analysis = new Analysis();
		generate();
	}


	/**
	 * Loop for random array input sizes 100 - 500000000
	 * 0 = random, 1 = sorted, 2 = backwards
	 * @throws IOException Throws for FileNotFoundException
	 */
	public void generate() throws IOException{
		for(int j = 0; j < 3; j++) {
			for (int i = 100; i <= 100000000; i = i * 10) {
				populate(i, j);
				if (i * 5 < 500000000)
					populate(i * 5, j);
				System.runFinalization();
			}
		}
	}

	/**
	 * Populates random arrays and sorts them
	 * TODO: Separate these into different methods for each sorting algorithm
	 * @param i	input size
	 * @throws IOException Throws for FileNotFoundException
	 */
	public void populate(int i, int inputType) throws IOException {
		b = new Bubble();
		is = new Insertion();
		s = new Selection();
		q = new Quick();
		m = new Merge();
		int[] arr, arr_b, arr_is, arr_s, arr_q, arr_m;
		String type = "";
		long start, end, elapsedTime;
		double elapsedTimeInSeconds;

		arr = new int[i];
		System.out.println("Running input size: " + i);

		if (inputType == 0) {
			arr = getRandomArray(i);
			type = "Random";
		} else if (inputType == 1){
			arr = getSortedArray(i);
			type = "Sorted";
		}
		else if(inputType == 2) {
			arr = getBackwardsArray(i);
			type = "Backwards";
		}

//		arr_b = b.bubbleSort(arr);
//		writeCustomFile("bubble", arr_b, i);
//		arr_is = is.insertionSort(arr);
//		writeCustomFile("insertion", arr_is, i);
//		arr_s = s.selectionSort(arr);
//		writeCustomFile("selection", arr_s, i);
//		arr_q = arr;
//		q.Qsort(arr_q, 0, i-1);
//		writeCustomFile("quick", arr_q, i);
		arr_q = arr;
		start = System.nanoTime();
		q.Qsort(arr_q, 0, i-1);
		end = System.nanoTime();
		elapsedTime = end - start;
		elapsedTimeInSeconds = (double) elapsedTime / 1000000000;
		System.out.println("Elapsed time in seconds: " + elapsedTimeInSeconds);
		analysis.writeAnalysis("Quick", type, i, elapsedTimeInSeconds);

		arr_m = arr;
		start = System.nanoTime();
		m.mergesort(arr_m, i);
		end = System.nanoTime();
		elapsedTime = end - start;
		elapsedTimeInSeconds = (double) elapsedTime / 1000000000;
		System.out.println("Elapsed time in seconds: " + elapsedTimeInSeconds);
//		writeCustomFile("merge", type, arr_m, i, elapsedTimeInSeconds);
		analysis.writeAnalysis("Merge", type, i, elapsedTimeInSeconds);
	}

	public void populateQuick(int i, int inputType){
//		q = new Quick();
//		int[] arr;
//		String type = "";
//		long start, end, elapsedTime;
//		double elapsedTimeInSeconds;
//
//		arr = new int[i];
//		System.out.println("Running input size: " + i);
//
//		if (inputType == 0) {
//			arr = getRandomArray(i);
//			type = "Random";
//		} else if (inputType == 1){
//			arr = getSortedArray(i);
//			type = "Sorted";
//		}
//		else if(inputType == 2) {
//			arr = getBackwardsArray(i);
//			type = "Backwards";
//		}

	}

	//TODO: based on length of time in nano/milliseconds, convert the time to a more readable format
	public void convertTime(){
        /*
        millisecond = 0.001 seconds
        nanosecond = 0.000000001 seconds
        nanosecond = 0.000001 milliseconds
         */
    }

    public int[] getRandomArray(int n){
		int[] toReturn = new int[n];
		for(int i = 1; i < n; i++){
			toReturn[i] = (int) (Math.random() * i) + 1;
		}
		return toReturn;
	}

	public int[] getSortedArray(int n){
		int[] toReturn = new int[n];
		for(int i = 0; i < n; i++){
			toReturn[i] = i + 1;
		}
		return toReturn;
	}

	public int[] getBackwardsArray(int n){
		int[] toReturn = new int[n];
		int j = n;
		for(int i = 0; i < n; i++){
			toReturn[i] = j;
			j--;
		}
		return toReturn;
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
	public void writeCustomFile(String algo, String type, int[] arr, int n, double runtime) throws IOException {
		String filename = algo + type + n + ".txt";
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
