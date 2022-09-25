package USACOGuide.UsacoGuideSilver.FloodFill;

import java.io.*;

/*
USACO 2016 January Contest, Silver
Problem 3. Build Gates
USACO Silver Guide: Floodfill - Normal
9/10 test cases :o - Stack overflow error from too much recursion
Concepts: Optimization through bounding/scoping - tunnel visioning
 */
public class BuildGates {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int n;
    static boolean farm[][];
    static boolean visited[][];
    static int size;
    static int ans = 0;
    static int minx=5000,maxx=-5000,maxy=-5000,miny=5000;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            f = new BufferedReader(new FileReader("gates.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("gates.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        n = Integer.parseInt(f.readLine());
        size = n*4+4;
        farm=new boolean[size][size];
        visited=new boolean[size][size];
        String in = f.readLine();
        int fjr=farm.length/2;
        int fjc=farm.length/2;
        farm[fjr][fjc]=true;
        minx=fjr;maxx=fjr;maxy=fjc;miny=fjc;
        for (int i=0;i<n;i++) {
            if(in.charAt(i)=='N'){
                farm[fjr+1][fjc]=true;
                farm[fjr+2][fjc]=true;
                fjr+=2;
            }
            else if (in.charAt(i)=='E'){
                farm[fjr][fjc+1]=true;
                farm[fjr][fjc+2]=true;
                fjc+=2;
            }
            else if(in.charAt(i)=='S'){
                farm[fjr-1][fjc]=true;
                farm[fjr-2][fjc]=true;
                fjr-=2;
            }
            else if (in.charAt(i)=='W'){
                farm[fjr][fjc-1]=true;
                farm[fjr][fjc-2]=true;
                fjc-=2;
            }
            minx=Math.min(minx,fjr); //minrow
            maxx=Math.max(maxx,fjr);
            miny=Math.min(miny,fjc); //mincol
            maxy=Math.max(maxy,fjc);
        }
        minx--;maxx++;miny--;maxy++;
//        for(int r=0;r<size;r++){
//            for(int c=0;c<size;c++) {
//                out.print(farm[r][c]?1:0);
//            }
//            out.println();
//        }
        //logic
        for(int r=minx;r<=maxx;r++){
            for(int c=miny;c<=maxy;c++) {
                if(!visited[r][c]&&!farm[r][c]){
                    //out.println("r: "+r+" c: "+c);
                    ans++;
                    floodfill(r,c);
                }
            }
        }
        //turn in answer
        out.println(ans-1);
        out.close();
        f.close();
    }
    static int[] dR = {0,0,1,-1};
    static int[] dC = {1,-1,0,0};
    public static void floodfill(int r, int c){
        if (r<minx||r>maxx||c<miny||c>maxy||visited[r][c]||farm[r][c]) return;
        visited[r][c] = true;
        for (int i=0;i<4;i++) {
            floodfill(r+dR[i],c+dC[i]);
        }
    }
}
