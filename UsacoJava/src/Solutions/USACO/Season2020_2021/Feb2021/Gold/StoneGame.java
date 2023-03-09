package Solutions.USACO.Season2020_2021.Feb2021.Gold;

import java.io.*;
import java.util.*;

public class StoneGame {
    static boolean submission = false;
    static boolean debug = false;

    static int N;
    static int S[];
    static int mx;

    public static void main(String[] args) throws IOException {
        //parse
        setup("");
        N = Integer.parseInt(br.readLine());
        S = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++) {
            S[i]= Integer.parseInt(st.nextToken());
            mx=Math.max(mx,S[i]);
        }
        if (debug){
            System.out.println("N: "+N+", S: "+Arrays.toString(S)+", mx: "+mx);
        }

        //presum
        int cnt[] = new int[2*mx+1];
        for (int i=1;i<=N;i++)cnt[S[i]]++;
        for (int i=1;i<=2*mx;i++)cnt[i]+=cnt[i-1];
        if (debug) System.out.println(Arrays.toString(cnt));

        //search all pairs (x,t)
        long ans = 0;
        for (int x=1;x<=mx;x++){
            boolean[] odd = new boolean[mx/x+1]; odd[0]=true;
            int odds = 0;
            for (int t=1;t<=mx/x;t++){
                int sum = cnt[x*(t+1)-1]-cnt[x*t-1];
                if (sum%2==1){
                    odd[t]=true;
                    odds++;
                }
            }
            if (debug){
                System.out.println("x: "+x);
                System.out.println("odds: "+odds);
                System.out.println("odd: "+Arrays.toString(odd));
            }
            for (int t=1;t<=mx/x;t++){
                if (odd[t]&&odd[t-1]){
                    if ((t==1&&odds==1)||(t!=1&&odds==2)) {
                        int sum = cnt[x*(t+1)-1]-cnt[x*t-1];
                        if (debug){
                            System.out.println("x: "+x+", t: "+t+" = "+sum);
                        }
                        ans += sum;
                    }
                }
            }
            if (debug){
                System.out.println();
            }
        }

        //ret
        out.println(ans);
        out.close();
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
