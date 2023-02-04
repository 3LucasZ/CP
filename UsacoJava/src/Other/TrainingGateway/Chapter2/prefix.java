package Other.TrainingGateway.Chapter2;

import java.io.*;
import java.util.*;
/*
LANG: JAVA
TASK: prefix
 */
public class prefix {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static ArrayList<String> primitives = new ArrayList<>();
    static StringBuilder str = new StringBuilder();

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("prefix.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }

        //parse input

        while (true){
            String next = br.readLine();
            if (next.equals(".")) break;
            StringTokenizer st = new StringTokenizer(next);
            while (st.hasMoreTokens()){
                primitives.add(st.nextToken());
            }
        }
       while (br.ready()){
           str.append(br.readLine());
       }

        if (!submission){
            System.out.println(primitives);
            System.out.println(str);
        }
        //logic: dp
        int N = str.length();
        int M = primitives.size();
        boolean[] dp = new boolean[N+1];
        dp[0] = true;
        for (int len=1;len<=N;len++){
            for (String p : primitives){
                int lastIndex = len-p.length();
                if (lastIndex < 0 || !dp[lastIndex]) continue;
                if (str.substring(lastIndex, len).equals(p)) dp[len]=true;
            }
        }
        //turn in answer
        for (int i=N;i>=0;i--){
            if (dp[i]) {
                out.println(i);
                out.close();
                return;
            }
        }
    }
}
