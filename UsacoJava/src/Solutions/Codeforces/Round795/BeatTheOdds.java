package Solutions.Codeforces.Round795;

import java.io.*;
import java.util.StringTokenizer;

public class BeatTheOdds {
    static boolean submission = false;
    static boolean debug = true;

    public static void main(String[] args) throws IOException {
        setup("");
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++){
            solve();
        }
        out.close();
    }
    public static void solve() throws IOException{
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int odd = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++){
            A[i] = Integer.parseInt(st.nextToken());
            if (A[i]%2==1) odd++;
        }
        out.println(Math.min(odd,N-odd));
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
