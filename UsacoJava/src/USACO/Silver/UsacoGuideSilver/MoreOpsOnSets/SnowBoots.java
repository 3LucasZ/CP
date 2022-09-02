package USACO.Silver.UsacoGuideSilver.MoreOpsOnSets;

import java.io.*;
import java.util.*;
/*
USACO Gold 2018 February
Snow Boots
USACO Silver Guide - More ops on Sets - Hard
Thoughts:
Looked at solution and did drawings on paper.
sort boots and tiles by decreasing depth
delete tiles if the boot can not step on them O(1) deletion cuz linked list :)
keep track of the gaps after each deletion O(1) Math.max and comp
if boot stride is big enough return 1

very efficient algorithm O(NlogN)
 */
public class SnowBoots {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int B;
    static SnowTile[] tiles;
    static Boot[] boots;
    static int[] ans;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("snowboots.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("snowboots.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        tiles = new SnowTile[N];
        boots = new Boot[B];
        ans = new int[B];

        st = new StringTokenizer(br.readLine());

        for (int i=0;i<N;i++){
            tiles[i]=new SnowTile(Integer.parseInt(st.nextToken()),i);
            //linked list
            if (i>0) {
                tiles[i].left=tiles[i-1];
                tiles[i-1].right=tiles[i];
            }
        }

        for (int i=0;i<B;i++){
            st = new StringTokenizer(br.readLine());
            boots[i] = new Boot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),i);
        }
        //sort tiles decreasing depth
        if (!submission) System.out.println(Arrays.toString(tiles));
        Arrays.sort(tiles, (a,b)->b.depth-a.depth);
        if (!submission) System.out.println(Arrays.toString(tiles));

        //sort boots decreasing depth
        Arrays.sort(boots, (a,b)->b.depth-a.depth);
        if (!submission) System.out.println(Arrays.toString(boots));

        int pointer=0;
        int maxStride = 1;
        for ( Boot boot : boots){
            while (pointer < N && tiles[pointer].depth>boot.depth) {
                //remove tile from linked list if too deep
                tiles[pointer].left.right=tiles[pointer].right;
                tiles[pointer].right.left=tiles[pointer].left;
                //update max stride
                maxStride=Math.max(maxStride,tiles[pointer].right.pos-tiles[pointer].left.pos);
                pointer++;
            }
            //all present tiles are steppable by this boot
            //is max stride small enough?
            if (boot.stride>=maxStride) ans[boot.index]=1;
            else ans[boot.index]=0;
        }

        //print ans
        for (int a:ans) out.println(a);
        out.close();
    }
    private static class Boot {
        int index;
        int depth;
        int stride;
        public Boot(int d, int s, int i){
            index=i;
            depth=d;
            stride=s;
        }
        public String toString(){
            return "["+depth+", "+stride+"]";
        }
    }
    private static class SnowTile {
        SnowTile left = null;
        SnowTile right = null;
        int depth;
        int pos;
        public SnowTile(int d, int p){
            depth=d;
            pos=p;
        }
        public String toString(){
            return "["+depth+", "+pos+"]";
        }
    }
}
