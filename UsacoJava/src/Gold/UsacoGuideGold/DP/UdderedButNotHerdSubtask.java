package Gold.UsacoGuideGold.DP;

import java.io.*;
import java.util.*;

public class UdderedButNotHerdSubtask {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = true;

    //heard
    static String str;
    //heard len
    static int L;
    //unique letters
    static int N = 0;
    //char -> index
    static HashMap<Character, Integer> index = new HashMap<>();
    //cnt
    static int[][] cnt;

    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        //parse
        str = br.readLine();
        L = str.length();

        //indexing
        for (int i=0;i<L;i++){
            if (!index.containsKey(str.charAt(i))) index.put(str.charAt(i),N++);
        }

        //letter adj cnt
        cnt = new int[N][N];
        for (int i=0;i<L-1;i++){
            int l = index.get(str.charAt(i));
            int r = index.get(str.charAt(i+1));
            cnt[l][r]++;
        }

        visited = new boolean[N];
        permute(new ArrayList<>());
        out.println(1+ans);
        out.close();
    }
    static boolean[] visited;
    public static void permute(ArrayList<Integer> build){
        if (build.size() == N){
            process(build);
        }
        else {
            for (int i=0;i<N;i++){
                if (!visited[i]){
                    visited[i]=true;
                    build.add(i);
                    permute(build);
                    visited[i]=false;
                    build.remove(build.size()-1);
                }
            }
        }
    }
    public static void process(ArrayList<Integer> arr){
        int tot = 0;
        for (int l=0;l<N;l++){
            for (int r=0;r<N;r++){
                if (arr.get(l)>=arr.get(r)) tot+=cnt[l][r];
            }
        }
        ans = Math.min(ans,tot);
    }
}
