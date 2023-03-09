package Solutions.Codeforces.Round795;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ShoeShuffling  {
    static boolean submission = false;
    static boolean debug = true;

    public static void main(String[] args) throws IOException {
        setup("");
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++){
            solve();
        }
        out.close();
    }
    public static void solve() throws IOException{
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N+1];
        ArrayList<Integer> ret = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        if (N==1) {
            out.println(-1);
            return;
        }

        int consec = 0;
        for (int i=1;i<=N;i++){
            A[i] = Integer.parseInt(st.nextToken());

            if (i==1){
                consec=1;
            }
            else if (i==N){
                if (A[i]==A[i-1])consec++;
                else consec=1;

                if (consec==1){
                    out.println(-1);
                    return;
                }
                int start = i-consec+1;
                int end = i;
                ret.add(end);
                for (int j=start;j<end;j++)ret.add(j);
            }
            else if (A[i]==A[i-1]){
                consec++;
            }
            else {
                if (consec==1){
                    out.println(-1);
                    return;
                }
                int start = i-consec;
                int end = i-1;
                ret.add(end);
                for (int j=start;j<end;j++)ret.add(j);
                consec=1;
            }
        }

        for (int i:ret){
            out.print(i+" ");
        }
        out.println();
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
1
6
1 1 2 2 2 3
 */