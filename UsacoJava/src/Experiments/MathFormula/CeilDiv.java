package Experiments.MathFormula;

public class CeilDiv{
	//* Question: if ceil(a/b)=c AND b<=sqrt(a) does ceil(a/c)=b?
	//* Answer: YES
	//* Question: if ceil(a/b)=c AND b>sqrt(a) does ceil(a/c)=b?
	//* Answer: NO
	static int fails = 0;
	public static void main(String[] args)
	{
		for (int a=10000;a<=100000;a++){
			test(a,randomRange(1,(int)Math.sqrt(a)));
		}
		System.out.println("fails: "+fails);
	}
	public static void test(int a, int b){
		System.out.println("Testing: "+a+", "+b);
		int c = ceilDiv(a,b);
		System.out.println("c: "+c);
		int b2 = ceilDiv(a,c);
		System.out.println("b2: "+b2);
		if (b2==b) System.out.println("Success");
		else fails++;
	}
	public static int randomRange(int lo, int hi){
		return (int)(Math.random()*(hi-lo)+lo);
	}
	public static int ceilDiv(int num, int denom){
		return (num+denom-1)/denom;
	}
}
