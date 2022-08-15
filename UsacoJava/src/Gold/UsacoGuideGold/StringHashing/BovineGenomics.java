package Gold.UsacoGuideGold.StringHashing;

import java.io.*;
import java.util.*;
/*
PROB: BovineGenomics
LANG: JAVA
*/
public class BovineGenomics {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static int M;

    static Hasher[][] hashers;
    public static void main(String[] args) throws IOException {
        //parse
        setup("cownomics");
        setup();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        hashers = new Hasher[2][N];
        //hash spotted
        for (int i=0;i<N;i++) hashers[0][i]=new Hasher(br.readLine(),A,B);
        //hash plain
        for (int i=0;i<N;i++) hashers[1][i]=new Hasher(br.readLine(),A,B);

        //binary search on len
        int lo=1;int hi=M;
        while (lo<hi){
            int mid = (lo+hi)/2;
            if (works(mid)) hi=mid;
            else lo=mid+1;
        }
        out.println(lo);
        out.close();
    }
    public static boolean works(int len){
        //complete search hashing
        for (int i=0;i<M;i++){
            int j=i+len-1;
            if (j>=M) return false;
            HashSet<Integer> plain = new HashSet<>();
            for (int c=0;c<N;c++) plain.add(hashers[1][c].getHash(i,j));
            boolean good = true;
            for (int c=0;c<N;c++) if (plain.contains(hashers[0][c].getHash(i,j))) {
                good=false;
                break;
            }
            if (good) {
                if (debug) System.out.println("i:"+i+", j: "+j);
                return true;
            }
        }
        return false;
    }
    //hash helpers
    static long A = (long)2e9+11;
    static long B = (long)2e9+33;
    static long[] pow;
    public static void setup(){
        pow = new long[1000];
        pow[0]=1;
        for (int i=1;i<1000;i++){
            pow[i]=(pow[i-1]*A)%B;
        }
    }
    private static class Hasher {
        long A;
        long B;
        long[] preHash;
        public Hasher(String str, long A, long B){
            this.A=A;
            this.B=B;
            preHash = new long[str.length()];
            preHash[0]=str.charAt(0);
            for (int i=1;i<str.length();i++){
                preHash[i]=(preHash[i-1]*A+str.charAt(i))%B;
            }
        }
        public int getHash(int l, int r){
            if (l==0) return (int)preHash[r];
            return (int)((((preHash[r]-preHash[l-1]*pow[r-l+1])%B)+B)%B);
        }
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