import java.io.*;
import java.util.*;
/*

 */
public class Firecrackers {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int T;
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++){
            solve();
        }
        out.close();
    }
    public static int solve() throws IOException {
        //io
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int[] firecrackers = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<M;i++) {
            firecrackers[i] = Integer.parseInt(st.nextToken());
        }
        //logik
        Arrays.sort(firecrackers);
        int t_caught;
        int t_police;
        if (A < B) {
            t_police = B-A-1;
            t_caught = B-2;
        }
        else {
            t_police = A-B-1;
            t_caught = N-B-1;
        }
//        out.println("t_caught: "+t_caught);
//        out.println("t_police: "+t_police);
        //binary search on the amt of firecrackers he can bring
        return 0;
    }
}
