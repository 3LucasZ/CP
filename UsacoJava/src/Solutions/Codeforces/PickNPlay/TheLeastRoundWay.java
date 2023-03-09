package Solutions.Codeforces.PickNPlay;

import java.io.*;
import java.util.*;
/*
PROB: TheLeastRoundWay
LANG: JAVA
*/
public class TheLeastRoundWay {
    static boolean submission = false;
    static boolean debug = false;

    static int N;
    static int [][] factor2;
    static int [][] factor5;
    static boolean [][] zero;

    static int[] dr = {0,1}; //1 -> D
    static int[] dc = {1,0}; //0 -> R
    static int INF = Integer.MAX_VALUE;

    static int[][] dir1;
    static int[][] dir2;
    static int[][] dir3;

    public static void main(String[] args) throws IOException {
        //parse
        setup("TheLeastRoundWay");
        N = Integer.parseInt(br.readLine());
        factor2 = new int[N][N];
        factor5 = new int[N][N];
        zero = new boolean[N][N];
        for (int r=0;r<N;r++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c=0;c<N;c++){
                int num = Integer.parseInt(st.nextToken());
                factor2[r][c]=getFactor(num,2);
                factor5[r][c]=getFactor(num,5);
                if (num==0) zero[r][c]=true;
            }
        }
        if (debug){
            System.out.println("factor2:");
            print(factor2);
            System.out.println("factor5:");
            print(factor5);
        }

        int[][] dp2;
        int[][] dp5;
        //* priority [2,5] dp
        int ans1;
        dp2 = new int[N][N]; for (int r=0;r<N;r++) for (int c=0;c<N;c++) dp2[r][c]=INF;
        dp2[0][0]=factor2[0][0];
        dp5 = new int[N][N]; for (int r=0;r<N;r++) for (int c=0;c<N;c++) dp5[r][c]=INF;
        dp5[0][0]=factor5[0][0];
        dir1 = new int[N][N];
        for (int r=0;r<N;r++){
            for (int c=0;c<N;c++){
                for (int i=0;i<2;i++) {
                    if (r+dr[i]>=N || c+dc[i]>=N) continue;
                    //second axis is 5
                    if (dp2[r+dr[i]][c+dc[i]] == dp2[r][c] + factor2[r+dr[i]][c+dc[i]]) {
                        if (dp5[r+dr[i]][c+dc[i]] > dp5[r][c] + factor5[r+dr[i]][c+dc[i]]) {
                            dp5[r + dr[i]][c + dc[i]] = dp5[r][c] + factor5[r + dr[i]][c + dc[i]];
                            dir1[r+dr[i]][c+dc[i]]=i;
                        }
                    }
                    //primary axis is 2
                    else if (dp2[r+dr[i]][c+dc[i]] > dp2[r][c] + factor2[r+dr[i]][c+dc[i]]){
                        dp2[r+dr[i]][c+dc[i]] = dp2[r][c] + factor2[r+dr[i]][c+dc[i]];
                        dp5[r+dr[i]][c+dc[i]] = dp5[r][c] + factor5[r+dr[i]][c+dc[i]];
                        dir1[r+dr[i]][c+dc[i]] = i;
                    }
                }
            }
        }
        ans1=Math.min(dp2[N-1][N-1],dp5[N-1][N-1]);
        if (debug) {
            System.out.println("dp2:");
            print(dp2);
            System.out.println("dp5: ");
            print(dp5);
            System.out.println("dir: ");
            print(dir1);
        }
        //* priority [5,2] dp
        int ans2;
        dp2 = new int[N][N]; for (int r=0;r<N;r++) for (int c=0;c<N;c++) dp2[r][c]=INF;
        dp2[0][0]=factor2[0][0];
        dp5 = new int[N][N]; for (int r=0;r<N;r++) for (int c=0;c<N;c++) dp5[r][c]=INF;
        dp5[0][0]=factor5[0][0];
        dir2 = new int[N][N];
        for (int r=0;r<N;r++){
            for (int c=0;c<N;c++){
                for (int i=0;i<2;i++) {
                    if (r+dr[i]>=N || c+dc[i]>=N) continue;
                    //second axis is 2
                    if (dp5[r+dr[i]][c+dc[i]] == dp5[r][c] + factor5[r+dr[i]][c+dc[i]]) {
                        if (dp2[r+dr[i]][c+dc[i]] > dp2[r][c] + factor2[r+dr[i]][c+dc[i]]) {
                            dp2[r + dr[i]][c + dc[i]] =factor2[r][c] + dp2[r + dr[i]][c + dc[i]];
                            dir2[r+dr[i]][c+dc[i]]=i;
                        }
                    }
                    //primary axis is 5
                    else if (dp5[r+dr[i]][c+dc[i]] > dp5[r][c] + factor5[r+dr[i]][c+dc[i]]){
                        dp5[r+dr[i]][c+dc[i]] = dp5[r][c] + factor5[r+dr[i]][c+dc[i]];
                        dp2[r+dr[i]][c+dc[i]] = dp2[r][c] + factor2[r+dr[i]][c+dc[i]];
                        dir2[r+dr[i]][c+dc[i]] = i;
                    }
                }
            }
        }
        ans2=Math.min(dp2[N-1][N-1],dp5[N-1][N-1]);
        if (debug) {
            System.out.println("dp2:");
            print(dp2);
            System.out.println("dp5: ");
            print(dp5);
            System.out.println("dir: ");
            print(dir2);
        }
        //* wonky 0 case
        int ans3;
        for (int r=0;r<N;r++) for (int c=0;c<N;c++) if (zero[r][c]) factor5[r][c]=-INF;
        dp2 = new int[N][N]; for (int r=0;r<N;r++) for (int c=0;c<N;c++) dp2[r][c]=INF;
        dp2[0][0]=factor2[0][0];
        dp5 = new int[N][N]; for (int r=0;r<N;r++) for (int c=0;c<N;c++) dp5[r][c]=INF;
        dp5[0][0]=factor5[0][0];
        dir3 = new int[N][N];
        for (int r=0;r<N;r++){
            for (int c=0;c<N;c++){
                for (int i=0;i<2;i++) {
                    if (r+dr[i]>=N || c+dc[i]>=N) continue;
                    //second axis is 2
                    if (dp5[r+dr[i]][c+dc[i]] == dp5[r][c] + factor5[r+dr[i]][c+dc[i]]) {
                        if (dp2[r+dr[i]][c+dc[i]] > dp2[r][c] + factor2[r+dr[i]][c+dc[i]]) {
                            dp2[r + dr[i]][c + dc[i]] =factor2[r][c] + dp2[r + dr[i]][c + dc[i]];
                            dir3[r+dr[i]][c+dc[i]]=i;
                        }
                    }
                    //primary axis is 5
                    else if (dp5[r+dr[i]][c+dc[i]] > dp5[r][c] + factor5[r+dr[i]][c+dc[i]]){
                        dp5[r+dr[i]][c+dc[i]] = dp5[r][c] + factor5[r+dr[i]][c+dc[i]];
                        dp2[r+dr[i]][c+dc[i]] = dp2[r][c] + factor2[r+dr[i]][c+dc[i]];
                        dir3[r+dr[i]][c+dc[i]] = i;
                    }
                }
            }
        }
        ans3=Math.min(dp2[N-1][N-1],dp5[N-1][N-1]);
        if (ans3<0) ans3=1;
        if (debug) {
            System.out.println("dp2:");
            print(dp2);
            System.out.println("dp5: ");
            print(dp5);
            System.out.println("dir: ");
            print(dir3);
        }
        //* ret
        if (ans1<=ans2 && ans1<=ans3){
            out.println(ans1);
            out.println(genPath(N-1,N-1,dir1));
        } else if (ans2<=ans1 && ans2<=ans3){
            out.println(ans2);
            out.println(genPath(N-1,N-1,dir2));
        } else {
            out.println(ans3);
            out.println(genPath(N-1,N-1,dir3));
        }
        out.close();
    }
    public static int getFactor(int num, int prime){
        int cnt = 0;
        while (num!=0 && num%prime==0){
            cnt++;
            num/=prime;
        }
        return cnt;
    }

    public static String genPath(int r, int c, int[][] dir){
        StringBuilder path = new StringBuilder();
        char[] mp = {'R','D'};
        while (!(r==0&&c==0)){
            path.append(mp[dir[r][c]]);
            int nr=r-dr[dir[r][c]];
            int nc=c-dc[dir[r][c]];
            r=nr;
            c=nc;
        }
        return path.reverse().toString();
    }

    public static void print(int[][] arr){
        for (int r=0;r<arr.length;r++){
            for (int c=0;c<arr[r].length;c++){
                String str = ""+arr[r][c];
                while (str.length()<5) str+=" ";
                System.out.print(str);
            }
            System.out.println();
        }
        System.out.println();
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