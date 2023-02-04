package Other.Codeforces.Round794;

import java.io.*;
import java.util.*;

public class CircularLocalMinimax {
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        for (int i=0;i<N;i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        //solve
        if (N%2==1) {
            out.println("NO");
            return;
        }
        Arrays.sort(A);
        int M = N/2;
        ArrayList<Integer> circle = new ArrayList<>();
        for (int i=0;i<M;i++){
            circle.add(A[i]);
            circle.add(A[i+M]);
        }

        for (int i=0;i<N;i+=2){
            if (circle.get(i)>=circle.get((i-1+N)%N)||circle.get(i)>=circle.get((i+1+N)%N)){
                out.println("NO");
                return;
            }
        }
        out.println("YES");
        for (int i=0;i<N;i++) out.print(circle.get(i)+" ");
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
