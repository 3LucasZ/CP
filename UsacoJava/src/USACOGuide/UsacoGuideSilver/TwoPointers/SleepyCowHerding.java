package USACOGuide.UsacoGuideSilver.TwoPointers;

import java.io.*;
import java.util.*;
/*
USACO 2019 February Contest, Silver
Problem 1. Sleepy Cow Herding
USACO Guide Silver: 2 Pointers Normal
 */
public class SleepyCowHerding {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int n;
    static int[] herd;
    //logic
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            f = new BufferedReader(new FileReader("herding.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("herding.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        n = Integer.parseInt(f.readLine());
        herd = new int[n];
        for (int i=0;i<n;i++) {
            herd[i] = Integer.parseInt(f.readLine());
        }
        Arrays.sort(herd);
        //printArr(herd);
        //logic
        //this is the min
        out.println(getMin());
        //this is the max
        out.println(getMax());
        out.close();
        f.close();
    }
    public static int getMax() {
        int ret = 0;
        ret = (herd[1] - herd[0] < herd[n-1] - herd[n-2]) ?
                herd[n-1] - herd[1] - (n-2) :
                herd[n-2] - herd[0] - (n-2);
        return ret;
    }
    public static int getMin() {
        int minHoles = Integer.MAX_VALUE;
        //edge case: aaaa____a and a____aaaa
        if (herd[n-1] - herd[n-2] > 2 && herd[n-2] - herd[0] == n-2) {
            minHoles = 2;
        }
        else if (herd[1] - herd[0] > 2 && herd[n-1] - herd[1] == n-2) {
            minHoles = 2;
        }
        else {
            int p2 = 0;
            for (int p1=0;p1<n;p1++) {
                while (p2 < n-1 && herd[p2+1] - herd[p1] < n) p2++;
                minHoles = Math.min(minHoles, n-(p2-p1+1));
            }
        }
        return minHoles;
    }
    public static void printArr(int[] arr) {
        String str = "";
        for (int i=0;i<arr.length;i++) {
            str += (arr[i] + ", ");
        }
        System.out.println(str);
    }
}
