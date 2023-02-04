package Other.Codeforces.Round836;

import java.io.*;
import java.util.*;
/*
PROB: AlmostAllMultiples
LANG: JAVA
*/
public class AlmostAllMultiples {
    static boolean submission = false;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        //handle TCS
        setup("AlmostAllMultiples");
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++){
            solve();
        }
        out.close();
    }
    public static void solve() throws IOException {
        //parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // P's size, N is a freelancer
        int X = Integer.parseInt(st.nextToken()); // X can not be used anymore
        //set the stage
        int[] ans = new int[N+1];
        for (int i=1;i<=N;i++) ans[i]=i;
        ans[1]=X; ans[N]=1;
        //case: N is not a multiple of X then we lose
        if (N%X!=0) {
            out.println(-1);
            return;
        }
        //case: N is X then we just print the stage
        if (N==X) {
            for (int i=1;i<=N;i++) out.print(ans[i]+" ");
            out.println();
            return;
        }
        //traverse perm
        ArrayList<Integer> changed = new ArrayList<>();
        int head = X;
        for (int i=X+1;i<=N;i++){
            if (i%head==0 && N%i==0) {
                changed.add(head);
                head=i;
            }
        }
        changed.add(head);
        if (debug) System.out.println(changed);
        for (int i=0;i<changed.size()-1;i++) ans[changed.get(i)]=changed.get(i+1);
        for (int i=1;i<=N;i++) out.print(ans[i]+" ");
        out.println();
    }
/*
1
12 2
 */



















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