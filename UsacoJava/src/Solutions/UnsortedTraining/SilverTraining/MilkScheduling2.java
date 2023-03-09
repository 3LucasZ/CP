package Solutions.UnsortedTraining.SilverTraining;/*
USACO 2013 December Contest, Silver
Problem 1. Milk Scheduling
 */
import java.io.*;
import java.util.*;
public class MilkScheduling2 {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static PriorityQueue<Cow> cows = new PriorityQueue<>((a,b)->b.d-a.d);
    static PriorityQueue<Cow> active = new PriorityQueue<>((a,b)->b.g-a.g);
    //const
    static int MAX_DEADLINE = 10000;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("msched.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("msched.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        for (int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int g = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            cows.add(new Cow(g,d));
        }
        if (!submission) out.println(cows);
        //logic
        int ans = 0;
        for (int i=MAX_DEADLINE;i>=1;i--)  {
            while (!cows.isEmpty() && cows.peek().d == i){
                active.add(cows.poll());
            }
            if (!active.isEmpty()) ans += active.poll().g;
        }
        //turn in answer
        out.println(ans);
        out.close();
    }
    private static class Cow {
        int g;
        int d;
        public Cow(int g, int d){
            this.g=g;
            this.d=d;
        }
        public String toString(){
            return "["+d+": "+g+"]";
        }
    }
}
