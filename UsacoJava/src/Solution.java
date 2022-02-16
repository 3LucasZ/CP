import java.io.*;
import java.util.*;

public class Solution {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=1;i<=T;i++){
            out.print("Case #"+i+": ");
            solve();
            out.println();
        }
        out.close();
    }

    public static void solve() throws IOException {
        //parse
        int N = Integer.parseInt(br.readLine());
        //init queries and active arraylist
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] query = new int[N];
        ArrayList<Integer> active = new ArrayList<>();
        for (int i=0;i<N;i++)query[i] = Integer.parseInt(st.nextToken());

        //setup with first query
        int HIndex=1;
        active.add(query[0]);

        for (int cur=1;cur<N;cur++){
            int pos = leq(active, HIndex)+1;
            active.add(pos, query[cur]);
            if (pos+1 > HIndex) HIndex++;
            out.print(HIndex+" ");
        }
    }
    public static int leq(ArrayList<Integer> arr, int query){
        int l=0; int r=arr.size()-1;
        while (l<r){
            int mid = (l+r)/2;
            if (arr.get(mid)>query)r=mid-1;
            else l=mid;
            //System.out.println("Stuck at: "+l+", "+r);
        }
        return l;
    }
}