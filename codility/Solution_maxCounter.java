package codility;
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution_maxCounter {
    public int[] solution(int N, int[] A) {
        // write your code in Java SE 8
        int max = 0, premax=0;
        int[] arr = new int[N];
        for(int idx= 0; idx < A.length; idx++) {
            if(A[idx] <= N) {
                if(arr[A[idx] - 1] < premax) {
                    arr[A[idx] - 1] = premax;
                }
                arr[A[idx] -  1]++;
                max = Math.max(arr[A[idx] - 1], max);
            } else {
                //모두 세팅
                premax = max;
            }
        }

        for(int idx = 0; idx < N; idx++) {
            if(arr[idx] < premax) {
                arr[idx] = premax;
            }
        }

        return arr;

    }
}