package Codeforces.Round836;

import java.io.*;
import java.util.*;
/*
PROB: XORAverage
LANG: JAVA
*/
public class XORAverage {
    static boolean submission = false;
    static boolean debug = true;

    public static void main(String[] args) throws IOException {
        setup("XORAverage");
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++){
            int N = Integer.parseInt(br.readLine());
            if (N%2==1){
                for (int j=0;j<N;j++) out.print("1 "); out.println();
            } else {
                out.print("1 3 ");
                for (int j=0;j<N-2;j++) out.print("2 "); out.println();
            }
        }
        out.close();
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