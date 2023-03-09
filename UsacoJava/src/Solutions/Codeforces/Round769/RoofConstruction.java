package Solutions.Codeforces.Round769;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class RoofConstruction {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int N;
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) solve();
        out.close();
    }
    public static void solve() throws IOException {
        int N = Integer.parseInt(br.readLine()) - 1;
        int threshold = Integer.highestOneBit(N);

        for (int i=threshold-1;i>=0;i--) out.print(i+" ");
        for (int i=threshold;i<=N;i++) out.print(i+" ");
        out.println();
    }
}
