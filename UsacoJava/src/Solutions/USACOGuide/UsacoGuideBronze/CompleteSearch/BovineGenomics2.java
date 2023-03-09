package Solutions.USACOGuide.UsacoGuideBronze.CompleteSearch;

import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BovineGenomics2 {
    //param
    static int numCows;
    static int numGenomes;
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
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        numCows = Integer.parseInt(st.nextToken());
        numGenomes = Integer.parseInt(st.nextToken());
        spotted = new int[numCows][numGenomes];
        plain = new int[numCows][numGenomes];
        for (int i=0;i<numCows;i++) {
            String str = f.readLine();
            for (int j=0;j<numGenomes;j++) {
                if (str.charAt(j) == 'A') {
                    spotted[i][j] = 0;
                }
                else if (str.charAt(j) == 'C') {
                    spotted[i][j] = 1;
                }
                else if (str.charAt(j) == 'T') {
                    spotted[i][j] = 2;
                }
                else if (str.charAt(j) == 'G') {
                    spotted[i][j] = 3;
                }
            }
        }
        for (int i=0;i<numCows;i++) {
            String str = f.readLine();
            for (int j=0;j<numGenomes;j++) {
                if (str.charAt(j) == 'A') {
                    plain[i][j] = 0;
                }
                else if (str.charAt(j) == 'C') {
                    plain[i][j] = 1;
                }
                else if (str.charAt(j) == 'T') {
                    plain[i][j] = 2;
                }
                else if (str.charAt(j) == 'G') {
                    plain[i][j] = 3;
                }
            }
        }
        //print2DArr(spotted);
        //print2DArr(plain);
        //logic
        int counter = 0;
        for (int i=0;i<numGenomes;i++) {
            for (int j=i+1;j<numGenomes;j++){
                for (int k=j+1;k<numGenomes;k++) {
                    TreeSet<Integer> spottedSums = new TreeSet<Integer>();
                    for (int l=0;l<numCows;l++) {
                        spottedSums.add(base4(spotted[l][i], spotted[l][j], spotted[l][k]));
                    }
                    //System.out.println("i: " + i + " j: " + j + " k: " + k + " sums: " + spottedSums);
                    boolean good = true;
                    for (int l=0;l<numCows;l++) {
                        if (spottedSums.contains(base4(plain[l][i], plain[l][j], plain[l][k]))) good = false;
                    }
                    if (good) counter ++;
                }
            }
        }
        //turn in answer
        out.println(counter);
        out.close();
        f.close();
    }
    public static int base4(int a, int b, int c) {
        return a*16 + b*4 + c;
    }
    public static void printArr(int[] arr) {
        String str = "";
        for (int i=0;i<arr.length;i++) {
            str += (arr[i] + ", ");
        }
        System.out.println(str);
    }
    public static void print2DArr(int[][] arr) {
        for (int i=0;i<arr.length;i++ ){
            printArr(arr[i]);
        }
        System.out.println();
    }
}
