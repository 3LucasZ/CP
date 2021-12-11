package Silver2019_1;

import java.io.*;
import java.util.*;

public class MooBuzz {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int n;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            f = new BufferedReader(new FileReader("moobuzz.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("moobuzz.out")));
        } else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        n = Integer.parseInt(f.readLine());
        //logic
        int cycles = (n - 1) / 8;
        int place = (n - 1) % 8;
        int[] places = new int[]{1, 2, 4, 7, 8, 11, 13, 14};
        out.println(cycles * 15 + places[place]);
        out.close();
        f.close();
    }
}
