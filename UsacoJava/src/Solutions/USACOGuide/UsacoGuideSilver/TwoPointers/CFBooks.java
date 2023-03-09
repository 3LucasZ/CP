package Solutions.USACOGuide.UsacoGuideSilver.TwoPointers;

import java.io.*;
import java.util.*;
/*
Codeforces
B. Books
USACO Guide Silver - 2 Pointers - Easy
 */
public class CFBooks {
    //io
    static BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int N;
    static int T;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(f.readLine());
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        //out.println(Arrays.toString(arr));
        //logic
        int l=0;
        int r=0;
        int sum=arr[0];
        int ans=0;
        while (l<N&&r<N){
            if (sum<=T){
                ans = Math.max(ans, r-l+1);
                r++;
                if(r<N) sum+=arr[r];
            }
            else {
                sum-=arr[l];
                l++;
            }
        }
        //turn in answer
        out.println(ans);
        out.close();
        f.close();
    }
}
