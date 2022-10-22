package Codeforces.Edu121;

import java.io.*;

public class MinorReduction {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) {
            solve(br.readLine());
        }
        out.close();
    }
    public static void solve(String num){
        int latestDoubleCombine = -1;
        int ld = -1;
        int firstSingleCombine = -1;
        int fs = -1;
        for (int i=0;i<=num.length()-2;i++) {
            int f = num.charAt(i)-48;
            int s = num.charAt(i+1)-48;
            if (f+s <= 9 && firstSingleCombine==-1) {
                firstSingleCombine = i;
                fs = f+s;
            }
            else if (f+s >= 10) {
                latestDoubleCombine = i;
                ld = f+s;
            }
        }
        if (latestDoubleCombine == -1){
            out.print(num.substring(0, firstSingleCombine));
            out.print(fs);
            out.print(num.substring(firstSingleCombine+2));
        } else {
            out.print(num.substring(0, latestDoubleCombine));
            out.print(ld);
            out.print(num.substring(latestDoubleCombine+2));
        }
        out.println();
    }
}
