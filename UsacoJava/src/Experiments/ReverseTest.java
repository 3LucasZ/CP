package Experiments;

import java.util.Arrays;

public class ReverseTest{
	public static void main(String[] args){
		int[] A = {1,3,4,5,22,5,5,6,2};
		System.out.println("A:"+Arrays.toString(A));
		reverse(A);
		System.out.println("A:"+Arrays.toString(A));
	}
	static void reverse(int[] arr){
		for (int i=0;i<arr.length/2;i++){
			int tmp = arr[i];
			arr[i]=arr[arr.length-1-i];
			arr[arr.length-1-i]=tmp;
		}
	}
}
