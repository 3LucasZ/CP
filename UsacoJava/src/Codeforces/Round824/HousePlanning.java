package Codeforces.Round824;

import java.io.*;
import java.util.*;

/*
PROB: HousePlanning
LANG: JAVA
*/
public class HousePlanning {
    static boolean submission = false;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        //* handle TCS
        setup("HousePlanning");
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) solve(i);
        out.close();
    }

    static int N;
    static int[] d1;
    static int[] d2;

    static Multiset Kmultiset1 = new Multiset();
    static Multiset Kmultiset2 = new Multiset();

    static boolean good = false;
    static int MID = (int)1e9;

    public static void solve(int tcs) throws IOException {
        if (debug) System.out.println("Case: " + tcs);
        good=false;
        //* parse
        N = Integer.parseInt(br.readLine());
        d1 = new int[N];
        d2 = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) d1[i]=Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) d2[i]=Integer.parseInt(st.nextToken());
        //* setup multisets
        Kmultiset1 = new Multiset();
        Kmultiset2 = new Multiset();
        for (int i=0;i<N;i++) Kmultiset1.add(d1[i]);
        for (int i=0;i<N;i++) Kmultiset2.add(d2[i]);
        //* bash solve |p1-p2|=k
        for (int i=0;i<N;i++){
            int l1 = d1[0]+d2[i];
            if (tryL(l1)) break;
            int l2=Math.abs(d1[0]-d2[i]);
            if (tryL(l2)) break;
        }
        //* end
        if (!good) out.println("NO");
    }
    public static boolean tryL(int L){
        if (debug) System.out.println("Trying: L="+L);
        //* setup
        int p1 = MID-L/2;
        int p2 = MID+L-L/2;
        ArrayList<Integer> h = new ArrayList<>();
        Multiset multiset1 = Kmultiset1.clone();
        Multiset multiset2 = Kmultiset2.clone();
        if (debug){
            System.out.println("init ms1: "+multiset1.ms.toString());
            System.out.println("init ms2: "+multiset2.ms.toString());
        }
        //* graph match ms1 w/ ms2
        //case1: d1,d2 contains > L
        while (!multiset1.ms.isEmpty()){
            int next1 = multiset1.ms.lastKey();
            int next2 = multiset2.ms.lastKey();
            if (Math.max(next1,next2)<L) break;
            //case a: d1 has greatest
            if (next1>next2){
                int complement = next1-L;
                if (!multiset2.ms.containsKey(complement)) return false;
                multiset1.rem(next1);
                multiset2.rem(complement);
                h.add(p1+next1);
            }
            //case b: d2 has greatest
            else {
                int complement = next2-L;
                if (!multiset1.ms.containsKey(complement)) return false;
                multiset2.rem(next2);
                multiset1.rem(complement);
                h.add(p2-next2);
            }
        }
        //case2: intermediary points
        while (!multiset1.ms.isEmpty()){
            int next = multiset1.ms.lastKey();
            int complement = L-next;
            if (!multiset2.ms.containsKey(complement)) return false;
            multiset1.rem(next);
            multiset2.rem(complement);
            h.add(p1+next);
        }
        //* ret found sol
        if (debug){
            System.out.println("final ms1: "+multiset1.ms.toString());
            System.out.println("final ms2: "+multiset2.ms.toString());
        }
        good=true;
        out.println("YES");
        for (int i : h) out.print(i+" ");
        out.println();
        out.print(p1+" "+p2);
        out.println();
        return true;
    }
    private static class Multiset {
        TreeMap<Integer, Integer> ms = new TreeMap<>();
        public void add(int x){
            if (!ms.containsKey(x))ms.put(x,0);
            ms.put(x,ms.get(x)+1);
        }
        public void rem(int x){
            ms.put(x,ms.get(x)-1);
            if (ms.get(x)==0) ms.remove(x);
        }
        public Multiset clone() {
            Multiset ret = new Multiset();
            ret.ms.putAll(ms);
            return ret;
        }
    }

    static BufferedReader br;
    static PrintWriter out;

    public static void setup(String fileName) throws IOException {
        if (submission) {
            br = new BufferedReader(new FileReader(fileName + ".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
    }
}
/*
1
10
0 0 1 1 2 2 3 4 4 4
0 1 1 2 3 3 3 3 4 5
 */