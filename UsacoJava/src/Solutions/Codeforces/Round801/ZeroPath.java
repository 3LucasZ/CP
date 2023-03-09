package Solutions.Codeforces.Round801;

import java.io.*;
import java.util.*;

public class ZeroPath {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) out.println(solve()?"YES":"NO");
        out.close();
    }

    public static boolean solve() throws IOException{
        //parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[][] grid = new int[R+1][C+1];
        for (int r=1;r<=R;r++){
            st = new StringTokenizer(br.readLine());
            for (int c=1;c<=C;c++){
                grid[r][c]=Integer.parseInt(st.nextToken());
            }
        }

        //most min dp
        int[][] most = new int[R+1][C+1];
        for (int r=0;r<=R;r++) for (int c=0;c<=C;c++) most[r][c]=Integer.MIN_VALUE;
        most[1][1]=grid[1][1];
        for (int r=1;r<=R;r++){
            for (int c=1;c<=C;c++){
                if (r==1 && c==1) continue;
                most[r][c]=Math.max(most[r-1][c],most[r][c-1])+grid[r][c];
            }
        }

        int[][] least = new int[R+1][C+1];
        for (int r=0;r<=R;r++) for (int c=0;c<=C;c++) least[r][c]=Integer.MAX_VALUE;
        least[1][1]=grid[1][1];
        for (int r=1;r<=R;r++){
            for (int c=1;c<=C;c++){
                if (r==1 && c==1) continue;
                least[r][c]=Math.min(least[r-1][c], least[r][c-1])+grid[r][c];
            }
        }

        return (least[R][C]%2==0 && least[R][C] <= 0 && most[R][C] >= 0);
    }
}