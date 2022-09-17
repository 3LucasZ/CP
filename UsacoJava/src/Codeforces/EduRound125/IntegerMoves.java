package Codeforces.EduRound125;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class IntegerMoves {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static HashSet<Integer> perfSquares = new HashSet<>();
    public static void main(String[] args) throws IOException {
        for (int i=0;i<10000;i++) perfSquares.add(i*i);
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) out.println(solve());
        out.close();
    }

    public static int solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        if (x==0&&y==0) return 0;
        if (perfSquares.contains(x*x+y*y)) return 1;
        return 2;
    }
}
