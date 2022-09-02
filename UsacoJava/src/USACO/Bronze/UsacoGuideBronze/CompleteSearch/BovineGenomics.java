package USACO.Bronze.UsacoGuideBronze.CompleteSearch;

import java.io.*;
import java.util.StringTokenizer;

public class BovineGenomics {
    //param
    static int n;
    static int m;
    static int[][] spotted;
    static int[][] plain;
    public static void main(String[] args) throws IOException {
        //default
        PrintWriter out;
        BufferedReader f;
        //setup
        boolean submission = true;
        if (submission) {
            f = new BufferedReader(new FileReader("cownomics.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("cownomics.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        StringTokenizer st = new StringTokenizer(f.readLine());
        //parse input
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        spotted = new int[n][m];
        plain = new int[n][m];
        for (int i=0;i<n;i++) {
            String entry = f.readLine();
            for (int j=0;j<m;j++) {
                spotted[i][j] = entry.charAt(j);
            }
        }
        for (int i=0;i<n;i++) {
            String entry = f.readLine();
            for (int j=0;j<m;j++) {
                plain[i][j] = entry.charAt(j);
            }
        }
        print2DArr(plain);
        print2DArr(spotted);
        //logic
        int count = 0;
        for (int i = 0;i<m;i++) {
            boolean bad = false;
            for (int j=0;j<n;j++) {
                for (int k=0;k<n;k++) {
                    if (spotted[j][i] == plain[k][i]) {
                        bad = true;
                    }
                }
            }
            if (!bad) count ++;
        }
        //turn in answer
        out.println(count);
        out.close();
        f.close();
    }
    public static void printArr(int[] arr) {
        String str = "";
        for (int i=0;i<arr.length;i++ ){
            str += (arr[i] + ", ");
        }
        System.out.println(str);
    }
    public static void print2DArr(int[][] arr) {
        for (int i=0;i<arr.length;i++) {
            printArr(arr[i]);
        }
    }
}
