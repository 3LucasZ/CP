package Solutions.Codeforces.Round836;

import java.io.*;

/*
PROB: SeeingDouble
LANG: JAVA
*/
public class SeeingDouble {
    static boolean submission = false;
    static boolean debug = true;

    public static void main(String[] args) throws IOException {
        setup("SeeingDouble");
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++){
            StringBuilder str = new StringBuilder(br.readLine());
            out.println(str.toString()+str.reverse().toString());
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