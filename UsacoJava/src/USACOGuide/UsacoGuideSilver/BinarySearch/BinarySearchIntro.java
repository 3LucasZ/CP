package USACOGuide.UsacoGuideSilver.BinarySearch;

import java.util.Arrays;

public class BinarySearchIntro {
    static int[] arr = {1,3,5,5,6,9,100,101};
    public static void main(String[] args) {
        System.out.println(findWithInBuilt(6));
        System.out.println(findWithBound(6));
    }
    public static int findWithInBuilt(int n) {
        return Arrays.binarySearch(arr, n);
    }
    public static int findWithBound(int n) {
        int left = 0, right = arr.length;
        while (left < right) {
            int middle = (left + right) / 2;
            if (arr[middle] < n) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        if (arr[left] == n) {
            return left;
        }
        return -1;
    }
}
