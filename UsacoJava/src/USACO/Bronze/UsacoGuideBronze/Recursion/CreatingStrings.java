package USACO.Bronze.UsacoGuideBronze.Recursion;

import java.util.*;

public class CreatingStrings {
    static int n;
    static int[] letterCount;
    static List<String> perms = new ArrayList<String>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String orig = scan.nextLine();
        n = orig.length();
        letterCount = new int[26];

        for (int i=0;i<n;i++) {
            letterCount[orig.charAt(i) - 'a'] += 1;
        }
        //printIntArray(letterCount);
        permute("");
        System.out.println(perms.size());
        for (int i=0;i<perms.size();i++) {
            System.out.println(perms.get(i));
        }
    }
    public static void permute(String s) {
        if (s.length() == n) {
            perms.add(s);
        }
        else {
            for (int i=0;i<26;i++) {
                if (letterCount[i] > 0) {
                    letterCount[i] -= 1;
                    permute(s + (char)(i + 'a'));
                    letterCount[i] += 1;
                }
            }
        }
    }
    public static void printIntArray(int[] intArray) {
        for (int i = 0; i < intArray.length; i++) {
            System.out.print(intArray[i] + ", ");
        }
    }
}