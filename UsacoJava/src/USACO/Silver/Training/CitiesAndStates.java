package USACO.Silver.Training;

import java.io.*;
import java.util.*;
/*
USACO 2016 December Contest, Silver
Problem 2. Cities and States
USACO Silver Training
Thoughts: Easy
Concepts: HashCode, O(N) look up table if you have enough memory
 */
public class CitiesAndStates {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int K = 26*26;
    static int[][] lookup = new int[K][K];
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("citystate.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("citystate.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        for (int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String u = st.nextToken();
            String v = st.nextToken();
            lookup[numerify(u.charAt(0),u.charAt(1))][numerify(v.charAt(0),v.charAt(1))]++;
        }
        //logic
        long ans = 0;
        for (int r=0;r<K;r++) {
            for (int c=0;c<K;c++) {
                if (r==c){

                }
                else {
                    ans += (long)lookup[r][c]*lookup[c][r];
                }
            }
        }
        //turn in answer
        out.println(ans/2);
        out.close();
    }
    public static int numerify(char a, char b) {
        return (a-'A')*26 + (b-'A');
    }
}
