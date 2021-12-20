import java.io.*;
import java.util.*;
/*
USACO 2020 US Open Contest, Silver
Problem 3. The Moo Particle
 */

public class TheMooParticle {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static Particle[] particles;
    static ArrayList<Integer> componentY = new ArrayList<>();
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
            particles[i] = new Particle(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }
        //logic
        Arrays.sort(particles, (a,b)->a.x-b.x);
        int lowest = Integer.MAX_VALUE;
        for (int i=0;i<N;i++) {
            if (particles[i].y<lowest) {
                componentY.add(particles[i].y);
                lowest = particles[i].y;
            }
            else {
                ArrayList<Integer> toRemove = new ArrayList<>();
                for (int y : componentY) {
                    if (y<=particles[i].y && y!=lowest){
                        toRemove.add(y);
                    }
                }
                for (Integer y: toRemove) {
                    componentY.remove(y);
                }
            }
        }
        //turn in answer
        out.println(componentY.size());
        out.close();
    }
    private static class Particle{
        int x;
        int y;
        public Particle(int x1, int y1){
            x=x1;
            y=y1;
        }
    }
}
