package Solutions.Codeforces.Round771;

import java.io.*;
import java.util.*;
/*
Codeforces Round #771 (Div. 2)
A. Reverse
Thoughts:
Ez first try :O
greedy: where does series first break?
1 2 3 7 8 4 5 6
^^ 4 is first to be out of tune
reverse arr from l=4, r=pos[4]
 */
public class Reverse {
    //io
    static boolean debug = false;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    public static void main(String[] args) throws IOException {
        if (debug){
            int[] arr = {0,1,2,3,4,5,6,7,8,9,10};
            reverse(arr,3,6);
            System.out.println(Arrays.toString(arr));
        }
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) solve();
        out.close();
    }
    public static void solve() throws IOException {
        //parse
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++) arr[i] = Integer.parseInt(st.nextToken());
        //logic
        //find first displacement
        int i=1;
        for (;i<=N;i++){
            if (arr[i]!=i) break;
        }
        int l=i;
        int r=l;
        for (;r<=N;r++){
            if (arr[r]==l) break;
        }
        if (r <= N) reverse(arr,l,r);
        for (int k=1;k<=N;k++) out.print(arr[k]+" ");
        out.println();
    }
    public static void reverse(int[] arr, int l, int r){
        for (int i=0;i<=(r-l)/2;i++){
            int tmp = arr[l+i];
            arr[l+i]=arr[r-i];
            arr[r-i]=tmp;
        }
    }
}
