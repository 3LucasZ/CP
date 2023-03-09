package Solutions.USACOGuide.UsacoGuideSilver.TwoPointers;

import java.io.*;
import java.util.*;
/*
USACO 2016 US Open Contest, Silver
Problem 2. Diamond Collector
USACO Guide Silver - 2 Pointers - Normal
Concepts: algorithm to find unique (moving a divider), prefixsums
 */
public class DiamondCollector {
    //io
    static BufferedReader f;
    static PrintWriter out;
    static boolean submission = true;
    //param
    static int n;
    static int k;
    static int[] sizes;
    static int[] lSums;
    static int[] rSums;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            f = new BufferedReader(new FileReader("diamond.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("diamond.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        n=Integer.parseInt(st.nextToken());
        k=Integer.parseInt(st.nextToken());
        sizes=new int[n];
        for (int i=0;i<n;i++) {
            sizes[i] = Integer.parseInt(f.readLine());
        }
        //logic
        Arrays.sort(sizes);
        //out.println(Arrays.toString(sizes));

        /*-------------------------------LEFT--------------------------------*/
        lSums = new int[n+1];
        int r=0;
        int l=0;
        for (r=0;r<n;r++) {
            lSums[r+1]=lSums[r];
            while(sizes[r]-sizes[l]>k) {
                l++;
                lSums[r+1]--;
            }
            lSums[r+1]++;
        }
        //out.println(Arrays.toString(lSums));
        for (int i=1;i<=n;i++) {
            lSums[i]=Math.max(lSums[i],lSums[i-1]);
        }
        //out.println(Arrays.toString(lSums));
        /*-------------------------------RIGHT--------------------------------*/
        rSums = new int[n+1];
        r=n-1;
        l=n-1;
        for (l=n-1;l>=0;l--) {
            rSums[l]=rSums[l+1];
            while (sizes[r]-sizes[l]>k){
                r--;
                rSums[l]--;
            }
            rSums[l]++;
        }
        //out.println(Arrays.toString(rSums));
        for (int i=n-1;i>=0;i--) {
            rSums[i]=Math.max(rSums[i],rSums[i+1]);
        }
        //out.println(Arrays.toString(rSums));
        int ans=0;
        for (int i=0;i<=n;i++){
            ans=Math.max(ans, rSums[i]+lSums[i]);
        }
        out.println(ans);
        out.close();
    }
}
