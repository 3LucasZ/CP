import java.io.*;

public class Prob1 {
    static boolean submission = false;
    static boolean debug = true;

    public static void main(String[] args) throws IOException {
        setup("");
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++){
            out.println(solve());
        }
        out.close();
    }
    public static long solve() throws IOException {
        long N = Long.parseLong(br.readLine());
//        boolean foundZero = false;
//        for (int i=0;N>0;i++){
//            int dig = (int) (N%2);
//            if (dig == 1){
//                if (foundZero) return 3;
//                else {
//
//                }
//            }
//            else {
//                foundZero = true;
//            }
//            N/=2;
//        }
        long t = 1;
        while (t < 4*N){
            if ((N&t)>0 && (N^t)>0) return t;
            if ((N&(t+1))>0 && (N^(t+1))>0) return t+1;
            t*=2;
        }
        return -1;
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
