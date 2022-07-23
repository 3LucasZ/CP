package Contests.LeetCode.Weekly299;

public class Solution3 {
    public int maximumsSplicedArray(int[] nums1, int[] nums2) {
        int N = nums1.length;
        int[] A = nums1.clone();
        int[] B = nums2.clone();
        int ans = 0;

        for (int it=0;it<2;it++){
            int sum = 0; for (int i=0;i<N;i++)sum+=A[i];
            int run = 0;
            int maxRun = 0;

            for (int i=0;i<N;i++){
                run += B[i]-A[i];
                run = Math.max(0,run);
                maxRun = Math.max(maxRun,run);
            }
            ans = Math.max(ans,maxRun+sum);
            A=nums2.clone();
            B=nums1.clone();
        }

        return ans;
    }
}

