package Solutions.TrainingGateway.Chapter1;

import java.io.*;
import java.util.*;

/*
LANG: JAVA
PROB: milk3
 */
public class milk3 {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int cap[] = new int[3];
    static boolean[][][] visited;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("milk3.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<3;i++) cap[i] = Integer.parseInt(st.nextToken());

        visited = new boolean[cap[0]+1][cap[1]+1][cap[2]+1];
        //logic
        Queue<int[]> BFS = new LinkedList<>();
        BFS.add(new int[]{0,0,cap[2]});
        while (!BFS.isEmpty()) {
            int[] next = BFS.poll();
            if (visited[next[0]][next[1]][next[2]]) continue;
            visited[next[0]][next[1]][next[2]]=true;
            for (int from=0;from<3;from++){
                for (int to=0;to<3;to++){
                    if (from!=to) BFS.add(pour(next,from,to));
                }
            }
        }
        //get answer tree
        TreeSet<Integer> ans = new TreeSet<>();
        for (int r=0;r<=cap[1];r++){
            for (int c=0;c<=cap[2];c++){
                if (visited[0][r][c]) ans.add(c);
            }
        }
        //print formatted answer tree
        int last = ans.pollLast();
        for (int e : ans) out.print(e+" ");
        out.print(last);
        out.println();
        out.close();
    }
    public static int[] pour(int[] orig, int from, int to){
        int pour = Math.min(orig[from], cap[to]-orig[to]);
        int[] ret = orig.clone();
        ret[from] -= pour;
        ret[to] += pour;
        return ret;
    }
    private static class State {
        int a;
        int b;
        int c;
        public State(int a,int b,int c){
            this.a=a;
            this.b=b;
            this.c=c;
        }
    }
}
