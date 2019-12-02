import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Asa Marshall
 * @author Zhyere Ducksworth
 * This program tests the speed of multiple sorting algorithms with input sizes of up to 500,000,000 and with different
 * input types including randomly sorted input, sorted input, and backwards sorted input. The program outputs to a text
 * file showing the elapsed time in seconds for the certain algorithm, input size, and input type.
 */
public class GenerateInput {
    private Analysis analysis;

    public GenerateInput() throws IOException {
        analysis = new Analysis();
        for(int i = 0; i < 3; i++)
            generate(); //for loop can be added here for testing multiple times.
        analysis.writeAverages();
    }


    /**
     * Loop for random array input sizes 100 - 500000000
     * 0 = random, 1 = sorted, 2 = backwards
     * TODO: O(n^2) algorithms should run 1m-5m, including Quick sort against already sorted input
     * TODO: Need to have multiple samples of each run (probably at least 3 to get an average). We can do 10, but we
     * 		 will probably have to end the test early - its going to take a long time.
     * TODO: Write code for generating averages? Probably can just do it ourselves.
     *
     * @throws IOException Throws for FileNotFoundException
     */
    public void generate() throws IOException {
		for(int j = 0; j < 3; j++) {
			for (int i = 100; i <= 100000000; i = i * 10) {
				populate("Merge", i, j);
				if (i * 5 < 500000000)
					populate("Merge",i * 5, j);
				System.runFinalization();
			}
		}
//        for (int j = 0; j < 3; j++) {
//            for (int i = 100; i <= 100000000; i = i * 10) {
//                populate("Quick", i, j);
//                if (j > 0 && i == 1000000)
//                    break;
//                if (i * 5 < 500000000)
//                    populate("Quick", i * 5, j);
//                System.runFinalization();
//            }
//        }
        //TODO: Bubble and insertion should run real fast for sorted arrays. Higher input size for them?
//		for(int j = 0; j < 3; j++) {
//			for (int i = 100; i <= 1000000; i = i * 10) {
//				populate("Bubble", i, j);
//				if (i * 5 < 5000000)
//					populate("Bubble",i * 5, j);
//				System.runFinalization();
//			}
//		}
//		for(int j = 0; j < 3; j++) {
//			for (int i = 100; i <= 1000000; i = i * 10) {
//				populate("Insertion", i, j);
//				if (i * 5 < 5000000)
//					populate("Insertion",i * 5, j);
//				System.runFinalization();
//			}
//		}
//		for(int j = 0; j < 3; j++) {
//			for (int i = 100; i <= 1000000; i = i * 10) {
//				populate("Selection", i, j);
//				if (i * 5 < 5000000)
//					populate("Selection",i * 5, j);
//				System.runFinalization();
//			}
//		}
    }

    /**
     * Populates random arrays and sorts them
     *
     * @param i input size
     * @throws IOException Throws for FileNotFoundException
     */
    public void populate(String algo, int i, int inputType) throws IOException {
        Bubble b = new Bubble();
        Insertion is = new Insertion();
        Selection s = new Selection();
        Quick q = new Quick();
        Merge m = new Merge();
        int[] arr, arr_a;//arr_b, arr_is, arr_s, arr_q, arr_m;
        String type = "";
        long start, end, elapsedTime;
        double elapsedTimeInSeconds;

        arr = new int[i];

        if (inputType == 0) {
            arr = getRandomArray(i);
            type = "Random";
        } else if (inputType == 1) {
            arr = getSortedArray(i);
            type = "Sorted";
        } else if (inputType == 2) {
            arr = getBackwardsArray(i);
            type = "Backwards";
        }
        System.out.println("Running input size: " + i + " of type " + type + " for " + algo);


        arr_a = arr;
        if (algo.equalsIgnoreCase("Merge")) {
            start = System.nanoTime();
            m.mergesort(arr_a, i);
            end = System.nanoTime();
            elapsedTime = end - start;
            elapsedTimeInSeconds = (double) elapsedTime / 1000000000;
            System.out.println("Elapsed time in seconds: " + elapsedTimeInSeconds);
            analysis.writeAnalysis(algo, type, i, elapsedTimeInSeconds);
        } else if (algo.equalsIgnoreCase("Quick")) {
            start = System.nanoTime();
            q.Qsort(arr_a, 0, i - 1);
            end = System.nanoTime();
            elapsedTime = end - start;
            elapsedTimeInSeconds = (double) elapsedTime / 1000000000;
            System.out.println("Elapsed time in seconds: " + elapsedTimeInSeconds);
            analysis.writeAnalysis(algo, type, i, elapsedTimeInSeconds);
        } else if (algo.equalsIgnoreCase("Bubble")) {
            start = System.nanoTime();
            b.bubbleSort(arr_a);
            end = System.nanoTime();
            elapsedTime = end - start;
            elapsedTimeInSeconds = (double) elapsedTime / 1000000000;
            System.out.println("Elapsed time in seconds: " + elapsedTimeInSeconds);
            analysis.writeAnalysis(algo, type, i, elapsedTimeInSeconds);
        } else if (algo.equalsIgnoreCase("Insertion")) {
            start = System.nanoTime();
            is.insertionSort(arr_a);
            end = System.nanoTime();
            elapsedTime = end - start;
            elapsedTimeInSeconds = (double) elapsedTime / 1000000000;
            System.out.println("Elapsed time in seconds: " + elapsedTimeInSeconds);
            analysis.writeAnalysis(algo, type, i, elapsedTimeInSeconds);
        } else if (algo.equalsIgnoreCase("Selection")) {
            start = System.nanoTime();
            s.selectionSort(arr_a);
            end = System.nanoTime();
            elapsedTime = end - start;
            elapsedTimeInSeconds = (double) elapsedTime / 1000000000;
            System.out.println("Elapsed time in seconds: " + elapsedTimeInSeconds);
            analysis.writeAnalysis(algo, type, i, elapsedTimeInSeconds);
        } else
            System.out.println("Error: Incorrectly specified algorithm");
        System.out.println();
    }

    //TODO: based on length of time in nano/milliseconds, convert the time to a more readable format
    public void convertTime() {
        /*
        millisecond = 0.001 seconds
        nanosecond = 0.000000001 seconds
        nanosecond = 0.000001 milliseconds
         */
    }

    public int[] getRandomArray(int n) {
        int[] toReturn = new int[n];
        for (int i = 1; i < n; i++) {
            toReturn[i] = (int) (Math.random() * i) + 1;
        }
        return toReturn;
    }

    public int[] getSortedArray(int n) {
        int[] toReturn = new int[n];
        for (int i = 0; i < n; i++) {
            toReturn[i] = i + 1;
        }
        return toReturn;
    }

    public int[] getBackwardsArray(int n) {
        int[] toReturn = new int[n];
        int j = n;
        for (int i = 0; i < n; i++) {
            toReturn[i] = j;
            j--;
        }
        return toReturn;
    }

    /**
     * Writes an output file unique to every algorithm and input size
     * TODO: Replace this with the method from Analyis.java
     *
     * @param algo Name of the algorithm used to sort the array
     * @param arr  Sorted array
     * @param n    Input size
     * @throws IOException Throws for FileNotFoundException
     */
    public void writeCustomFile(String algo, String type, int[] arr, int n, double runtime) throws IOException {
        String filename = algo + type + n + ".txt";
        //File file = new File(filename);
        FileWriter fr = new FileWriter(filename);
        fr.write("Runtime: " + runtime + "seconds");
        fr.write(System.lineSeparator());
        for (int i = 0; i < n; i++) {
            fr.write("" + arr[i]);
            fr.write(System.lineSeparator());
        }
        fr.close();
    }

    /**
     * Drives code
     *
     * @param args none
     * @throws IOException Throws for FileNotFoundException
     */
    public static void main(String[] args) throws IOException {
        new GenerateInput();
    }

}
