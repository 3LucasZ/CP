import java.io.*;
import java.util.*;
/*
LANG: JAVA
TASK: subset
 */
public class subset {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("subset.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());

        //logic
        int tot=N*(N+1)/2;
        if (tot%2==1) {
            out.println(0);
            out.close();
            return;
        }
        long[] dp = new long[tot+1];
        dp[0]=1;
        for (int val=1;val<=N;val++){
            for (int sum=tot;sum>=1;sum--){
                int search = sum-val;
                if (search < 0) continue;
                dp[sum]+=dp[search];
            }
        }
        if (!submission) {
            System.out.println(tot);
            System.out.println(Arrays.toString(dp));
        }
        //turn in answer
        out.println(dp[tot/2]/2);
        out.close();
    }
}
