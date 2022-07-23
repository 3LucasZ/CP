package Misc.Procrastinate;

public class LongestCommonSubsequence {
    private static class Solution {
        int[][] longestDP;
        public int longestCommonSubsequence(String text1, String text2) {
            longestDP = new int[text1.length()][text2.length()];
            for (int r=0;r<text1.length();r++) {
                for (int c=0;c<text2.length();c++) {
                    if (r > 0) longestDP[r][c] = Math.max(longestDP[r][c], longestDP[r-1][c]);
                    if (c > 0) longestDP[r][c] = Math.max(longestDP[r][c], longestDP[r][c-1]);
                    if (text1.charAt(r)==text2.charAt(c)) longestDP[r][c]++;
                }
            }
            return longestDP[text1.length()-1][text2.length()-1];
        }
    }
    public static void main(String[] args){
        Solution sol = new Solution();
        System.out.println(sol.longestCommonSubsequence("abcde","ace"));
    }
}
