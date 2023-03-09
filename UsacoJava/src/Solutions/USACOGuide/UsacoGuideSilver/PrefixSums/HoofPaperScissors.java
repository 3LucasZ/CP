package Solutions.USACOGuide.UsacoGuideSilver.PrefixSums;

import java.io.*;

/*
USACO 2017 January Contest, Silver
Problem 2. Hoof, Paper, Scissors
USACO Guide Silver - Prefix Sums - Easy
 */
public class HoofPaperScissors {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int n;
    static int[] HSum;
    static int[] PSum;
    static int[] SSum;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            f = new BufferedReader(new FileReader("hps.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        n = Integer.parseInt(f.readLine());
        HSum = new int[n+1];
        PSum = new int[n+1];
        SSum = new int[n+1];
        for (int i=1;i<n+1;i++) {
            HSum[i]=HSum[i-1];
            PSum[i]=PSum[i-1];
            SSum[i]=SSum[i-1];
            char fj = f.readLine().charAt(0);
            if (fj=='H'){
                PSum[i]++;
            }
            else if (fj=='P'){
                SSum[i]++;
            }
            else {
                HSum[i]++;
            }
        }
        //out.println(Arrays.toString(HSum));
        //out.println(Arrays.toString(PSum));
        //out.println(Arrays.toString(SSum));
        int max=0;
        for (int i=0;i<n;i++) {
            int HBefore = HSum[i];
            int HAfter = HSum[n] - HSum[i];
            int PBefore = PSum[i];
            int PAfter = PSum[n] - PSum[i];
            int SBefore = SSum[i];
            int SAfter = SSum[n] - SSum[i];
            max = Math.max(HBefore + HAfter, max);
            max = Math.max(HBefore + PAfter, max);
            max = Math.max(HBefore + SAfter, max);

            max = Math.max(PBefore + HAfter, max);
            max = Math.max(PBefore + PAfter, max);
            max = Math.max(PBefore + SAfter, max);

            max = Math.max(SBefore + HAfter, max);
            max = Math.max(SBefore + PAfter, max);
            max = Math.max(SBefore + SAfter, max);
        }
        //logic
        //turn in answer
        out.println(max);
        out.close();
        f.close();
    }
}
