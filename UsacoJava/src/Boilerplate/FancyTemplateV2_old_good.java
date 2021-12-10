package Boilerplate;

import java.io.*;

public class FancyTemplateV2_old_good {
    //io
    static boolean submission = false;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int n;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            f = new BufferedReader(new FileReader(".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(".out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        n = Integer.parseInt(f.readLine());
        //logic
        //turn in answer
        out.println();
        out.close();
        f.close();
    }
}