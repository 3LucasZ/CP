package Other.USACO.Season2020_2021.Dec2020.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DaisyChains {
    //param
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        //parse input
        n = Integer.parseInt(f.readLine());
        StringTokenizer st = new StringTokenizer(f.readLine());
        int[] flowers = new int[n];
        for (int i=0;i<n;i++) {
            flowers[i] = Integer.parseInt(st.nextToken());
        }
        //logic
        int count = 0;
        for (int i=0;i<n;i++) {
            for (int j=i;j<n;j++) {
                int petalSum = 0;
                boolean good = false;
                for (int k = i;k<=j;k++) {
                    petalSum += flowers[k];
                }
                for (int k = i;k<=j;k++) {
                    if (petalSum%(j-i+1)==0 && flowers[k] == petalSum/(j-i+1)) {
                        //System.out.println("i: " + i + " j: " + j + " petal sum: " + petalSum + " petal avg: " + petalSum/(j-i+1) + " avg flower: " + flowers[k]);
                        good = true;
                    }
                }
                if (good) count ++;
            }
        }
        //turn in answer
        out.println(count);
        out.close();
        f.close();
    }
}
