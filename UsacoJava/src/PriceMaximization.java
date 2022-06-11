import java.io.*;
import java.util.*;

public class PriceMaximization {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = true;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++){
            solve();
        }
        out.close();
    }
    public static void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        long ret = 0;
        st = new StringTokenizer(br.readLine());
        ArrayList<Integer> mod = new ArrayList<>();
        for (int i=0;i<N;i++) {
            int next = Integer.parseInt(st.nextToken());
            if (next%K!=0) mod.add(next%K);
            ret+=next/K;
        }

        Collections.sort(mod);
        for (int i=0;i<mod.size()/2;i++){
            int l = mod.get(i);
            int r = mod.get(mod.size()-1-l);
            if (l+r>=K){
                ret++;
            }
        }

        out.println(ret);
    }
}
