package Other.USACO.Season2016_2017.Open2017.Plat;

import java.io.*;
import java.util.*;
/*
PROB: ModernArt
LANG: JAVA
*/
public class ModernArt {
    static boolean submission = true;
    static boolean debug = false;

    //input
    static int N;
    static int[][] grid;

    //special case checking
    static HashSet<Integer> appear = new HashSet<>();

    //building colors 2D Presum
    static int colorR1[];
    static int colorR2[];
    static int colorC1[];
    static int colorC2[];
    static int[][] colors;

    //const
    static int INF = Integer.MAX_VALUE/10;

    public static void main(String[] args) throws IOException {
        //parse
        setup("art");
        N = Integer.parseInt(br.readLine());
        grid = new int[N+1][N+1];
        colorR1 = new int[N*N+1]; Arrays.fill(colorR1,INF);
        colorR2 = new int[N*N+1]; Arrays.fill(colorR2,-INF);
        colorC1 = new int[N*N+1]; Arrays.fill(colorC1,INF);
        colorC2 = new int[N*N+1]; Arrays.fill(colorC2,-INF);
        for (int r=1;r<=N;r++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c=1;c<=N;c++){
                int color = Integer.parseInt(st.nextToken());
                grid[r][c]=color;
                //constructing rectangles that were painted
                colorR1[color]=Math.min(colorR1[color],r);
                colorR2[color]=Math.max(colorR2[color],r);
                colorC1[color]=Math.min(colorC1[color],c);
                colorC2[color]=Math.max(colorC2[color],c);
                appear.add(color);
            }
        }
        //special case checker
        if (appear.size()<=2) {
            out.println(N*N-1);
            out.close();
            return;
        }
        //colors 2d presum
        colors = new int[N+2][N+2];
        for (int color = 1;color<=N*N;color++){
            int r1=colorR1[color];
            int r2=colorR2[color];
            int c1=colorC1[color];
            int c2=colorC2[color];
            if (r1==INF) continue;
            colors[r1][c1]++;
            colors[r1][c2+1]--;
            colors[r2+1][c1]--;
            colors[r2+1][c2+1]++;
        }
        for (int r=1;r<=N;r++){
            for (int c=1;c<=N;c++){
                colors[r][c]+=colors[r-1][c]+colors[r][c-1]-colors[r-1][c-1];
            }
        }
        if (debug) print(colors);
        //search all r,c for colors who are on top of another color
        boolean[] onTop = new boolean[N*N+1];
        for (int r=1;r<=N;r++){
            for (int c=1;c<=N;c++){
                if (colors[r][c]>1) onTop[grid[r][c]]=true;
            }
        }
        //ret
        int ans = 0;
        for (int i=1;i<=N*N;i++) if (!onTop[i]) ans++;
        out.println(ans);
        out.close();
    }
    public static void print(int[][] arr){
        for (int r=0;r<arr.length;r++){
            for (int c=0;c<arr[r].length;c++){
                String str = ""+arr[r][c];
                while (str.length()<5) str+=" ";
                System.out.print(str);
            }
            System.out.println();
        }
        System.out.println();
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