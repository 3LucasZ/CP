package Codeforces.Round778;

import java.io.*;

public class PrefixRemovals {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) out.println(solve());
        out.close();
    }

    public static String solve() throws IOException{
        String str = br.readLine();
        int N = str.length();
        int[] cnt = new int[26];

        for (int i=0;i<N;i++) cnt[str.charAt(i)-'a']++;
        for (int i=0;i<N;i++){
            cnt[str.charAt(i)-'a']--;
            if (cnt[str.charAt(i)-'a']==0) return str.substring(i);
        }
        return "";
    }
}