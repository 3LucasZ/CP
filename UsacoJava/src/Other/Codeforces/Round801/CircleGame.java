package Other.Codeforces.Round801;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CircleGame {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = true;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) solve();
        out.close();
    }

    public static void solve() throws IOException{
        //parse
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        for (int i=0;i<N;i++) A[i]=Integer.parseInt(st.nextToken());

        //log
        if (N%2==1) out.println("Mike");
        else {
            int min = Integer.MAX_VALUE;
            for (int i=0;i<N;i++){
                min=Math.min(A[i],min);
            }
            for (int i=0;i<N;i++){
                A[i]-=min;
                if (A[i]==0 && i%2==0) {
                    out.println("Joe");
                    break;
                }
                else if (A[i]==0 && i%2==1) {
                    out.println("Mike");
                    break;
                }
            }
        }
    }
}
