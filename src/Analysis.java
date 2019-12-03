import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Asa Marshall
 * @author Zhyere Ducksworth
 *
 * This class is used to write test results to a text file.
 */
public class Analysis {

    private ArrayList<Average> averages;

    public Analysis() throws IOException {
        this.averages = new ArrayList<>();
    }

    /**
     * Checks if there exists an Average object for a specific test case. If yes, add the runtime to the Average
     *
     * @param algo
     * @param type
     * @param n
     * @param runtime
     */
    public void calculateAverage(String algo, String type, int n, double runtime){
        boolean create = true;
        for(int i = 0; i < averages.size(); i++){
            if(algo.equalsIgnoreCase(averages.get(i).getAlgo()) && type.equalsIgnoreCase(averages.get(i).getInputType())
                    && n == averages.get(i).getInputSize()){
                averages.get(i).add(runtime);
                create = false;
            }
        }
        if(create){
            averages.add(new Average(algo, n, type));
            averages.get(averages.size()-1).add(runtime);
        }
    }

    public void writeAverages() throws IOException {
        FileWriter fw = new FileWriter("sorting_analysis.txt", true);
        for(Average a : averages){
            fw.write(a.toString());
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    public void writeAnalysis(String algo, String type, int n, double runtime) throws IOException {
        /*
        We need to run tests on algorithms for Merge and Quick first, up to 100m. After running every input size/type
        against these two, we should run input sizes/types against the O(n^2) algorithms one at a time.

        Sample output for Analysis
        Input size/type: 100/random
        Merge: 0s
        Quick: 0s

        Input size/type: 500/random
        Merge: 0s
        Quick: 0s

        ...

        Input size/type: 100/sorted
        Merge: 0s
        Quick: 0s
         */
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
