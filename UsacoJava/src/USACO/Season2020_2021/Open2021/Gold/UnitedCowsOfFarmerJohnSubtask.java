package USACO.Season2020_2021.Open2021.Gold;

import java.io.*;
import java.util.StringTokenizer;

public class UnitedCowsOfFarmerJohnSubtask {
    static boolean submission = false;
    static boolean debug = true;

    static int N;
    static int[] breed;
    public static void main(String[] args) throws IOException {
        setup("");
        N = Integer.parseInt(br.readLine());
        breed = new int[N+1];
        //N presums [breed][1...N]
        int[][] presum = new int[N+1][N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++){
            breed[i] = Integer.parseInt(st.nextToken());
            for (int j=1;j<=N;j++){
                presum[j][i]=presum[j][i-1];
                presum[breed[i]][i]++;
            }
        }
        int ans = 0;
        for (int i=1;i<=N;i++){
            for (int j=i+1;j<=N;j++){
                int ibreed=breed[i];
                int icnt=presum[ibreed][j]-presum[ibreed][i];
                int jbreed=breed[j];
                int jcnt=presum[jbreed][j-1]-presum[jbreed][i-1];
                if (icnt==0&&jcnt==0) ans++;
            }
        }

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
