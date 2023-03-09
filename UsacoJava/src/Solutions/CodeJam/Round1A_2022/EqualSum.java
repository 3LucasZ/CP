package Solutions.CodeJam.Round1A_2022;

import java.io.*;
import java.util.*;

public class EqualSum {
    static boolean submission = false;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        //* handle TCS
        setup();
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) solve(i);
        out.close();
    }

    public static void solve(int tcs) throws IOException {
        //* parse
        int N = Integer.parseInt(br.readLine());
        //* feed binaries 2^0 ... 2^29 and then 32 equal pairs (1024+x,2048-x) then 6 random crap
        for (int i=1;i<=32;i++) System.out.print((1024+i)+" "+(2048-i)+" ");
        System.out.print("9 10 11 5 13 12 ");
        for (int i=0;i<30;i++) System.out.print((1<<i)+" ");
        System.out.println();
        //* accept computer stuff and heuristic it a bit
        ArrayList<Integer> run = new ArrayList<>();
        int runExtra = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++){
            int next = Integer.parseInt(st.nextToken());
            if (runExtra>0) {
                runExtra-=next;
            }
            else {
                runExtra+=next;
                run.add(next);
            }
        }
        //* add 16/32 pairs
        for (int i=1;i<=16;i++) {
            run.add((1024+i));
            run.add((2048-i));
        }
        //* add 3/6 random crap
        run.add(9); run.add(10); run.add(11);
        //* add our binary until they are equal
        for (int i=29;i>=0;i--){
            if (runExtra>0) {
                runExtra-=(1<<i);
            } else {
                runExtra+=(1<<i);
                run.add(1<<i);
            }
        }
        //* ret
        if (debug) System.out.println("runExtra: "+runExtra);
        for (int i : run) System.out.print(i+" ");
        System.out.println();
    }


    static BufferedReader br;
    static PrintWriter out;

    public static void setup() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
}