package CodeJam.Testing;

import java.io.*;
import java.util.*;
/*
Program to test interactive_tester.py for CodeJam Interactive problems
 */
public class NumberGuessing {
    static boolean submission = false;
    static boolean debug = true;

    public static void main(String[] args) throws IOException {
        //* handle TCS
        setup();
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) solve(i);
        out.close();
    }

    public static void solve(int tcs) throws IOException {
        //* parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(br.readLine());
        //* ask
        int lo = A+1; int hi=B;
        while (true){
            int mid = (lo+hi)/2;
            //try mid
            System.out.println(mid);
            //get res
            String res = br.readLine();
            if (res.equals("TOO_SMALL")){
                lo=mid+1;
            } else if (res.equals("TOO_BIG")){
                hi=mid-1;
            } else {
                break;
            }
        }
    }


    static BufferedReader br;
    static PrintWriter out;

    public static void setup() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
}