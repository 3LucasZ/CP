package Silver.Usaco2019_2020_Silver.Silver2020_4;

import java.io.*;
import java.util.*;
/*
USACO 2020 US Open Contest, Silver
Problem 2. Cereal
USACO Practice
Concepts: Propagation
Copy cat sad :(
 */
public class Cereal {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int M;
    static int f[];
    static int s[];
    static int takenBy[];
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("cereal.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("cereal.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        f = new int[N+1];
        s = new int[N+1];
        takenBy = new int[M+1];
        for (int i=N;i>=1;i--) {
            st = new StringTokenizer(br.readLine());
            f[i] = Integer.parseInt(st.nextToken());
            s[i] = Integer.parseInt(st.nextToken());
        }
        //out.println(Arrays.toString(f));
        //out.println(Arrays.toString(s));
        //out.println(Arrays.toString(takenBy));
        //logic
        int ans = 0;
        int[] ret = new int[N];
        for (int i=1;i<=N;i++) {
           int j = i;
           int pos = f[i];
           while (true){
               if (takenBy[pos] == 0){
                   takenBy[pos] = j;
                   ans++;
                   break;
               }
               else if (takenBy[pos] > j) {
                   break;
               }
               else {
                   int k = takenBy[pos];
                   takenBy[pos] = j;
                   if (pos == s[k]) break;
                   j = k;
                   pos = s[k];
               }
           }
           ret[i-1]=ans;
        }
        //turn in answer
        for (int i=N-1;i>=0;i--) {
            out.println(ret[i]);
        }
        out.close();
    }
}
