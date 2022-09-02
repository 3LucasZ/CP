package USACO.Bronze.Usaco2019_2020_Bronze.Usaco2020_4;

import java.io.*;

public class SocialDistancing1 {
    //param
    static int n;
    static boolean[] stalls;
    public static void main(String[] args) throws IOException {
        //default
        PrintWriter out;
        BufferedReader f;
        //setup
        boolean submission = true;
        if (submission) {
            f = new BufferedReader(new FileReader("socdist1.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("socdist1.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        n = Integer.parseInt(f.readLine());
        stalls = new boolean[n];
        String str = f.readLine();
        for(int i=0;i<n;i++) {
            stalls[i] = str.charAt(i) == '1';
        }
        boolean[] temp;
        int largestSmallest = 0;
        //case 1: leftmost + rightmost
        if (!stalls[0] && !stalls[n-1]) {
            temp = stalls.clone();
            temp[0] = true;
            temp[n - 1] = true;
            largestSmallest = Math.max(largestSmallest, findSmallestGap(temp));
        }
        //case 2: leftmost + largest gap
        if (!stalls[0]) {
            temp = stalls.clone();
            temp[0] = true;
            int[] ret = findLargestGap(temp);
            if (ret[0] > 1) {
                temp[ret[1] - (ret[0] / 2)] = true;
                largestSmallest = Math.max(largestSmallest, findSmallestGap(temp));
            }
        }
        //case 3: rightmost + largest gap
        if (!stalls[n-1]) {
            temp = stalls.clone();
            temp[n-1] = true;
            int[] ret = findLargestGap(temp);
            if (ret[0] > 1) {
                temp[ret[1] - (ret[0] / 2)] = true;
                largestSmallest = Math.max(largestSmallest, findSmallestGap(temp));
            }
        }
        //case 4: largest gap 1 + largest gap 2
        if (true) {
            temp = stalls.clone();
            int[] ret = findLargestGap(temp);
            if (ret[0] > 1) {
                temp[ret[1] - (ret[0] / 2)] = true;
                ret = findLargestGap(temp);
                if (ret[0] > 1) {
                    temp[ret[1] - (ret[0] / 2)] = true;
                    largestSmallest = Math.max(largestSmallest, findSmallestGap(temp));
                }
            }
        }
        //case 4: put both cows in one big gap
        if (true) {
            temp = stalls.clone();
            int[] ret = findLargestGap(temp);
            if (ret[0] > 2) {
                temp[ret[1] - (ret[0] / 3)] = true;
                temp[ret[1] - (2 * ret[0] / 3)] = true;
                largestSmallest = Math.max(largestSmallest, findSmallestGap(temp));
            }
        }
        out.println(largestSmallest);
        out.close();
        f.close();
    }
    public static int findSmallestGap(boolean[] arr) {
        int smallestGap = Integer.MAX_VALUE;
        int counter = 0;
        for (int i=0;i<n;i++) {
            if (!arr[i]) {
                counter ++;
            }
            else if (i != 0){
                smallestGap = Math.min(smallestGap, counter);
                counter = 0;
            }
        }
        return smallestGap + 1;
    }
    public static int[] findLargestGap(boolean[] arr) {
        int largestGap = 0;
        int index = 0;
        int counter = 0;
        for (int i=0;i<n;i++) {
            if (!arr[i]) {
                counter ++;
            }
            else if (i != 0 ){
                largestGap = Math.max(largestGap, counter);
                index = i;
                counter = 0;
            }
        }
        return new int[]{largestGap + 1, index};
    }
}