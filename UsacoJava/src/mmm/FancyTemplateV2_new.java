package mmm;

import java.io.*;

public class FancyTemplateV2_new {
    //io
    static BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int n;
    public static void main(String[] args) throws IOException {
        //parse input
        n = Integer.parseInt(f.readLine());
        //logic
        //turn in answer
        out.println();
        out.close();
        f.close();
    }
    //helper method
    public static void printArr(int[] arr) {
        String str = "";
        for (int i=0;i<arr.length;i++) {
            str += (arr[i] + ", ");
        }
        System.out.println(str);
    }
}