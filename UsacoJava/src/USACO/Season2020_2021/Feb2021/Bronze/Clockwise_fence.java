package USACO.Season2020_2021.Feb2021.Bronze;

import java.io.*;

public class Clockwise_fence {
    static BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(f.readLine());
        for (int i=0;i<N;i++) {
            String directions = f.readLine();
            int points = directions.length();
            //out.println(points);
            int min = 0;
            int current = 0;
            String direction = "";
            for (int j = 0; j < points; j++) {
                if (directions.charAt(j) == 'N') {
                    current += 1;
                } else if (directions.charAt(j) == 'S') {
                    current -= 1;
                } else if (directions.charAt(j) == 'E' && current == min) {
                    direction = "CCW";
                } else if (directions.charAt(j) == 'W' && current == min) {
                    direction = "CW";
                }
                if (current < min) {
                    min = current;
                }
            }
            out.println(direction);
        }
        out.close();
    }
}