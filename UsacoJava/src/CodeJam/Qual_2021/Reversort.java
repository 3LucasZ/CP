package CodeJam.Qual_2021;

import java.io.*;
import java.util.*;
/*
Time Complexity:
T <= 100
N <= 100
O(N*N*T) works!
 */
public class Reversort {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static int T;
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) {
            solve(i+1);
        }
        out.close();
    }
    public static void solve(int round) throws IOException {
        //parse
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int cost = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        //simulate
        for (int i=0;i<N-1;i++) {
            //find next max + index
            int min = 101;
            int minIndex = 101;
            for (int j=i;j<N;j++) {
                if (arr[j] < min) {
                    min=arr[j];
                    minIndex=j;
                }
            }
            cost += minIndex - i + 1;
            //reverse
            for (int k=0;k<(minIndex-i+1)/2;k++) {
                int tmp = arr[i+k];
                arr[i+k] = arr[minIndex-k];
                arr[minIndex-k] = tmp;
            }
            //out.println(Arrays.toString(arr));
        }
        //print ans
        out.println("Case #"+round+": "+cost);
    }
}
