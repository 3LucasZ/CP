import java.io.*;
import java.util.ArrayList;

public class PairProgramming {
    static boolean submission = false;
    static boolean debug = true;

    static int T;
    static int N;
    static long MOD = (long)1e9+7;
    public static void main(String[] args) throws IOException {
        //handle TCS
        setup("");
        T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) solve();
        out.close();
    }
    public static void solve() throws IOException{
        //parse
        N = Integer.parseInt(br.readLine());
        ArrayList<Boolean> Btype = new ArrayList<>();
        ArrayList<Boolean> Etype = new ArrayList<>();
        String str;

        str = br.readLine();
        for (int i=0;i<N;i++){
            if (str.charAt(i)=='0') Btype = new ArrayList<>();
            else if (str.charAt(i)=='1') ;
            else Btype.add(str.charAt(i)=='+'?true:false);
        }
        int B = Btype.size();

        str = br.readLine();
        for (int i=0;i<N;i++){
            if (str.charAt(i)=='0') Etype = new ArrayList<>();
            else if (str.charAt(i)=='1') ;
            else Etype.add(str.charAt(i)=='+'?true:false);
        }
        int E = Etype.size();

        //grid DP
        //dp[Bi+1][Ei+1][last: B/E]
        int[][][] dp = new int[B+1][E+1][2];
        dp[0][0][0]=1;
        dp[0][0][1]=1;
        for (int b=0;b<B;b++){
            for (int e=0;e<E;e++){
                dp[b+1][e][0] += dp[b][e][0];
                if (Btype.get(b)!=Etype.get(e+1))dp[b][e+1][1] += dp[b][e][0];

                dp[b][e+1][1] += dp[b][e][1];
                if (Btype.get(b+1)!=Etype.get(e))dp[b+1][e][0] += dp[b][e][1];
            }
        }
        out.println(dp[B][E][0]+dp[B][E][1]);
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
