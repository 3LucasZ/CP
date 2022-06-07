package Codeforces.Round795;

import java.io.*;
import java.util.StringTokenizer;

public class SumOfSubstrings {
    static boolean submission = false;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        setup("");
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++){
            out.println(solve());
        }
        out.close();
    }
    public static int solve() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        String str = br.readLine();
        int[] A = new int[N];
        for (int i=0;i<N;i++) A[i]=str.charAt(i)-48;

        int sum = 0;
        for (int i=1;i<N;i++){
            sum+=A[i-1]*10+A[i];
        }
        if (debug) System.out.println(sum);

        int firstOne = -1;
        int lastOne = -1;

        for (int i=0;i<N;i++) if (A[i]==1) lastOne=i;
        for (int i=N-1;i>=0;i--) if (A[i]==1) firstOne=i;

        int firstDistance = firstOne;
        int lastDistance =  N-lastOne-1;

        //CASE 1: both -1
        if (firstOne==-1&&lastOne==-1);
        //CASE 2: firstOne==lastOne
        else if (firstOne==lastOne){
            //SUB CASE A: one is at 0
            if (firstOne==0) {
                if (K>=lastDistance)sum-=9;
            }
            //SUB CASE B: one is at N-1
            else if (firstOne==N-1);
            //SUB CASE C: one is in the middle
            else {
                if (K>=lastDistance)sum-=10;
                else if (K>=firstDistance)sum-=1;
            }
        }
        //CASE 3: proper coexistence
        else {
            if (lastDistance != 0 && K>=lastDistance){
                K-=lastDistance;
                sum-=10;
            }
            if (firstDistance != 0 && K>=firstDistance){
                K-=firstDistance;
                sum-=1;
            }
        }

        if (debug){
            System.out.println("["+firstDistance+", "+lastDistance+"]");
        }
        return sum;
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
3
2 0
10
2 0
01
2 100
10
 */
