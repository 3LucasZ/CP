import java.io.*;
/*
USACO 2012 November Contest, Silver
Problem 3. Balanced Cow Breeds
 */
public class BalancedCowBreeds {
    //io
    static boolean submission = false;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static String str;
    static int N;
    //const
    static final int MOD = 2012;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("bbreeds.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("bbreeds.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        str = br.readLine();
        N = str.length();
        int numGroups = N/2;
        int ans = 1;
        //logic
        int lcnt = 0;
        for (int i=0;i<N;i++) {
            if (str.charAt(i)=='('){
                lcnt++;
            }
            else {
                ans = (ans * lcnt) % MOD;
                lcnt--;
            }
        }
        for (int i=0;i<numGroups;i++) {
            ans = (ans * 2) % MOD;
        }
        //turn in answer
        out.println(ans);
        out.close();
    }
}
