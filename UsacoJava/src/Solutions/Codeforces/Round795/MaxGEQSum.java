package Solutions.Codeforces.Round795;

import java.io.*;
import java.util.*;

public class MaxGEQSum {
    static boolean submission = false;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        setup("");
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++){
            out.println(solve()?"YES":"NO");
        }
        out.close();
    }
    public static boolean solve() throws IOException{
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        //left max of i
        int[] pi = new int[N+1];
        Arrays.fill(pi,0);

        //right max of i
        int[] ai = new int[N+1];
        Arrays.fill(ai,N+1);

        //arr
        int[] A = new int[N+1];
        for (int i=1;i<=N;i++) A[i]=Integer.parseInt(st.nextToken());

        //fill pi ai
        Stack<Integer> stack = new Stack();
        for (int i=1;i<=N;i++){
            while(!stack.empty() && A[stack.peek()] < A[i]){
                int poll = stack.pop();
                ai[poll]=i;
            }
            if (!stack.empty()) {
                if (A[i]==A[stack.peek()]){
                    pi[i]=pi[stack.peek()];
                }
                else {
                    pi[i]=stack.peek();
                }
            }
            stack.add(i);
        }
        if (debug) for (int i=1;i<=N;i++) System.out.println(i+": ["+pi[i]+", "+ai[i]+"]");

        long prefixes[] = new long[N+2];
        for (int i=1;i<=N;i++) prefixes[i] = prefixes[i-1]+A[i];
        SegTree maxPrefix = new SegTree(N, prefixes);

        long suffixes[] = new long[N+2];
        for (int i=N;i>=1;i--) suffixes[i] = suffixes[i+1]+A[i];
        SegTree maxSuffix = new SegTree(N, suffixes);

        if (debug) for (int i=1;i<=N;i++) System.out.println(maxPrefix.get(1,i));
        if (debug) for (int i=1;i<=N;i++) System.out.println(maxSuffix.get(i,N));

        for (int i=1;i<=N;i++){
            long l = maxSuffix.get(pi[i]+1,i) - suffixes[i];
            long r = maxPrefix.get(i, ai[i]-1) - prefixes[i];
            if (l > 0 || r > 0) return false;
        }
        return true;
    }
    private static class SegTree {
        //1-indexed
        //get is inclusive
        int size;
        long[] tree;
        public SegTree(int n, long[] arr){
            size = 1;
            while (size < n) size *= 2;
            tree = new long[2*size+1];

            for (int i=1;i<=n;i++){
                tree[i+size-1]=arr[i];
            }
            for (int i=size-1;i>=1;i--){
                tree[i]=Math.max(tree[i*2],tree[i*2+1]);
            }
        }
        long get(int a, int b) {
            a+=size-1;
            b+=size-1;
            long ret = Long.MIN_VALUE;
            while (a<=b){
                if (a%2==1) ret=Math.max(ret,tree[a++]);
                if (b%2==0) ret=Math.max(ret,tree[b--]);
                a/=2;
                b/=2;
            }
            return ret;
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
/*
1
8
7 9 6 2 3 3 5 7
 */