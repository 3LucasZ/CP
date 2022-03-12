import java.io.*;
import java.util.*;

public class AlyonaAndATree {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);

    //in
    static int N;
    static int[] val;
    static ArrayList<Edge>[] tree;

    public static void main(String[] args) throws IOException{
        //parse
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) val[i] = Integer.parseInt(st.nextToken());
        for (int v=2;v<=N;v++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            tree[v].add(new Edge(u, weight));
        }

        //log

    }
    private static class Edge {
        int v;
        int weight;
        public Edge(int v, int w){
            this.v=v;
            this.weight=w;
        }
        public String toString(){
            return "["+v+", "+weight+"]";
        }
    }
}
