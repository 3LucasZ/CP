package USACO.Platinum.EC.STR7;

import java.io.*;
import java.util.StringTokenizer;

public class EmptyStalls {
    static boolean submission = false;
    static boolean debug = true;

    static int N;
    static int K;
    static int[] stall;
    static boolean[] full;

    public static void main(String[] args) throws IOException {
        //parse
        setup("");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        stall = new int[N];

        for (int i=0;i<K;i++){
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            long A = Integer.parseInt(st.nextToken());
            long B = Integer.parseInt(st.nextToken());
            for (int j=1;j<=Y;j++)stall[(int)(((A*j+B)%N+N)%N)]+=X;
        }
        //out.println(Arrays.toString(stall));

        //carriage double circular
        int run = 0;
        for (int i=0;i<2*N;i++){
            int j = i%N;
            run+=stall[j];
            stall[j]=0;
            if (run > 0) {
                stall[j]++;
                run--;
            }
        }

        int i;
        for (i=0;i<N;i++)if (stall[i]==0) break;
        out.println(i);
        out.close();
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
