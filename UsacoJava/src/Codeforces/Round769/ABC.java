package Codeforces.Round769;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ABC {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) throws IOException {
       int T = Integer.parseInt(br.readLine());
       for (int i=0;i<T;i++) solve();
       out.close();
    }

    public static void solve() throws IOException{
        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();
        int cnt = 0;
        for (int i=0;i<N;i++) if (str.charAt(i)=='0') cnt++;
        if (cnt > 1 || N - cnt > 1) out.println("NO");
        else out.println("YES");
    }
}
