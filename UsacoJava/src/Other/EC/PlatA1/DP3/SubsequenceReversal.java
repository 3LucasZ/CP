package Other.EC.PlatA1.DP3;

import java.io.*;

public class SubsequenceReversal {

    static boolean submission = false;
    static boolean debug = true;

    static int N;
    static int A[];
    public static void main(String[] args) throws IOException {
        //parse
        setup("subrev");
        N = Integer.parseInt(br.readLine());
        A = new int[N+2];
        for (int i=1;i<=N;i++){
            A[i]=Integer.parseInt(br.readLine());
        }
        A[0]=51;
        A[N+1]=0;

        //dp[lo][hi][min][max] = best from [lo...hi] where min is min pick and max is max pick
        int[][][][] dp = new int[N+2][N+2][52][52];

        //init
        for (int idx=1;idx<=N;idx++){
            dp[idx][idx][A[idx]][A[idx]]=1;
        }
        for (int lo=1;lo<=N-1;lo++){
            int hi=lo+1;
            dp[lo][hi][A[lo]][A[hi]]=2;
            dp[lo][hi][A[hi]][A[lo]]=2;
        }

        //push transitions
        for(int len=0;len<=N;len++){
            for (int lo=1;lo+len<=N;lo++){
                int hi=lo+len;
                for (int min=1;min<=50;min++){
                    for (int max=min;max<=50;max++){
                        //(1)
                        dp[lo-1][hi][min][max] = Math.max(dp[lo-1][hi][min][max], dp[lo][hi][min][max]);
                        dp[lo][hi+1][min][max] = Math.max(dp[lo][hi+1][min][max], dp[lo][hi][min][max]);
                        //(2)
                        if (A[hi+1]>=max){
                            dp[lo][hi+1][min][A[hi+1]] = Math.max(dp[lo][hi+1][min][A[hi+1]], dp[lo][hi][min][max]+1);
                        }
                        if (A[lo-1]<=min){
                            dp[lo-1][hi][A[lo-1]][max] = Math.max(dp[lo-1][hi][A[lo-1]][max], dp[lo][hi][min][max]+1);
                        }
                        //(3)
                        swap(A, lo-1, hi+1);
                        if (A[hi+1]>=max){
                            dp[lo-1][hi+1][min][A[hi+1]] = Math.max(dp[lo-1][hi+1][min][A[hi+1]], dp[lo][hi][min][max]+1);
                        }
                        if (A[lo-1]<=min){
                            dp[lo-1][hi+1][A[lo-1]][max] = Math.max(dp[lo-1][hi+1][A[lo-1]][max], dp[lo][hi][min][max]+1);
                        }
                        //special double add
                        if (A[lo-1]<=min && A[hi+1]>=max) {
                            dp[lo-1][hi+1][A[lo-1]][A[hi+1]] = Math.max(dp[lo-1][hi+1][A[lo-1]][A[hi+1]], dp[lo][hi][min][max]+2);
                        }
                        swap(A, lo-1, hi+1);
                    }
                }
            }
        }

        //ret
        int ans = 0;
        for (int min=1;min<=50;min++){
            for (int max=min;max<=50;max++){
                ans=Math.max(ans,dp[1][N][min][max]);
            }
        }
        out.println(ans);
        out.close();
    }
    public static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }


















    static BufferedReader br;
    static PrintWriter out;
    public static void setup(String fileName) throws IOException {
        if (submission) {
            br = new BufferedReader(new FileReader(fileName+".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
    }
}
