package Other.Codeforces.Round830;

import java.io.*;

/*
PROB: Ugu
LANG: JAVA
*/
public class Ugu {
    static boolean submission = false;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        //* handle TCS
        setup("Ugu");
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) solve(i);
        out.close();
    }

    static int N;
    public static void solve(int tcs) throws IOException {
        if (debug) System.out.println("Case: " + tcs);
        //* parse
        N = Integer.parseInt(br.readLine());
        String str = br.readLine();
        int last = -1;
        int first = str.charAt(0)-'0';
        int components = 0;
        for (int i=0;i<N;i++){
            int cur = str.charAt(i)-'0';
            if (cur!=last) components++;
            last=cur;
        }
        //* greedy
        if (debug) System.out.println("Components: "+components);
        if (components==1) out.println(0);
        else out.println(components-2+first);
    }


    static BufferedReader br;
    static PrintWriter out;

    public static void setup(String fileName) throws IOException {
        if (submission) {
            br = new BufferedReader(new FileReader(fileName + ".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
    }
}