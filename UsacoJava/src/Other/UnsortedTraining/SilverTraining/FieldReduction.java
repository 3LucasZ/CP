package Other.UnsortedTraining.SilverTraining;

import java.io.*;
import java.util.*;
/*
USACO 2016 US Open Contest, Silver
Problem 1. Field Reduction
USACO Silver Training
Thoughts/Concepts: Permutation building, complete search
Official solution: Slick and concise with Greedy obtaination -> wheat/chaff separation
 */
public class FieldReduction {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    //sorted by x
    static Cow[] xcows;
    //sorted by y
    static Cow[] ycows;
    static int minArea = Integer.MAX_VALUE;
    static int T;
    static int R;
    static int D;
    static int L;
    //debug
    static char type[] = {'T','R','D','L'};
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("reduce.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("reduce.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        xcows = new Cow[N];
        ycows = new Cow[N];
        for (int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            Cow add = new Cow(x,y);
            xcows[i] = add;
            ycows[i] = add;
        }
        //logic
        //sort
        Arrays.sort(xcows,(a,b)->a.x-b.x);
        Arrays.sort(ycows,(a,b)->a.y-b.y);
//        System.out.println(Arrays.toString(xcows));
//        System.out.println(Arrays.toString(ycows));
        //unit test for TTT:
//        System.out.println("UNIT TEST:");
//        T = ycows[N-1].y;
//        R = xcows[N-1].x;
//        D = ycows[0].y;
//        L = xcows[0].x;
//        remove(0);
//        remove(1);
//        remove(2);
//        getTRDL();
//        out.println("T:"+T+", R: "+R+", D: "+D+", L: "+L);
//        out.println((T-D)*(R-L));
//        out.println();
        //generate permutation: 3 length long of 4 diff characters
        for (int i=0;i<64;i++) {
            int area = 0;
            Cow[] removed = new Cow[3];
            int k=i;
            T = ycows[N-1].y;
            R = xcows[N-1].x;
            D = ycows[0].y;
            L = xcows[0].x;
            for (int j=0;j<3;j++) {
                removed[j]=remove(k%4);
//                System.out.print(type[k%4] + " ");
                k/=4;
            }
//            System.out.println();
            getTRDL();
            area = (T-D)*(R-L);
            minArea = Math.min(area, minArea);
            for (int j=0;j<3;j++) {
                removed[j].include = true;
            }
        }
        //turn in answer
        out.println(minArea);
        out.close();
    }
    public static void getTRDL(){
        for (int i=0;i<N;i++) {
            if (xcows[i].include){
                L = xcows[i].x;
                break;
            }
        }
        for (int i=0;i<N;i++) {
            if (ycows[i].include){
                D = ycows[i].y;
                break;
            }
        }
        for (int i=N-1;i>=0;i--) {
            if (xcows[i].include){
                R = xcows[i].x;
                break;
            }
        }
        for (int i=N-1;i>=0;i--) {
            if (ycows[i].include){
                T = ycows[i].y;
                break;
            }
        }
    }
    public static Cow remove(int type){
        Cow toRemove = null;
        //Top
        if (type==0){
            for (int i=N-1;i>=0;i--) {
                if (ycows[i].include){
                    ycows[i].include = false;
                    toRemove = ycows[i];
                    break;
                }
            }
        }
        //Right
        else if (type==1){
            for (int i=N-1;i>=0;i--){
                if (xcows[i].include){
                    xcows[i].include = false;
                    toRemove = xcows[i];
                    break;
                }
            }
        }
        //Bottom
        else if (type==2){
            for (int i=0;i<N;i++) {
                if (ycows[i].include){
                    ycows[i].include = false;
                    toRemove = ycows[i];
                    break;
                }
            }
        }
        //Left
        else if (type==3){
            for (int i=0;i<N;i++){
                if (xcows[i].include){
                    xcows[i].include = false;
                    toRemove = xcows[i];
                    break;
                }
            }
        }
        else {
            System.out.println("INVALID TYPE");
        }
        return toRemove;
    }
    private static class Cow {
        int x;
        int y;
        boolean include = true;
        public Cow(int x1, int y1){
            x=x1;
            y=y1;
        }
        public String toString(){
            return "["+x+", "+y+"]";
        }
    }
}
