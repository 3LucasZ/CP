import java.io.*;
import java.util.StringTokenizer;

/*
PROB: UpDownSubsequence
LANG: JAVA
*/
public class UpDownSubsequenceSubtask {
    static boolean submission = false;
    static boolean debug = true;

    static int N; //#cow
    static int[] P; //cow perm
    static char[] A; //UDU seq

    public static void main(String[] args) throws IOException {
        //parse
        setup("UpDownSubsequence");
        N = Integer.parseInt(br.readLine());
        P = new int[N+1];
        A = new char[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++) P[i]=Integer.parseInt(st.nextToken());
        String str = br.readLine();
        for (int i=1;i<=N;i++) A[i]=str.charAt(i-1);
        //2d dp
        int[][] dp = new int[N+1][N+1];
        for (int i=1;i<N;i++) dp[i][0]=P[i];
        int ans = 0;
        for (int i=1;i<N;i++){
            for (int j=0;j<N;j++){
                if (dp[i][j]!=0){
                    //dp[i+1][j]=Math.min(dp[i+1][j],);
                    //if (P[i+1])
                }
            }
        }
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