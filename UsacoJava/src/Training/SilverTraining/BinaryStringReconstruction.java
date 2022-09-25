package Training.SilverTraining;

import java.io.*;
import java.util.*;
/*
Educational Codeforces Round 94 (Rated for Div. 2)
C. Binary String Reconstruction
Goal: 2-sat practice, USACO Silver Training
Tags: 1500, greedy, constructive, brute force, 2-sat
Thoughts:
casing for str[i] = 0 or 1 and also dummy variables for 1...X and N..X+N
contradiction -> -1

alt:
greedy make everything 1
solve for forced 0's
ret -1 or str if determinant
 */
public class BinaryStringReconstruction {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) System.out.println(solve());
        out.close();
    }
    public static String solve() throws IOException {
        //parse
        String str = br.readLine();
        int N = str.length();
        int X = Integer.parseInt(br.readLine());
        int[] S = new int[X+N+X+1];
        for (int i=X+1;i<=X+N;i++) S[i] = str.charAt(i-X-1)-48;
        if (debug) System.out.println(Arrays.toString(S));

        //init
        int[] construct = new int[X+N+X+1];
        Arrays.fill(construct, -1);
        for (int i=1;i<=X;i++) construct[i]=0;
        for (int i=X+N+1;i<=X+N+X;i++) construct[i]=0;
        if (debug) System.out.println(Arrays.toString(construct));

        //construct
        for (int i=X+1;i<=N+X;i++){
            if (S[i]==0){
                if (construct[i+X]==1 || construct[i-X]==1) return ""+-1;
                construct[i+X]=0; construct[i-X]=0;
            }
            else {
                if (construct[i-X]==-1) construct[i-X]=1;
                else if (construct[i-X]==0) {
                    if (construct[i+X]==0) return ""+-1;
                    construct[i+X]=1;
                }
            }
        }

        StringBuilder ret=new StringBuilder();
        for (int i=X+1;i<=X+N;i++) ret.append(construct[i]==-1?1:construct[i]);
        return ret.toString();
    }
}
