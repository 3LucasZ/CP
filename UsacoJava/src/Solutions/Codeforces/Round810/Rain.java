package Solutions.Codeforces.Round810;

import java.io.*;
import java.util.*;

public class Rain {
    static boolean debug = false;

    static int N;
    static long M;
    static Point[] rainDays;

    static TreeMap<Long, Long> map;

    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        N = io.nextInt();
        M = io.nextInt();
        rainDays = new Point[N];
        for (int i=0;i<N;i++){
            int x = io.nextInt();
            int p = io.nextInt();
            rainDays[i] = new Point(i,x,p);
        }

        //* create map of second derivative
        map = new TreeMap<>();
        for (int i=0;i<N;i++){
            add(rainDays[i].x,-2);
            add(rainDays[i].x-rainDays[i].y,1);
            add(rainDays[i].x+rainDays[i].y,1);
        }
        if (debug){
            io.println("map: "+map);
        }

        //* convert map into x, dm arrays
        int sz = map.size();
        long[] x = new long[sz];
        long[] dm = new long[sz];
        for (int i=0;i<sz;i++){
            Map.Entry<Long, Long> entry = map.pollFirstEntry();
            x[i]=entry.getKey();
            dm[i]=entry.getValue();
        }

        //* find rain at each crit
        Point[] crits = new Point[sz];
        long slope = dm[0];
        crits[0]= new Point(0,x[0],0);
        for (int i=1;i<sz;i++){
            crits[i] = new Point(0,x[i],crits[i-1].y+(x[i]-x[i-1])*slope);
            slope+=dm[i];
        }
        if (debug){
            io.println("crits: "+Arrays.toString(crits));
        }
        //quick crit mod to make sure they are more fair
        for (Point p : crits) {
            p.y-=M;
            if (p.y<=0) p.id=100;
        }
        if (debug){
            io.println("crits: "+Arrays.toString(crits));
        }
        //* rotate all crits, rainydays by 45 deg clockwise
        for (Point p : crits) pointRotate45(p);
        for (Point p : rainDays) pointRotate45(p);

        //* find the point of all the crits intersection
        Point thresh = new Point(0,Long.MIN_VALUE,Long.MIN_VALUE);
        for (Point p : crits) {
            if (p.id==100) continue;
            thresh.x=Math.max(thresh.x,p.x);
            thresh.y=Math.max(thresh.y,p.y);
        }
        if (debug){
            io.println("thresh: "+thresh);
            io.println("rainyDays: "+Arrays.toString(rainDays));
        }

        //* find all rainyDays in thresh region
        char[] ret = new char[N];
        for (Point p : rainDays){
            if (p.x>=thresh.x && p.y>=thresh.y){
                ret[p.id]='1';
            } else {
                ret[p.id]='0';
            }
        }

        //* truly ret
        for (int i=0;i<N;i++){
            io.print(ret[i]);
        }
        io.println();
    }
    static void pointRotate45(Point orig){
        long x = orig.x;
        long y = orig.y;
        orig.x=(x+y);
        orig.y=(y-x);
    }
    static void add(long x, long freq){
        if (!map.containsKey(x)) map.put(x,0L);
        map.put(x,map.get(x)+freq);
    }
    private static class Point {
        int id;
        long x;
        long y;
        public Point (int id, long x, long y){
            this.id=id;
            this.x=x;
            this.y=y;
        }
        public String toString(){
            return "["+x+", "+y+"]";
        }
    }




















    static IO io;
    public static void main(String[] args) throws IOException {
        io = new IO(debug);
        int T = io.nextInt();
        for (int i=1;i<=T;i++) solve(i);
        io.close();
    }
    private static class IO {
    BufferedReader br;
    StringTokenizer st;
    PrintWriter out;
    boolean debug;
    public IO(boolean dbg)  {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        debug=dbg;
    }
    public IO(String fileName, boolean dbg) throws IOException {
        br = new BufferedReader(new FileReader(fileName+".in"));
        out = new PrintWriter(new FileWriter(fileName+".out"));
        debug=dbg;
    }
    String next()
    {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }
    int nextInt() { return Integer.parseInt(next()); }
    long nextLong() { return Long.parseLong(next()); }
    double nextDouble() {return Double.parseDouble(next());}
    String nextLine() {
        String str = "";
        try {
            if(st.hasMoreTokens()){
                str = st.nextToken("\n");
            }
            else{
                str = br.readLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
    void println(){
        if (debug) System.out.println();
        else out.println();
    }
    void println(Object obj){
        if (debug) System.out.println(obj);
        else out.println(obj);
    }
    void print(Object obj){
        if (debug) System.out.print(obj);
        else out.print(obj);
    }
    public static void print(int[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                String str = "" + arr[r][c];
                while (str.length() < 5) str += " ";
                System.out.print(str);
            }
            System.out.println();
        }
        System.out.println();
    }
    void close(){
        out.close();
    }
};}