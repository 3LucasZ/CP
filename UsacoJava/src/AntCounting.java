import java.io.*;
import java.util.StringTokenizer;

public class AntCounting {
    //io
    static boolean submission = false;
    static boolean debug = true;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int F;
    static int A;
    static int S;
    static int B;
    static int famSize[];
    static long dp[][];

    //helper
    static long MOD=(long)1e6;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader(".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        F = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        famSize = new int[F+1];
        for (int i=0;i<A;i++){
            int fam = Integer.parseInt(br.readLine());
            famSize[fam]++;
        }
        //logic: dp[last fam][set size]
        dp = new long[F+1][B+1];
        for (int i=1;i<=F;i++){
            //for (int j=)
        }

        //turn in answer
        out.println();
        out.close();
    }
}
