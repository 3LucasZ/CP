import java.io.*;
import java.util.*;
/*
AtCoder
D - Pond
Binary Search - USACO Silver Guide - Normal
 */
public class Pond {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int N;
    static int K;
    static int[][] height;
    static Square[] squares;
    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        height = new int[N+1][N+1];
        squares = new Square[N*N];
        for (int r=1;r<=N;r++) {
            st = new StringTokenizer(br.readLine());
            for (int c=1;c<=N;c++) {
                int h = Integer.parseInt(st.nextToken());
                height[r][c] = h;
                squares[N*(r-1)+(c-1)] = new Square(r,c,h);
            }
        }
        Arrays.sort(squares, (a,b)->a.height-b.height);
        //checkpoint
//        out.println(Arrays.toString(squares));
//        out.println();
//        for (int i=0;i<=N;i++) {
//            out.println(Arrays.toString(height[i]));
//        }
//        out.println();
//        for (int i=0;i<N*N-1;i++) {
//            out.println(squares[i]);
//            out.println(possibleMedian(squares[i]));
//            out.println();
//        }
//        possibleMedian(squares[6]);
        //logic
        int mid = (K*K)/2 + 1;
        int lo = 0;
        int hi = N*N-1;
        while (lo < hi){
            int med = (lo + hi)/2;
            if (lessThanX(squares[med]) < mid) hi = med;
            else lo = med + 1;
        }
        //turn in answer
        out.println(squares[lo].height);
        out.close();
    }
    public static int lessThanX(Square median){
        int[][] lessThan = new int[2*K][2*K];
        boolean[][] outOfBounds = new boolean[2*K][2*K];
        for (int r=1;r<=2*K-1;r++) {
            for (int c=1;c<=2*K-1;c++) {
                int squares_r = median.r-K+r;
                int squares_c = median.c-K+c;
                if (squares_r <= 0 || squares_r > N || squares_c <= 0 || squares_c > N) {
                    outOfBounds[r][c] = true;
                    continue;
                }
//                out.println("hello from: " + height[r][c]);
                lessThan[r][c] = lessThan[r-1][c] + lessThan[r][c-1] - lessThan[r-1][c-1] + (height[squares_r][squares_c] < median.height ? 1 : 0);
            }
        }
//        for (int i=0;i<2*K;i++) {
//            out.println(Arrays.toString(greaterThan[i]));
//        }
//        out.println();
//        for (int i=0;i<2*K;i++) {
//            out.println(Arrays.toString(lessThan[i]));
//        }
//        out.println();
//        for (int r=0;r<2*K;r++) {
//            for (int c=0;c<2*K;c++) {
//                out.print(outOfBounds[r][c] ? 1 : 0);
//            }
//            out.println();
//        }
//        out.println();
        int mostLess = 0;
        for (int r=1;r<=K;r++) {
            for (int c=1;c<=K;c++) {
                if (outOfBounds[r][c] || outOfBounds[r+K-1][c+K-1]) continue;
                int less = lessThan[r+K-1][c+K-1] - lessThan[r-1][c+K-1] - lessThan[r+K-1][c-1] + lessThan[r-1][c-1];
//                out.println(greater);
//                out.println(less);
//                out.println();
                mostLess = Math.max(mostLess, less);
//                    out.println(less);
//                    out.println(greater);
            }
        }
        return mostLess;
    }
    private static class Square {
        int r;
        int c;
        int height;
        public Square(int r1, int c1, int h1){
            r=r1;
            c=c1;
            height=h1;
        }
        public String toString(){
            return "["+r+", "+c+": "+height+"]";
        }
    }
}
