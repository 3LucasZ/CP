import java.io.*;
import java.util.*;
public class AppleCatching {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);

    //param
    static int N;
    static ArrayList<Cluster> apples = new ArrayList<>();
    static ArrayList<Cluster> cows = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        //parse input
        N = Integer.parseInt(br.readLine());
        for (int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            Cluster add = new Cluster(t,x,n);
            if (q==1) cows.add(add);
            else apples.add(add);
        }

        //logic


        //turn in answer
        out.println();
        out.close();
    }
    private static class Cluster {
        int n;
        int t;
        int x;
        public Cluster(int t, int x, int n){
            this.t=t;
            this.x=x;
            this.n=n;
        }
    }
}
