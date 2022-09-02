package USACO.Silver.UsacoGuideSilver.FloodFill;

import java.io.*;
import java.util.*;
/*
USACO 2017 US Open Contest, Silver
Problem 3. Where's Bessie?
USACO Silver Guide - Floodfill - Normal
 */
public class WheresBessie {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int[][] image;
    static boolean[][] visited;
    static int[] components;
    static int r1;
    static int r2;
    static int c1;
    static int c2;
    static ArrayList<PCL> PCLS = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("where.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("where.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        image = new int[N][N];
        for (int r=0;r<N;r++) {
            String str = br.readLine();
            for (int c=0;c<N;c++) {
                image[r][c] = str.charAt(c) - 'A';
            }
        }
        //parse input works
        //print2DArr(image);
        //logic
        for (r1=0;r1<N;r1++) {
            for (r2=r1;r2<N;r2++){
                for (c1=0;c1<N;c1++) {
                    for (c2=c1;c2<N;c2++) {
                        isPCL();
                    }
                }
            }
        }
//        out.println(PCLS.size());
//        for (PCL e : PCLS){
//            out.println(e);
//        }
//        out.println();

        int ans = 0;
        for (PCL potential : PCLS){
            boolean good = true;
            for (PCL container : PCLS){
                if (container != potential && container.contains(potential))good = false;
            }
            if (good) ans++;
        }
        //turn in answer
        out.println(ans);
        out.close();
    }
    public static void isPCL(){
        visited = new boolean[N][N];
        components = new int[26];
        for (int r=r1;r<=r2;r++) {
            for (int c=c1;c<=c2;c++) {
                if (!visited[r][c]) {
                    components[image[r][c]]++;
                    floodfill(r, c, image[r][c]);
                }
            }
        }
        boolean found1 = false;
        boolean found2 = false;
        int variety = 0;
        for (int i=0;i<26;i++) {
            if (components[i] == 1) found1 = true;
            if (components[i] >= 2) found2 = true;
            if (components[i] > 0) variety++;
        }
        if (found1 && found2 && variety == 2) PCLS.add(new PCL(r1, r2, c1, c2));
    }
    static int[] dr = {0,0,1,-1};
    static int[] dc = {1,-1,0,0};
    public static void floodfill(int r, int c, int color){
        if (r<r1||r>r2||c<c1||c>c2||visited[r][c]||image[r][c]!=color) return;
        visited[r][c] = true;
        for (int i=0;i<4;i++) {
            floodfill(r+dr[i],c+dc[i], color);
        }
    }
    private static class PCL {
        int rr1;
        int rr2;
        int cc1;
        int cc2;
        public PCL(int rrr1, int rrr2, int ccc1, int ccc2){
            rr1=rrr1;
            cc1=ccc1;
            rr2=rrr2;
            cc2=ccc2;
        }
        public boolean contains(PCL other){
            return other.rr1>=rr1&&other.rr2<=rr2&&other.cc1>=cc1&&other.cc2<=cc2;
        }
        public String toString(){
            String ret = "";
            for (int r=rr1;r<=rr2;r++) {
                for (int c=cc1;c<=cc2;c++) {
                    ret += image[r][c] + " ";
                }
                ret += "\n";
            }
            return ret;
        }
    }
    public static void print2DArr(int[][] arr) {
        for (int r=0;r<arr.length;r++) {
            for (int c=0;c<arr[r].length;c++) {
                out.print(arr[r][c]+" ");
            }
            out.println();
        }
        out.println();
    }
}
