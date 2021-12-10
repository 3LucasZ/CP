package UsacoGuideSilver.BinarySearch;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
USACO 2016 December Contest, Silver
Problem 1. Counting Haybales
USACO Guide Silver Easy Binary Search
 */
public class CountingHaybales {
    //param
    static int n;
    static int q;
    static int[] haybales;
    public static void main(String[] args) throws IOException {
        //default
        PrintWriter out;
        BufferedReader f;
        //setup
        boolean submission = true;
        if (submission) {
            f = new BufferedReader(new FileReader("haybales.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("haybales.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        haybales = new int[n];
        st = new StringTokenizer(f.readLine());
        for (int i=0;i<n;i++) {
            haybales[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(haybales);
        for (int i=0;i<q;i++) {
            st = new StringTokenizer(f.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int left = Arrays.binarySearch(haybales, start);
            if (left < 0) {
                left = Math.abs(left) - 1;
            }
            int right = Arrays.binarySearch(haybales, end);
            if (right < 0) {
                right = Math.abs(right) - 2;
            }
            out.println(right - left + 1);
        }
        //printArr(haybales);
        out.close();
        f.close();
    }
    public static void printArr(int[] arr) {
        String str = "";
        for (int i=0;i<arr.length;i++) {
            str += (arr[i] + ", ");
        }
        System.out.println(str);
    }
}
