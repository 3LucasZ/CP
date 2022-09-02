package USACO.Silver.UsacoGuideSilver.CustomComparatorsAndCoordinateCompression;/*
USACO 2019 January Contest, Silver
Problem 3. Mountain View
USACO Guide Silver Custom Comparators Normal
Concepts: Logic, sorting, diagram
 */
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MountainView {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int n;
    static Mountain[] mountains;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            f = new BufferedReader(new FileReader("mountains.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("mountains.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        n = Integer.parseInt(f.readLine());
        mountains = new Mountain[n];
        for (int i=0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            mountains[i] = new Mountain(x-y, x+y);
        }
        //logic
        Arrays.sort(mountains);
        //printArr(mountains);
        int maxRight = mountains[0].x2;
        int count = 1;
        for (int i=1;i<n;i++) {
            if (mountains[i].x2 <= maxRight);
            else {
                maxRight = mountains[i].x2;
                count ++;
            }
        }
        //turn in answer
        out.println(count);
        out.close();
        f.close();
    }
    public static void printArr(Object[] arr) {
        String str = "";
        for (int i=0;i<arr.length;i++) {
            str += (arr[i] + ", ");
        }
        debugPrintln(str);
    }
    public static void debugPrintln(String str) {
        if (!submission) {
            System.out.println(str);
        }
    }
    public static class Mountain implements Comparable<Mountain>{
        int x1;
        int x2;
        public Mountain(int a, int b) {
            x1 = a;
            x2 = b;
        }
        public int compareTo(Mountain other) {
            if (x1 != other.x1) return x1 - other.x1;
            return -1*(x2 - other.x2);
        }
        public String toString() {
            return "(x1: " + x1 + ", x2: " + x2 + ")";
        }
    }
}
/*
3
4 6
5 7
6 8
 */
