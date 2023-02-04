package Other.USACO.SeasonOld.Dec2014.Gold;

import java.io.*;
import java.util.*;
/*
PROB: CowJog
LANG: JAVA
*/
public class CowJog {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static long[] A;

    public static void main(String[] args) throws IOException {
        //parse
        setup("cowjog");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        long T = Integer.parseInt(st.nextToken());
        A = new long[N];
        for (int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            A[i]=T*s+p;
        }
        if (debug){
            System.out.println("A: "+Arrays.toString(A));
        }
        //min LIS greedy
        Multiset seqs = new Multiset();
        for (int i=0;i<N;i++){
            seqs.add(A[i]);
            Long l = seqs.ms.lowerKey(A[i]);
            if (l!=null) seqs.rem(l);
        }
        //ret
        int ans = 0;
        for (int i : seqs.ms.values()) ans+=i;
        out.println(ans);
        out.close();
    }
    private static class Multiset {
        TreeMap<Long, Integer> ms = new TreeMap<>();
        public void add(long x){
            if (!ms.containsKey(x))ms.put(x,0);
            ms.put(x,ms.get(x)+1);
        }
        public void rem(long x){
            ms.put(x,ms.get(x)-1);
            if (ms.get(x)==0) ms.remove(x);
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