package Solutions.USACOGuide.UsacoGuideSilver.PrefixSums;

import java.io.*;
import java.util.Arrays;

/*
USACO 2016 January Contest, Silver
Problem 2. Subsequences Summing to Sevens
USACO Guide Silver Prefix Sums Easy
 */
public class SubsequencesSummingToSevens {
    //param
    static int n;
    static int[] prefixSum;
    public static void main(String[] args) throws IOException {
        //default
        PrintWriter out;
        BufferedReader f;
        //setup
        boolean submission = true;
        if (submission) {
            f = new BufferedReader(new FileReader("div7.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("div7.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        n = Integer.parseInt(f.readLine());
        prefixSum = new int[n+1];
        prefixSum[0] = 0;
        for (int i=1;i<n+1;i++){
            prefixSum[i] = (prefixSum[i-1] + Integer.parseInt(f.readLine())) % 7;
        }
        printArr(prefixSum);
        int[] firstMods = new int[7];
        Arrays.fill(firstMods, -1);
        int max = 0;
        for (int i=0;i<n+1;i++){
            if (firstMods[prefixSum[i]] == -1) {
                firstMods[prefixSum[i]] = i;
            }
            else {
                max = Math.max(max, i - firstMods[prefixSum[i]]);
            }
        }
        //turn in answer
        out.println(max);
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
