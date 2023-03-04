package Helper;

public class Rotate45{
	/*
	1. rotate an array element by 45 degrees CCW/CW (without overflow)
	2. rotate a point by 45 degrees CCW/CW (around origin)
	 */
	static int N = 5;
	public static void main(String[] args){
		// testing rotate array
		int[][] arr = new int[N][N];
		arr[1][2]=1;
		arr[2][4]=1;
		arr[3][4]=1;
		arr[4][4]=1;
		arr[1][3]=1;
		for (int r=0;r<N;r++){
			for (int c=0;c<N;c++){
				System.out.print(arr[r][c]+"  ");
			}
			System.out.println();
		}
		System.out.println();
		int[][] tArr = new int[2*N][2*N];
		for (int r=0;r<N;r++){
			for (int c=0;c<N;c++){
				Point t = elementRotate45CCW(r,c);
				tArr[t.x][t.y]=arr[r][c];
			}
		}
		for (int r=0;r<2*N;r++){
			for (int c=0;c<2*N;c++){
				System.out.print(tArr[r][c]+"  ");
			}
			System.out.println();
		}
		System.out.println();

		//testing rotate points
		Point test = new Point(1,3);
		System.out.print(test+" -> ");
		pointRotate45CCW(test);
		System.out.println(test);
	}
	static Point elementRotate45CCW(int r,int c) {
		return new Point(N-r+c,r+c);
	}
	static void pointRotate45CCW(Point orig){
		int x = orig.x;
		int y = orig.y;
		orig.x=x-y;
		orig.y=x+y;
	}
	static void pointRotate45CW(Point orig){
		int x = orig.x;
		int y = orig.y;
		orig.x=x+y;
		orig.y=y-x;
	}
	private static class Point {
		int x;
		int y;
		public Point (int x, int y){
			this.x=x;
			this.y=y;
		}
		public String toString(){
			return "["+x+", "+y+"]";
		}
	}
}
