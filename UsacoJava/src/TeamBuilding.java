import java.io.*;
import java.util.*;

public class TeamBuilding {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;
    //param
    static int poolSize;
    static int teamSize;
    static int audSize;
    static Person[] pool;
    public static void main(String[] args) throws IOException {
        //parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        poolSize = Integer.parseInt(st.nextToken());
        teamSize = Integer.parseInt(st.nextToken());
        audSize = Integer.parseInt(st.nextToken());
        pool = new Person[poolSize];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<poolSize;i++){
            pool[i] = new Person();
            pool[i].audStrength = Integer.parseInt(st.nextToken());
        }
        for (int i=0;i<poolSize;i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<teamSize;j++){
                pool[i].positionStrength[j]=Integer.parseInt(st.nextToken());
            }
        }

        //greedy
        Arrays.sort(pool, Comparator.comparingLong(a->-a.audStrength));
        if (debug) for (Person p : pool) System.out.println(p);

        //greedy bitset dp
        //dp[on xth sweep][team composition]
        //setup
        int subsets = 1<<teamSize;
        long[][] dp = new long[poolSize+1][subsets];
        for (int r=0;r<=poolSize;r++) for (int c=0;c<subsets;c++) dp[r][c]=-1;
        dp[0][0]=0;

        //transition
        for (int x=1;x<=poolSize;x++){
            for (int s=0;s<subsets;s++){
                //try adding x as aud
                int aud = x - 1 - Integer.bitCount(s);
                if (aud < audSize) {
                    if (dp[x-1][s]!=-1){
                        dp[x][s]=dp[x-1][s]+pool[x-1].audStrength;
                    }
                }
                else {
                    if (dp[x-1][s]!=-1){
                        dp[x][s]=dp[x-1][s];
                    }
                }

                //try adding x to position
                for (int position=0;position<teamSize;position++){
                    int bit = 1 << position;
                    if ((s&bit)!=0 && dp[x-1][s^bit]!=-1){
                        dp[x][s]=Math.max(dp[x][s],dp[x-1][s^bit]+pool[x-1].positionStrength[position]);
                    }
                }
            }
        }

        if (debug){
            for (int i=0;i<=poolSize;i++){
                System.out.println(Arrays.toString(dp[i]));
            }
        }
        out.println(dp[poolSize][subsets-1]);
        out.close();
    }
    private static class Person {
        long[] positionStrength;
        long audStrength;
        public Person(){
            positionStrength = new long[teamSize];
        }
        public String toString(){
            return "["+audStrength+", "+Arrays.toString(positionStrength)+"]";
        }
    }
}