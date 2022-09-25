package EC.SilverPS;

import java.io.*;
import java.util.*;

public class BalancedTeams {
    //io
    static boolean submission = false;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N = 12;
    static int[] skill = new int[12];
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("bteams.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("bteams.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        int tot = 0;
        for (int i=0;i<N;i++) {
            int s = Integer.parseInt(br.readLine());
            skill[i] = s;
            tot+=s;
        }
        //logic
        int ans = Integer.MAX_VALUE;
        for (int i=0;i<Math.pow(4,12);i++) {
            int[] team = new int[12];
            int[] cnt = new int[4];
            int[] tskill = new int[4];
            int k=i;
            for (int j=0;j<12;j++) {
                team[j] = k % 4;
                cnt[team[j]]++;
                tskill[team[j]]+=skill[j];
                k/=4;
            }
            if (cnt[0] != 3 || cnt[1] != 3 || cnt[2] != 3 || cnt[3] != 3) continue;
            Arrays.sort(tskill);
            ans = Math.min(ans, tskill[3]-tskill[0]);
        }
        //turn in answer
        out.println(ans);
        out.close();
    }
}
