package USACO.Silver.UsacoGuideSilver.BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
Codeforces: MagicShip
Educational Codeforces Round 60 C.
USACO Silver Guide - Binary Search - Normal
FUNZIES :) - first try 100%
 */
public class MagicShip {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static Vector delta;
    static int N;
    static Vector[] weather = new Vector[]{
            new Vector(0,1),
            new Vector(1,0),
            new Vector(0,-1),
            new Vector(-1,0)};
    static Vector[]  weather_net;
    //temp
    static Vector start;
    static Vector end;
    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        start = new Vector(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        end = new Vector(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        N = Integer.parseInt(br.readLine());
        String str = br.readLine();
        weather_net = new Vector[N];
        Vector prev = new Vector(0,0);
        for (int i=0;i<N;i++) {
            int id;
            if (str.charAt(i)=='U') id=0;
            else if (str.charAt(i)=='R') id=1;
            else if (str.charAt(i)=='D') id=2;
            else id=3;
            weather_net[i]=new Vector(prev.x,prev.y);
            weather_net[i].add(weather[id]);
            prev = weather_net[i];
        }
        //infer
        delta = new Vector(end.x-start.x,end.y-start.y);
        //checkpoint
//        out.println(delta);
//        out.println(Arrays.toString(weather_net));
        //checkpoint
        //for (int i=1;i<=10;i++) out.println(tryDays(i));
        long lo = 1;
        long hi = (long) 1e15;
        while (lo < hi) {
            long med = (lo + hi)/2;
            if (tryDays(med)) hi = med;
            else lo = med+1;
        }
        if (lo == 1e15) out.println(-1);
        else out.println(lo);
        out.close();
    }
    public static boolean tryDays(long days){
        long cycles = (days-1)/N;
        int on_id = (int) ((days-1) % N);
        Vector accumulate = new Vector(weather_net[N-1].x*cycles,weather_net[N-1].y*cycles);
        Vector on_weather = weather_net[on_id];
        Vector net = new Vector(accumulate.x+on_weather.x,accumulate.y+on_weather.y);
        return Math.abs(delta.x - net.x) + Math.abs(delta.y - net.y) <= days;
    }
    private static class Vector {
        long x;
        long y;
        public Vector(long xi, long yi){
            x=xi;
            y=yi;
        }
        public void add(Vector other){
            x+=other.x;
            y+=other.y;
        }
        public String toString(){
            return "["+x+", "+y+"]";
        }
    }
}
