package Boilerplate;

import java.io.*;
import java.util.*;

public class HelperMethods {
    static PrintWriter out;
    static BufferedReader f;
    static boolean submission;
    public static void printArr(int[][] arr) {
        for (int i=0;i<arr.length;i++) {
            out.println(Arrays.toString(arr[i]));
        }
        out.println();
    }
    public static void printArr(boolean[] arr) {
        String str = "";
        for (int i=0;i<arr.length;i++) {
            str += (arr[i] ? 1 : 0 ) + "  ";
        }
        out.println(str);
        out.println();
    }
    public static void print2DArr(boolean[][] arr) {
        for (int i=0;i<arr.length;i++) {
            printArr(arr[i]);
        }
        out.println();
    }
    public static void debugPrintln(String str) {
        if (!submission) {
            System.out.println(str);
        }
    }
}
