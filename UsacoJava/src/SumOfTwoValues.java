import java.io.*;
import java.util.*;

public class SumOfTwoValues {
    //io
    static BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int n;
    static int target;
    static int[] nums;
    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());
        nums = new int[n];
        st = new StringTokenizer(f.readLine());
        for (int i=0;i<n;i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);
        printArr(nums);
        int pointer1 = 0;
        int pointer2 = n-1;
        boolean found = false;
        while (true) {
            out.println("HELLO WORLD");
            if (nums[pointer1] + nums[pointer2] < target) {
                pointer1 ++;
            }
            else if (nums[pointer1] + nums[pointer2] == target) {
                found = true;
                break;
            }
            else {
                pointer2 --;
            }
            if (pointer1 >= pointer2) break;
        }
        //logic
        //turn in answer
        if (found) {
            out.println(pointer1 + " " + pointer2);
        }
        else {
            out.println("IMPOSSIBLE");
        }
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
