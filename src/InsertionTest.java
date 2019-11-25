import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class InsertionTest {
    private Scanner fr;
    private File file;
    private int[] arr;
    private Insertion is = new Insertion();

    @org.junit.jupiter.api.BeforeEach
    void setUp() throws IOException {
        file = new File("input.txt");
        fr = new Scanner(file);
        Stream<String> lines = Files.lines(file.toPath());
        int n = (int) lines.count();
        arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = fr.nextInt();
//            System.out.println(arr[i]);
        }
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        fr.close();
    }

    @org.junit.jupiter.api.Test
    void insertionSort() {
        int[] temp = arr;
        Arrays.sort(temp);
        assertArrayEquals(temp, is.insertionSort(arr));
    }
}