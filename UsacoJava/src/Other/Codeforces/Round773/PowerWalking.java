package Other.Codeforces.Round773;

import java.io.*;
import java.util.*;

public class PowerWalking {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) throws IOException {
       int T = Integer.parseInt(br.readLine());
       for (int i=0;i<T;i++) solve();
       out.close();
    }

    public static void solve() throws IOException{
        //parse
        int N = Integer.parseInt(br.readLine());
        HashSet<Integer> groups = new HashSet<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++){
            groups.add(Integer.parseInt(st.nextToken()));
        }
        //logic
        for (int K=1;K<=N;K++){
            if (K > groups.size()) out.print(K+" ");
            else out.print(groups.size()+" ");
        }
        out.println();
    }
}
