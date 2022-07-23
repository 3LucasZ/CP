package Contests.Codeforces.Round797;

import java.io.*;
import java.util.*;

public class ShiftingString {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++){
            solve();
        }
        out.println();
        out.close();
    }
    public static void solve() throws IOException{
        //parse
        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();
        int[] p = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++){
            p[i] = Integer.parseInt(st.nextToken());
        }

        //log
        int[] transform = new int[N+1];
        for (int i=1;i<=N;i++){
            transform[p[i-1]]=i;
        }
        if (debug){
            System.out.println(Arrays.toString(transform));
        }
        long ans = 1;
        //disjoint cycles
        boolean[] visited = new boolean[N+1];
        for (int i=1;i<=N;i++){
            if (visited[i]) continue;
            ArrayList<Character> cycle = new ArrayList<>();
            int node=i;
            while (true){
                cycle.add(str.charAt(node-1));
                node=transform[node];
                visited[node]=true;
                if (node==i) break;
            }
            for (int rotate=1;rotate<=cycle.size();rotate++){
                if (cycle.size()%rotate==0){
                    boolean goodRotation = true;
                    for (int j=0;j<cycle.size();j++){
                        if(cycle.get((j+rotate)%cycle.size())!=cycle.get(j)) goodRotation=false;
                    }
                    if (goodRotation) {
                        ans=lcm(ans,rotate);
                        break;
                    }
                }
            }
        }
        out.println(ans);
    }
    public static long lcm(long a, long b){
        return a*b/gcd(a,b);
    }
    public static long gcd(long a, long b){
        if (b==0) return a;
        return gcd(b,a%b);
    }
}
