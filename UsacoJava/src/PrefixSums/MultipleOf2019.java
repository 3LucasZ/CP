package PrefixSums;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
//Beautiful example of O(n) :)
public class MultipleOf2019 {
    public static void main(String args[]) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        String str = f.readLine();
        int num = 0;
        int len = str.length();
        int pow = 1;
        int[] count = new int[2019];
        count[0] = 1;

       for (int i=len-1;i>=0;i--) {
           num = (num + pow * (str.charAt(i)-'0')) % 2019;
           count[num]++;
           pow = pow * 10 % 2019;
       }

       long answer = 0;
       for (int i=0;i<2019;i++) {
           answer += (long) (count[i] * (count[i] - 1) / 2);
       }
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
