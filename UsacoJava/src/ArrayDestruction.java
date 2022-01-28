/*

 */
import java.io.*;
import java.util.*;
public class ArrayDestruction {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) {
            out.println(solve());
        }
        out.close();
    }
    public static String solve() throws IOException {
        //parse
        int N = 2*Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            int a = Integer.parseInt(st.nextToken());
            arr[i]=a;
        }
        //which element do we remove
        for (int i=0;i<N;i++) {
            StringBuilder ans = new StringBuilder();
            PriorityQueue<Integer> active = new PriorityQueue<>();
            for (int j=0;j<N;j++) {
                active.add(arr[j]);
            }
            int rem = arr[i];
            active.remove(rem);
            int x = active.poll();
            ans.append(x+rem+"\n");
            ans.append(x+" "+rem+"\n");
            boolean works = true;
            for (int j=0;j<N/2-1;j++){
                int xNew = active.poll();
                int y = x-xNew;
                if (!active.contains(y)) {
                    works=false;
                    break;
                }
                ans.append(xNew+" "+y+"\n");
                active.remove(y);
                x=xNew;
            }
            if (works) return ans.toString();
        }
        return "NONE";
    }
    private static class Pair {
        int max;
        int x;
        int y;
        public Pair(int x,int y){
            this.x=x;
            this.y=y;
            this.max=Math.max(x,y);
        }
    }
}
