package Solutions.CodeJam.Qual_2022;

import java.io.*;
import java.util.*;

public class PunchedCards {
    static boolean submission = false;
    static boolean debug = true;

    public static void main(String[] args) throws IOException {
        //* handle TCS
        setup();
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) solve(i);
        out.close();
    }

    public static void solve(int tcs) throws IOException {
        System.out.print("Case #" + tcs + ": ");
        if (debug) System.out.println("");
        //* parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = 2*Integer.parseInt(st.nextToken())+1;
        int C = 2*Integer.parseInt(st.nextToken())+1;
        //* frame
        char[][] ret = new char[R][C]; for (int r=0;r<R;r++) for (int c=0;c<C;c++) ret[r][c]='.';
        //* (odd,even) are |
        for (int r=0;r<R;r++){
            for (int c=0;c<C;c++){
                if (r%2==1 && c%2==0) ret[r][c]='|';
                if (r%2==0 && c%2==0) ret[r][c]='+';
                if (r%2==0 && c%2==1) ret[r][c]='-';
            }
        }
        ret[0][0]='.'; ret[0][1]='.'; ret[1][0]='.';
        for (int r=0;r<R;r++){
            for (int c=0;c<C;c++){
                System.out.print(ret[r][c]);
            }
            System.out.println();
        }
    }



    static BufferedReader br;
    static PrintWriter out;

    public static void setup() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
}