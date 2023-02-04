package Other.USACO.SeasonOld.March2014.Gold;

import java.io.*;
import java.util.*;
/*
PROB: CountingFriends
LANG: JAVA
*/
public class CountingFriends {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static int[] C;

    public static void main(String[] args) throws IOException {
        //parse
        setup("fcount");
        N = Integer.parseInt(br.readLine());
        C = new int[N+1];
        for (int i=0;i<N+1;i++) C[i]=Integer.parseInt(br.readLine());
        if (debug) System.out.println(Arrays.toString(C));

        //try to remove each number
        ArrayList<Integer> ans = new ArrayList<>();
        int[] freq = new int[N+1];
        for (int i=0;i<=N;i++)freq[C[i]]++;
        for (int i=0;i<=N;i++){
            freq[C[i]]--;
            if (debug) System.out.println("Trying: "+Arrays.toString(freq));
            if (trySet(freq.clone())) ans.add(i+1);
            freq[C[i]]++;
        }

        //ret
        out.println(ans.size());
        for (int i : ans) out.println(i);
        out.close();
    }
    public static boolean trySet(int[] set){
        //simulate
        for (int next=N;next>=1;next--){
            while (set[next]!=0){
                set[next]--;
                int[] nextSet = set.clone();
                int took = 0;
                for (int take=N;take>=1;take--){
                    while (set[take]!=0 && took!=next){
                        set[take]--;
                        nextSet[take]--;
                        nextSet[take-1]++;
                        took++;
                    }
                }
                if (took!=next) return false;
                set = nextSet;
                if (debug)System.out.println(Arrays.toString(set));
            }
        }
        return true;
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