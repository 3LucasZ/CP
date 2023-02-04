package Other.Q;

import java.io.*;
import java.util.*;

public class Weightlifting {
    static boolean submission = false;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        //* handle TCS
        setup();
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) solve(i);
        out.close();
    }

    static int E;
    static int W;
    static int[][] amt;

    public static void solve(int tcs) throws IOException {
        System.out.print("Case #" + tcs + ": ");
        if (debug) System.out.println("");
        //* parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        E = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        amt = new int[E][W];
        for (int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<W;j++){
                amt[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        //* precomp intersection size
        int[][] intersection = new int[E][E];
        for (int l=0;l<E;l++){
            int[] run = new int[W]; for (int i=0;i<W;i++) run[i]=100;
            for (int r=l;r<E;r++){
                for (int i=0;i<W;i++) {
                    run[i]=Math.min(run[i],amt[r][i]);
                    intersection[l][r]+=run[i];
                }
            }
        }
        //if (debug) print(intersection);

        //* dp
        int[][] dp = new int[E][E]; for (int i=0;i<E;i++) for (int j=0;j<E;j++) dp[i][j]=Integer.MAX_VALUE/10;
        for (int l=E-1;l>=0;l--){
            for (int r=l;r<E;r++){
                if (l==r) {
                    dp[l][r]=0;
                    continue;
                }
                for (int x=l;x<=r-1;x++){
                    dp[l][r]=Math.min(dp[l][r],dp[l][x]+dp[x+1][r]+2*(intersection[l][x]+intersection[x+1][r]-2*intersection[l][r]));
                }
            }
        }

        //* ret
        System.out.println(dp[0][E-1]+2*intersection[0][E-1]);
    }

    public static void print(int[][] arr){
        for (int r=0;r<arr.length;r++){
            for (int c=0;c<arr[r].length;c++){
                String str = ""+arr[r][c];
                while (str.length()<5) str+=" ";
                System.out.print(str);
            }
            System.out.println();
        }
        System.out.println();
    }

    static BufferedReader br;
    static PrintWriter out;

    public static void setup() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
}