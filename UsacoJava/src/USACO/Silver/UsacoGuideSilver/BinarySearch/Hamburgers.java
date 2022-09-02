package USACO.Silver.UsacoGuideSilver.BinarySearch;

import java.io.*;
import java.util.*;

/*
Codeforces Round #218 (Div. 2)
C. Hamburgers
USACO Silver Guide - Additional Practice - "NORMAL" (It is extremely easy btw)
Concepts: Binary Search
 */
public class Hamburgers {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //0 - B, 1 - S, 2 - C
    static int[] recipe = new int[3];
    static int[] kitchen = new int[3];
    static int[] price = new int[3];
    static long money;
    public static void main(String[] args) throws IOException {
        //parse input
        String str = br.readLine();
        for (int i=0;i<str.length();i++) {
            if (str.charAt(i)=='B') recipe[0]++;
            if (str.charAt(i)=='S') recipe[1]++;
            if (str.charAt(i)=='C') recipe[2]++;
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<3;i++) {
            kitchen[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<3;i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }
        money = Long.parseLong(br.readLine());
//        out.println(Arrays.toString(recipe));
//        out.println(Arrays.toString(kitchen));
//        out.println(Arrays.toString(price));
//        out.println(money);
        //for (int i=0;i<20;i++) out.println(makeBurgers(i));
        long lo = 0;
        long hi = (long) 1e13;
        while (lo<hi){
            long med = (lo + hi + 1)/2;
            if (makeBurgers(med)) lo = med;
            else hi = med - 1;
        }
        out.println(lo);
        out.close();
    }
    public static boolean makeBurgers(long burgers){
        long spend = 0;
        for (int i=0;i<3;i++) {
            spend += Math.max(0,burgers * recipe[i] - kitchen[i])*price[i];
        }
        //out.println(spend);
        return spend <= money;
    }
}
