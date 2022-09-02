package USACO.Silver.UsacoGuideSilver.CustomComparatorsAndCoordinateCompression;/*
Code Forces
Correct Placement
USACO Silver Guide - Custom Comparators and Coordinate Compression - Normal
Notes:
adding 2x per friend: w x h and h x w, treating them as 2d points and plane sweep with binary search
be careful to use same ref object in multiple arrays
 */
import java.io.*;
import  java.util.*;
public class CorrectPlacement {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) {
            solve();
        }
        out.close();
    }
    public static void solve() throws IOException {
        //parse
        int N = Integer.parseInt(br.readLine());
        int[] ans = new int[N+1];
        Friend[] friendsSorted = new Friend[2*N];
        for (int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            Friend a = new Friend(i+1,h,w);
            Friend b = new Friend(i+1,w,h);
            friendsSorted[2*i] = a;
            friendsSorted[2*i+1] = b;
            ans[i+1]=-1;
        }
        //sort
        Arrays.sort(friendsSorted, (a,b)->a.h-b.h);
        //checkpoint
        //out.println(Arrays.toString(friends));
        //out.println(Arrays.toString(friendsSorted));
        //left to right sweep with active treeSet
        TreeSet<Friend> active = new TreeSet<>((a,b)->a.w-b.w);
        ArrayList<Friend> waiting = new ArrayList<>();
        for (int i=0;i<2*N;i++){
            if (i!=0 && friendsSorted[i].h!=friendsSorted[i-1].h) {
                for (Friend f : waiting) active.add(f);
                waiting = new ArrayList<>();
            }
            Friend curr = friendsSorted[i];
            Friend pot = active.lower(curr);
            if (pot!=null) ans[curr.id] = pot.id;
            waiting.add(curr);
        }
        //print ans
        for (int i=1;i<=N;i++) {
            out.print(ans[i] + " ");
        }
        out.println();
    }
    private static class Friend {
        int id;
        int h;
        int w;
        public Friend(int id, int h, int w){
            this.id=id;
            this.h=h;
            this.w=w;
        }
        public String toString(){
            return "["+id+": "+h+", "+w+"]";
        }
    }
}
