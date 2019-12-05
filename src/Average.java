/**
 * @author Asa Marshall
 * @author Zhyere Ducksworth
 * <p>
 * This class stores information about test runs. Keeps track of run times to determine average.
 */
public class Average {

    private int n, count;
    private String algo, inputType;
    private double sum;

    public Average() {
        this.n = 0;
        this.algo = "";
        this.inputType = "";
        this.sum = 0;
        this.count = 0;
    }

    public Average(String algo, int n, String inputType) {
        this.n = n;
        this.algo = algo;
        this.inputType = inputType;
        this.sum = 0;
        this.count = 0;
    }

    /**
     * Increments counter, adds parameter to local sum
     *
     * @param num value added to sum
     */
    public void add(double num) {
        count++;
        sum += num;
    }

    //Accessors

    public double getAverage() {
        return sum / count;
    }

    public int getInputSize() {
        return n;
    }

    public String getAlgo() {
        return algo;
    }

    public String getInputType() {
        return inputType;
    }

    @Override
    public String toString() {
        return "Average for " + algo + " " + n + "/" + inputType + " is " + getAverage() + "s.";
    }
}
