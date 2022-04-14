/*
PROB: agrinet
LANG: JAVA
 */
import java.io.*;
import java.util.*;
public class agrinet {
    //io
    static boolean submission = false;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int N;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader(".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        for (int u=0;u<N;u++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int v=0;v<N;v++){
                int cost = Integer.parseInt(st.nextToken());
                dist[u][v]=cost;
            }
        }

        //logic


        //turn in answer
        out.println();
        out.close();
    }
}
