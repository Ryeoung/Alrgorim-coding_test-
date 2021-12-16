package livecoding;

public class Solution_이분탐색 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 5, 5, 5, 5, 5, 5, 5,  6, 7, 8, 9, 10};
//        System.out.println(binarySearch(arr, 0));
        System.out.println(lowerBound(arr, 5));
        System.out.println(upperBound(arr, 5));
    }
    static int upperBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while(left < right) {
            int mid = (left + right) / 2;
            if(arr[mid] > target) {
                right = mid ;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    static int lowerBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while(left < right) {
            int mid = (left + right) / 2;
            if(arr[mid] >= target) {
                right = mid ;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while(left < right) {
            int mid = (left + right) / 2;
            if(arr[mid] == target) {
                return mid;
            }
            else if(arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
