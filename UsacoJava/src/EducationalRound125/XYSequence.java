package EducationalRound125;

import java.io.*;
import java.util.*;

public class XYSequence {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) out.println(solve());
        out.close();
    }

    public static long solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        long ret = 0;
        int ni = 0;
        for(int i=0;i<N;i++){
            if (ni + X > B) ni-=Y;
            else ni+=X;
            ret += ni;
        }

        return ret;
    }
}
