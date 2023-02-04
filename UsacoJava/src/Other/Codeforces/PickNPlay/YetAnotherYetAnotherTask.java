package Other.Codeforces.PickNPlay;

import java.io.*;
import java.util.*;
/*
PROB: YetAnotherYetAnotherTask
LANG: JAVA
*/
public class YetAnotherYetAnotherTask {
    static boolean submission = false;
    static boolean debug = false;

    static int N;
    static int[] A;
    public static void main(String[] args) throws IOException {
        //*parse
        setup("YetAnotherYetAnotherTask");
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++)A[i]=Integer.parseInt(st.nextToken());
        //*dp
        //init: dp[i][mx run ai] = mx run sum
        int[][] dp = new int[N][61];
        for (int i=0;i<N;i++) dp[i][30+A[i]]=A[i];
        //transitions
        for (int i=0;i<N-1;i++){
            for (int mx=0;mx<=60;mx++){
                if (dp[i][mx]==0) continue;
                if (mx-30 >= A[i+1]) dp[i+1][mx]=Math.max(dp[i+1][mx],dp[i][mx]+A[i+1]);
                else dp[i+1][A[i+1]+30]=Math.max(dp[i+1][A[i+1]+30],dp[i][mx]+A[i+1]);
            }
        }
        if (debug) print(dp);
        //ret
        int ans = 0;
        for (int i=0;i<N;i++) {
            for (int mx=0;mx<=60;mx++){
                if (dp[i][mx]==0) continue;
                ans=Math.max(ans,dp[i][mx]-mx+30);
            }
        }
        out.println(ans);
        out.close();
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
    public static void setup(String fileName) throws IOException {
        if (submission) {
            br = new BufferedReader(new FileReader(fileName+".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
    }
}