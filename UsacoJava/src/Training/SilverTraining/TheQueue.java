package Training.SilverTraining;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
Codeforces Round #398 Div. 2
B. The Queue
USACO Silver Training
CF Randomizer: 2100 greedy
Thoughts:
the logic was NOT BAD for a 2100! There was 2 stupid missed edge cases:
0 people in line (T1 greedy), and first person is > T1 (T1 greedy)
other then that, we want to greedily place "Jeff" at index 1 less than a person
bruteforce N positions, O(N) algorithm
THERE WAS SO MUCH GREEDY IN THIS PROBLEM AHHHH IM SO GREEDY LOL
 */
public class TheQueue {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static long T1;
    static long T2;
    static int N;
    static long T;
    static long[] arrival = new long[N];

    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        T1 = Long.parseLong(st.nextToken());
        T2 = Long.parseLong(st.nextToken());
        T = Long.parseLong(st.nextToken());
        N = Integer.parseInt(br.readLine());

        //EDGE CASE #1
        if (N==0){
            out.println(T1);
            out.close();
            return;
        }
        st = new StringTokenizer(br.readLine());
        arrival = new long[N];
        for (int i=0;i<N;i++){
            arrival[i] = Long.parseLong(st.nextToken());
        }

        //logic
        long best = Long.MAX_VALUE;
        long ans = -1;
        long finishT = T1;
        long STOP = T2-T+1;

        //EDGE CASE #2
        if (arrival[0] > T1){
            out.println(T1);
            out.close();
            return;
        }

        for (int i=0;i<N;i++){
            //get best wait
            long wait = finishT-(arrival[i]-1);
            if (wait < best) {
                best = wait;
                ans = arrival[i]-1;
            }

            finishT = Math.max(finishT + T, arrival[i] + T);
            if (finishT > STOP) break;
        }
        if (finishT <= STOP) {
            ans = finishT;
        }
        //turn in answer
        out.println(ans);
        out.close();
    }
}
