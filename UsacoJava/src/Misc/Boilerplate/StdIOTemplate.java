package Misc.Boilerplate;

import java.io.*;

public class StdIOTemplate {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int N;
    public static void main(String[] args) throws IOException {
        //parse input
        N = Integer.parseInt(br.readLine());
        //logic
        //turn in answer
        out.println();
        out.close();
    }
}