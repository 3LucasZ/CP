import java.io.*;
import java.util.StringTokenizer;

public class CowCamp {
    static boolean submission = false;
    static boolean debug = true;

    static int T;
    static int K;

    public static void main(String[] args) throws IOException {
        //choose function
        //parse
        setup("");
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        //E = expected roll value


        //L = P(redo)
        double L = Math.pow(0.5,T-1);
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
