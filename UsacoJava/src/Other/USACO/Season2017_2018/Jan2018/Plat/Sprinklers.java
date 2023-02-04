package Other.USACO.Season2017_2018.Jan2018.Plat;

import java.io.*;
import java.util.*;
/*
PROB: Sprinklers
LANG: JAVA
*/
public class Sprinklers {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static int[] A; //A[x]=y
    static long MOD = (long)(1e9+7);
    public static void main(String[] args) throws IOException {
        //*parse
        setup("sprinklers");
        N = Integer.parseInt(br.readLine());
        A = new int[N+1];
        for (int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())+1;
            int y = Integer.parseInt(st.nextToken())+1;
            A[x]=y;
        }
        //*pre
        //hi
        int[] hi = new int[N+1]; hi[N]=A[N];
        for (int x=N-1;x>=1;x--) hi[x]=Math.max(hi[x+1],A[x]);
        //lo
        int[] lo = new int[N+1]; lo[1]=A[1];
        for (int x=2;x<=N;x++) lo[x]=Math.min(lo[x-1],A[x]);
        //yb
        int[] yb = new int[N+1]; yb[N]=1;
        for (int y=N-1;y>=1;y--){
            yb[y]=yb[y+1];
            while (lo[yb[y]]>y)yb[y]++;
        }
        for (int y=1;y<=N;y++) yb[y]--;
        //ybsum
        long[] ybsum = new long[N+1];
        for (int y=1;y<=N;y++) ybsum[y]=(ybsum[y-1]+yb[y])%MOD;
        //ybsumsum
        long[] ybsumsum = new long[N+1];
        for (int y=1;y<=N;y++) ybsumsum[y]=(ybsumsum[y-1]+ybsum[y])%MOD;
        //dbg
        if (debug) {
            System.out.println("hi: "+Arrays.toString(hi));
            System.out.println("lo: "+Arrays.toString(lo));
            System.out.println("yb: "+Arrays.toString(yb));
            System.out.println("ybsum: "+Arrays.toString(ybsum));
            System.out.println("ybsumsum: "+Arrays.toString(ybsumsum));
        }
        //*counting
        long ans = 0;
        for (int x2=1;x2<=N;x2++){
            if (debug) System.out.println("x2: "+x2);
            //bulk add
            long add = ((long)(hi[x2]-1)*(hi[x2])/2)%MOD;
            add=(add*(x2-1))%MOD;
            if (debug) System.out.println("add: "+add);
            //bulk remove
            long bad1 = ((2L*hi[x2]-lo[x2])*(lo[x2]-1)/2)%MOD;
            bad1=(bad1*(x2-1))%MOD;
            if (debug) System.out.println("bad1: "+bad1);
            long bad2 = (ybsumsum[hi[x2]-1]-ybsumsum[lo[x2]-1]+MOD)%MOD;
            bad2=(bad2-(ybsum[lo[x2]-1]*(hi[x2]-lo[x2])%MOD)+MOD)%MOD;
            if (debug) System.out.println("bad2: "+bad2);
            add = (add-bad1-bad2+MOD)%MOD;
            ans=(ans+add)%MOD;
        }
        //ret
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