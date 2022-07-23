package Misc.Procrastinate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BookShop {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = true;
    //param
    static int N;
    static int X;
    static int[] price;
    static int[] page;
    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        //logic
        price = new int[N];
        page = new int[N];
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++){
            price[i] = Integer.parseInt(st1.nextToken());
            page[i] = Integer.parseInt(st2.nextToken());
        }
        //knapsack
        int[]dp = new int[X+1];
        for (int pay=1;pay<=X;pay++){
        //    for (int )
        }
        //turn in answer
        out.println();
        out.close();
    }
}
