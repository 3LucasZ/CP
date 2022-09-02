package USACO.Bronze.Usaco2020_2021_Bronze.Bronze2021_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JustStalling {
    //param
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        //parse input
        n = Integer.parseInt(f.readLine());
        int[] canFit = new int[n];
        int[] stalls = new int[n];
        StringTokenizer cowsParser = new StringTokenizer(f.readLine());
        StringTokenizer stallsParser = new StringTokenizer(f.readLine());
        for (int i=0;i<n;i++) {
            stalls[i] = Integer.parseInt(stallsParser.nextToken());
        }
        for (int i=0;i<n;i++) {
            int count = 0;
            int cowHeight = Integer.parseInt(cowsParser.nextToken());
            for (int j=0;j<n;j++) {
                if (cowHeight <= stalls[j]) count ++;
            }
            canFit[i] = count;
        }
        long answer = 1;
        //printArr(canFit);
        Arrays.sort(canFit);
        //printArr(canFit);
        for (int i = 0;i<n;i++) {
            answer *= (canFit[i] - i);
        }
        //logic
        //turn in answer
        out.println(answer);
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
