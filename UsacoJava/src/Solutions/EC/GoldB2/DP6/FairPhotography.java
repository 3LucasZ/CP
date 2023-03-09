package Solutions.EC.GoldB2.DP6;

import java.io.*;
import java.util.*;
/*
Fair Photography
Gold Advanced B 6
DP - optimization - 2 pointers
thoughts:
pretty easy, need to make 2 init observations
window is even
window must have cnt_w >= cnt_s
2 pointer has an obs too
only r+=2 if and only if a >> right bound is achievable ( + some greedy)
do for all l
do 2 pointer twice, one for odd and even (window even constraint)
 */
public class FairPhotography {
    //io
    static boolean submission = false;
    static boolean debug = false;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int N;
    static Cow[] cows;

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
        N = Integer.parseInt(br.readLine());
        cows = new Cow[N+1];
        cows[0] = new Cow(true, -1);
        for (int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int pos = Integer.parseInt(st.nextToken());
            boolean w = st.nextToken().equals("W");
            cows[i] = new Cow(w, pos);
        }
        Arrays.sort(cows,(a,b)->a.pos-b.pos);
        if (debug) System.out.println(Arrays.toString(cows));
        //logic part 1
        int[] whites = new int[N+1];
        for (int i=1;i<=N;i++) whites[i]=whites[i-1]+(cows[i].white?1:-1);
        if (debug) System.out.println(Arrays.toString(whites));
        int[] whitesParity = new int[N+1];
        whitesParity[N]=whites[N];
        whitesParity[N-1]=whites[N-1];
        for (int i=N-2;i>=0;i--){
            whitesParity[i]=Math.max(whitesParity[i+2], whites[i]);
        }
        if (debug) System.out.println(Arrays.toString(whitesParity));
        int bestWindow = 0;
        int l;
        int r;
        //logic part 2
        //1,3,5,...
        r=0;
        for (l=0;l<=N-1;l+=2){
            while (r+2<=N && whitesParity[r+2]-whites[l]>=0) r+=2;
            if (debug) System.out.println(l+", "+r);
            bestWindow = Math.max(bestWindow, cows[r].pos-cows[l+1].pos);
        }
        if (debug) System.out.println();
        r=1;
        for (l=1;l<=N-1;l+=2){
            while (r+2<=N && whitesParity[r+2]-whites[l]>=0) r+=2;
            if (debug) System.out.println(l+", "+r);
            bestWindow = Math.max(bestWindow, cows[r].pos-cows[l+1].pos);
        }
        //turn in answer
        out.println(bestWindow);
        out.close();
    }
    private static class Cow {
        boolean white;
        int pos;
        public Cow(boolean w, int p){
            white=w;
            pos=p;
        }
        public String toString(){
            return "["+(white?"W":"S")+", "+pos+"]";
        }
    }
}
