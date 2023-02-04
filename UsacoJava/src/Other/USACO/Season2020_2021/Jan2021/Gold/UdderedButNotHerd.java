package Other.USACO.Season2020_2021.Jan2021.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

public class UdderedButNotHerd {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = true;

    //heard
    static String str;
    //heard len
    static int L;
    //unique letters
    static int N = 0;
    //char -> index
    static HashMap<Character, Integer> index = new HashMap<>();
    //cnt
    static int[][] cnt;

    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        //parse
        str = br.readLine();
        L = str.length();

        //indexing
        for (int i=0;i<L;i++){
            if (!index.containsKey(str.charAt(i))) index.put(str.charAt(i),N++);
        }

        //letter adj cnt
        cnt = new int[N][N];
        for (int i=0;i<L-1;i++){
            int l = index.get(str.charAt(i));
            int r = index.get(str.charAt(i+1));
            cnt[l][r]++;
        }

        //subset dp
        int subsets = 1 << N;
        int[] dp = new int[subsets];
        dp[0]=1;
        for (int s=1;s<subsets;s++){
            dp[s]=L;
            for (int last=0;last<N;last++){
                int bit = 1<<last;
                if ((s&bit)!=0){
                    int sum = dp[s^bit];
                    for (int other=0;other<N;other++){
                        int otherBit = 1<<other;
                        if ((s&otherBit)!=0){
                            sum += cnt[other][last];
                        }
                    }
                    dp[s]=Math.min(dp[s],sum);
                }
            }
        }

        out.println(dp[subsets-1]);
        out.close();
    }
}
