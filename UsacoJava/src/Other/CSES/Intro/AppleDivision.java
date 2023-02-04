package Other.CSES.Intro;

import java.util.Scanner;
import java.util.StringTokenizer;

public class AppleDivision {
    static int n;
    static int[] weights;
    static long totalWeight = 0;
    static long minDiff = Long.MAX_VALUE;


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        String temp = scan.nextLine();
        StringTokenizer st = new StringTokenizer(scan.nextLine());
        weights = new int[n];

        for (int i=0;i<n;i++) {
            int weight = Integer.parseInt(st.nextToken());
            weights[i] = weight;
            totalWeight += weight;
        }

        //printIntArray(weights);
        search(1, 0);
        System.out.println(minDiff);
    }
    public static void search(int round, long prevSum) {
        if (round > n) {
            long firstSum = prevSum;
            long secondSum = totalWeight - prevSum;
            long diff = Math.abs(firstSum - secondSum);
            if (diff < minDiff) {
                minDiff = diff;
            }
        }
        else {
            search(round + 1, prevSum + weights[round - 1]);
            search(round + 1, prevSum);
        }
    }
    public static void printIntArray(int[] intArray) {
        for (int i = 0; i < intArray.length; i++) {
            System.out.print(intArray[i] + ", ");
        }
    }
    public static int sumArray(int[] intArray) {
        int sum = 0;
        for (int i=0;i<intArray.length;i++) {
            sum += intArray[i];
        }
        return sum;
    }
}