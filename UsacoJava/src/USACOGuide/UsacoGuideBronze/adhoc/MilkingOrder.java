package USACOGuide.UsacoGuideBronze.adhoc;
/*
USACO 2018 US Open Contest, Bronze
Problem 2. Milking Order
 */
import java.io.*;
import java.util.*;

public class MilkingOrder {
    //param
    static int n;
    public static void main(String[] args) throws IOException {
        //default
        PrintWriter out;
        BufferedReader f;
        //setup
        boolean submission = false;
        if (submission) {
            f = new BufferedReader(new FileReader("milkorder.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("milkorder.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        int hierarchyNum = Integer.parseInt(st.nextToken());
        int entitledNum = Integer.parseInt(st.nextToken());
        //System.out.println(n + " " + entitledNum + " " + hierarchyNum);
        int[] placement = new int[n+1];
        int[] hierarchy = new int[hierarchyNum];
        st = new StringTokenizer(f.readLine());
        for (int i=0;i<hierarchyNum;i++) {
            hierarchy[i] = Integer.parseInt(st.nextToken());
        }
        //printArr(hierarchy);
        for (int i=0;i<entitledNum;i++) {
            st = new StringTokenizer(f.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            placement[second] = first;
        }
        //logic
        printArr(placement);

        //turn in answer
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
