package Training.SilverTraining;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class HammingDistance {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int N;
    static int K;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        nums = new int[N];
        for (int i=0;i<N;i++) nums[i] = Integer.parseInt(br.readLine(), 2);
        //logic
        int min = Integer.MAX_VALUE;
        for (int i=0;i<N;i++){
            for (int j=i+1;j<N;j++){
                min = Math.min(min, Integer.bitCount(nums[i]^nums[j]));
            }
        }
        //turn in answer
        out.println(min);
        out.close();
    }
}
