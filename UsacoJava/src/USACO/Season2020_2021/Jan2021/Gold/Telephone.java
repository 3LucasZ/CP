package USACO.Season2020_2021.Jan2021.Gold;

import java.io.*;
import java.util.*;
/*
USACO 2021 January Contest, Gold
Problem 2. Telephone
Thoughts:
Hard, fun, clever...
Sub-problem:
N<=1000
Run Djikstra with N Vertices N*N Edges
Full problem:
N<=10^5
KEY: Flatten the graph
    K is super small < 50
    transmit time is linear and could be thought of as a message travelling 1 cow / unit time
0/1 BFS for shortest path
SetItem <position, sender breed>
go left <position -1, sender breed> + 1 cost
go right <position + 1, sender breed> + 1 cost
accept <position, position breed> + 0 cost

Really elegant, gave up way too early. Similar to lasers and mirrors gold problem!

Debug session:
PRINT -1 IF IMPOSSIBLE, NOT 0
edge case: dont go to node N-1 with a breed that N-1 doesn't accept!
 */
public class Telephone {
    static boolean submission = false;
    static boolean debug = true;

    static int N;
    static int K;
    static int[] breed;

    static boolean[][] canTalk;
    public static void main(String[] args) throws IOException {
        //parse
        setup("");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        breed = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++){
            breed[i] = Integer.parseInt(st.nextToken());
        }
        canTalk = new boolean[K+1][K+1];
        for (int i=1;i<=K;i++){
            String str = br.readLine();
            for (int j=1;j<=K;j++){
                canTalk[i][j]=str.charAt(j-1)=='1';
            }
        }

        //O/1 BFS
        //[location][breed id]
        int[][] cost = new int[N][K+1];
        boolean[][] visited = new boolean[N][K+1];

        Deque<Item> BFS = new LinkedList<>();
        BFS.add(new Item(0,breed[0]));
        visited[0][breed[0]]=true;
        while (!BFS.isEmpty()){
            Item next = BFS.poll();
            if (next.location>0&&!visited[next.location-1][next.breed]) {
                Item left = new Item(next.location - 1, next.breed);
                visited[next.location-1][next.breed]=true;
                cost[next.location-1][next.breed]=cost[next.location][next.breed]+1;
                BFS.addLast(left);
            }
            if (next.location<N-1&&!visited[next.location+1][next.breed]){
                if (!canTalk[next.breed][breed[next.location+1]] && next.location+1==N-1);
                else {
                    Item right = new Item(next.location + 1, next.breed);
                    visited[next.location + 1][next.breed] = true;
                    cost[next.location + 1][next.breed] = cost[next.location][next.breed] + 1;
                    BFS.addLast(right);
                }
            }
            if (canTalk[next.breed][breed[next.location]]&&!visited[next.location][breed[next.location]]){
                Item self = new Item(next.location, breed[next.location]);
                visited[next.location][breed[next.location]]=true;
                cost[next.location][breed[next.location]]=cost[next.location][next.breed];
                BFS.addFirst(self);
            }
        }
        if (cost[N-1][breed[N-1]]==0) out.println(-1);
        else out.println(cost[N-1][breed[N-1]]);
        out.close();
    }
    private static class Item {
        int location;
        int breed;
        public Item(int location, int breed){
            this.location=location;
            this.breed=breed;
        }
    }




















    static BufferedReader br;
    static PrintWriter out;
    public static void setup(String fileName) throws IOException {
        if (submission) {
            br = new BufferedReader(new FileReader(fileName+".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
    }
}
