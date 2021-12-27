package StarLeague;

import java.io.*;
import java.util.*;

public class GoingToTheMovies {
    //io
    static boolean submission = false;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int C;
    static int[] weight;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("cowflix.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("cowflix.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        weight = new int[N];
        for (int i=0;i<N;i++) {
            weight[i] = Integer.parseInt(br.readLine());
        }
        //logic
        for (int i=0;i<(int)Math.pow(2, N);i++) {
            int k=i;
            int temp = 0;
            for (int j=0;j<N;j++) {
                if (k%2==0){

                }else {
                    temp+=weight[j];
                }
                k/=2;
            }
            if (temp <= C && C - temp < C - ans) {
                ans = temp;
            }
        }
        //turn in answer
        out.println(ans);
        out.close();
    }
}
