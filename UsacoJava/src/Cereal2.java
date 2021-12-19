import java.io.*;
import java.util.*;
public class Cereal2 {
    //io
    static boolean submission = false;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int M;
    static int[] f;
    static int[] s;
    static int[] occ;
    static int ans[];
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader(".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        f = new int[N];
        s = new int[N];
        occ = new int[M+1];
        ans = new int[N];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            f[i] = Integer.parseInt(st.nextToken());
            s[i] = Integer.parseInt(st.nextToken());
        }
        int cnt = 0;
        for (int i=N-1;i>=0;i--) {
            int j=i;
            int pos = f[i];
            while (true){
                if (occ[pos]==0){
                    occ[pos] = j;
                    cnt++;
                    break;
                }
                else if (occ[pos] < j) {
                    break;
                }
                else {
                    int k = occ[pos];
                }
            }
        }
        //logic
        //turn in answer
        out.println();
        out.close();
        br.close();
    }
}
