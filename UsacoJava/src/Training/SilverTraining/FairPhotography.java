package Training.SilverTraining;

import java.io.*;
import java.util.*;
/*
USACO 2014 US Open, Silver
Problem 1. Fair Photography
USACO Silver Training
Thoughts:
Looked at solution :(
for every right cow find best left cow
O(Nlog(N))
 */
public class FairPhotography {
    // io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    // param
    static int N;
    static Cow[] cows;
    static int[] presum;
    static TreeSet<Integer> evenPresum = new TreeSet<>((a,b)->presum[a]-presum[b]);
    static TreeSet<Integer> oddPresum = new TreeSet<>((a,b)->presum[a]-presum[b]);
    public static void main(String[] args) throws IOException {
        // io
        if (submission) {
            br = new BufferedReader(new FileReader("fairphoto.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("fairphoto.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        // parse input
        N = Integer.parseInt(br.readLine());
        cows = new Cow[N+1];
        presum = new int[N+1];
        // dummy cow
        cows[0]=new Cow(-1, true);
        for (int i=1;i<=N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Cow next = new Cow(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0)=='W');
            cows[i] = next;
        }
        // sort
        Arrays.sort(cows, (a,b)->a.pos-b.pos);
        if (!submission) System.out.println(Arrays.toString(cows));
        // presum create
        for (int i=1;i<=N;i++) {
            presum[i]=presum[i-1];
            if (cows[i].white)presum[i]++;
            else presum[i]--;
        }
        if (!submission) System.out.println(Arrays.toString(presum));
        // presum even
        for (int i=0;i<=N;i+=2){
            if (evenPresum.isEmpty() || presum[i]<presum[evenPresum.last()]){
                evenPresum.add(i);
            }
        }
        if (!submission) System.out.println(evenPresum);
        // presum odd
        for (int i=1;i<=N;i+=2){
            if (oddPresum.isEmpty() || presum[i]<presum[oddPresum.last()]){
                oddPresum.add(i);
            }
        }
        if (!submission) System.out.println(oddPresum);
        //logic
        // get best even
        int beven = 0;
        for (int i=0;i<=N;i+=2) {

            if (evenPresum.floor(i)==null || evenPresum.floor(i)+1>N) continue;
            int size = cows[i].pos-cows[evenPresum.floor(i)+1].pos;
            //if (!submission) out.println(size);
            beven = Math.max(beven, size);
        }
        if (!submission) System.out.println(beven);

        //get best odd
        int bodd = 0;
        for (int i=1;i<=N;i+=2) {
            if (oddPresum.floor(i)==null || oddPresum.floor(i)+1>N) continue;
            int size = cows[i].pos-cows[oddPresum.floor(i)+1].pos;
            //if (!submission) out.println(size);
            bodd = Math.max(bodd, size);
        }
        if (!submission) System.out.println(bodd);
        //turn in answer
        out.println(Math.max(bodd,beven));
        out.close();
    }
    private static class Cow {
        boolean white;
        int pos;
        public Cow(int p, boolean w){
            pos=p;
            white=w;
        }
        public String toString() {
            return "["+pos+": "+white+"]";
        }
    }
}
