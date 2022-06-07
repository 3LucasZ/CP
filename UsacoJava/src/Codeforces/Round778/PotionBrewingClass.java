package Codeforces.Round778;

import java.io.*;
import java.util.*;

public class PotionBrewingClass {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;

    //glob input
    static int N;
    static ArrayList<Edge>[] tree;
    static int[][] pfNet;
    static ArrayList<Integer> primes;
    static int P;

    //helper
    static long MOD = 998244353;
    static int MAXPRIME = 1000;

    //primes
    static int baseP;
    static ArrayList<Integer> basePrimes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        //primes
        for (int i=2;i<=MAXPRIME;i++){
            boolean add = true;
            for (int prime : basePrimes){
                if (prime * prime > i) break;
                if (i%prime==0) add=false;
            }
            if (add) basePrimes.add(i);
        }
        baseP = basePrimes.size();
        if (debug) {
            System.out.println(basePrimes);
            System.out.println(baseP);
        }

        //tcs
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) out.println(solve());
        out.close();
    }

    public static long solve() throws IOException{
        //parse tree + new primes
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N+1]; for (int i=1;i<=N;i++) tree[i] = new ArrayList<>();
        primes = (ArrayList<Integer>) basePrimes.clone();
        for (int i=0;i<N-1;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int uj = Integer.parseInt(st.nextToken());
            int vj = Integer.parseInt(st.nextToken());
            if (isPrime(uj)) primes.add(uj);
            if (isPrime(vj)) primes.add(vj);
            tree[u].add(new Edge(v, vj,uj));
            tree[v].add(new Edge(u, uj,vj));
        }
        if (debug) for (int i=1;i<=N;i++) System.out.println(tree[i]);
        P=primes.size();
        //finesse
        pfNet = new int[N+1][P];
        DFS(new Edge(1, 1, 1), 0);

        if (debug && false) {
            for (int i=1;i<=N;i++) System.out.println(Arrays.toString(pfNet[i]));
        }

        int[] lcm = new int[P];
        for (int i=0;i<P;i++){
            for (int n=1;n<=N;n++){
                lcm[i]=Math.max(lcm[i],-pfNet[n][i]);
            }
        }
        if (debug) System.out.println("LCM: "+Arrays.toString(lcm));

        long ans = 0;
        for (int n=1;n<=N;n++){
            for (int i=0;i<P;i++){
                pfNet[n][i]+=lcm[i];
            }
            ans = (ans + sum(pfNet[n]))%MOD;
        }

        if (debug) {
            for (int i=1;i<=N;i++) System.out.println(Arrays.toString(pfNet[i]));
        }

        if (debug) {
            System.out.println(ans);
        }

        return ans;
    }
    public static void DFS(Edge node, int par){
        pfNet[node.v]=pfGen(node.f, pfNet[par]);

        for (Edge child : tree[node.v]){
            if (child.v==par) continue;
            DFS(child, node.v);
        }
    }

    public static int[] pfGen(Fraction f, int[] prev){
        int[] ret = prev.clone();

        for (int pi=0;pi<P&&primes.get(pi)<=f.num;pi++){
            while (f.num%primes.get(pi)==0){
                ret[pi]++;
                f.num/=primes.get(pi);
            }
        }

        for (int pi=0;pi<P&&primes.get(pi)<=f.denom;pi++){
            while (f.denom%primes.get(pi)==0){
                ret[pi]--;
                f.denom/=primes.get(pi);
            }
        }

        return ret;
    }

    private static class Edge {
        int v;
        Fraction f;
        public Edge(int v, long n, long d){
            this.v=v;
            this.f=new Fraction(n,d);
        }
        public String toString(){
            return "["+v+": "+f+"]";
        }
    }

    private static class Fraction {
        long denom;
        long num;
        public Fraction(long n, long d){
            denom=d;
            num=n;
        }
        public String toString(){
            return num+"/"+denom;
        }
    }

    public static long sum(int[] pf){
        long sum = 1;
        for (int i=0;i<P;i++){
            for (int j=0;j<pf[i];j++){
                sum = (sum * primes.get(i))%MOD;
            }
        }
        return sum;
    }

    public static boolean isPrime(int num){
        for (int i=0;i<baseP&&basePrimes.get(i)*basePrimes.get(i)<=num;i++) if (num%basePrimes.get(i)==0) return false;
        return true;
    }
}