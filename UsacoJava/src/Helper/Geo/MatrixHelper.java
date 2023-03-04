package Helper.Geo;

public class MatrixHelper{
	public static void main(String[] args){

	}
	//change to int if necessary
	static long[][] multMatrix(long[][] A,long[][] B){
		//ret = AB = B(A)
		int rA = A.length;
		int cA = A[0].length;
		int rB = B.length;
		int cB = B[0].length;
		if (rB!=cA) throw new RuntimeException("ILLEGAL MATRIX MULT rA:"+rA+", cA:"+cA+", rB:"+rB+", cB:"+cB);
		long[][] ret = new long[rA][cB];
		for (int i=0;i<rA;i++){
			for (int j=0;j<cB;j++){
				for (int k=0;k<rB;k++){
					//insert mod when necessary
					ret[i][j] = (ret[i][j]+A[i][k]*B[k][j]);
				}
			}
		}
		return ret;
	}
}
