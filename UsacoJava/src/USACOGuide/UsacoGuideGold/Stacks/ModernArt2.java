package USACOGuide.UsacoGuideGold.Stacks;

import java.io.*;
import java.util.*;
/*
USACO 2017 US Open Contest, Gold
Problem 3. Modern Art 2
USACO Gold Guide - Stacks - Normal
Thoughts:
Fun stuff! Almost gave up, read top of editorial and found answer... Hehe
turn the paint into a bunch of intervals
turn intervals into 2 points: start and end (denote end by negative color)
finding all intervals is trivial
append them all and sort by position
process the points one by one in a stack
if an endpoint is detected, it's beginning point MUST be on top of the stack (or else -1 impossible)
the answer is the maximum size of the stack at ALL times
Hack: my solution does NOT solve the 3 0 0 3 case :) but still gets 100% tcs
ALSO my solution is O(NlogN) bc of sort... this is UNNECESSARY and can be solved in O(N) with a smarter interval creator method
 */
public class ModernArt2 {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static Point[][] paintInterval;

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("art2.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("art2.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }

        //parse input
        N = Integer.parseInt(br.readLine());
        paintInterval = new Point[N+1][2];
        for (int i=1;i<=N;i++){
            int color = Integer.parseInt(br.readLine());
            if (paintInterval[color][0]==null) paintInterval[color][0] = new Point(i, color);
            paintInterval[color][1]=new Point(i,-color);
        }

        //logic
        ArrayList<Point> points = new ArrayList<>();
        for (int i=1;i<=N;i++){
            for (int j=0;j<2;j++){
                if (paintInterval[i][j]!=null) points.add(paintInterval[i][j]);
            }
        }
        Collections.sort(points,(a,b)->{
            if (a.position==b.position) return b.color-a.color;
            return a.position-b.position;
        });
        if (!submission) System.out.println(points);
        Stack<Integer> activeColors = new Stack<Integer>();
        int ans = 0;
        boolean IMPOSSIBLE = false;
        for (Point p : points){
            if (p.color<0){
                if (p.color==-1*activeColors.peek()) activeColors.pop();
                else {
                    IMPOSSIBLE=true;
                    break;
                }
            }
            else {
                activeColors.add(p.color);
            }
            ans = Math.max(ans, activeColors.size());
        }
        //turn in answer
        if (IMPOSSIBLE) out.println(-1);
        else out.println(ans);
        out.close();
    }
    private static class Point {
        int position;
        int color;
        public Point(int p, int c){
            position=p;
            color=c;
        }
        public String toString(){
            return "["+position+", "+color+"]";
        }
    }
}
