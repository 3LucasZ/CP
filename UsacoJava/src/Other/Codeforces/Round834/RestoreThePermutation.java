package Other.Codeforces.Round834;

import java.io.*;
import java.util.*;
/*
PROB: RestoreThePermutation
LANG: JAVA
*/
public class RestoreThePermutation {
    static boolean submission = false;
    static boolean debug = true;

    public static void main(String[] args) throws IOException {
        setup("RestoreThePermutation");
        //* TCS
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) solve();
        out.close();
    }

    static int N;
    static int[] B;
    static void solve() throws IOException {
        //* parse
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        B = new int[N/2];
        for (int i=0;i<N/2;i++) B[i]=Integer.parseInt(st.nextToken());
        //* unused numbers
        TreeSet<Integer> unused = new TreeSet<>();
        for (int i=1;i<=N;i++) unused.add(i); for (int i=0;i<N/2;i++) unused.remove(B[i]);
        //* case: too many unused numbers
        if (unused.size()!=N/2) {
            out.println(-1);
            return;
        }
        //* find partner for each B
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i=N/2-1;i>=0;i--){
            ans.add(B[i]);
            Integer partner = unused.lower(B[i]);
            //no partner -> N/A
            if (partner==null){
                out.println(-1);
                return;
            }
            //partner -> continue
            ans.add(partner);
            unused.remove(partner);
        }
        //* ret
        Collections.reverse(ans);
        for (int i=0;i<N;i++) out.print(ans.get(i)+" ");
        out.println();
    }
    private static class Element {
        int val;
        int pos;
        int pair;
        public Element(int p, int v){
            this.val=v;
            this.pos=p;
        }
    }
    private static class SegTree {
        //1-indexed
        //range is []
        int size;
        long[] tree;
        public SegTree(int n){
            size = 1;
            while (size < n) size *= 2;
            tree = new long[2*size+1];
        }
        long get(int k){
            long ret = 0;
            for (k+=size-1;k>=1;k/=2){
                ret+=tree[k];
            }
            return ret;
        }
        void add(int a, int b, int x) {
            a+=size-1;
            b+=size-1;
            while (a<=b){
                if (a%2==1) tree[a++]+=x;
                if (b%2==0) tree[b--]+=x;
                a/=2;
                b/=2;
            }
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