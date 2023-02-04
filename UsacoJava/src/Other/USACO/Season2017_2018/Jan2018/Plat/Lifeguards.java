package Other.USACO.Season2017_2018.Jan2018.Plat;

import java.io.*;
import java.util.*;
/*
PROB: Lifeguards
LANG: JAVA
*/
public class Lifeguards {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static int K;
    static ArrayList<Seg> segs = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        //parse
        setup("lifeguards");
        StringTokenizer st= new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Seg[] A = new Seg[N];
        for (int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            A[i]=new Seg(l,r);
        }
        //sort by increasing l
        Arrays.sort(A,(a,b)->a.l-b.l);
        //only include segs not superseded
        segs.add(new Seg(-1,-1));
        for (int i=0;i<N;i++){
            Seg top = segs.get(segs.size()-1);
            if (A[i].r>top.r) segs.add(A[i]);
        }
        //update N,K
        int lost = N-segs.size()+1;
        N=segs.size()-1;
        K-=lost;
        if (debug){
            System.out.println(segs);
            System.out.println("N:"+N+", K:"+K);
        }
        //case: # removed initially already greater than K
        if (K<0)K=0;
        //left right dp sweep with 2 pointers
        int[][] dp1 = new int[N+1][K+1]; //1..i, keep i and r removed
        int[][] dp2 = new int[N+1][K+1]; //1..i, r removed
        int j=0;
        for (int i=1;i<=N;i++){
            //find j
            while (segs.get(j+1).r<segs.get(i).l) j++;
            for (int r=0;r<=Math.min(i-1,K);r++){
                //case 1: try prev=0..j
                if (r-(i-j-1)>=0) dp1[i][r]=Math.max(dp1[i][r],dp2[j][r-(i-j-1)]+segs.get(i).len());
                //case 2: try prev=j+1
                if (j+1!=i && r-(i-j-2)>=0) dp1[i][r]=Math.max(dp1[i][r],dp1[j+1][r-(i-j-2)]+segs.get(i).r-segs.get(j+1).r);
                //update dp2
                if (r==0) dp2[i][r]=dp1[i][r];
                else dp2[i][r]=Math.max(dp2[i-1][r-1],dp1[i][r]);
                //if (debug) System.out.println("i="+i+", j="+j+", r="+r+", dp1[i][r]="+dp1[i][r]);
            }
        }
        //ret
        out.println(dp2[N][K]);
        out.close();
    }
    private static class Seg {
        int l;
        int r;
        public Seg (int l, int r){
            this.l=l;
            this.r=r;
        }
        public String toString(){
            return "["+l+", "+r+"]";
        }
        public int len(){
            return r-l;
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