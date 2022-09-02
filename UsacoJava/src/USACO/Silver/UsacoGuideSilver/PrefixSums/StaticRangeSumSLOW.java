package USACO.Silver.UsacoGuideSilver.PrefixSums;

import java.io.*;
import java.util.StringTokenizer;

public class StaticRangeSumSLOW {
    //param
    public static void main(String[] args) throws IOException {
        long operations = 0;
        //default
        PrintWriter out;
        BufferedReader f;
        //setup
        f = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        int arrLen = Integer.parseInt(st.nextToken());
        int[] arr = new int[arrLen];
        int numQuery = Integer.parseInt(st.nextToken());
        //logic
        st = new StringTokenizer(f.readLine());
        for (int i=0;i<arrLen;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for (int i=0;i<numQuery;i++) {
            st = new StringTokenizer(f.readLine());
            int lower = Integer.parseInt(st.nextToken());
            int upper = Integer.parseInt(st.nextToken());
            int sum = 0;
            for (int j=0;j<arrLen;j++) {
                if (j>=lower && j<upper) {
                    sum += arr[j];
                    operations ++;
                    //System.out.println(sum);
                }
            }
            out.println(sum);
        }
        out.println("This took: " + operations + " operations.");
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
/*
using the following data:
n q -> O(nq)
5 20
1 10 100 1000 10000
0 5
0 5
0 5
0 5
0 5
0 5
0 5
0 5
0 5
0 5
0 5
0 5
0 5
0 5
0 5
0 5
0 5
0 5
0 5
0 5
 */
