package USACOGuide.UsacoGuideSilver.CustomComparatorsAndCoordinateCompression;

import java.io.*;
import java.util.*;
/*
USACO 2018 January Contest, Silver
Problem 1. Lifeguards
USACO Guide Silver - Custom Sorting - Easy
 */
public class Lifeguards {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int n;
    static Shift[] shifts;
    //logic
    static int sum = 0;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            f = new BufferedReader(new FileReader("lifeguards.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("lifeguards.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        n = Integer.parseInt(f.readLine());
        shifts = new Shift[n];
        for (int i=0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            shifts[i] = new Shift(u,v);
        }
        //logic
        Arrays.sort(shifts, (a, b) -> {
            if (a.A == b.A) return a.B - b.B;
            return a.A - b.A; });
        //out.println(Arrays.toString(shifts));


        for (int i=0;i<n;i++) {
            //Case 1: The previous interval contains this one -> ignore this
            if (i>= 1 && shifts[i-1].contains(shifts[i])) {
                min = Math.min(min, 0);
                shifts[i].B = shifts[i-1].B;
            }
            //Case 2: The previous interval does not contain this one
            else {
                int lo = shifts[i].A;
                int hi = shifts[i].B;
                if (i >= 1 && shifts[i-1].B > shifts[i].A) lo = shifts[i-1].B;
                if (i <= n-2 && shifts[i].B > shifts[i+1].A) hi = shifts[i+1].A;
                int alone_time = Math.abs(hi-lo);
                min = Math.min(min, alone_time);
                sum += shifts[i].B - lo;
            }
            //out.println("sum: " + sum + " min: " + min);
        }
        //turn in answer
        out.println(sum - min);
        out.close();
        f.close();
    }
    public static class Shift {
        int A;
        int B;
        public Shift(int a, int b) {
            A=a;
            B=b;
        }
        public boolean contains(Shift o) {
            return A <= o.A && B >= o.B;
        }
        public String toString() {
            return "[" + A + ", " + B + "]";
        }
    }
}
