package Other.Codeforces.Edu121;

import java.io.*;
import java.util.*;
public class EquidistantLetters {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        for (int i=0;i<N;i++) {
            solve(br.readLine());
        }
        out.close();
    }
    public static void solve(String str){
        char[] split = new char[str.length()];
        for (int i=0;i<str.length();i++) {
            split[i] = str.charAt(i);
        }
        Arrays.sort(split);
        out.println(split);
    }
}
