package Solutions.USACO.Season2020_2021.Feb2021.Bronze;

import java.io.*;
import java.util.*;

public class Comfortable_cows {
    static BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static int dx[] = {-1, 1, 0, 0};
    static int dy[] = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(f.readLine());
        int map[][] = new int[1001][1001];
        int comfyCount = 0;
        for(int i=0;i<N;i++){
            StringTokenizer cow = new StringTokenizer(f.readLine());
            int r = Integer.parseInt(cow.nextToken());
            int c = Integer.parseInt(cow.nextToken());
            int comfyChange = 0;
            for (int d = 0; d < 4; d++) {
                if (valid_position(r + dx[d], c + dy[d], N)) {
                    if (comfortable(r + dx[d], c + dy[d], N, map)) {
                        comfyChange--;
                    }
                }
            }
            map[r][c] = 1;
            if (comfortable(r,c,N,map)) {
                comfyChange++;
            }
            for (int d = 0; d < 4; d++) {
                if (valid_position(r + dx[d], c + dy[d], N)) {
                    if (comfortable(r + dx[d], c + dy[d], N, map)) {
                        comfyChange++;
                    }
                }
            }
            comfyCount += comfyChange;
            out.println(comfyCount);
        }
        out.close();
    }
    static boolean valid_position(int x,int y,int N)
    {
        return (x>=0 && x<=1000 && y>=0 && y<=1000);
    }

    static boolean comfortable(int x,int y,int N,int arr[][]) {
        if (arr[x][y] == 0) return false;
        int neighbors = 0;
        for (int d = 0; d < 4; d++) {
            if (valid_position(x + dx[d], y + dy[d], N)) {
                if (arr[x + dx[d]][y + dy[d]] == 1) {
                    neighbors++;
                }
            }
        }
        return neighbors == 3;
    }
}