package Experiments.TimeTesters;

public class MassMatrix{
	public static void main(String[] args){
		long t1 = System.currentTimeMillis();
		int i=1000000;
		int j=2;
		int k=2;
		long[][][] mat = new long[i][j][k];
		long t2 = System.currentTimeMillis();
		System.out.println("It took:"+(t2-t1)+"ms to create ["+i+"]["+j+"]["+k+"]");

	}
}
