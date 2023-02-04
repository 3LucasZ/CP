package Other.USACOGuide.UsacoGuideSilver.TwoPointers;

import java.io.*;
import java.util.*;
/*
CSES Problem Set
Sum of Three Values
USACO Guide - 2 Pointers - Easy
 */
public class SumOfThreeValues {
    //io
    static BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int N;
    static int T;
    static Element[] arr;
    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        arr = new Element[N];
        st = new StringTokenizer(f.readLine());
        for (int i=0;i<N;i++) {
            arr[i] = new Element(i+1,Integer.parseInt(st.nextToken()));
        }
        //logic
        Arrays.sort(arr, (a,b)->a.num-b.num);
        if (!possible()) out.println("IMPOSSIBLE");
        //close
        out.close();
        f.close();
    }
    public static boolean possible(){
        for (int i=1;i<N-1;i++) {
            int left=0;
            int right=N-1;
            int target = T-arr[i].num;
            while (left < right) {
                if (left == i) left++;
                if (right == i) right--;
                int sum = arr[left].num + arr[right].num;
                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    out.println(arr[left].pos + " " + arr[right].pos + " " + arr[i].pos);
                    return true;
                }
            }
        }
        return false;
    }
    private static class Element {
        int pos;
        int num;
        public Element(int p, int n){
            pos=p;
            num=n;
        }
    }
}
