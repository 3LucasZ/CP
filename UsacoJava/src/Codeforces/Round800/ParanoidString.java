package Codeforces.Round800;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ParanoidString {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = true;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) solve();
        out.close();
    }

    public static void solve() throws IOException{
        int N = Integer.parseInt(br.readLine());
        char[] str = (" "+br.readLine()).toCharArray();

        long ans = 0;

        //a case
        for (int i=1;i<=N;i++){
            ans++;
        }

//        //ab case
//        for (int i=2;i<=N;i++){
//            if(str[i]!=str[i-1])ans++;
//        }

//        //ab... case
//        int r=1;
//        for (int l=1;l<=N;l++){
//            r=Math.max(r,l+2);
//            if(str[l]=='0'){
//                int nextR = r;
//                while (nextR<=N && str[nextR]!='1') nextR++;
//                if (str[nextR]=='1') r=nextR;
//            }
//        }

//        int[] zeros = new int[N+1];
//        for (int i=1;i<=N;i++){
//            zeros[i]=zeros[i-1];
//            if(str[i]=='0')zeros[i]++;
//
//            if(i>=3 && str[i]=='1')ans+=zeros[i-2];
//        }
//
        for (int i=2;i<=N;i++){
            if (str[i]!=str[i-1])ans+=i-1;
        }

        out.println(ans);
    }
}
/*
//00000 case
        pointer=0;
        for (int i=1;i<=N;i++){
            if (str[i]=='0') {
                int len = i-pointer;
                if (len > 1) {
                    ans-=(len-1);
                    if (pointer!=0) ans-=1;
                }
            }
            else {
                pointer = i;
            }
        }

        //11111 case
        pointer=N+1;
        for (int i=N;i>=1;i--){
            if (str[i]=='1'){
                int len = pointer-i;
                if (len > 1) {
                    ans-=(len-1);
                    if (pointer!=N+1) ans-=1;
                }
            }
            else {
                pointer = i;
            }
        }
 */