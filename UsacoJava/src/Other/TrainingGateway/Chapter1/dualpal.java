package Other.TrainingGateway.Chapter1;/*
ID: Lucas Zheng [lucas.z4]
LANG: JAVA
TASK: dualpal
*/

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class dualpal {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("dualpal.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
        StringTokenizer s = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(s.nextToken());
        int S = Integer.parseInt(s.nextToken());

        //int N = 7;
        //int S = 11;
        ArrayList<Integer> result = new ArrayList<Integer>();
        while (result.size() < N) {
            S++;
            int a = 0;
            for(int i=2;i<=10;i++) {
                if (isPalindrome(toNewBase(S,i))) {
                    a++;
                }
                if (a==2) {
                    result.add(S);
                    break;
                }
            }
        }

        for (int e : result) {
            out.println(e);
            //System.out.println(e);
        }
        out.close();
    }
    public static boolean isPalindrome(String num) {
        String temp[] = num.split("");
        for (int i=0;i <= temp.length/2;i++) {
            if (! temp[i].equals(temp[temp.length-1-i])) {
                return false;
            }
        }
        return true;
    }
    public static String toNewBase(int anum, int newBase) {
        int num = anum;
        String result = "";
        while(num > 0) {
            int rem = num % newBase;
            /*if (rem > 9) {
                result = (char) (rem - 10 + 'A') + result;
            }
            else {
             */
            result = rem + result;
            //}
            num = num / newBase;
        }
        return result;
    }
}
