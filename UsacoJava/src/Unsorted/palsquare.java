package Unsorted;/*
ID: Lucas Zheng [lucas.z4]
LANG: JAVA
TASK: Complete_Search.palsquare
*/
import java.io.*;
import java.util.*;

public class palsquare {
    public static void main(String[] args) throws IOException {
        //setting variables
        BufferedReader f = new BufferedReader(new FileReader("Complete_Search.palsquare.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Complete_Search.palsquare.out")));
        int base = Integer.parseInt(f.readLine());

        //printing the answer
        //int base = 9;
        //System.out.println(toNewBase(2,2));

        for (int i=1; i<=300; i++) {
            if(isPalindrome(toNewBase(i*i, base))){
                out.println(toNewBase(i, base) + " " + toNewBase(i*i, base));
            }
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
            if (rem > 9) {
                result = (char) (rem - 10 + 'A') + result;
            }
            else {
                result = rem + result;
            }
            num = num / newBase;
        }
        return result;
    }
}
