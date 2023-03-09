package Solutions.TrainingGateway.Chapter1;

import java.io.*;

/*
LANG: JAVA
TASK: skidesign
USACO Training Revisited
USACO Training
Complete Search
 */
public class skidesign {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int[] heights;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("skidesign.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        heights = new int[N];
        for (int i=0;i<N;i++) {
           heights[i] = Integer.parseInt(br.readLine());
        }
        //search
        int ans = Integer.MAX_VALUE;
        for (int lb=0;lb<=83;lb++) {
            int rb=lb+17;
            int sum = 0;
            for (int i=0;i<N;i++) {
                if (heights[i] < lb) sum += Math.pow(heights[i]-lb,2);
                else if (heights[i] > rb) sum += Math.pow(heights[i]-rb,2);
            }
            ans = Math.min(ans,sum);
        }
        //turn in answer
        out.println(ans);
        out.close();
    }
}
