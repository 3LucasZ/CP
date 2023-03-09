package Solutions.USACO.Season2018_2019.Dec2018.Gold;

import java.io.*;
import java.util.*;
/*
USACO 2018 December Contest, Gold
Problem 2. Cowpatibility
Thoughts:
CRAZE counting problem - complementary counting, PIE (Math)
very very nice
let cowpatible = N(N-1)/2
subtract all cowpatible cows
PIE = + {1} - {2} + {3} - {4} + {5}
where {N} denotes a set size N and all pairs of cows that match the set
clever set gen by sort, hashMap, 1..31 loop

TLE on TCS 9 & 10, no idea how to fix, this is already linear on N...
 */
public class Cowpatibility {
    static boolean submission = true;
    static boolean debug = false;

    static long N;
    static HashMap<ArrayList<Integer>, Integer> count = new HashMap<>();

    static long[] PIE = {0,1,-1,1,-1,1};
    public static void main(String[] args) throws IOException {
        setup("cowpatibility");
        N = Long.parseLong(br.readLine());
        for (int i=0;i<N;i++){
            int[] cow = new int[5];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0;j<5;j++){
                cow[j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(cow);
            gen(cow);
        }
        long ans = N*(N-1)/2;
        for (Map.Entry<ArrayList<Integer>,Integer> subset : count.entrySet()){
            ans -= PIE[subset.getKey().size()]*(subset.getValue()-1)*subset.getValue()/2;
        }
        out.println(ans);
        out.close();
    }
    public static void gen(int[] arr){
        for (int i=1;i<=31;i++){
            ArrayList<Integer> subset = new ArrayList<>();
            int k=i;
            for (int j=0;j<5;j++){
                if ((k&1)==1)subset.add(arr[j]);
                k=k>>1;
            }
            Integer curr = count.get(subset);
            if (curr==null)count.put(subset,1);
            else count.put(subset,curr+1);
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
