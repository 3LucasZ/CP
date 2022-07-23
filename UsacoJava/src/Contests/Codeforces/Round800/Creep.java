package Contests.Codeforces.Round800;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Creep {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = true;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) solve();
        out.close();
    }

    public static void solve() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int iterate = Math.max(A,B);
        for (int i=0;i<iterate;i++){
            if (A>0)out.print(0);
            if (B>0) out.print(1);
            A--;
            B--;
        }
        out.println();
    }
}
