package Experiments.BuiltinJava;

import java.util.Arrays;

public class GenericMethod{
	public static void main(String[] args){
		Boolean[] arr = new Boolean[10];
		System.out.println("last of "+Arrays.toString(arr)+":"+last(arr));
	}
	static <T> T last(final T[] arr){
		return arr[arr.length-1];
	}
}
