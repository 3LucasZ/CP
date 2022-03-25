package Misc.Boilerplate;

import java.io.*;
import java.util.*;

public class SimpleMultipleTCS {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) out.println(solve());
        out.close();
    }

    public static int solve() throws IOException {
        return 0;
    }
}
