import java.io.*;
import java.util.*;
/*
USACO 2012 December Contest, Silver
Problem 2. Wifi Setup
 */
public class WifiSetup {
    //io
    static boolean submission = false;
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
        int ans=0;
        int l=0; int r=0;
        int cost=0; int prevCost=2*A;
        for (r=0;r<=N-2;r++){
            cost = (loc[r+1]-loc[l])/2 * B + 2*A;
            if (!submission) out.println("[l: "+l+", r: "+r+", cost: "+cost+", prevCost: "+prevCost+"]");
            if (cost < prevCost + 2*A) {
                prevCost=cost;
            }
            else {
                ans += prevCost;
                l=r;
                prevCost = 2*A;
            }
        }
        ans += prevCost;
        //turn in answer
        out.println(ans/2 + (ans%2==0?"":".5"));
        out.close();
    }
}
