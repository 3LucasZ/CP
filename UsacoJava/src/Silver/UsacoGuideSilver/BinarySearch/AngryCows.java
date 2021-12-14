package Silver.UsacoGuideSilver.BinarySearch;

import java.io.*;
import java.util.*;

/*
USACO 2016 January Contest, Silver
Problem 1. Angry Cows
USACO Guide Silver Easy Binary Search
Concept: false false false true true true : find first true
 */
public class AngryCows {
    static boolean submission = true;
    //param
    static int n;
    static int k;
    static int[] bales;
    public static void main(String[] args) throws IOException {
        //default
        PrintWriter out;
        BufferedReader f;
        //setup
        if (submission) {
            f = new BufferedReader(new FileReader("angry.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        bales = new int[n];
        for (int i=0;i<n;i++) {
            bales[i] = Integer.parseInt(f.readLine());
        }
        Arrays.sort(bales);
        //printArr(bales);
        //logic

        int left = 0, right = bales[n-1];
        //System.out.println(right);
        while (left < right) {
            int middle = (left + right) / 2;
            if (!tryRadius(middle)) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        if (tryRadius(left)) {
            out.println(left);
        }
        out.close();
        f.close();
    }
    public static boolean tryRadius(int r) {
        //cow lands on bales[i] + r and affects bales[i] , bales[i]+2r
        //debugPrintln("Radius: " + r);
        int cowsUsed = 0;
        int leftIndex = 0;
        while (leftIndex < bales.length) {
            int leftNum = bales[leftIndex];
            int rightNum = leftNum + 2*r;
            int rightIndex = Arrays.binarySearch(bales,rightNum);
            if (rightIndex < 0) {
                rightIndex = Math.abs(rightIndex+1) - 1;
            }
            //debugPrintln("Throwing cow at: " + (leftNum + r));
            //debugPrintln("Affecting bales at: " + leftNum + " to " + rightNum);
            //debugPrintln("All bales gone from indexes: " + leftIndex + " to " + rightIndex);
            leftIndex = rightIndex + 1;
            cowsUsed ++;
        }
        debugPrintln("");
        return cowsUsed <= k;
    }
    public static void printArr(int[] arr) {
        String str = "";
        for (int i=0;i<arr.length;i++) {
            str += (arr[i] + ", ");
        }
        debugPrintln(str);
    }
    public static void debugPrintln(String str) {
        if (!submission) {
            System.out.println(str);
        }
    }
}
