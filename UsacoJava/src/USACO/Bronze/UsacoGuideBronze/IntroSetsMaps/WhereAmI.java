package USACO.Bronze.UsacoGuideBronze.IntroSetsMaps;

import java.io.*;
import java.util.*;
/*
USACO 2019 December Contest, Bronze
Problem 2. Where Am I?
Intro Sets and Maps - Easy - Bronze
 */

public class WhereAmI {
    //default
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int n;
    static String colors;

    public static void main(String[] args) throws IOException {
        //setup
        if (submission) {
            f = new BufferedReader(new FileReader("whereami.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("whereami.out")));
            n = Integer.parseInt(f.readLine());
            colors = f.readLine();
        }
        else {
            n = 7;
            colors = "ABCDABC";
        }
        //logic
        int k = 0;
        int cycles = 100000;
        Set<String> colorSubsets = new TreeSet<>();
        while (colorSubsets.size() < cycles) {
            colorSubsets = new TreeSet<>();
            k ++ ;
            cycles = n - k + 1;
            for (int i=0;i<cycles;i++) {
                colorSubsets.add(colors.substring(i, i+k));
            }
        }
        //print
        if (submission) {
            out.println(k);
            f.close();
            out.close();
        }
        else {
            System.out.println(k);
        }
    }
}