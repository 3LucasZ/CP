package Other.Codeforces.Round797;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class RestoringTheDurationOfTasks {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = true;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) solve();
        out.close();
    }
    public static void solve()throws IOException{
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] s = new int[N];
        int[] f = new int[N];
        for (int i=0;i<N;i++) s[i]=Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) f[i] = Integer.parseInt(st.nextToken());

        out.print(f[0]-s[0]+" ");
        for (int i=1;i<N;i++){
            out.print(f[i]-Math.max(f[i-1],s[i])+" ");
        }
        out.println();
    }
}
