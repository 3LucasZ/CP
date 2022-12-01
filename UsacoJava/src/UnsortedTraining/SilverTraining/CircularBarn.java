package UnsortedTraining.SilverTraining;

import java.io.*;
import java.util.*;
/*
USACO 2016 February Contest, Silver
Problem 1. Circular Barn
USACO Silver Training
Notes:
Looked at solution idea multiple times
Lemma #1: if A crosses B to get to C then it is better for A -> B and B -> C
try every starting spot
pick up all the cows
walk around dropping the longest awaited cow
keep track of sum
if ran out of cows to drop, then try different starting spot
O(N^2) algorithm (N starts, N walks)
n <= 1000 so N^2 was something to look for
Gold version: N <= 100K, so still unsolved as of now
 */
public class CircularBarn {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int[] cows;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("cbarn.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        cows = new int[N];
        for (int i = 0; i < N; i++) {
            int c = Integer.parseInt(br.readLine());
            cows[i] = c;
        }
        if (!submission) System.out.println(Arrays.toString(cows));
        //logic
        for (int start = 0; start < N; start++) {
            //setup
            Queue<Integer> active = new LinkedList<>();
            int energy = 0;
            for (int i = 0; i < cows[start]; i++) {
                active.add(0);
            }
            //circular walk
            for (int t=1;t<N;t++){
                if (!submission) System.out.println(active);
                int search = (t+start)%N;
                for (int i=0;i<cows[search];i++) {
                    active.add(t);
                }
                if (active.isEmpty()) break;
                energy += Math.pow(active.poll()-t,2);
            }
            if (active.isEmpty()) continue;
            energy += Math.pow(active.poll()-N,2);
            //turn in answer
            out.println(energy);
            out.close();
        }
    }
}
