package Misc.Boilerplate;

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
    //boolean
    public static void print2DArr(boolean[][] arr) {
        for (int i=0;i<arr.length;i++) {
            printArr(arr[i]);
        }
        out.println();
    }
    //single char/digit
    public static void print2DArr(int[][] arr) {
        for (int r=0;r<arr.length;r++) {
            for (int c=0;c<arr[r].length;c++) {
                out.print(arr[r][c]+" ");
            }
            out.println();
        }
        out.println();
    }
    public static void debugPrintln(String str) {
        if (!submission) {
            out.println(str);
        }
    }
    private static class MultiSet {
        TreeMap<Integer, Integer> ms = new TreeMap<>();
        public MultiSet(){}
        public void add(int e){
            if (!ms.containsKey(e)) {
                ms.put(e,1);
            }
            else {
                ms.put(e, ms.get(e) + 1);
            }
        }
        public void remove(int e){
            if (ms.get(e)==1) ms.remove(e);
            else ms.put(e,ms.get(e)-1);
        }
        public String toString(){
            return ms.toString();
        }
    }
}
