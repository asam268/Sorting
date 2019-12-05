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
        for (int i = 0; i < 10; i++) //run tests 10 times
            generate(); //generate tests
        analysis.writeAverages(); //write average run times
    }


    /**
     * Loop for random array for varying input sizes. Algorithms with time complexities of O(n^2) run input sizes up to
     * 1,000,000. Merge sort runs up to 100,000,000, since it finishes within seconds.
     * 0 = random, 1 = sorted, 2 = backwards
     *
     * @throws IOException Throws for FileNotFoundException
     */
    public void generate() throws IOException {
        for (int j = 0; j < 3; j++) {
            for (int i = 100; i <= 100000000; i = i * 10) {
                populate("Merge", i, j);
                if (i * 5 < 500000000)
                    populate("Merge", i * 5, j);
                System.runFinalization();
            }
        }
        for (int j = 0; j < 3; j++) {
            for (int i = 100; i <= 100000000; i = i * 10) {
                populate("Quick", i, j);
                if (j > 0 && i == 1000000)
                    break;
                if (i * 5 < 500000000)
                    populate("Quick", i * 5, j);
                System.runFinalization();
            }
        }
        for (int j = 0; j < 3; j++) { //three tests: random, sorted, backwards
            for (int i = 100; i <= 1000000; i = i * 10) { //input sizes: 100, 500, 1000, ... , 1000000
                populate("Bubble", i, j); //Creates arrays, sorts, and keeps track of run times
                if (i * 5 < 5000000)
                    populate("Bubble", i * 5, j);
                System.runFinalization(); //Clears unused variables to avoid memory errors
            }
        }
        for (int j = 0; j < 3; j++) {
            for (int i = 100; i <= 1000000; i = i * 10) {
                populate("Insertion", i, j);
                if (i * 5 < 5000000)
                    populate("Insertion", i * 5, j);
                System.runFinalization();
            }
        }
        for (int j = 0; j < 3; j++) {
            for (int i = 100; i <= 1000000; i = i * 10) {
                populate("Selection", i, j);
                if (i * 5 < 5000000)
                    populate("Selection", i * 5, j);
                System.runFinalization();
            }
        }
    }

    /**
     * For each sorting algorithm, gets a random, sorted, and backwards sorted array of size i and measures the amount
     * of time it takes for the different algorithms to sort each
     *
     * @param algo      Name of the algorithm
     * @param i         Size of the array to sort
     * @param inputType Type of array (random, sorted, backwards sorted)
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

    /**
     * Gets a random array of integers of size n
     *
     * @param n size
     * @return integer array
     */
    public int[] getRandomArray(int n) {
        int[] toReturn = new int[n];
        for (int i = 1; i < n; i++) {
            toReturn[i] = (int) (Math.random() * i) + 1;
        }
        return toReturn;
    }

    /**
     * Gets a sorted array with values 1 to n
     *
     * @param n size
     * @return integer array
     */
    public int[] getSortedArray(int n) {
        int[] toReturn = new int[n];
        for (int i = 0; i < n; i++) {
            toReturn[i] = i + 1;
        }
        return toReturn;
    }

    /**
     * Gets a backwards sorted array with values n to 1
     *
     * @param n size
     * @return integer array
     */
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
     * Drives code
     *
     * @param args none
     * @throws IOException Throws for FileNotFoundException
     */
    public static void main(String[] args) throws IOException {
        new GenerateInput();
    }

}
