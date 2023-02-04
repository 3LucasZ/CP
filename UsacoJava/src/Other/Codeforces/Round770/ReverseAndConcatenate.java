package Other.Codeforces.Round770;

import java.io.*;
import java.util.*;
/*
Codeforces Round #770
A. Reverse and Concatenate
 */
public class ReverseAndConcatenate {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int T;
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++){
            solve();
        }
        out.close();
    }
    public static void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        st.nextToken();
        int K = Integer.parseInt(st.nextToken());
        String str = br.readLine();
        boolean symmetric = true;
        for (int i=0;i<str.length();i++){
            if (str.charAt(i)!=str.charAt(str.length()-1-i))symmetric = false;
        }
        if (symmetric || K==0) out.println(1);
        else out.println(2);
    }
}