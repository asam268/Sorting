import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author Zhyere Ducksworth
 * Quick Sort
 */
public class Quick {

    int partition(int[] arr, int low, int high) {
        int pivot = arr[high];

        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
            if (arr[j] <= pivot) {
                i++;

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    void Qsort(int[] arr, int low, int high) {
        int[] stack = new int[high - low + 1];

        //initialize top of stack
        int top = -1;

        stack[++top] = low;
        stack[++top] = high;

        while (top >= 0) {
            high = stack[top--];
            low = stack[top--];


            int p = partition(arr, low, high);


            if (p - 1 > low) {
                stack[++top] = low;
                stack[++top] = p - 1;

            }
            if (p + 1 < high) {
                stack[++top] = p + 1;
                stack[++top] = high;
            }
        }
    }

}
	