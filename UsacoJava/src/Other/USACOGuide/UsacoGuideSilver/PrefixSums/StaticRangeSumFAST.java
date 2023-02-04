package Other.USACOGuide.UsacoGuideSilver.PrefixSums;

import java.io.*;
import java.util.StringTokenizer;

public class StaticRangeSumFAST {
    //global param
    static int n;
    static int q;
    static long operations = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        int[] prefixSums = new int[n+1];
        prefixSums[0] = 0;
        //logic
        st = new StringTokenizer(f.readLine());
        for (int i=1;i<n+1;i++) {
            prefixSums[i] = prefixSums[i-1] + Integer.parseInt(st.nextToken());
            operations ++;
        }
        printArr(prefixSums);
        for (int i=0;i<q;i++) {
            st = new StringTokenizer(f.readLine());
            int lower = Integer.parseInt(st.nextToken());
            int higher = Integer.parseInt(st.nextToken());
            System.out.println(prefixSums[higher] - prefixSums[lower]);
            operations ++;
        }
        //turn in answer
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
n q -> O(n + q)
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

