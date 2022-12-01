package UnsortedTraining.SilverTraining;

import java.io.*;

/*
USACO 2013 March Contest, Silver
Problem 1. Poker Hands
USACO Silver Training
Extremely Easy - greedy layers technique
 */
public class PokerHands {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("poker.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("poker.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        N = Integer.parseInt(br.readLine());
        int stacks = 0;
        long ans = 0;
        for (int i=0;i<N;i++) {
            int stack = Integer.parseInt(br.readLine());
            if (stack > stacks){
                ans += (long)stack-stacks;
            }
            stacks = stack;
        }
        out.println(ans);
        out.close();
    }
}
