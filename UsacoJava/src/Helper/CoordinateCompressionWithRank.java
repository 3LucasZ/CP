package Helper;

import java.util.Arrays;
import java.util.Comparator;

public class CoordinateCompressionWithRank{
	public static void main(String[] args){
		int N = 10;
		int[] A = {-1000, 1,7,5,3,0,2,2,6,9,8};
		Integer[] srt = new Integer[N+1];
		for (int i=0;i<=N;i++) srt[i]=i;
		Arrays.sort(srt,Comparator.comparingInt(a->A[a]));
		int[] Acompressed = new int[N+1];
		for (int i=1;i<=N;i++){
			Acompressed[srt[i]]=Acompressed[srt[i-1]];
			if (A[srt[i]]>A[srt[i-1]]) Acompressed[srt[i]]++;
		}
		System.out.println("A:"+Arrays.toString(A));
		System.out.println("B:"+Arrays.toString(Acompressed));
	}
}
