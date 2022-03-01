import java.io.*;
import java.util.*;

public class FairPhotography {
    //io
    static boolean submission = false;
    static boolean debug = true;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int N;
    static Cow[] cows;

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader(".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        cows = new Cow[N+1];
        cows[0] = new Cow(true, -1);
        for (int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int pos = Integer.parseInt(st.nextToken());
            boolean w = st.nextToken().equals("W");
        }
        //logic
        //turn in answer
        out.println();
        out.close();
    }
    private static class Cow {
        boolean white;
        int pos;
        public Cow(boolean w, int p){
            white=w;
            pos=p;
        }
    }
}
