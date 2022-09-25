package USACOGuide.UsacoGuideSilver.BinarySearch;/*
USACO 2018 December Contest, Silver
Problem 1. Convention
USACO Guide Silver - Binary Search - Easy
 */
import java.io.*;
import java.util.*;
public class Convention {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int N;
    static int M;
    static int C;
    static int[] arrival;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            f = new BufferedReader(new FileReader("convention.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("convention.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arrival = new int[N];
        st = new StringTokenizer(f.readLine());
        for (int i=0;i<N;i++){
            arrival[i] = Integer.parseInt(st.nextToken());
        }
        //arrival[N] = Integer.MAX_VALUE;
        //logic
        Arrays.sort(arrival);
        //out.println(Arrays.toString(arrival));
        int lo=0,hi=arrival[N-1]-arrival[0];
        while (lo<hi){
            int mid = (lo+hi)/2;
            if (tryMinMax(mid)) hi = mid;
            else lo = mid + 1;
        }
        //turn in answer
        out.println(lo);
        out.close();
        f.close();
    }
    public static boolean tryMinMax(int D) {
        int p1 = 0, p2 = 0;
        int buses = 1;
        while (p1 < N && p2 < N) {
            //current state check
            //C cows in bus and within D
            if (p2-p1+1==C && arrival[p2]-arrival[p1]<=D) {
                p2++;
                p1=p2;
                if (p2 != N) buses++;
                //out.println(1);
            }
            //C cows in bus and not within D
            else if (p2-p1+1==C && arrival[p2]-arrival[p1] > D) {
                p1=p2;
                if (p2 != N) buses++;
                //out.println(2);
            }
            //Not C cows in bus and within D
            else if (p2-p1+1<C && arrival[p2]-arrival[p1]<=D) {
                p2++;
                //out.println(3);
            }
            //Not C cows in bus and not within D
            else if (p2-p1+1<C && arrival[p2]-arrival[p1]>D) {
                p1=p2;
                if (p2 != N) buses++;
                //out.println(4);
            }
            //out.println("p1: " + p1 + " , p2: " + p2 + ", buses: " + buses);
        }
        return buses <= M;
    }
}
