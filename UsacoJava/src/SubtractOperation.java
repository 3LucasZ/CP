import java.io.*;
import java.util.*;

public class SubtractOperation {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) out.println(solve());
        out.close();
    }

    public static String solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int K=Integer.parseInt(st.nextToken());
        HashSet<Integer> elements = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) elements.add(Integer.parseInt(st.nextToken()));
        for (int e : elements){
            if (elements.contains(K+e)) return "YES";
        }
        return "NO";
    }
}
