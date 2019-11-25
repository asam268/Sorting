import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class DataSets {

    public static void createInput() throws IOException{
        int n;
        FileWriter fw = new FileWriter("input.txt", true);
        for(int i = 0; i < 100; i++){
            n = (int) (Math.random() * 100) + 1;
            fw.write("" + n);
            fw.write("\n");
        }
        fw.close();
    }

    public static void createOutput() throws IOException {
        File file = new File("input.txt");
        Scanner fr = new Scanner(file);
        Stream<String> lines = Files.lines(file.toPath());
        int n = (int) lines.count();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = fr.nextInt();
        }
        fr.close();
        Arrays.sort(arr);
        FileWriter fw = new FileWriter("output.txt");
        for(int w : arr){
            fw.write("" + w);
            fw.write("\n");
        }
        fw.close();
    }

    public static void main(String[] args) throws IOException {
        createOutput();
    }
}
