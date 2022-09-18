import java.io.*;
import java.util.*;

public class Sabotage {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static long[] M;
    static long tot;

    static long MAXM = 10000*1000;

    public static void main(String[] args) throws IOException {
        //parse
        setup("sabotage");
        N = Integer.parseInt(br.readLine());
        M = new long[N];
        for (int i=0;i<N;i++) {
            M[i]=Integer.parseInt(br.readLine())*(long)1000;
            tot+=M[i];
        }
        if (debug) System.out.println(Arrays.toString(M));

        //bin search
        long lo = 0; long hi = MAXM;
        while (lo<hi){
            long mid = (lo+hi)/2;
            if (debug) System.out.println("Trying: "+mid);
            if (tryTar(mid)) hi=mid;
            else lo=mid+1;
        }
        out.println((double)lo/1000);
        out.close();
    }
    public static boolean tryTar(long tar){
        long best = 0;
        long cur = 0;
        for (int i=1;i<=N-2;i++){
            cur+=M[i]-tar;
            if (cur <= 0) cur = 0;
            best=Math.max(best,cur);
        }
        return tot-N*tar-best<=0;
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
