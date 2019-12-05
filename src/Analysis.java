import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Asa Marshall
 * @author Zhyere Ducksworth
 * <p>
 * This class is used to write test results to a text file.
 */
public class Analysis {

    private ArrayList<Average> averages;

    public Analysis() throws IOException {
        this.averages = new ArrayList<>();
    }

    /**
     * Checks if there exists an Average object for a specific test case. If yes, add the runtime to the Average object.
     * If no, create a new Average object to store data.
     *
     * @param algo    Name of the algorithm
     * @param type    Input type
     * @param n       Input size
     * @param runtime Runtime of the algorithm
     */
    public void calculateAverage(String algo, String type, int n, double runtime) {
        boolean create = true;
        for (Average average : averages) {
            if (algo.equalsIgnoreCase(average.getAlgo()) && type.equalsIgnoreCase(average.getInputType())
                    && n == average.getInputSize()) {
                average.add(runtime);
                create = false;
            }
        }
        if (create) {
            averages.add(new Average(algo, n, type));
            averages.get(averages.size() - 1).add(runtime);
        }
    }

    /**
     * Writes all data from the Average array to 'sorting_analysis.txt'
     *
     * @throws IOException throws for FileNotFoundException
     */
    public void writeAverages() throws IOException {
        FileWriter fw = new FileWriter("sorting_analysis.txt", true);
        for (Average a : averages) {
            fw.write(a.toString());
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Writes the data from each test to 'sorting_analysis.txt'
     *
     * @param algo    Name of the algorithm
     * @param type    Input type
     * @param n       Input size
     * @param runtime Runtime of the algorithm
     * @throws IOException throws for FileNotFoundException
     */
    public void writeAnalysis(String algo, String type, int n, double runtime) throws IOException {
        calculateAverage(algo, type, n, runtime);
        FileWriter fw = new FileWriter("sorting_analysis.txt", true);
        fw.write("Input Size/Type: " + n + "/" + type);
        fw.write(System.lineSeparator());
        fw.write(algo + ": " + runtime + " seconds");
        fw.write(System.lineSeparator());
        fw.write(System.lineSeparator());
        fw.close();
    }
}
