package Contests.Codeforces.Round796;

import java.io.*;

public class ManipulatingHistory {
    static boolean submission = false;
    static boolean debug = true;

    public static void main(String[] args) throws IOException {
        setup("");

        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++){
            out.println(solve());
        }
        out.close();
    }
    public static char solve() throws IOException{
        int N = Integer.parseInt(br.readLine());
        int ans = 0;
        for (int i=0;i<2*N+1;i++){
            String str = br.readLine();
            for (int j=0;j<str.length();j++){
                ans^=str.charAt(j);
            }
        }
        return (char) ans;
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
