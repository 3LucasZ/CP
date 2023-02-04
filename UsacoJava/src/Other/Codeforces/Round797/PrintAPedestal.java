package Other.Codeforces.Round797;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class PrintAPedestal {
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
        int base = N/3;
        int rem = N%3;
        int a = base;
        int b = base;
        int c = base;
        if (rem==0) {
            c--;
            a++;
        } else if (rem==1){
            a+=2;
            c--;
        } else {
            a+=2;
            b++;
            c--;
        }
        out.println(b+" "+a+" "+c);
    }
}
