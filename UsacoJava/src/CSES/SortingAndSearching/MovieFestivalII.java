package USACO.Silver.UsacoGuideSilver.SilverGreedy;

import java.io.*;
import java.util.*;
/*
CSES Problem Set
Movie Festival II
USACO Silver Guide - Greedy + Sorting - Normal
Notes:
Read Solution once and then understood
9/11 because TLE from need of FastIO B(
Thoughts:
First solve the one watcher problem: sort the movies by end time and assign the ones you can
Now solve the N watcher problem: sort the movies by end time and assign each to the watcher that finishes last
 */
public class MovieFestivalII {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int N;
    static int K;
    static Movie[] movies = new Movie[N];
    static Multiset watchers = new Multiset();
    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        movies = new Movie[N];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            movies[i] = new Movie(a,b);
        }
        //logic
        //sort movies by end time
        Arrays.sort(movies, (a,b)->{
            if (a.end==b.end) return a.start-b.start;
            return a.end-b.end;
        });
        //watchers multiset setup
        for (int i=0;i<K;i++) watchers.add(0);
        //loop through the movies (by end time order) and give it to the watcher who can watch it and finished watching earliest
        int ans = 0;
        for (int i=0;i<N;i++) {
            Movie next = movies[i];
            Integer watcher = watchers.ms.floorKey(next.start);
            if (watcher == null) continue;
            watchers.remove(watcher);
            watchers.add(next.end);
            ans++;
        }
        out.println(ans);
        out.close();
    }
    private static class Multiset {
        public TreeMap<Integer, Integer> ms;
        public Multiset(){
            ms = new TreeMap<>();
        }
        public void add(int n){
            if (!ms.containsKey(n)) ms.put(n, 0);
            ms.put(n,ms.get(n)+1);
        }
        public void remove(int n){
            ms.put(n,ms.get(n)-1);
            if (ms.get(n)==0) ms.remove(n);
        }
    }
    private static class Movie {
        int start;
        int end;
        public Movie(int a, int b){
            start=a;
            end=b;
        }
    }
}
