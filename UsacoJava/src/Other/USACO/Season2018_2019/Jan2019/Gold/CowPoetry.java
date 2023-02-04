package Other.USACO.Season2018_2019.Jan2019.Gold;

import java.io.*;
import java.util.*;
/*
Unique Music
Gold Advanced B - DP
Midterm
basic dp stuff...
O(logN) exponentiation is crazy!!!
 */
public class CowPoetry {
    static boolean submission = true;
    static boolean debug = false;

    //param
    static int N;
    static int M;
    static int K;

    static int[] syllable;
    static int[] rhyme;
    static Multiset pattern;
    //dp
    static long[] dpSyllable;
    static long[] dpRhyme;

    //helper
    static final long MOD = (long)1e9+7;

    public static void main(String[] args) throws IOException {
        setup("poetry");//parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        syllable = new int[N];
        rhyme = new int[N];
        for (int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            syllable[i] = Integer.parseInt(st.nextToken());
            rhyme[i] = Integer.parseInt(st.nextToken());
        }
        pattern = new Multiset();
        for (int i=0;i<M;i++){
            pattern.add(br.readLine().charAt(0));
        }
        //logic
        //dpsyllable
        dpSyllable = new long[K+1];
        dpSyllable[0]=1;
        for (int i=0;i<=K;i++){
            for (int j=0;j< syllable.length;j++){
                int search = i+syllable[j];
                if (search <= K) dpSyllable[search]=(dpSyllable[search]+dpSyllable[i])%MOD;
            }
        }
        if (debug) System.out.println(Arrays.toString(dpSyllable));
        if (debug)System.out.println(pattern.ms);
        //dprhyme
        dpRhyme = new long[N+1];
        for (int i=0;i<rhyme.length;i++){
            dpRhyme[rhyme[i]]=(dpRhyme[rhyme[i]]+dpSyllable[K-syllable[i]])%MOD;
        }
        if (debug) System.out.println(Arrays.toString(dpRhyme));




        long ans = 1;
        //turn in answer
        for (Integer val : pattern.ms.values()) {
            ans=(ans*powerSum(val))%MOD;
        }
        out.println(ans);
        out.close();
    }
    private static long powerSum(int power){
        long sum = 0;
        for (long val : dpRhyme){
            sum = (sum + exp(val,power))%MOD;
        }
        return sum;
    }
    private static long exp(long base, int power){
        if (power==0) return 1;
        if (power==1) return base%MOD;
        long ans = exp(base,power/2);
        ans = (ans*ans)%MOD;
        if (power%2==1) ans=(ans*base)%MOD;
        return ans%MOD;
    }
    private static class Multiset {
        HashMap<Character, Integer> ms = new HashMap<>();
        public void add(Character c){
            if (!ms.containsKey(c)) ms.put(c,0);
            ms.put(c,ms.get(c)+1);
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
