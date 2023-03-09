package Solutions.Codeforces.Round796;

import java.io.*;
import java.util.StringTokenizer;

public class PatchoulisMagicalTalisman {
    static boolean submission = false;
    static boolean debug = true;

    public static void main(String[] args) throws IOException {
        setup("");
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++){
            out.println(solve());
        }
        out.close();
    }
    public static int solve() throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        int evenCnt = 0;
        for (int i=0;i<N;i++){
            A[i]=Integer.parseInt(st.nextToken());
            if (A[i]%2==0) evenCnt++;
        }

        if (evenCnt == N){
            int min = Integer.MAX_VALUE;
            for (int i=0;i<N;i++){
                int k = A[i];
                int ops = 0;
                while (k % 2 == 0) {
                    k/=2;
                    ops++;
                }
                min = Math.min(min,ops);
            }
            return min + evenCnt - 1;
        } else {
            return evenCnt;
        }
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
