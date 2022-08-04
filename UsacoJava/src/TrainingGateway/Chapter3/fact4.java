package TrainingGateway.Chapter3;

import java.io.*;
import java.util.*;

/*
PROB: fact4
LANG: JAVA
 */

public class fact4 {
    static boolean submission = true;
    static boolean debug = true;

    public static void main(String[] args) throws IOException {
        setup("fact4");
        int N = Integer.parseInt(br.readLine());
        long ret = 1;
        for (int i=1;i<=N;i++){
            ret*=i;
            while (ret%10==0) ret/=10;
            ret%=100000;
        }

        out.println(ret%10);
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
