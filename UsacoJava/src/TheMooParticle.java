import java.io.*;
import java.util.*;

public class TheMooParticle {
    //io
    static BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int N;
    static int[] x, y;
    static Integer cid[];
    static int minl[];
    static int maxr[];

    public static void main(String[] args) throws IOException {
        //parse input
        N = Integer.parseInt(f.readLine());
        x = new int[N];
        y = new int[N];
        cid = new Integer[N];
        minl = new int[N];
        maxr = new int[N];
        for (int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            x[i] = u;
            y[i] = v;
            cid[i] = i;
        }
        //logic
        Arrays.sort(cid, (o1, o2) -> {
            if (x[o1] == x[o2]) return y[o1] - y[o2];
            else return x[o1] - x[o2];
        });

        out.close();
        f.close();
    }
}
