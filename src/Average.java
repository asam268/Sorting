public class Average {

    private int n, count;
    private String algo, inputType;
    private double sum;

    public Average(){
        this.n = 0;
        this.algo = "";
        this.inputType = "";
        this.sum = 0;
        this.count = 0;
    }

    public Average(String algo, int n, String inputType){
        this.n = n;
        this.algo = algo;
        this.inputType = inputType;
        this.sum = 0;
        this.count = 0;
    }

    public void add(int num){
        count++;
        sum += num;
    }

    public double getAverage(){
        return sum/count;
    }

    public String getAlgo() {
        return algo;
    }

    public String getInputType() {
        return inputType;
    }
}
