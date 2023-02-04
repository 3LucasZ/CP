package Other.Codeforces.Round794;

import java.io.*;
import java.util.StringTokenizer;

public class OddSubarrays {
    static boolean submission = false;
    static boolean debug = true;

    public static void main(String[] args) throws IOException {
        setup("");
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) {
            solve();
        }
        out.close();
    }
    public static void solve() throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) arr[i]=Integer.parseInt(st.nextToken());
        int ans = 0;
        for (int i=1;i<N;i++) {
            if (arr[i]<arr[i-1]){
                ans++;
                i++;
            }
        }
        out.println(ans);
    }




















    static BufferedReader br;
    static PrintWriter out;
    public static void setup(String fileName) throws IOException {
        if (submission) {
            br = new BufferedReader(new FileReader(fileName+".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
    }
}
