package USACO.Silver.UsacoGuideSilver.TwoPointers;

import java.io.*;
import java.util.*;
/*
CSES Problem Set
Sum of Two Values
USACO Guide Silver - 2 Pointers - Easy
Concepts: Greedy sorting, 2 pointers, custom comparator
 */
public class SumOfTwoValues {
    //io
    static BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int n;
    static int target;
    static Element[] nums;
    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());
        nums = new Element[n];
        st = new StringTokenizer(f.readLine());
        for (int i=0;i<n;i++) {
            nums[i] = new Element(i+1, Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(nums, (a,b)->a.num-b.num);
        int l = 0;
        int r = n-1;
        boolean impossible = true;
        while (l<r) {
            if (nums[l].num+nums[r].num == target) {
                out.println(nums[l].index+" "+nums[r].index);
                impossible = false;
                break;
            }
            if (nums[l].num+nums[r].num > target) {
                r--;
            }
            else {
                l++;
            }
        }
        if (impossible) out.println("IMPOSSIBLE");
        out.close();
        f.close();
    }
    private static class Element {
        int index;
        int num;
        public Element(int i, int n) {
            index = i;
            num = n;
        }
        public String toString() {
            return "["+index+": " + num + "]";
        }
    }
}
