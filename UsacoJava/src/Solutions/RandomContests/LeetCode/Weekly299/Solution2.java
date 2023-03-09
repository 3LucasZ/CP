package Solutions.RandomContests.LeetCode.Weekly299;

public class Solution2 {
    final long MOD = (long) (1e9+7);
    public int countHousePlacements(int n) {
        long[][] dp = new long[n][2];
        dp[0][0]=1;
        dp[0][1]=1;
        for (int i=0;i<n-1;i++){
            dp[i+1][1]=(dp[i][0])%MOD;
            dp[i+1][0]=(dp[i][0]+dp[i][1])%MOD;
        }
        long half = (dp[n-1][0]+dp[n-1][1])%MOD;
        long ans = (half*half)%MOD;
        return (int) ans;
    }
}
