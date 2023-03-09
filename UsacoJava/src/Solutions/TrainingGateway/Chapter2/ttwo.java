package Solutions.TrainingGateway.Chapter2;

import java.io.*;

/*
PROB: ttwo
LANG: JAVA
The Tamworth Two
 */
public class ttwo {
    //io
    static boolean submission = true;
    static boolean debug = true;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static Position FJ;
    static Position C;
    static boolean[][] blocked;

    //helper
    static int N = 10;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("ttwo.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("ttwo.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        blocked = new boolean[N][N];
        for (int r=0;r<N;r++){
            String str = br.readLine();
            for (int c=0;c<N;c++){
                if (str.charAt(c)=='*') blocked[r][c]=true;
                else if (str.charAt(c)=='F') FJ = new Position(r,c);
                else if (str.charAt(c)=='C') C = new Position(r,c);
            }
        }

        //logic: constrained simulation
        int ans = 0;
        for (int t=1;t<=N*N*N*N+1;t++){
            move(C);
            move(FJ);
            if (equals(C,FJ)){
                ans=t;
                break;
            };
        }
        //turn in answer
        out.println(ans);
        out.close();
    }
    public static boolean equals(Position a, Position b){
        return a.r==b.r&&a.c==b.c;
    }
    public static void move(Position pos){
        if (inBound(pos.r+dr[pos.dir],pos.c+dc[pos.dir]) && !blocked[pos.r+dr[pos.dir]][pos.c+dc[pos.dir]]) {
            pos.r += dr[pos.dir];
            pos.c += dc[pos.dir];
        } else{
            pos.dir=(pos.dir+1)%4;
        }
    }
    public static boolean inBound(int r, int c){
        return !(r<0||r>=N||c<0||c>=N);
    }
    private static class Position {
        int dir;
        int r;
        int c;
        public Position(int r, int c){
            this.r=r;
            this.c=c;
            dir=0;
        }
    }
}
