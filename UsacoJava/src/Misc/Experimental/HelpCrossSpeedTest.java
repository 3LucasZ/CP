package Misc.Experimental;

import java.util.*;
import java.io.*;
/*
Java solution to:
USACO 2017 February Contest, Silver
Problem 1. Why Did the Cow Cross the Road
from online. My solution is extremely similar but "Too slow"
Turns out this is 500ms faster.
My guesses for issue: slow IO, parallel array instead of custom object, additional random code that was not really needed
 */
public class HelpCrossSpeedTest {
    public static void main (String[] args) throws IOException {
        Kattio io = new Kattio("helpcross");
        int numChickens = io.nextInt();
        int numCows = io.nextInt();
        int[] chicken = new int[numChickens]; // where the chicken are located
        int[] earliest = new int[numCows];
        int[] latest = new int[numCows];
        boolean[] marked = new boolean[numCows];

        for (int x = 0; x < numChickens; x++) {
            chicken[x] = io.nextInt();
        }
        for (int x = 0; x < numCows; x++){
            earliest[x] = io.nextInt();
            latest[x] = io.nextInt();
        }
        Arrays.sort(chicken);

        int count = 0;
        for (int x = 0; x < numChickens; x++) { // loop through each chicken
            int smallest = -1;
            // find the cow that ends the soonest that the chicken is in its range
            for (int y = 0; y < numCows; y++) {
                // the cow has not crossed the road yet and the
                // chicken's position is able to help the cow cross
                if (! marked[y] && chicken[x] >= earliest[y] && chicken[x] <= latest[y]) {
                    if (smallest < 0 || latest[y] < latest[smallest]) {
                        smallest = y;
                    }
                }
            }
            if (smallest != -1) {
                marked[smallest] = true;
                count++;
            }
        }
        io.println(count);
        io.close();
    }
    static class Kattio extends PrintWriter {
        private BufferedReader r;
        private StringTokenizer st;

        // standard input
        public Kattio() { this(System.in, System.out); }
        public Kattio(InputStream i, OutputStream o) {
            super(o);
            r = new BufferedReader(new InputStreamReader(i));
        }
        // USACO-style file input
        public Kattio(String problemName) throws IOException {
            super(new FileWriter(problemName + ".out"));
            r = new BufferedReader(new FileReader(problemName + ".in"));
        }

        // returns null if no more input
        public String next() {
            try {
                while (st == null || !st.hasMoreTokens())
                    st = new StringTokenizer(r.readLine());
                return st.nextToken();
            } catch (Exception e) { }
            return null;
        }

        public int nextInt() { return Integer.parseInt(next()); }
        public double nextDouble() { return Double.parseDouble(next()); }
        public long nextLong() { return Long.parseLong(next()); }
    }
}