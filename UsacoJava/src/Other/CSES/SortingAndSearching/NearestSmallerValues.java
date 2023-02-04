package Other.USACOGuide.UsacoGuideGold.Stacks;

import java.io.*;
import java.util.*;
/*
CSES Problem Set
Nearest Smaller Value
USACO Gold Guide - Stacks - Easy
Thoughts:
THIS IS THE PQ APPROACH, STACK APPROACH WILL BE V.2!
basically stole the idea from frisbee 2022 silver january hahahaha
 */
public class NearestSmallerValues {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int N;
    static Entry[] arr;
    static int[] ans;
    public static void main(String[] args) throws IOException {
        //parse input
        N = Integer.parseInt(br.readLine());
        arr = new Entry[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) arr[i] = new Entry(i+1, Integer.parseInt(st.nextToken()));

        //logic
        Arrays.sort(arr,(a,b)->{
            if (a.value==b.value) return b.index-a.index;
            return a.value-b.value;
        });
        TreeSet<Integer> index = new TreeSet<>();
        ans = new int[N+1];
        for (Entry e : arr){
            Integer left = index.lower(e.index);
            if (left==null) ans[e.index]=0;
            else ans[e.index]=left;
            index.add(e.index);
        }
        //turn in answer
        for (int i=1;i<=N;i++) out.print(ans[i]+" ");
        out.close();
    }
    private static class Entry {
        int index;
        int value;
        public Entry(int i, int v){
            index=i;
            value=v;
        }
    }
}
