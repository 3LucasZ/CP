package Helper;

import java.util.Arrays;

public class CoordinateCompressionWithRank{
	public static void main(String[] args){
		int N = 10;
		int[] A = {1,7,5,3,0,2,2,6,9,8};
		Integer[] srt = new Integer[N];
		for (int i=0;i<N;i++){
			srt[i]=i;
		}
		Arrays.sort(srt,(a,b)->A[a]-A[b]);
		for (int i=0;i<N;i++){
			System.out.println(A[srt[i]]);
		}
	}
}
