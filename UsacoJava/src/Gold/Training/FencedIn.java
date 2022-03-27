package Gold.Training;

import java.io.*;
import java.util.*;
public class FencedIn {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int A;
    static int B;
    static int N;
    static int M;
    static ArrayList<Edge>[][]  farm;

    //on row r, cost to traverse columns
    static int[] rcost;
    //on col c, cost to traverse rows
    static int[] ccost;

    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("fencedin.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("fencedin.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }

        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        rcost = new int[M+1];
        ccost = new int[N+1];

        int[] vlines = new int[N+2];
        vlines[0]=0;vlines[N+1]=A;
        for (int i=1;i<=N;i++){
            vlines[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(vlines);

        int[] hlines = new int[M+2];
        hlines[0]=0;hlines[M+1]=B;
        for (int i=1;i<=M;i++){
            hlines[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(hlines);

        for (int i=0;i<=N;i++){
            ccost[i] = vlines[i+1]-vlines[i];
        }

        for (int i=0;i<=M;i++){
            rcost[i] = hlines[M-i+1]-hlines[M-i];
        }

        if (!submission){
            System.out.println(Arrays.toString(rcost));
            System.out.println(Arrays.toString(ccost));
        }

        //logic: SCUFFED MST
        visited = new boolean[M+1][N+1];
        long cost = 0;

        PriorityQueue<Edge> MST = new PriorityQueue<>((a,b)->a.cost-b.cost);
        MST.add(new Edge(0,0,0));
        for (int i=0;i<=(N+1)*(M+1)-1;i++){
            Edge next = MST.poll();
            if (visited[next.r][next.c]) {
                i--;
                continue;
            }

            visited[next.r][next.c]=true;
            cost+=next.cost;

            if (!submission) System.out.println(next);

            if (next.r < M && !visited[next.r+1][next.c]) MST.add(new Edge(next.r+1,next.c,ccost[next.c]));
            if (next.r > 0 && !visited[next.r-1][next.c]) MST.add(new Edge(next.r-1, next.c,ccost[next.c]));
            if (next.c < N && !visited[next.r][next.c+1]) MST.add(new Edge(next.r,next.c+1,rcost[next.r]));
            if (next.c > 0 && !visited[next.r][next.c-1]) MST.add(new Edge(next.r,next.c-1,rcost[next.r]));
        }

        //turn in answer
        out.println(cost);
        out.close();
    }
    private static class Edge {
        int cost;
        int r;
        int c;
        public Edge(int r, int c, int cost){
            this.r=r;
            this.c=c;
            this.cost=cost;
        }
        public String toString(){
            return "["+cost+": "+r+", "+c+"]";
        }
    }
}
