package Other.Codeforces.Round824;

import java.io.*;
import java.util.*;
/*
PROB: MetaSet
LANG: JAVA
*/
public class MetaSet {
    static boolean submission = false;
    static boolean debug = true;

    static int N;
    static int K;
    public static void main(String[] args) throws IOException {
        //* parse
        setup("MetaSet");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int[][] card = new int[N][K]; //get: nth card, kth feature
        for (int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<K;j++){
                card[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        //* bash every pair, find the complement, store freq of complement in a table
        HashMap<Long, Integer> map = new HashMap<>();
        for (int i=0;i<N;i++){
            for (int j=i+1;j<N;j++){
                int[] complement = new int[K];
                for (int k=0;k<K;k++){
                    if (card[i][k]==card[j][k]){
                        complement[k]=card[i][k];
                    } else {
                        complement[k]=3-card[i][k]-card[j][k];
                    }
                }
                long pack = packCard(complement);
                map.putIfAbsent(pack, 0);
                map.put(pack,map.get(pack)+1);
            }
        }
        //* for every card, see how many meta-sets if it was a central card
        int ans = 0;
        for (int i=0;i<N;i++){
            long pack = packCard(card[i]);
            if (map.containsKey(pack)) {
                int freq = map.get(pack);
                ans+=freq*(freq-1)/2;
            }
        }
        //ret
        out.println(ans);
        out.close();
    }
    static long packCard(int[] card){
        long ret = 0;
        long pow = 1;
        for (int i=0;i<K;i++){
            ret+=pow*card[i];
            pow*=3;
        }
        return ret;
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