package Other.USACOGuide.UsacoGuideGold.DP;

import java.io.*;
import java.util.*;
/*
PROB: BookShop
LANG: JAVA
*/
public class BookShop {
    static boolean submission = false;
    static boolean debug = true;

    static int N;
    static int X;
    static int[] price;
    static int[] pages;

    public static void main(String[] args) throws IOException {
        //parse
        setup("BookShop");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        price = new int[N];
        pages = new int[N];
        for (int i=0;i<N;i++){
            price[i]=Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++){
            pages[i]=Integer.parseInt(st.nextToken());
        }

        //dp knapsack
        int[] dp = new int[X+1]; Arrays.fill(dp,-1); dp[0]=0;
        for (int b=0;b<N;b++){
            for (int sz=X;sz>=0;sz--){
                if (dp[sz]==-1) continue;
                int next = price[b]+sz;
                if (next<=X){
                    dp[next]=Math.max(dp[next],dp[sz]+pages[b]);
                }
            }
        }

        //ret
        int ans = 0;
        for (int sz=0;sz<=X;sz++) ans=Math.max(ans,dp[sz]);
        out.println(ans);
        out.close();
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