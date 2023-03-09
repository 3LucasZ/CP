package Solutions.USACOGuide.UsacoGuideSilver.TwoPointers;
/*
CSES Problem Set
Subarray Sums I
USACO Guide - 2 Pointers - Easy
 */
import java.io.*;
import java.util.*;

public class SubarraySumsI {
    //io
    static BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int n;
    static int target;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(f.readLine());
        for (int i=0;i<n;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        //out.println(Arrays.toString(arr));
        int l = 0;
        int r = 0;
        int sum = arr[0];
        int ans = 0;
        while (l<n&&r<n){
            if (sum<target) {
                r++;
                if (r>=n) break;
                sum+=arr[r];
            }
            else if (sum>target) {
                sum-=arr[l];
                l++;
            }
            else {
                ans++;
                sum-=arr[l];
                l++;
            }
        }
        out.println(ans);
        out.close();
        f.close();
    }
}
