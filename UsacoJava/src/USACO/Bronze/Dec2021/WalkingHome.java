package USACO.Bronze.Dec2021;

import java.io.*;
import java.util.*;

public class WalkingHome {
    //io
    static BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int T;
    static int N;
    static int K;
    public static void main(String[] args) throws IOException {
        //parse input
        T = Integer.parseInt(f.readLine());
        for (int i=0;i<T;i++) {
            solve();
        }
        out.close();
        f.close();
    }
    public static void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        boolean[][] haybales = new boolean[N][N];
        int ans = 0;
        for (int i=0;i<N;i++) {
            String str = f.readLine();
            for (int j=0;j<N;j++){
                haybales[i][j]=str.charAt(j)=='H'?true:false;
            }
            //out.println(Arrays.toString(haybales[i]));
        }
        if (K>=1){
            int good1 = 1;
            int good2 = 1;
            for (int r=0;r<N;r++) {
                if (haybales[r][0]) {
                    good1 = 0;
                }
                if (haybales[r][N-1]){
                    good2 = 0;
                }
            }
            for (int c=0;c<N;c++) {
                if (haybales[0][c]){
                    good2 = 0;
                }
                if (haybales[N-1][c]){
                    good1 = 0;
                }
            }
            ans += (good1 + good2);
        }
        if (K>=2) {
            //2 vertical 1 horizontal
            for (int v=1;v<N-1;v++){
                //good vert?
                boolean goodVert = true;
                for (int c=0;c<N;c++) {
                    if (haybales[v][c]) goodVert = false;
                }
                if (goodVert) {
                    boolean goodHoriz = true;
                    for (int r=0;r<N;r++) {
                        if (r <= v && haybales[r][0]) goodHoriz = false;
                        if (r >= v && haybales[r][N-1]) goodHoriz = false;
                    }
                    if (goodHoriz) ans ++;
                }
            }
            for (int v=1;v<N-1;v++){
                //good vert?
                boolean goodVert = true;
                for (int r=0;r<N;r++) {
                    if (haybales[r][v]) goodVert = false;
                }
                if (goodVert) {
                    boolean goodHoriz = true;
                    for (int c=0;c<N;c++) {
                        if (c <= v && haybales[0][c]) goodHoriz = false;
                        if (c >= v && haybales[N-1][c]) goodHoriz = false;
                    }
                    if (goodHoriz) ans ++;
                }
            }
        }
        if (K>=3) {
            //RDRD case
            for (int right=1;right<N-1;right++){
                for (int down=1;down<N-1;down++){
                    boolean r1 = true;
                    boolean r2 = true;
                    boolean d1 = true;
                    boolean d2 = true;
                    for (int r=0;r<N;r++) {
                        if (r<=right && haybales[0][r]) r1 = false;
                        if (r>=right && haybales[down][r]) r2 = false;
                    }
                    for (int d=0;d<N;d++) {
                        if (d<=down && haybales[d][right]) d1 = false;
                        if (d>=down && haybales[d][N-1]) d2 = false;
                    }
                    if (r1 && r2 && d1 && d2) ans ++;
                }
            }
            //DRDR case
            for (int right=1;right<N-1;right++){
                for (int down=1;down<N-1;down++){
                    boolean r1 = true;
                    boolean r2 = true;
                    boolean d1 = true;
                    boolean d2 = true;
                    for (int r=0;r<N;r++) {
                        if (r<=right && haybales[down][r]) r1 = false;
                        if (r>=right && haybales[N-1][r]) r2 = false;
                    }
                    for (int d=0;d<N;d++) {
                        if (d<=down && haybales[d][0]) d1 = false;
                        if (d>=down && haybales[d][right]) d2 = false;
                    }
                    if (r1 && r2 && d1 && d2) ans ++;
                }
            }
        }
        out.println(ans);
    }
}
