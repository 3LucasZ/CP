package Solutions.TrainingGateway.Chapter3;

import java.io.*;
import java.util.StringTokenizer;
/*
PROB: game1
LANG: JAVA
 */
public class game1 {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static int[] points;

    public static void main(String[] args) throws IOException {
        //parse + points presum
        setup("game1");
        N = Integer.parseInt(br.readLine());
        points = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++){
            if (!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            points[i]=points[i-1]+Integer.parseInt(st.nextToken());
        }

        //dp for loop
        int[][] ans = new int[N+1][N+1];
        for (int l=N;l>=1;l--){
            ans[l][l]=points(l,l);
            for (int r=l+1;r<=N;r++){
                ans[l][r]=points(l,r)-Math.min(ans[l+1][r],ans[l][r-1]);
            }
        }

        //ret
        out.println(ans[1][N]+" "+(points(1,N)-ans[1][N]));
        out.close();
    }
    public static int points(int l,int r){
        return points[r]-points[l-1];
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
