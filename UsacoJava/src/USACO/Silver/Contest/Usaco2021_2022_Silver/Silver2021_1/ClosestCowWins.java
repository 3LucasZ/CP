package USACO.Silver.Contest.Usaco2021_2022_Silver.Silver2021_1;

import java.io.*;
import java.util.*;

/*
USACO 2021 December Contest, Silver
Problem 1. Closest Cow Wins
USACO Competition Redo
Thoughts:
VERY HARD GREEDY!!
place lefty cow, claim all lefty (same with righty)
between any 2 Nhoj adj cow, can place 1 or 2 cow
if 1 cow, sliding window -> best sum
if 2 cow, tot sum - best sum
sort the potentials and add the best ones
we can add tot sum - best sum bc it is < best sum and if its picked best sum would have been picked already
 */
public class ClosestCowWins {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;

    //param
    static int K;
    static int M;
    static int N;
    static Grass[] grasses;
    static int[] nhoj;
    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        grasses = new Grass[K];
        nhoj = new int[M];
        for (int i=0;i<K;i++){
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            grasses[i] = new Grass(t,p);
        }
        for (int i=0;i<M;i++){
            nhoj[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(grasses, (a,b)->a.position-b.position);
        Arrays.sort(nhoj);

        //logic
        ArrayList<Long> potential = new ArrayList<>();

        long farLeft = 0;
        int grassPointer = 0;
        while (grassPointer < K && grasses[grassPointer].position < nhoj[0]) {
            farLeft += grasses[grassPointer].taste;
            grassPointer++;
        }
        potential.add(farLeft);
        if (debug) System.out.println("Far left: "+farLeft);

        //EXTREMELY MESSY GREEDY PROCESSING
        for (int i=0;i<M-1;i++){
            int left = nhoj[i];
            int right = nhoj[i+1];
            int window = (right-left+1)/2;
            long bestSum = 0;
            long sum = 0;

            int grassPointer2 = grassPointer;
            long sum2 = 0;
            while (grassPointer2 < K && grasses[grassPointer2].position < right){
                sum2+=grasses[grassPointer2].taste;
                grassPointer2++;
            }

            grassPointer2 = grassPointer;
            while (grassPointer < K && grasses[grassPointer].position < right) {
                while (grassPointer2 < K && grasses[grassPointer2].position < right && grasses[grassPointer2].position - grasses[grassPointer].position < window) {
                    sum+=grasses[grassPointer2].taste;
                    grassPointer2++;
                }
                bestSum = Math.max(bestSum, sum);
                sum-=grasses[grassPointer].taste;
                grassPointer++;
            }

            potential.add(bestSum);
            potential.add(sum2-bestSum);

            if (debug) {
                System.out.println("["+nhoj[i]+", "+nhoj[i+1]+"]: ["+bestSum+", "+(sum2-bestSum)+"]");
            }
        }

        long farRight = 0;
        while (grassPointer < K){
            farRight += grasses[grassPointer].taste;
            grassPointer++;
        }
        potential.add(farRight);
        if (debug) System.out.println("Far right: "+farRight);

        Collections.sort(potential);
        Collections.reverse(potential);
        if (debug) {
            System.out.println(potential);
        }

        //turn in answer
        long ans = 0;
        for (int i=0;i<Math.min(N,potential.size());i++){
            ans+=potential.get(i);
        }
        out.println(ans);
        out.close();
    }

    private static class Grass {
        int taste;
        int position;
        public Grass (int t, int p){
            taste=t;
            position=p;
        }
    }
}