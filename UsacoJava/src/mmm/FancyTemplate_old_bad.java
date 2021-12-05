package mmm;

import java.io.*;
import java.util.*;

public class FancyTemplate_old_bad {
    //default
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int n;
    static String colors;

    public static void main(String[] args) throws IOException {
        //setup
        if (submission) {
            f = new BufferedReader(new FileReader(".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(".out")));
            n = Integer.parseInt(f.readLine());
            colors = f.readLine();
        }
        else {
            n = 7;
            colors = "ABCDABC";
        }
        //logic
        //print
        if (submission) {
            out.println();
            f.close();
            out.close();
        }
        else {
            System.out.println();
        }
    }
}