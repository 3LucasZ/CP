package Solutions.USACO.Season2021_2022.Jan2022.Silver;

import java.io.*;
import java.util.*;

public class CowFrisbee {
    //io
    static boolean debug = false;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int N;
    static TreeSet<Integer> position = new TreeSet<>();
    static int[] id;
    public static void main(String[] args) throws IOException {
        //parse
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        id = new int[N+1];
        for (int i=1;i<=N;i++) {
            int height = Integer.parseInt(st.nextToken());
            position.add(i);
            id[height]=i;
        }
        if (debug){
            System.out.println(position);
            System.out.println(Arrays.toString(id));
        }
        //logic
        long ans = 0;
        for (int i=1;i<=N;i++) {
            Integer lower = position.lower(id[i]);
            Integer higher = position.higher(id[i]);
            if (lower != null) ans += (long)id[i]-lower+1;
            if (higher != null) ans += (long)higher-id[i]+1;
            position.remove(id[i]);
        }
        out.println(ans);
        out.close();
    }
}
