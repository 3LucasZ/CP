package Codeforces.Round770;

import java.io.*;
import java.util.*;
/*
Codeforces Round #770
D. Finding Zero
Silver Training: constructive, interactive, math
Thoughts:
Quite difficult, fun implementation
take a series of 4 indice (arbitrary so lets pick first 4 in queue)
ask 4 queries of form !a, !b, !c, !d
the 2 smallest queries are dangerous: ie they may contain a 0
i.e. !a is smallest, a might contain 0
so the smallest are added back into the queue
with 4 queries we are able to reduce 2 indices from our searching
This will barely but perfectly (hint hint) fit the 2*n-1 query requirement
Weird case: n is odd so when we remove 2 each time requiring 4 indice we have this problem: 3 indice
sol: add dummy indice somewhere in the beggining
Some problem hints from the problem statement to look out for:
EXACTLY 1 ZERO
2*n-2 queries, 2 tries to guess the 0
4 <= n <= 1000
 */
public class FindZero {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) throws IOException {
        //parse input
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) solve();
    }
    public static void solve() throws IOException {
        //setup
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> queue = new ArrayList<>();
        for (int i=1;i<=N;i++) queue.add(i);

        while (queue.size()>2){
            int[] safe = handleQuaternion(new int[]{queue.remove(0),queue.remove(0),queue.remove(0),queue.remove(0)});
            queue.add(safe[0]);
            queue.add(safe[1]);
            //dummy add so that queue will be even sized (happens only once)
            if (queue.size()%2==1) queue.add(safe[2]);
        }
        System.out.println("! "+queue.get(0)+" "+queue.get(1));
    }
    public static int[] handleQuaternion(int[] quart) throws IOException {
        Pair[] res = new Pair[4];
        for (int i=0;i<4;i++){
            res[i] = new Pair(i, response(quart, i));
        }
        Arrays.sort(res,(a,b)->a.value-b.value);
        //mistake made here: need to return quart[res[0].index] not res[0].value or index and etc... : )
        return new int[]{quart[res[0].index],quart[res[1].index],quart[res[2].index]};
    }
    public static int response(int[] quart, int spec) throws IOException {
        StringBuilder query = new StringBuilder();
        query.append("? ");
        for (int i=0;i<4;i++){
            if (i!=spec) query.append(quart[i]+" ");
        }
        System.out.println(query);
        return Integer.parseInt(br.readLine());
    }
    private static class Pair {
        int index;
        int value;
        public Pair(int i, int v){
            index=i;
            value=v;
        }
    }
}