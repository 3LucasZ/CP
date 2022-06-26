package Helper;

import java.util.Arrays;

public class Swap {
    public static void main(String[] args) {
        int[] arr = {0,1,2,3,4,5,6,7,8,9,10};
        System.out.println(Arrays.toString(arr));
        swap(arr,2,6);
        System.out.println(Arrays.toString(arr));
        swap(arr,2,6);
        System.out.println(Arrays.toString(arr));
    }
    public static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }
}
