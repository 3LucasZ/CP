package Other.TrainingGateway.Chapter2;

import java.io.*;
import java.util.*;
/*
LANG: JAVA
TASK: concom
Thoughts:
first try solve, took a while, beautiful graph problem solved with BFS and adjacency matrices
 */
public class concom {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int N;
    static int[][] percentControl;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("concom.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }

        //parse input
        N = Integer.parseInt(br.readLine());
        percentControl = new int[101][101];
        visited = new boolean[101][101];
        for (int i=1;i<=100;i++) {percentControl[i][i]=100; visited[i][i]=true;}
        for (int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            percentControl[u][v]=p;
        }

        //logic
        for (int u=1;u<=100;u++){
            Queue<Integer> bfs = new LinkedList<>();
            for (int v=1;v<=100;v++){
                if (!visited[u][v]&&percentControl[u][v]>50) {
                    bfs.add(v);
                    visited[u][v]=true;
                }
            }

            while (!bfs.isEmpty()){
                int next = bfs.poll();
                for (int v=1;v<=100;v++){
                    if (visited[u][v]) continue;
                    percentControl[u][v] += percentControl[next][v];
                    if (percentControl[u][v]>50) {
                        visited[u][v]=true;
                        bfs.add(v);
                    }
                }
            }
        }
        //turn in answer
        for (int u=1;u<=100;u++){
            for (int v=1;v<=100;v++){
                if (u!=v && percentControl[u][v]>50) {
                    out.println(u+" "+v);
                }
            }
        }
        out.close();
    }
}
