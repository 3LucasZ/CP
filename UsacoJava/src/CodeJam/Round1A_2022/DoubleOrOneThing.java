package CodeJam.Round1A_2022;

import java.io.*;
import java.util.*;

public class DoubleOrOneThing {
    static boolean submission = false;
    static boolean debug = false;


    public static void main(String[] args) throws IOException {
        //* handle TCS
        setup();
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) solve(i);
        out.close();
    }

    static String str;
    public static void solve(int tcs) throws IOException {
        print("Case #" + tcs + ": ");
        if (debug) println("");
        //* parse
        str = br.readLine();
        //* log
        StringBuilder sb = new StringBuilder(""+str.charAt(str.length()-1));
        boolean prevDoubled = false;
        for (int i=str.length()-2;i>=0;i--){
            sb.append(str.charAt(i));
            if (str.charAt(i)==str.charAt(i+1) && prevDoubled) {
                sb.append(str.charAt(i));
            }
            else if (str.charAt(i) > str.charAt(i+1)){
                prevDoubled=false;
            }
            else if (str.charAt(i) < str.charAt(i+1)) {
                sb.append(str.charAt(i));
                prevDoubled=true;
            }
        }
        println(sb.reverse());
    }


    static BufferedReader br;
    static PrintWriter out;

    public static void setup() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    public static void print(Object str) {
        if (debug) System.out.print(str);
        else out.print(str);
    }

    public static void println(Object str) {
        if (debug) System.out.println(str);
        else out.println(str);
    }
}