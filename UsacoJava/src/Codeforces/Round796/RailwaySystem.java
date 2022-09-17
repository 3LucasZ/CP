package Codeforces.Round796;

import java.io.*;
import java.util.*;

public class RailwaySystem {
    static boolean submission = false;
    static boolean debug = true;

    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        setup("");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[] query = new int[M];
        Edge[] edges = new Edge[M];
        for (int i=0;i<M;i++){
            query[i]=1;
            int len = send(query);
            query[i]=0;
            edges[i]=new Edge(i, len);
        }

        Arrays.sort(edges, (a,b)->a.len-b.len);

        int minST = 0;
        for (int i=0;i<M;i++){
            Edge nxt = edges[i];
            query[nxt.id]=1;
            int maxST = send(query);

            //new minST edge
            if (minST + nxt.len == maxST){
                minST = maxST;
            } else {
                query[nxt.id]=0;
            }
        }
        out.println("! "+minST);
        out.close();
    }
    public static int send(int[] query) throws IOException {
        out.print("? ");
        for (int j=0;j<M;j++){
            out.print(query[j]);
        }
        out.println();
        out.flush();
        return Integer.parseInt(br.readLine());
    }
    private static class Edge {
        int id;
        int len;
        public Edge(int i, int l){
            this.id=i;
            this.len=l;
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
