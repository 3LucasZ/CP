package EC.PlatA.STR7;

import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class GameOfLines {
    static boolean submission = false;
    static boolean debug = true;
    public static void main(String[] args) throws IOException {
        setup("");
        int N = Integer.parseInt(br.readLine());
        int[] x = new int[N];
        int[] y = new int[N];

        HashSet<Integer> slopes = new HashSet<>();
        for (int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }

        for (int i=0;i<N;i++){
            for (int j=0;j<N;j++){
                if (i==j) continue;
                int dy = y[j]-y[i];
                int dx = x[j]-x[i];
                if (dy < 0) {
                    dy*=-1;
                    dx*=-1;
                }
                if (dx==0) dy=1;
                else if (dy==0) dx=1;
                else {
                    int GCD = gcd(Math.abs(dx),Math.abs(dy));
                    dx/=GCD;
                    dy/=GCD;
                }
                slopes.add(dy+2000+5000*(dx+2000));
            }
        }

        out.println(slopes.size());
        out.close();
    }
    static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
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
/*
4
-1 1
-2 0
0 0
1 1
 */