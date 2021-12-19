package Bronze.UsacoGuideBronze.CompleteSearch;

import java.io.*;
import java.util.StringTokenizer;

public class MilkPails {
    //param
    static int n;
    public static void main(String[] args) throws IOException {
        //default
        PrintWriter out;
        BufferedReader f;
        //setup
        boolean submission = true;
        if (submission) {
            f = new BufferedReader(new FileReader("pails.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("pails.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        int b1 = Integer.parseInt(st.nextToken());
        int b2 = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int max = 0;
        for (int i=0;i<m/b1+1;i++) {
            for (int j=0;j<m/b2+1;j++) {
                int sum = i*b1 + j*b2;
                if (sum <= m && sum > max) {
                    max = sum;
                }
            }
        }
        //logic
        //turn in answer
        out.println(max);
        out.close();
        f.close();
    }
}
