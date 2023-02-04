package Other.USACOGuide.UsacoGuideBronze.adhoc;

import java.io.*;
import java.util.StringTokenizer;

//Greedy
public class Race {
    //default
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int distance;
    static int n;

    public static void main(String[] args) throws IOException {
        //setup
        if (submission) {
            f = new BufferedReader(new FileReader("race.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("race.out")));
            StringTokenizer st = new StringTokenizer(f.readLine());
            distance = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
        }
        else {
            n = 1;
            distance = 10;

        }
        for (int i=0;i<n;i++) {
            if (submission) {
                out.println(solve(distance, Integer.parseInt(f.readLine())));
            }
            else {
                System.out.println(solve(distance, 2));
            }
        }
        out.close();
        f.close();
    }
    public static int solve(int distance, int lastSpeed) {
        int lhdistance = 0;
        int rhdistance = 0;
        int time = 0;
        for (int currspeed=1;;currspeed++) {
            time ++;
            lhdistance += currspeed;
            if (lhdistance + rhdistance >= distance) {
                return time;
            }
            if (currspeed >= lastSpeed) {
                time ++;
                rhdistance += currspeed;
                if (lhdistance + rhdistance >= distance) {
                    return time;
                }
            }
        }
    }
}
