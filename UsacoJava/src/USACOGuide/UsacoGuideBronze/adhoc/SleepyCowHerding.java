package USACOGuide.UsacoGuideBronze.adhoc;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SleepyCowHerding {
    //param
    static int cows[] = new int[3];
    public static void main(String[] args) throws IOException {
        //default
        PrintWriter out;
        BufferedReader f;
        //setup
        boolean submission = true;
        if (submission) {
            f = new BufferedReader(new FileReader("herding.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("herding.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        cows[0] = Integer.parseInt(st.nextToken());
        cows[1] = Integer.parseInt(st.nextToken());
        cows[2] = Integer.parseInt(st.nextToken());
        Arrays.sort(cows);
        //logic
        int[] diff = {cows[1] - cows[0], cows[2] - cows[1]};
        int min;
        int max;
        Arrays.sort(diff);
        max = diff[1] - 1;
        if (diff[0] == 1) {
            if (diff[1] == 1) min = 0;
            else if (diff[1] == 2) min = 1;
            else min = 2;
        }
        else if (diff[0] == 2) {
            min = 1;
        }
        else {
            min = 2;
        }

        //turn in answer
        out.println(min);
        out.println(max);
        out.close();
        f.close();
    }
}
