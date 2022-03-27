package Gold.Training;

import java.io.*;
import java.util.*;

public class CowChecklist {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int H;
    static int G;
    static Position[] HP;
    static Position[] GP;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("checklist.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("checklist.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken()); HP = new Position[H+1];
        G = Integer.parseInt(st.nextToken()); GP = new Position[G+1];

        for (int i=1;i<=H;i++) {
            st = new StringTokenizer(br.readLine());
            HP[i] = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        for (int i=1;i<=G;i++){
            st = new StringTokenizer(br.readLine());
            GP[i] = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        if (!submission) {
            System.out.println(Arrays.toString(HP));
            System.out.println(Arrays.toString(GP));
        }

        //logic: DP
        int[][][] dp = new int[H+1][G+1][2];
        for (int i=0;i<=H;i++) for (int j=0;j<=G;j++) for (int k=0;k<2;k++) dp[i][j][k]=Integer.MAX_VALUE;
        dp[1][0][0] = 0;
        for (int h=0;h<=H;h++){
            for (int g=0;g<=G;g++){
                for (int t=0;t<2;t++){
                    if (dp[h][g][t]==Integer.MAX_VALUE) continue;
                    if (t==0){
                        if (h < H) dp[h+1][g][0]=Math.min(dp[h][g][0]+cost(HP[h],HP[h+1]), dp[h+1][g][0]);
                        if (g < G) dp[h][g+1][1]=Math.min(dp[h][g][0]+cost(HP[h],GP[g+1]), dp[h][g+1][1]);
                    }
                    else {
                        if (h < H) dp[h+1][g][0]=Math.min(dp[h][g][1]+cost(GP[g],HP[h+1]), dp[h+1][g][0]);
                        if (g < G) dp[h][g+1][1]=Math.min(dp[h][g][1]+cost(GP[g],GP[g+1]), dp[h][g+1][1]);
                    }
                }
            }
        }

        if (!submission){
            System.out.println(cost(HP[1],GP[1]));
        }
        //turn in answer
        out.println(dp[H][G][0]);
        out.close();
    }
    public static int cost (Position p, Position q){
        return (p.x-q.x)*(p.x-q.x)+(p.y-q.y)*(p.y-q.y);
    }
    private static class Position{
        int x;
        int y;
        public Position(int x, int y){
            this.x=x;
            this.y=y;
        }
        public String toString(){
            return "["+x+", "+y+"]";
        }
    }
}
