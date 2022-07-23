package Contests.Codeforces.PickNPlay;

import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class RequiredLength {
    static boolean submission = false;
    static boolean debug = false;

    static int N;
    static long X;
    static long thresh;

    public static void main(String[] args) throws IOException {
        setup("");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Long.parseLong(st.nextToken());
        thresh = (long) Math.pow(10, N-1);

        if (debug){
            System.out.println("x: "+X);
            System.out.println("thresh: "+thresh);
        }
        int time = 0;
        HashSet<Long> past = new HashSet<>();
        HashSet<Long> curr = new HashSet<>();
        past.add(X);
        while (true){
            boolean win = false;
            for (Long next : past){
                if (next >= thresh) win = true;
                long i = next;
                while (i > 0){
                    int dig = (int) (i%10);
                    i/=10;
                    curr.add(next*dig);
                }
            }
            if (win) break;

            if (debug){
                System.out.println(curr);
            }
            if (past.equals(curr)) {
                time = -1;
                break;
            }
            past = curr;
            curr = new HashSet<>();
            time++;
        }
        out.println(time);
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
