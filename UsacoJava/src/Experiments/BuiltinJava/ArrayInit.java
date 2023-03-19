package Experiments.BuiltinJava;

import java.util.Arrays;

public class ArrayInit{
	public static void main(String[] args){
		int[][] jagged = new int[5][];
		for (int i=0;i<5;i++){
			jagged[i] = new int[i+1];
		}
		for (int i=0;i<5;i++) System.out.println(jagged[i].length+": "+Arrays.toString(jagged[i]));
	}
}
