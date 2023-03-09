package Solutions.RandomContests.LeetCode.Weekly299;

class Solution1 {
    static boolean debug = true;
    public boolean checkXMatrix(int[][] grid) {
        int N = grid.length;
        for (int r=0;r<N;r++) {
            for (int c=0;c<N;c++){
                if (r==c || r==N-1-c) {
                    if (grid[r][c]==0) {
                        return false;
                    }
                }
                else if (grid[r][c]!=0) return false;
            }
        }
        return true;
    }
}

