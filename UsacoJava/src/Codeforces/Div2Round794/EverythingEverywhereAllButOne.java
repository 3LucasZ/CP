package Codeforces.Div2Round794;

import java.io.*;
import java.util.StringTokenizer;

/*
Codeforces Round #794 (Div. 2)
A. Everything Everywhere All But One
 */
public class EverythingEverywhereAllButOne {
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
        int[] nums = new int[N];
        int sum = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++){
            int k = Integer.parseInt(st.nextToken());
            sum+=k;
            nums[i]=k;
        }

        for (int i=0;i<N;i++){
            if ((sum-nums[i])==nums[i]*(N-1)) {
                out.println("YES");
                return;
            }
        }
        out.println("NO");
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
