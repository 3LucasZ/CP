package UnsortedTraining.SilverTraining;

import java.io.*;
import java.util.*;
/*
USACO 2014 December Contest, Silver
Problem 3. Cow Jog
USACO Silver Training
 */
public class CowJog {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int T;
    static long[] positions;
    static long[] speeds;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("cowjog.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("cowjog.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        positions = new long[N];
        speeds = new long[N];
        for (int i=N-1;i>=0;i--) {
            st = new StringTokenizer(br.readLine());
            positions[i] = Integer.parseInt(st.nextToken());
            speeds[i] = Integer.parseInt(st.nextToken());
        }
        //out.println(Arrays.toString(positions));
        //logic
        int ans = N;
        positions[0] += speeds[0]*T;
        for (int i=1;i<N;i++) {
            positions[i] += speeds[i]*T;
            if (positions[i] >= positions[i-1]){
                ans--;
                positions[i] = positions[i-1];
            }
        }
        //turn in answer
        out.println(ans);
        out.close();
    }
}
