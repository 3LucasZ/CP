package USACO.Silver.UsacoGuideSilver.BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/*
CSES Problem Set
Multiplication Table
USACO Silver Guide - Binary Search - Normal
Thoughts: clever way of finding how many nums were smaller than a particular num in a row
:( Had to glance at solution
 */
public class MultiplicationTable {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static long N;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        //unit test
//        for (int i=1;i<=N*N;i++) {
//            System.out.println(leq(i));
//        }
        long T = (N*N+1)/2;
//        System.out.println(N);
//        System.out.println(T);
        long lo = 1;
        long hi = N*N;
        while (lo<hi){
            long mid = (lo+hi)/2;
            if (leq(mid) >= T){
                hi = mid;
            }
            else {
                lo = mid + 1;
            }
        }
        out.println(lo);
        out.close();
    }
    public static long leq(long M){
        long ret = 0;
        for (long r=1;r<=N;r++) {
            ret += Math.min(N,M/r);
        }
        return ret;
    }
}
