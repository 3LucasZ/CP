package USACO.Platinum.EC.STR7;

import java.io.*;
import java.util.*;

public class Censoring {
    static boolean submission = false;
    static boolean debug = false;

    static String S;
    static int N;

    static String T;
    static int M;

    //HASHING
    static long A = 1610612741;
    static long[] pow;
    static long B = (int)1e9+9;

    public static void main(String[] args) throws IOException {
        //parse
        setup("");
        S = br.readLine();
        N = S.length();
        T = br.readLine();
        M = T.length();

        //setup
        pow = new long[N];
        pow[0]=1; for (int i=1;i<N;i++) pow[i]=(pow[i-1]*A)%B;
        long searchHash = 0;
        for (int i=0;i<M;i++) searchHash = (searchHash + T.charAt(i)*pow[M-1-i])%B;
        if (debug) out.println("search hash: "+searchHash);

        //running hash function
        Stack<Character> ans = new Stack<>();
        long[] h = new long[N+1]; h[0]=0;

        //i is the index of S we are on
        for (int i=0;i<N;i++){
            ans.add(S.charAt(i));
            h[ans.size()] = (h[ans.size()-1]*A + S.charAt(i))%B;

            //find hash of tail
            int b = ans.size();
            int a = b-M+1;
            long tailHash = h[b];
            if (a>0) tailHash = ((tailHash-h[a-1]*pow[b-a+1])%B+B)%B;
            if (a<0) continue;
            if (debug) out.println("tail hash for "+(ans.size()-1)+": "+tailHash);

            //tail of ans is same as search so remove tail
            if (tailHash==searchHash)for (int j=0;j<M;j++)ans.pop();
        }
        for (char c : ans)out.print(c);
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
