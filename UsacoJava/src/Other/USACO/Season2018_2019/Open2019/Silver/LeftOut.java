package Other.USACO.Season2018_2019.Open2019.Silver;

import java.io.*;

/*
PROB: LeftOut
LANG: JAVA
*/
public class LeftOut {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static boolean[][] L;

    public static void main(String[] args) throws IOException {
        setup("leftout");
        solve();
        out.close();
    }

    public static void solve() throws IOException {
        N = Integer.parseInt(br.readLine());
        L = new boolean[N][N];
        for (int r=0;r<N;r++){
            String str = br.readLine();
            for (int c=0;c<N;c++){
                L[r][c]=str.charAt(c)=='L';
            }
        }

        //* make it a L L
        //            L X
        // pattern
        if (!L[0][0]) flipRow(0);
        for (int c=1;c<N;c++){
            if (!L[0][c]) flipCol(c);
        }
        for (int r=1;r<N;r++){
            if (!L[r][0]) flipRow(r);
        }
        if (debug){
            for (int r=0;r<N;r++){
                for (int c=0;c<N;c++){
                    if (L[r][c]) System.out.print(1);
                    else System.out.print(0);
                }
                System.out.println();
            }
            System.out.println();
        }
        //Case1: L L ==> R L
        //       L R     L L
        int cnt = 0;
        int a = 0; int b = 0;
        for (int r=0;r<N;r++){
            for (int c=0;c<N;c++){
                if (L[r][c]) {
                    cnt++;
                } else {
                    a=r;b=c;
                }
            }
        }
        if (cnt==N+N-1){
            out.println("1 1");
            return;
        }
        //Case2: L L
        //       L L
        if (cnt==N*N){
            out.println("-1");
            return;
        }
        //Case3: L L
        //       L []
        if (cnt==N*N-1){
            out.println((a+1)+" "+(b+1));
            return;
        }
        //Case5: L []
        //       L L
        for (int c=1;c<N;c++){
            cnt+=flipCol(c);
            if (cnt==N*N-1){
                out.println(1+" "+(c+1));
                return;
            }
            cnt+=flipCol(c);
        }
        //Case4: L L
        //       []L
        for (int r=1;r<N;r++){
            cnt+=flipRow(r);
            if (cnt==N*N-1){
                out.println((r+1)+" "+1);
                return;
            }
            cnt+=flipRow(r);
        }
        out.println("-1");
    }
    static int flipRow(int r){
        int ret = 0;
        for (int c=0;c<N;c++){
            L[r][c]=!L[r][c];
            if (L[r][c])ret++;
            else ret--;
        }
        return ret;
    }

    static int flipCol(int c){
        int ret = 0;
        for (int r=0;r<N;r++){
            L[r][c]=!L[r][c];
            if (L[r][c]) ret++;
            else ret--;
        }
        return ret;
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