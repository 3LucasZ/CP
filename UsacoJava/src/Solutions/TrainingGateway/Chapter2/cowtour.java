package Solutions.TrainingGateway.Chapter2;

import java.io.*;
import java.text.DecimalFormat;
import java.util.StringTokenizer;
/*
PROB: cowtour
LANG: JAVA
 */
public class cowtour {
    //io
    static boolean debug = false;
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int N;
    static boolean[][] connected;
    static double dist[][];
    static Position[] positions;

    //helper
    static final double INF = Double.MAX_VALUE/2-1000;
    static final double epsilon = 0.000001;

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("cowtour.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("cowtour.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }

        //parse input
        N = Integer.parseInt(br.readLine());
        positions = new Position[N];
        for (int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            positions[i] = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        connected = new boolean[N][N];
        for (int u=0;u<N;u++){
            String str = br.readLine();
            for (int v=0;v<N;v++){
                connected[u][v]=str.charAt(v)=='1';
                if (u==v) connected[u][v]=true;
            }
        }

        //logic: Bellman ford ES-SP
        dist = new double[N][N];
        for (int u=0;u<N;u++) for (int v=0;v<N;v++) dist[u][v]=INF;
        for (int u=0;u<N;u++) for (int v=0;v<N;v++) if (connected[u][v]) dist[u][v]=getDistance(u,v);
        if (debug) print(dist);
        for (int m=0;m<N;m++) for (int u=0;u<N;u++) for (int v=0;v<N;v++) dist[u][v]=Math.min(dist[u][v],dist[u][m]+dist[m][v]);
        if (debug) print(dist);

        //bash edges
        double ans = Double.MAX_VALUE;
        double edgeCase = 0;
        for (int u=0;u<N;u++){
            for (int v=0;v<N;v++){
                if (!equals(dist[u][v],INF)) edgeCase = Math.max(edgeCase,dist[u][v]);
            }
        }
        for (int u=0;u<N;u++){
            for (int v=0;v<N;v++){
                if (!equals(dist[u][v],INF)) continue;
                double uMAX = 0;
                for (int m=0;m<N;m++) if (!equals(dist[u][m],INF)) uMAX = Math.max(dist[u][m],uMAX);
                double vMAX = 0;
                for (int m=0;m<N;m++) if (!equals(dist[v][m],INF)) vMAX = Math.max(dist[v][m],vMAX);
                if (debug) System.out.println(u+", "+v+" "+(getDistance(u,v)+uMAX+vMAX));
                ans = Math.min(ans, getDistance(u,v)+uMAX+vMAX);
            }
        }

        //turn in answer
        out.println(new DecimalFormat(".000000").format(Math.max(edgeCase,ans)));
        out.close();
    }

    public static double getDistance(int u, int v){
        return Math.sqrt(
                (long) (positions[u].x - positions[v].x)*
                        (positions[u].x-positions[v].x) +
                (long) (positions[u].y - positions[v].y)*
                        (positions[u].y-positions[v].y));
    }

    public static boolean equals(double a, double b) {
        return Math.abs(a-b) < epsilon;
    }

    private static class Position {
        int x;
        int y;
        public Position(int x, int y){
            this.x=x;
            this.y=y;
        }
    }

    public static void print(double[][] arr){
        for (int r=0;r<N;r++){
            for (int c=0;c<N;c++){
                if (equals(arr[r][c],INF)) out.print("N/A");
                else out.print(new DecimalFormat("###.00").format(arr[r][c]));
                out.print(" ");
            }
            out.println();
        }
        out.println();
    }
}
