package USACO.Bronze.Usaco_2021_December_InstantPromotion;/*
USACO 2021 December Contest, Bronze
Problem 2. Air Cownditioning
 */
import java.io.*;
import java.util.*;
public class AirCownditioning {
    //io
    static BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int n;
    static int dx[];
    public static void main(String[] args) throws IOException {
        //parse input
        n = Integer.parseInt(f.readLine());
        dx = new int[n];
        StringTokenizer st1 = new StringTokenizer(f.readLine());
        StringTokenizer st2 = new StringTokenizer(f.readLine());
        for (int i=0;i<n;i++) {
            dx[i]=Integer.parseInt(st1.nextToken())-Integer.parseInt(st2.nextToken());
        }
        //out.println(Arrays.toString(dx));
        //logic
        int ans = Math.abs(dx[0]);
        for (int i=1;i<n;i++) {
            if (dx[i]<=0 && dx[i-1] <=0){
                ans += Math.max(0, Math.abs(dx[i])-Math.abs(dx[i-1]));
            }
            else if (dx[i]>=0 && dx[i-1] >=0){
                ans += Math.max(0, dx[i]-dx[i-1]);
            }
            else if (dx[i]>=0 && dx[i-1]<=0) {
                ans += Math.abs(dx[i]);
            }
            else if (dx[i]<=0 && dx[i-1]>=0){
                ans += Math.abs(dx[i]);
            }
        }
        //turn in answer
        out.println(ans);
        out.close();
        f.close();
    }
}
