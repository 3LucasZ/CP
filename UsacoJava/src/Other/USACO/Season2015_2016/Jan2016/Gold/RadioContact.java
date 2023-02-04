package Other.USACO.Season2015_2016.Jan2016.Gold;

import java.io.*;
import java.util.*;

public class RadioContact {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int N;
    static int M;
    static Position[] FJ;
    static Position[] B;

    //helper
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("radio.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("radio.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }

        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        FJ = new Position[N+1];
        M = Integer.parseInt(st.nextToken());
        B = new Position[M+1];
        st = new StringTokenizer(br.readLine());
        int FJx = Integer.parseInt(st.nextToken());
        int FJy = Integer.parseInt(st.nextToken());
        FJ[0] = new Position(FJx, FJy);
        st = new StringTokenizer(br.readLine());
        int Bx = Integer.parseInt(st.nextToken());
        int By = Integer.parseInt(st.nextToken());
        B[0] = new Position(Bx,By);
        String FJi = br.readLine();
        for (int i=1;i<=N;i++) {
            char l = FJi.charAt(i-1);
            int id = -1;
            if (l=='N') id=0;
            if (l=='E') id=1;
            if (l=='S') id=2;
            if (l=='W') id=3;
            FJ[i]=new Position(FJ[i-1].x+dx[id],FJ[i-1].y+dy[id]);
        }
        String Bi = br.readLine();
        for (int i=1;i<=M;i++) {
            char l = Bi.charAt(i-1);
            int id = -1;
            if (l=='N') id=0;
            if (l=='E') id=1;
            if (l=='S') id=2;
            if (l=='W') id=3;
            B[i]=new Position(B[i-1].x+dx[id],B[i-1].y+dy[id]);
        }
        if (!submission){
            System.out.println(Arrays.toString(FJ));
            System.out.println(Arrays.toString(B));
        }
        //logic: dp
        int[][] dp = new int[N+1][M+1];
        for (int i=0;i<=N;i++) for (int j=0;j<=M;j++) dp[i][j]=Integer.MAX_VALUE;
        dp[0][0]=0;
        for (int i=0;i<N;i++){
            for (int j=0;j<M;j++){
                dp[i+1][j]=Math.min(dp[i+1][j],dp[i][j]+cost(i+1,j));
                dp[i][j+1]=Math.min(dp[i][j+1],dp[i][j]+cost(i,j+1));
                dp[i+1][j+1]=Math.min(dp[i+1][j+1],dp[i][j]+cost(i+1,j+1));
            }
        }

        //turn in answer
        out.println(dp[N][M]);
        out.close();
    }
    public static int cost(int i, int j) {
        return (FJ[i].x-B[j].x)*(FJ[i].x-B[j].x) + (FJ[i].y-B[j].y)*(FJ[i].y-B[j].y);
    }

    private static class Position {
        int x;
        int y;
        public Position(int x,int y){
            this.x=x;
            this.y=y;
        }
        public String toString(){
            return "["+x+", "+y+"]";
        }
    }
}
