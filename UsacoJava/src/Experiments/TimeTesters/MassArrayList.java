package Experiments.TimeTesters;

import java.util.ArrayList;

public class MassArrayList{
	public static void main(String[] args){
		long t1 = System.currentTimeMillis();
		int MX = 300000;
		ArrayList<Integer>[] add = new ArrayList[MX+1]; for (int i=0;i<=MX;i++) add[i] = new ArrayList<>();
		long t2 = System.currentTimeMillis();
		System.out.println("It took:"+(t2-t1)+"ms to create "+MX+" array lists");
	}
}
