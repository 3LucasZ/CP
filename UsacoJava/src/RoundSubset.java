/*
CodeForces
 */
import java.io.*;
import java.util.*;
public class RoundSubset {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static int N;
    static int K;
    static int num[];
    public static void main(String[] args) throws IOException {
        //parse input + Legendre
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        num = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            long u = Long.parseLong(st.nextToken());
            for (int F=0;F<=18;F++){
                if (u % (long)Math.pow(5, F) != 0) {
                    num[i] = F-1;
                    break;
                }
            }
        }
        //logic
        Arrays.sort(num);
        //out.println(Arrays.toString(num));
        int ans = 0;
        for (int i=N-K;i<N;i++) {
            ans += num[i];
        }
        out.println(ans);
        out.close();
    }
}
