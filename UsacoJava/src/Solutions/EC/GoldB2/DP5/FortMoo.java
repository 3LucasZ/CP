package Solutions.EC.GoldB2.DP5;

import java.io.*;
import java.util.*;
/*
Fort Moo
Gold Advanced B 5
DP - optimization - 2 pointers
loop through r1 r2 bounds
keep track of width by 2 pointers
if r1r2c is valid, that is c1, keep enlarging the rectangle to the right until the frame is swampy
then find a new c to start with
every time we find a nice ltb frame, query the r to see if it is valid, if valid update max area
 */
public class FortMoo {
    //io
    static boolean debug = false;
    static boolean submission = false;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int R;
    static int C;
    //presum to count swampy spots
    static boolean[][] swampy;
    static int[][] blocks;
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        blocks = new int[R+1][C+1];
        swampy = new boolean[R+1][C+1];
        for (int i=1;i<=R;i++){
            String str = br.readLine();
            for (int j=1;j<=C;j++){
                swampy[i][j] = str.charAt(j-1)=='X';
                blocks[i][j] = blocks[i-1][j]+blocks[i][j-1]-blocks[i-1][j-1]+(swampy[i][j]?1:0);
            }
        }
        //UT
        if (debug) {
            for (int r=1;r<=R;r++) {
                for (int c=1;c<=C;c++) {
                    System.out.print(blocks[r][c]+" ");
                }
                System.out.println();
            }
            System.out.println(valid(1, 5, 2));
            System.out.println(valid(1, 5, 3));
        }
        int MAX_AREA = 0;
        //logic
        for (int r1=1;r1<=R;r1++){
            for (int r2=r1;r2<=R;r2++){
                int c1=1;
                for (int c2=1;c2<=C;c2++){
                    while (true) {
                        if (c1 >= C) break;
                        if (valid(r1,r2,c1)) break;
                        c1++;
                    }
                    if (c1 >= C) break;
                    c2=Math.max(c1,c2);
                    if (valid(r1,r2,c2)) MAX_AREA=Math.max((r2-r1+1)*(c2-c1+1),MAX_AREA);
                    if (swampy[r1][c2]||swampy[r2][c2]) c1=c2;
                }
            }
        }
        //turn in answer
        out.println(MAX_AREA);
        out.close();
    }
    public static boolean valid(int r1, int r2, int c){
        int b = blocks[r2][c]-blocks[r2][c-1]-blocks[r1-1][c]+blocks[r1-1][c-1];
        if (debug) System.out.println(b);
        return b == 0;
    }
}
