import java.io.*;
import java.util.*;

public class TheLazyCow {
    //io
    static boolean debug = true;
    static boolean submission = false;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int K;
    static int[][] field;
    static int[][] presum;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader(".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(".out")));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        field = new int[2*N-1][2*N-1];
        for (int r=0;r<N;r++){
            st = new StringTokenizer(br.readLine());
            for (int c=0;c<N;c++){
                field[r-c+N-1][r+c]=Integer.parseInt(st.nextToken());
            }
        }
        if (debug){
            for (int r=0;r<field.length;r++){
                for (int c=0;c<field.length;c++){
                    System.out.print(field[r][c]+" ");
                    if (field[r][c]<=9) System.out.print(" ");
                }
                System.out.println();
            }
            System.out.println();
        }
        //logic
        presum = new int[2*N][2*N];
        for (int r=1;r<2*N;r++){
            for (int c=1;c<2*N;c++){
                presum[r][c] = field[r-1][c-1]+presum[r-1][c]+presum[r][c-1]-presum[r-1][c-1];
            }
        }
        if (debug){
            for (int r=1;r<2*N;r++){
                for (int c=1;c<2*N;c++){
                    System.out.print(presum[r][c]+" ");
                    if (presum[r][c]<=9) System.out.print(" ");
                    if (presum[r][c]<=99) System.out.print(" ");
                }
                System.out.println();
            }
        }
        //turn in answer
        int MAX = 0;
        for (int r=1;r<=2*N-2*K-1;r++){
            for (int c=1;c<=2*N-2*K-1;c++){
                int value = sum(r,c,r+2*K, c+2*K);
                MAX = Math.max(MAX, value);
            }
        }
        if (K>N) MAX = sum(1,1,2*N-1,2*N-1);
        out.println(MAX);
        out.close();
    }
    private static int sum(int r1, int c1, int r2, int c2){
        return presum[r2][c2]-presum[r2][c1-1]-presum[r1-1][c2]+presum[r1-1][c1-1];
    }
}