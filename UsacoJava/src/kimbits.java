import java.io.*;
import java.util.*;

/*
PROB: kimbits
LANG: JAVA
 */

public class kimbits {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static int L;
    static long I;

    static int choose[][];
    public static void main(String[] args) throws IOException {
        setup("kimbits");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        I = Long.parseLong(st.nextToken());

        choose = new int[N+1][N+1];
        for (int n=0;n<=N;n++){
            choose[n][0]=1;
            for (int k=1;k<=n;k++){
                choose[n][k]=(int)((long)choose[n][k-1]*(n-k+1)/k);
            }
        }
        if (debug){
            for (int n=0;n<=N;n++)System.out.println(Arrays.toString(choose[n]));
        }

        int skip = 0;
        int bits = L;
        int[] ans = new int[N];
        for (int i=N;i>0;i--){
            //try placing 1 on i
            int newSkip = skip;
            for (int j=0;j<=bits;j++){
                newSkip+=choose[i-1][j];
            }
            //if new skip is too big, dont place a 1
            if (newSkip >= I) continue;
            bits--;
            skip=newSkip;
            ans[N-i]=1;
        }

        //ret
        for (int i=0;i<N;i++) out.print(ans[i]);
        out.println();
        out.close();
    }
    public static int choose(int n,int k){
        if (n<0 || k<0) return 0;
        if (n>k) return 0;
        return choose[n][k];
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
