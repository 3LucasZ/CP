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
        ArrayList<Boolean> Btype = new ArrayList<>(); Btype.add(true);
        ArrayList<Boolean> Etype = new ArrayList<>(); Etype.add(true);
        String str;

        str = br.readLine();
        for (int i=0;i<N;i++){
            if (str.charAt(i)=='0') {Btype = new ArrayList<>();Btype.add(true);Btype.add(false);}
            else if (str.charAt(i)=='1') ;
            else Btype.add(str.charAt(i)=='+'?true:false);
        }
        int B = Btype.size()-1;

        str = br.readLine();
        for (int i=0;i<N;i++){
            if (str.charAt(i)=='0') {Etype = new ArrayList<>();Etype.add(true);Etype.add(false);}
            else if (str.charAt(i)=='1') ;
            else Etype.add(str.charAt(i)=='+'?true:false);
        }
        int E = Etype.size()-1;

        //check
        if (debug){
            out.println("Bessie: "+Btype);
            out.println("Elseie: "+Etype);
        }
        if (B==0 || E==0) {out.println(1);return;}

        //grid DP
        //dp[Bi+1][Ei+1][last: B/E]
        int[][][] dp = new int[B+1][E+1][2];
        dp[0][1][1]=1;
        dp[1][0][0]=1;
        for (int b=0;b<B;b++){
            for (int e=0;e<E;e++){
                dp[b+1][e][0] += dp[b][e][0];
                if (e==0 || Btype.get(b+1)!=Etype.get(e))dp[b+1][e][0] += dp[b][e][1];

                dp[b][e+1][1] += dp[b][e][1];
                if (b==0 || Btype.get(b)!=Etype.get(e+1))dp[b][e+1][1] += dp[b][e][0];
            }
        }

        //check n ret
        if (debug){
            for (int b=0;b<=B;b++) for (int e=0;e<=E;e++) for (int i=0;i<2;i++) out.println("b: "+b+", e: "+e+", i: "+i+" = "+dp[b][e][i]);
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
