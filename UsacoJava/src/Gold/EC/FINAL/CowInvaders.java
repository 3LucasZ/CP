package Gold.EC.FINAL;

import java.io.*;
import java.util.*;

public class CowInvaders {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);

    static int P;
    static int A;
    static int B;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        P = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        boolean[] dp2 = new boolean[P+1];
        dp2[0]=true;

        boolean[] dp1 = new boolean[P+1];
        dp1[0]=true;
        for (int i=1;i<=P;i++){
            if ((i-A)>=0&&dp1[i-A]) {
                dp1[i]=true;
                dp2[i/2]=true;
            }
            if ((i-B)>=0&&dp1[i-B]) {
                dp1[i]=true;
                dp2[i/2]=true;
            }
        }

        for (int i=1;i<=P;i++){
            if (dp2[i])continue;
            if ((i-A)>=0&&dp2[i-A]) dp2[i]=true;
            if ((i-B)>=0&&dp2[i-B]) dp2[i]=true;
        }

        for (int i=P;i>=0;i--){
            if (dp1[i]||dp2[i]){
                out.println(i);
                out.close();
                return;
            }
        }
    }
}
