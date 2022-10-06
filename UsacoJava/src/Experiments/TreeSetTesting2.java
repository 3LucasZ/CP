package Experiments;

import java.util.TreeSet;

public class TreeSetTesting2 {
    public static void main(String[] args){
        int N = 7;
        int[] dp = {100,10,10,90,50,80,80,0};
        int[] p = {4,3,1000,2,3,6,30,10000};
        TreeSet<Integer> window = new TreeSet<>((a,b)->{
            //second Gcnt goes from highest to lowest
            if (dp[a]==dp[b]) return p[b]-p[a];
                //first dp goes from lowest to highest
            else return dp[a]-dp[b];
        });
        for (int i=0;i<N;i++) window.add(i);
        for (int i=0;i<N;i++){
            int next = window.pollFirst();
            System.out.println("i: "+next+", dp: "+dp[next]+", p: "+p[next]);
        }
        for (int i=0;i<N;i++) window.add(i);
        System.out.println("higher: "+window.higher(N));
    }
}
