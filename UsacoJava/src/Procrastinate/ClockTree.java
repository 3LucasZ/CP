package Procrastinate;

import java.io.*;
import java.util.*;
/*
USACO 2020 February Contest, Silver
Problem 3. Clock Tree
I give up LOL
 */
public class ClockTree {
    //io
    static boolean submission = false;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int N;
    static Room[] barn;
    static int[] origClock;

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            f = new BufferedReader(new FileReader("clocktree.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("clocktree.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(f.readLine());
        barn = new Room[N+1];
        origClock = new int[N+1];
        StringTokenizer st = new StringTokenizer(f.readLine());
        for (int i=1;i<=N;i++) {
            barn[i] = new Room(Integer.parseInt(st.nextToken())%12,i);
            origClock[i] = barn[i].clock;
        }
        for (int i=0;i<N-1;i++) {
            st = new StringTokenizer(f.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            barn[u].connected.add(barn[v]);
            barn[v].connected.add(barn[u]);
        }
        //logic
        int ans = 0;
        for (int i=1;i<=N;i++) {
            if(startAt(i)) ans++;
        }
        //turn in answer
        out.println(ans);
        out.close();
        f.close();
    }
    public static boolean startAt(int room){
        //reset clocks
        for (int i=1;i<=N;i++) barn[i].clock=origClock[i];
        dfs(room, 0);
        if (!submission) System.out.println(Arrays.toString(barn));
        boolean good = true;
        for (int i=1;i<=N;i++) {
            if (barn[i].clock!=0)good=false;
        }
        return good;
    }
    public static void dfs(int node, int parent){
        for (int i=0;i<barn[node].connected.size();i++) {
            Room child = barn[node].connected.get(i);
            if (child.id==parent) continue;
            if (parent==0 && i==barn[node].connected.size()-1) {
                if (!submission) {
                    System.out.println(Arrays.toString(barn));
                    System.out.println(findLeaf(child.id,node));
                }
                dfs(findLeaf(child.id, node), -1);
            }
            else dfs(child.id, node);
        }
        if (parent>0) {
            barn[parent].wiggle(12-barn[node].clock);
            barn[node].clock=0;
        }
    }
    public static int findLeaf(int node, int parent){
        int leaf = 0;
        for (Room child : barn[node].connected){
            if (child.id!=parent) leaf = findLeaf(child.id, node);
        }
        if (barn[node].connected.size()==1) return node;
        return leaf;
    }
    private static class Room {
        int clock;
        int id;
        ArrayList<Room> connected = new ArrayList<>();
        public Room(int c, int i){
            clock=c;
            id=i;
        }
        public void wiggle (int t){
            clock = (clock+t) % 12;
        }
        public String toString(){
            return "["+id+": "+clock+"]";
        }
    }
}
