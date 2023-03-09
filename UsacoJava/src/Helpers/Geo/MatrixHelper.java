package Helpers.Geo;

public class MatrixHelper{
	public static void main(String[] args){
		long[][] m1 = {{6,-2},{3,7}};
		long[][] m2 = {{1,-2},{-3,4}};
		long[][] res = multMatrix(m1,m2);
		// should equal: {{12,-20},{-18,22}}
		for (int i=0;i<res.length;i++){
			for (int j=0;j<res[0].length;j++){
				System.out.print(res[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	/*
	Complexity: O(rA*cB*rB) ~ O(N^3)
	Modularity: change to int if necessary use mod if necessary
	Reliability: Fully tested (IntersectionAndUnion CF)
	 */
	static long[][] multMatrix(long[][] A,long[][] B){
		//ret = AB = A(B) = transform B using A
		int rA = A.length;
		int cA = A[0].length;
		int rB = B.length;
		int cB = B[0].length;
		if (rB!=cA) throw new RuntimeException("ILLEGAL MATRIX MULT rA:"+rA+", cA:"+cA+", rB:"+rB+", cB:"+cB);
		long[][] ret = new long[rA][cB];
		for (int i=0;i<rA;i++){
			for (int j=0;j<cB;j++){
				for (int k=0;k<rB;k++){
					ret[i][j] = (ret[i][j]+A[i][k]*B[k][j]);
				}
			}
		}
		return ret;
	}
}
