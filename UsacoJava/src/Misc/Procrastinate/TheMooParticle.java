package Misc.Procrastinate;

import java.io.*;
import java.util.*;
/*
USACO 2020 US Open Contest, Silver
Problem 3. The Moo Particle
USACO Practice
Concepts: clever connected components
Copy cat sad :(
 */

public class TheMooParticle {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static Particle[] particles;
    static int[] minl;
    static int[] maxr;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("moop.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("moop.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        particles = new Particle[N];
        for (int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            particles[i] = new Particle(x,y);
        }
        //logic
        Arrays.sort(particles, (a,b)->{
            if (a.x==b.x) return a.y-b.y;
            return a.x-b.x;
        });
        //out.println(Arrays.toString(particles));
        minl = new int[N];
        minl[0] = particles[0].y;
        for (int i=1;i<N;i++) {
            minl[i] = Math.min(minl[i-1],particles[i].y);
        }
        maxr = new int[N];
        maxr[N-1] = particles[N-1].y;
        for (int i=N-2;i>=0;i--){
            maxr[i] = Math.max(maxr[i+1],particles[i].y);
        }
        int ans = 1;
        for (int i=0;i<N-1;i++) {
            if (minl[i] > maxr[i+1]) ans++;
        }
        out.println(ans);
        out.close();
    }
    private static class Particle{
        int x;
        int y;
        public Particle(int x1, int y1){
            x=x1;
            y=y1;
        }
        public String toString() {
            return "["+x+", "+y+"]";
        }
    }
}
