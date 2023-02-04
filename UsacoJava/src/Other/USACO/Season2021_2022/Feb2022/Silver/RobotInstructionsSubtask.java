package Other.USACO.Season2021_2022.Feb2022.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RobotInstructionsSubtask {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = true;

    //param
    static int N;
    static long fin[] = new long[2];
    static long dx[];
    static long dy[];
    static int ans[];
    public static void main(String[] args) throws IOException {
        //parse input
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        fin[0] = Integer.parseInt(st.nextToken());
        fin[1] = Integer.parseInt(st.nextToken());
        dx = new long[N];
        dy = new long[N];
        ans = new int[N+1];
        for (int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            dx[i] = Integer.parseInt(st.nextToken());
            dy[i] = Integer.parseInt(st.nextToken());
        }
        //ut
        if (debug){
            System.out.println(Arrays.toString(getSum(4)));
        }
        //logic: bash every thing
        for (long i=1;i<(long)Math.pow(2,N);i++) {
            long[] res = getSum(i);
            if (res[0]==fin[0] && res[1]==fin[1]) ans[Integer.bitCount((int) i)]++;
        };
        //turn in answer
        for (int i=1;i<=N;i++){
            out.println(ans[i]);
        }
        out.close();
    }
    public static long[] getSum(long num){
        int index=0;
        long[] ret = new long[]{0,0};
        while (num > 0){
            if (num%2==1){
                ret[0]+=dx[index];
                ret[1]+=dy[index];
            }
            num/=2;
            index++;
        }
        return ret;
    }
}
