package Other.Codeforces.PickNPlay;

import java.io.*;
import java.util.*;
/*
PROB: GameGame
LANG: JAVA
*/
public class GameGame {
    static boolean submission = false;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        setup("GameGame");
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) solve();
        out.close();
    }

    public static void solve() throws IOException {
        //parse
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++){
            A[i]=Integer.parseInt(st.nextToken());
        }

        //count freq bit[2^i aka ith bit]
        int[] freq = new int[50];
        for (int i : A){
            for (int bit=0;bit<50;bit++){
                if (i==0)break;
                if (i%2==1) freq[bit]++;
                i/=2;
            }
        }

        //find bestBit
        int bestBit = -1;
        for (int i=49;i>=0;i--){
            if (freq[i]%2==1) {
                bestBit=i;
                break;
            }
        }
        if (debug) {
            System.out.println("Best bit: "+bestBit);
            System.out.println(Arrays.toString(freq));
        }

        //ret
        if (bestBit==-1) out.println("DRAW");
        else if (freq[bestBit]%4==3 && (N-freq[bestBit])%2==0) out.println("LOSE");
        else out.println("WIN");
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