package Solutions.UnsortedTraining.SilverTraining;

import java.io.*;
import java.util.*;
/*
USACO 2013 November Contest, Silver
Problem 2. Crowded Cows
USACO Silver Training
Fun! Sliding window efficiency
 */
public class CrowdedCows {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int D;
    static Cow[] cows;
    static int[] lmaxHeight;
    static int[] rmaxHeight;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("crowded.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("crowded.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        cows = new Cow[N];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            cows[i] = new Cow(x, h);
        }
        //logic
        Arrays.sort(cows, (a,b)->a.x-b.x);
        //if (!submission) out.println(Arrays.toString(cows));
        //sliding window
        PriorityQueue<Cow> window = new PriorityQueue<>((a,b)->b.h-a.h);
        int l;
        int r;

        lmaxHeight = new int[N];
        r=0;
        for (l=0;l<N;l++) {
            while (r < N && cows[r].x-cows[l].x <= D) {
                window.add(cows[r]);
                r++;
            }
            lmaxHeight[l] = window.peek().h;
            window.remove(cows[l]);
        }
        //if (!submission) out.println(Arrays.toString(lmaxHeight));

        rmaxHeight = new int[N];
        l=N-1;
        for (r=N-1;r>=0;r--) {
            while (l >= 0 && cows[r].x-cows[l].x <= D) {
                window.add(cows[l]);
                l--;
            }
            rmaxHeight[r] = window.peek().h;
            window.remove(cows[r]);
        }
        //if (!submission) out.println(Arrays.toString(rmaxHeight));
        //check over windows
        int ans = 0;
        for (int i=0;i<N;i++) {
            if (lmaxHeight[i] >= 2*cows[i].h && rmaxHeight[i] >= 2*cows[i].h) ans++;
        }
        //turn in answer
        out.println(ans);
        out.close();
    }
    private static class Cow {
        int x;
        int h;
        public Cow(int x1, int h1){
            x=x1;
            h=h1;
        }
        public String toString(){
            return "["+x+": "+h+"]";
        }
    }
}
