import java.io.*;
import java.util.*;
/*

 */
public class PogoCow {
    //io
    static boolean submission = false;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static Target[] targets;
    static int[][] pointsDP;
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
        targets = new Target[N+1];
        //dp: [last target][current target] = pts
        //0 last target means that it is a starting point
        pointsDP = new int[N+1][N+1];
        targets[0] = new Target(-1, -1);
        for (int i=1;i<=N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            targets[i] = new Target(x, p);
        }
        //logic
        Arrays.sort(targets, (a,b)->a.x-b.x);
        for (int curr=1;curr<=N;curr++) {
            pointsDP[0][curr] = targets[curr].points;
            for (int last=1;last<=N;last++) {
                if (pointsDP[last][curr] > 0) {
                    int distance = targets[curr].x - targets[last].x;
                }
            }
        }
        //turn in answer
        out.println();
        out.close();
    }
    private static class Target {
        int x;
        int points;
        public Target(int x1, int p1){
            x=x1;
            points=p1;
        }
        public String toString(){
            return "["+x+": "+points+"]";
        }
    }
}
