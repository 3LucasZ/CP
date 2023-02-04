package Other.TrainingGateway.Chapter3;

import java.io.*;
import java.util.*;
/*
PROB: camelot
LANG: JAVA
 */
public class camelot {
    static boolean submission = true;
    static boolean debug = false;

    //board size
    static int R;
    static int C;

    //knight moves
    static int[] hdr = {-2,-2,2,2,-1,-1,1,1};
    static int[] hdc = {1,-1,1,-1,-2,2,-2,2};
    //knight dist from r1c1 to r2c2
    static int[][][][] hDist;
    //knights
    static ArrayList<Piece> knights = new ArrayList<>();

    //king moves
    static int[] kdr = {-1,-1,-1,0,0,1,1,1};
    static int[] kdc = {1,0,-1,1,-1,1,0,-1};
    //king dist to r1c1
    static int[][] kDist;
    //king
    static Piece king;

    static int[][] sum;

    static int INF = Integer.MAX_VALUE/10;

    public static void main(String[] args) throws IOException {
        //parse
        setup("camelot");
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        hDist = new int[R][C][R][C];
        kDist = new int[R][C];
        st = new StringTokenizer(br.readLine());int kc = st.nextToken().charAt(0)-'A';int kr = Integer.parseInt(st.nextToken())-1;
        king = new Piece(kr,kc);
        while (true){
            String str = br.readLine();
            if (str==null) break;
            st = new StringTokenizer(str);
            while (true){
                kc = st.nextToken().charAt(0)-'A';kr = Integer.parseInt(st.nextToken())-1;
                knights.add(new Piece(kr,kc));
                if (!st.hasMoreTokens()) break;
            }
        }

        for (int r=0;r<R;r++) for (int c=0;c<C;c++) knightBFS(new Piece(r,c));
        kingBFS();

        if (debug){
            for (int r=0;r<R;r++) {
                for (int c = 0; c < C; c++) {
                    System.out.print(hDist[4][4][r][c] + " ");
                }
                System.out.println();
            }
            System.out.println();
            for (int r=0;r<R;r++) {
                for (int c = 0; c < C; c++) {
                    System.out.print(kDist[r][c] + " ");
                }
                System.out.println();
            }
        }

        //sum
        sum = new int[R][C];
        for (int r=0;r<R;r++) for (int c=0;c<C;c++) for (Piece knight : knights) {
            sum[r][c]+=hDist[r][c][knight.r][knight.c];
        }

        //complete search
        int ans = INF;
        for (int er=0;er<R;er++){
            for (int ec=0;ec<C;ec++){
                int radius=2;
                int kingStartR = Math.max(0,king.r-radius);
                int kingStartC = Math.max(0,king.c-radius);
                int kingEndR = Math.min(R-1,king.r+radius);
                int kingEndC = Math.min(C-1,king.c+radius);
                for (int mr=kingStartR;mr<=kingEndR;mr++){
                    for (int mc=kingStartC;mc<=kingEndC;mc++){
                        for (Piece knight : knights){
                            //moves without knight
                            int moves = sum[er][ec];
                            moves-=hDist[knight.r][knight.c][er][ec];
                            //add pick up time
                            moves+=hDist[knight.r][knight.c][mr][mc]+hDist[mr][mc][er][ec]+kDist[mr][mc];
                            ans=Math.min(moves,ans);
                        }
                    }
                }
                //no pick up case
                ans=Math.min(sum[er][ec]+kDist[er][ec],ans);
            }
        }

        //ret
        out.println(ans);
        out.close();
    }
    public static void knightBFS(Piece piece){
        Queue<Piece> BFS = new LinkedList<>();
        BFS.add(piece);
        for (int i=0;i<R;i++) for (int j=0;j<C;j++) hDist[piece.r][piece.c][i][j]=INF;
        hDist[piece.r][piece.c][piece.r][piece.c] = 0;
        while (!BFS.isEmpty()){
            Piece next = BFS.poll();
            for (int i=0;i<8;i++) {
                Piece child = new Piece(next.r+hdr[i],next.c+hdc[i]);
                if (inRange(child.r,child.c)&&hDist[piece.r][piece.c][child.r][child.c]==INF) {
                    BFS.add(child);
                    hDist[piece.r][piece.c][child.r][child.c] = hDist[piece.r][piece.c][next.r][next.c]+1;
                }
            }
        }
    }
    public static void kingBFS(){
        Queue<Piece> BFS = new LinkedList<>();
        BFS.add(king);
        for (int i=0;i<R;i++) for (int j=0;j<C;j++) kDist[i][j]=INF;
        kDist[king.r][king.c]=0;
        while (!BFS.isEmpty()){
            Piece next = BFS.poll();
            for (int i=0;i<8;i++) {
                Piece child = new Piece(next.r+kdr[i],next.c+kdc[i]);
                if (inRange(child.r,child.c)&&kDist[child.r][child.c]==INF) {
                    BFS.add(child);
                    kDist[child.r][child.c] = kDist[next.r][next.c]+1;
                }
            }
        }
    }
    public static boolean inRange(int r,int c){
        return !(r<0 || r>=R || c<0 || c>=C);
    }
    private static class Piece {
        int r;
        int c;
        public Piece(int r, int c){
            this.r=r;
            this.c=c;
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
