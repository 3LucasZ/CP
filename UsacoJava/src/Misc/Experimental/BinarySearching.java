package Misc.Experimental;

public class BinarySearching {
    public static void main(String[] args){
        int[] arr = new int[]{1,2,2,2,3,3,3,4,4,4,4,4,4,5};
        int search = 3;
        int l=0; int r=arr.length-1;
        while (l<r){
            int mid = (l+r)/2;
            if (arr[mid]>search)r=mid-1;
            else l=mid;
            System.out.println("Stuck at: "+l+", "+r);
        }
        System.out.println(l+1);
    }
}
