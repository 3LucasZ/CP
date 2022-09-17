package Codeforces.Round794;

import java.io.*;
import java.util.*;

public class Linguistics {
    static boolean submission = false;
    static boolean debug = true;

    public static void main(String[] args) throws IOException {
        setup("");
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++){
            out.println(solve()?"YES":"NO");
        }
        out.close();
    }
    public static boolean solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int AB = Integer.parseInt(st.nextToken());
        int BA = Integer.parseInt(st.nextToken());
        String string = br.readLine();
        return true;
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
