package Other.USACO.Season2021_2022.Dec2021.Bronze;

import java.io.*;

public class LonelyPhoto2 {
    //io
    static BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int n;
    static int[] GSum;
    static int[] HSum;
    public static void main(String[] args) throws IOException {
        //parse input
        n = Integer.parseInt(f.readLine());
        //logic
        String str = f.readLine();
        GSum = new int[n+1];
        HSum = new int[n+1];
        for (int i=0;i<n;i++) {
            GSum[i+1]=GSum[i];
            HSum[i+1]=HSum[i];
            if (str.charAt(i)=='G') {
                GSum[i+1]++;
            }
            else {
                HSum[i+1]++;
            }
        }
//        out.println(Arrays.toString(GSum));
//        out.println(Arrays.toString(HSum));
        //logic
        long ans = 0;
        for (int l=1;l<=n;l++) {
            for (int r=l+2;r<=n;r++) {
                if (HSum[r]-HSum[l-1]==1){
                    ans++;
                }
                if (GSum[r]-GSum[l-1]==1){
                    ans++;
                }
                if(HSum[r]-HSum[l-1]>1 && GSum[r]-GSum[l-1]>1) break;
            }
        }
        //turn in answer
        out.println(ans);
        out.close();
        f.close();
    }
}
