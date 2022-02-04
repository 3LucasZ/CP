import java.io.*;
import java.util.*;

public class SearchingForSoulmates {
    //io
    static boolean debug = false;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int N;
    public static void main(String[] args) throws IOException {
        //test cases
        N = Integer.parseInt(br.readLine());
        for (int i=0;i<N;i++) {
            out.println(solve());
        }
        out.close();
    }
    public static long solve() throws IOException {
        //parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        long u = Long.parseLong(st.nextToken());
        long v = Long.parseLong(st.nextToken());
        ArrayList<Long> chain1 = createChain1(u);
        if (debug) System.out.println(chain1);
        ArrayList<Long> chain2 = createChain2(v);
        if (debug) System.out.println(chain2);
        long ans = Long.MAX_VALUE;
        for (int i=0;i<chain1.size();i++) {
            for (int j=0;j<chain2.size();j++) {
                if (chain1.get(i) <= chain2.get(j)) {
                    ans = Math.min(ans, i+j+chain2.get(j)-chain1.get(i));
                }
            }
        }
        return ans;
    }
    public static ArrayList<Long> createChain1(long n){
        ArrayList<Long> ret = new ArrayList<>();
        while (n > 1) {
            ret.add(n);
            if (n % 2==0) n/=2;
            else n++;
        }
        ret.add(1L);
        return ret;
    }
    public static ArrayList<Long> createChain2(long n){
        ArrayList<Long> ret = new ArrayList<>();
        while (n > 1) {
            ret.add(n);
            if (n % 2==0) n/=2;
            else n--;
        }
        ret.add(1L);
        return ret;
    }
}
