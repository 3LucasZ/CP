package Codeforces.Round771;

import java.io.*;
import java.util.*;
/*
Codeforces Round #771 (Div. 2)
B. Odd Swap Sort
Thoughts:
Ez first try :O
array can be sorted IF AND ONLY IF  all evens are non decreasing and all odds are non decreasing
in their current order
 */
public class OddSwapSort {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) {
            out.println(solve()?"Yes":"No");
        }
        out.close();
    }
    public static boolean solve() throws IOException {
        int N = Integer.parseInt(br.readLine());
        int lastEven = -2;
        int lastOdd = -1;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++){
            int num = Integer.parseInt(st.nextToken());
            if (num%2==0 && num<lastEven) return false;
            if (num%2==1 && num<lastOdd) return false;
            if (num%2==0) lastEven=num;
            if (num%2==1) lastOdd=num;
        }
        return true;
    }
}
