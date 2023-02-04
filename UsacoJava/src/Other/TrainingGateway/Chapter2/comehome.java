package Other.TrainingGateway.Chapter2;

import java.io.*;
import java.util.*;

/*
PROB: comehome
LANG: JAVA
 */
public class comehome {
    //io
    static boolean submission = true;
    static boolean debug = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int P;
    static final int N = 52;
    static int[][] dist;

    //helper
    static final int INF = Integer.MAX_VALUE/2;
    static HashMap<Character, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("comehome.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("comehome.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //preprocess
        for (int i=0;i<26;i++){
            map.put((char)(i+'a'),i);
            map.put((char)(i+'A'),i+26);
        }
        //parse input
        P = Integer.parseInt(br.readLine());
        dist = new int[N][N];
        for (int u=0;u<N;u++) for (int v=0;v<N;v++) if (u!=v) dist[u][v]=INF;
        for (int i=0;i<P;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = map.get(st.nextToken().charAt(0));
            int v = map.get(st.nextToken().charAt(0));
            int len = Integer.parseInt(st.nextToken());
            dist[u][v]=Math.min(dist[u][v],len);
            dist[v][u]=Math.min(dist[v][u],len);
        }

        //logic: 50^3 inefficient bellman ford for quickness :)
        for (int m=0;m<N;m++){
            for (int u=0;u<N;u++){
                for (int v=0;v<N;v++){
                    dist[u][v]=Math.min(dist[u][v],dist[u][m]+dist[m][v]);
                }
            }
        }

        int ans = 26;
        for (int u=26;u<51;u++){
            if (dist[u][51]<dist[ans][51])ans=u;
        }

        //turn in answer
        out.println((char)(ans+'A'-26)+" "+dist[ans][51]);
        out.close();
    }
}
