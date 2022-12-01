package UnsortedTraining.SilverTraining;

import java.io.*;
import java.util.*;
/*
USACO 2013 January Contest, Silver
Problem 2. Square Overlap
USACO Silver Training
Concepts: Sliding Window, "Active" set, rigorous proof for uniqueness
EXTREMELY FUN!!! :)
 */
public class SquareOverlap {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int K;
    static Center[] squares;
    static TreeSet<Center> active = new TreeSet<Center>((a,b)->{
        if (a.y==b.y) return a.x-b.x;
        return a.y-b.y;
    });
    static int l = 0;
    static int r = 0;
    static int overlaps = 0;
    static long area = 0;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("squares.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("squares.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        squares = new Center[N];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            Center next = new Center(x,y);
            squares[i] = next;
        }
        Arrays.sort(squares, (a,b)->a.x-b.x);
//        out.println(Arrays.toString(squares));
        //logic
        for (r=0;r<N;r++) {
            while (squares[r].x-squares[l].x >= K) {
                active.remove(squares[l]);
                l++;
            };
            Center higher = active.higher(squares[r]);
            Center lower = active.lower(squares[r]);
            if (higher != null && higher.y-squares[r].y < K) {
                int width = K - Math.abs(higher.x-squares[r].x);
                int height = K - Math.abs(higher.y-squares[r].y);
                area = (long) width * height;
                overlaps++;
            }
            if (lower != null && squares[r].y-lower.y < K) {
                int width = K - Math.abs(lower.x-squares[r].x);
                int height = K - Math.abs(lower.y-squares[r].y);
                area = (long) width * height;
                overlaps++;
            }
            active.add(squares[r]);
        }
        //turn in answer
        if (overlaps > 1) out.println(-1);
        else out.println(area);
        out.close();
    }
    private static class Center {
        int x;
        int y;
        public Center(int x1, int y1){
            x=x1;
            y=y1;
        }
        public String toString(){
            return "["+x+", "+y+"]";
        }
    }
}
