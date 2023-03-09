package Solutions.USACOGuide.UsacoGuideSilver.SilverGreedy;/*
USACO 2018 US Open Contest, Silver
Problem 2. Lemonade Line
USACO Guide Silver - Greedy - Very Easy
 */
import java.io.*;
import java.util.*;
public class LemonadeLine {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int n;
    static Integer[] wait;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            f = new BufferedReader(new FileReader("lemonade.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("lemonade.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        n = Integer.parseInt(f.readLine());
        wait = new Integer[n];
        StringTokenizer st = new StringTokenizer(f.readLine());
        for (int i=0;i<n;i++) {
            wait[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(wait, Comparator.reverseOrder());
        //logic
        int i;
        for (i=0;i<n;i++) {
            if (wait[i] < i) break;
        }
        //turn in answer
        out.println(i);
        out.close();
        f.close();
    }
}
