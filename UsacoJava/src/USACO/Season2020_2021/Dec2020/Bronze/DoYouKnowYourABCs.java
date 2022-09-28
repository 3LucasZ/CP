package USACO.Season2020_2021.Dec2020.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DoYouKnowYourABCs {
    //param
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        int[] nums = new int[7];
        for (int i=0;i<7;i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        //logic
        Arrays.sort(nums);
        if (nums[0] + nums[1] + nums[2] == nums[6]) {
            System.out.println(nums[0] + " " + nums[1] + " " + nums[2]);
        }
        else {
            System.out.println(nums[0] + " " + nums[1] + " " + nums[3]);
        }
    }
}
