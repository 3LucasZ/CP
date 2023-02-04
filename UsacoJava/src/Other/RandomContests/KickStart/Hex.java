package Other.RandomContests.KickStart;

import java.io.*;

public class Hex {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //glob
    static int N;
    static int[][] board;
    static boolean[][] visited;
    static int startCnt;
    static int endCnt;
    //const
    static final int[] dr={0,-1,-1,0,1,1};
    static final int[] dc={-1,0,1,1,0,-1};
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=1;i<=T;i++){
            out.println("Case #"+i+": "+solve());
        }
        out.close();
    }

    public static String solve() throws IOException{
        //parse
        N = Integer.parseInt(br.readLine());
        //R:1, B:2, X: 0
        board = new int[N][N];
        visited = new boolean[N][N];
        int reds = 0;
        int blues = 0;
        for (int r=0;r<N;r++){
            String str = br.readLine();
            for (int c=0;c<N;c++){
                if (str.charAt(c)=='B') {
                    board[r][c]=1;
                    blues++;
                }
                if (str.charAt(c)=='R') {
                    board[r][c]=2;
                    reds++;
                }
            }
        }
        //logic:

        // blue floodfill, does blue win?
        int blueWins=0;
        for (int r=0;r<N;r++){
            startCnt=0;
            endCnt=0;
            floodfill(r,0,1);
            if (startCnt==0||endCnt==0);
            else if (startCnt>1&&endCnt>1) return "Impossible";
            else blueWins++;
        }
        if(blueWins>1) return "Impossible";

        // red floodfill, does red win?
        int redWins=0;
        for (int c=0;c<N;c++){
            startCnt=0;
            endCnt=0;
            floodfill(0,c,2);
            if (startCnt==0||endCnt==0);
            else if (startCnt>1&&endCnt>1) return "Impossible";
            else redWins++;
        }
        if (redWins>1) return "Impossible";
        //turn in
        if (blueWins==1&&redWins==1) return "Impossible";
        if (blueWins==1){
            if (blues==reds||blues==reds+1) return "Blue wins";
            return "Impossible";
        }
        if (redWins==1){
            if (reds==blues||reds==blues+1) return "Red wins";
            return "Impossible";
        }
        if (Math.abs(blues-reds)>1)return("Impossible");
        return("Nobody wins");
    }
    public static void floodfill(int r, int c, int color){
        if (r<0||r>=N||c<0||c>=N||board[r][c]!=color||visited[r][c]) return;
        visited[r][c]=true;
        if (color==1){
            if (c==N-1) endCnt++;
            if (c==0) startCnt++;
        }
        else if (color==2) {
            if (r==N-1) endCnt++;
            if (r==0) startCnt++;
        }
        for (int i=0;i<6;i++){
            floodfill(r+dr[i],c+dc[i],color);
        }
    }
}