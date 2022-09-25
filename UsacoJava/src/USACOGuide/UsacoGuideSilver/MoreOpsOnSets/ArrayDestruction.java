package USACOGuide.UsacoGuideSilver.MoreOpsOnSets;/*
Codeforces
Round #696 Div. 2
C. Array Destruction
Type: More Ops on Sorted Sets, Normal
Notes:
Looked at solution and solution code
PQ vs Multiset
nuance - when does a removal not work?
How to make adjustments to minimize time (clone tree instead of making a new one)
greedy with FAST IMPLEMENTATION
O(logN * N^2) algorithm
 */
import java.io.*;
import java.util.*;
public class ArrayDestruction {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) {
            out.print(solve());
        }
        out.close();
    }
    //O(N*(NlogN+N) algorithm -> loop through each to remove and
    public static String solve() throws IOException {
        //parse
        int N = 2*Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        MultiSet orig = new MultiSet();
        for (int i=0;i<N;i++) {
            int a = Integer.parseInt(st.nextToken());
            arr[i]=a;
            orig.add(a);
        }
        //which element do we remove
        for (int i=0;i<N;i++) {
            //setup
            StringBuilder ans = new StringBuilder();
            ans.append("YES"+"\n");
            MultiSet active = new MultiSet();
            active.ms = new TreeMap<>(orig.ms);
            int rem = arr[i];
            active.remove(rem);
            if (active.ms.lastKey()<rem) continue;
            int x = active.ms.lastKey();
            active.remove(x);
            ans.append(x+rem+"\n");
            ans.append(x+" "+rem+"\n");
            boolean works = true;
            //loop
            for (int j=0;j<N/2-1;j++){
                int xNew = active.ms.lastKey();
                active.remove(xNew);
                int y = x-xNew;
                if (!active.ms.containsKey(y)) {
                    works=false;
                    break;
                }
                ans.append(xNew+" "+y+"\n");
                active.remove(y);
                x=xNew;
            }
            if (works) return ans.toString();
        }
        return "NO"+"\n";
    }
    private static class MultiSet {
        TreeMap<Integer, Integer> ms = new TreeMap<>();
        public MultiSet(){}
        public void add(int e){
            if (!ms.containsKey(e)) {
                ms.put(e,1);
            }
            else {
                ms.put(e, ms.get(e) + 1);
            }
        }
        public void remove(int e){
            if (ms.get(e)==1) ms.remove(e);
            else ms.put(e,ms.get(e)-1);
        }
        public String toString(){
            return ms.toString();
        }
    }
}
