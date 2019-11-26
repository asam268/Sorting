import java.io.FileWriter;
import java.io.IOException;

public class Analysis {

    public Analysis() throws IOException {
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
        FileWriter fw = new FileWriter("sorting_analysis.txt", true);
        fw.write("Input Size/Type: " + n + "/" + type);
        fw.write(System.lineSeparator());
        fw.write(algo + ": " + runtime + " seconds");
        fw.write(System.lineSeparator());
        fw.write(System.lineSeparator());
        fw.close();
    }
}
