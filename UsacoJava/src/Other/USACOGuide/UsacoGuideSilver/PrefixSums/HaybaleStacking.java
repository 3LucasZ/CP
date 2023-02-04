package Other.USACOGuide.UsacoGuideSilver.PrefixSums;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class HaybaleStacking {
    //param
    //num stacks
    static int n;
    //num instruction
    static int k;
    static int[] dbales;
    static int[] bales;
    public static void main(String[] args) throws IOException {
        //default
        PrintWriter out;
        BufferedReader f;
        //setup
        boolean submission = false;
        if (submission) {
            f = new BufferedReader(new FileReader(".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(".out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dbales = new int[n+1];
        bales = new int[n+1];
        dbales[0] = 0;
        bales[0] = 0;
        for (int i=0;i<k;i++) {
            st = new StringTokenizer(f.readLine());
            dbales[Integer.parseInt(st.nextToken())]++;
            dbales[Integer.parseInt(st.nextToken())+1]--;
        }
        printArr(dbales);
        //logic
        for (int i=1;i<n+1;i++) {
            bales[i] = bales[i-1] + dbales[i];
        }
        printArr(bales);
        Arrays.sort(bales);
        printArr(bales);
        System.out.println("median at: " + ((n/2) + 1));
        //turn in answer
        out.println(bales[(n/2)+1]);
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
