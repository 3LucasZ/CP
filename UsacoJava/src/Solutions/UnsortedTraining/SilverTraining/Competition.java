package Solutions.UnsortedTraining.SilverTraining;

import java.io.*;
import java.util.*;
/*
Codeforces Round #103 (Div. 2)
E. Competition
USACO Silver Training
Codeforces Randomizer Greedy 2200
Thoughts:
Read the crappy editorial... got the idea
turn problem into interval processing problem
use double pq to process and get answer :)
 */
public class Competition {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;
    //param
    static int N;
    static int M;
    static PriorityQueue<Seg> segments = new PriorityQueue<>((a,b)->a.start-b.start);
    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            segments.add(new Seg(N-r+1,c,i+1));
        }
        if (debug) System.out.println(segments);

        PriorityQueue<Seg> toProcess = new PriorityQueue<>((a,b)->a.end-b.end);
        ArrayList<Integer> ans = new ArrayList<>();
        for (int t=1;t<=N;t++){
            while (!segments.isEmpty() && segments.peek().start==t) toProcess.add(segments.poll());
            while (!toProcess.isEmpty() && toProcess.peek().end < t) toProcess.poll();
            if (!toProcess.isEmpty()) ans.add(toProcess.poll().id);
        }

        //turn in answer
        out.println(ans.size());
        for (int a : ans) out.print(a+" ");
        out.close();
    }
    private static class Seg {
        int id;
        int start;
        int end;
        public Seg(int s, int e, int i){
            start=s;
            end=e;
            id=i;
        }
        public String toString(){
            return "["+start+", "+end+"]";
        }
    }
}
