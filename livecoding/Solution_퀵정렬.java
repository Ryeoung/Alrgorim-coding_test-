package livecoding;

import java.util.Arrays;

public class Solution_퀵정렬 {
    public static void main(String[] args) {
        int[] array = {
                5 , 2, 3, 4, 7, 4, 1, 9, 8
        } ;
        quickSort(array,0 , array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    static void quickSort(int[] array, int left, int right) {
        if(left >= right) {
            return;
        }
        int pivote = particition(array, left, right);
        quickSort(array, left, pivote - 1);
        quickSort(array, pivote + 1, right);
    }

   static private int particition(int[] array, int left,  int right) {
        int pivote = array[left];
        int low = left + 1;
        int high = right;
        while(low <= high) {
            while(low <= high && array[low] < pivote) {
                ++low;
            }

            while(low <= high && array[high] >= pivote) {
                --high;
            }

            if(low < high) {
                swap(array, low, high);
                ++low;
                --high;
            }

        }
        swap(array, left, high);
        return high;
    }
    static void swap (int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
