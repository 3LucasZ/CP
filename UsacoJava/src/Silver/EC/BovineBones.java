package Silver.EC;

import java.io.*;
import java.util.*;

public class BovineBones {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int[] dice;
    static int[] rolls;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("bones.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("bones.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        dice = new int[3];
        for (int i=0;i<3;i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }
        rolls = new int[dice[0]+dice[1]+dice[2]+1];
        //logic
        for (int i=1;i<=dice[0];i++) {
            for (int j=1;j<=dice[1];j++) {
                for (int k=1;k<=dice[2];k++) {
                    rolls[i+j+k]++;
                }
            }
        }
//        out.println(Arrays.toString(dice));
//        out.println(Arrays.toString(rolls));
        int ans = 0;
        int max = 0;
        for (int i=1;i<rolls.length;i++) {
            if (rolls[i] > max) {
                max = rolls[i];
                ans = i;
            }
        }
        //turn in answer
        out.println(ans);
        out.close();
    }
}
