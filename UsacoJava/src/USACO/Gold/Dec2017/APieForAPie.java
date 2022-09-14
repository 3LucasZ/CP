package USACO.Gold.Dec2017;/*
USACO 2017 December Contest, Gold
Problem 1. A Pie for a Pie
USACO Gold Training
Thoughts:
Beautiful problem, algorithm in mind worked first try!!!!
Multisource BFS
weight is only 1 per edge
10^5 nodes
using finessed treeset we are able to do logN erase and find
 */

import java.io.*;
import java.util.*;

public class APieForAPie {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int N;
    static int D;
    static Pie[] bessieArr;

    //helper
    static final int INF = Integer.MAX_VALUE;

    static TreeSet<Pie> elsiePies = new TreeSet<>((a, b)-> {
        if (a.B == b.B) return a.id-b.id;
        return a.B-b.B;
    });

    static TreeSet<Pie> bessiePies = new TreeSet<>((a, b)-> {
        if (a.E == b.E) return a.id-b.id;
        return a.E-b.E;
    });

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("piepie.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("piepie.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }

        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        bessieArr = new Pie[N+1];
        for (int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int B = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            Pie pie = new Pie(i,B,E);
            bessieArr[i]=pie;
            bessiePies.add(pie);
            pie.isBessie=true;
        }
        for (int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int B = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            Pie pie = new Pie(i,B,E);
            elsiePies.add(pie);
            pie.isBessie=false;
        }

        //logic: BFS
        Queue<Pie> BFS = new LinkedList<>();
        while (true){
            Pie next = bessiePies.lower(new Pie(INF, INF, 0));
            if (next==null || next.E!=0) break;
            BFS.add(next);
            next.distance=1;
            bessiePies.remove(next);
        }
        while (true){
            Pie next = elsiePies.lower(new Pie(INF, 0, INF));
            if (next==null || next.B!=0) break;
            BFS.add(next);
            next.distance=1;
            elsiePies.remove(next);
        }
        if (!submission) System.out.println(BFS);

        while (!BFS.isEmpty()){
            Pie queue = BFS.poll();
            if (!submission) System.out.println(queue);
            if (!queue.isBessie){
                while (true){
                    Pie next = bessiePies.lower(new Pie(INF, 0, queue.E));
                    if (next==null || queue.E-next.E > D) break;
                    BFS.add(next);
                    next.distance=queue.distance+1;
                    bessiePies.remove(next);
                }
            }
            else {
                while (true){
                    Pie next = elsiePies.lower(new Pie(INF, queue.B, 0));
                    if (next==null || queue.B-next.B > D) break;
                    BFS.add(next);
                    next.distance=queue.distance+1;
                    elsiePies.remove(next);
                }
            }
        }

        //turn in answer
        for (int i=0;i<N;i++){
            out.println(bessieArr[i].distance);
        }
        out.close();
    }


    private static class Pie {
        boolean isBessie;
        int id;
        int B;
        int E;
        int distance = -1;
        public Pie(int id, int B, int E){
            this.id=id;
            this.B=B;
            this.E=E;
        }
        public String toString(){
            return "["+id+": "+B+", "+E+"]";
        }
    }
}
