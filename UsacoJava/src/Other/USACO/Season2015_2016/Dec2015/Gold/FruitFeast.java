package Other.USACO.Season2015_2016.Dec2015.Gold;

import java.io.*;
import java.util.*;
/*
USACO 2015 December Contest, Gold
Problem 2. Fruit Feast
USACO Gold Training - DP/Floodfill - EXTREMELY EASY
 */
public class FruitFeast {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int T;
    static int A;
    static int B;
    static int max_fullness = 0;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("feast.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("feast.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        visited = new boolean[T+1][2];
        //logic
        floodfill(0, 0);

        //turn in answer
        out.println(max_fullness);
        out.close();
    }
    public static void floodfill(int fullness, int drankWater){
        if (fullness > T || visited[fullness][drankWater]) return;
        max_fullness = Math.max(fullness, max_fullness);
        visited[fullness][drankWater] = true;
        if (drankWater == 0) floodfill(fullness/2, 1);
        floodfill(fullness+A, drankWater);
        floodfill(fullness+B, drankWater);
    }
}
