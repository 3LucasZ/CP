package Experiments.MathFormula;

public class SqrtSymmetry{
	public static void main(String[] args){
		int N = 400000;

		int min = Integer.MAX_VALUE;
		int cnt = 0;
		for (int l=1;l<=N;l++){
			int w = ((N+l-1)/l);
			if (l+w<min) cnt=0;
			if (l+w<=min){
				System.out.println("l: "+l+", w: "+w+", min: "+(l+w));
				min=l+w;
				System.out.println("rem: "+(N-l*w));
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
