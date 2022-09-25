package Training.SilverTraining;

import java.io.*;
import java.util.*;
/*
USACO 2012 December Contest, Silver
Problem 2. Wifi Setup
Notes:
Trivial 1 liner... lol extremely greedy
always take the smaller of d/2 * B and A.
 */
public class WifiSetup {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int A;
    static int B;
    static int[] loc;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("wifi.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("wifi.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        loc = new int[N];
        for (int i=0;i<N;i++) {
            loc[i] = 2*Integer.parseInt(br.readLine());
        }
        Arrays.sort(loc);
        if (!submission) out.println(Arrays.toString(loc));
        //logic
        int ans=2*A;
        for (int i=1;i<N;i++) ans += Math.min(B*(loc[i]-loc[i-1])/2, 2*A);
        //turn in answer
        out.println(ans/2 + (ans%2==0?"":".5"));
        out.close();
    }
}
