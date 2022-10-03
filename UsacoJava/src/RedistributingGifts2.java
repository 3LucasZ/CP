import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RedistributingGifts2 {
    static boolean submission = false;
    static boolean debug = false;

    static int N;
    static boolean[][] graph;

    public static void main(String[] args) throws IOException {
        //parse
        setup("");
        N = Integer.parseInt(br.readLine());
        graph = new boolean[N][N];
        for (int cow=0;cow<N;cow++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int favor=N;favor>=1;favor--){
                int gift = Integer.parseInt(st.nextToken());
                int giftOwner = gift-1;
                graph[giftOwner][cow]=true;
                if (gift-1==cow) break;
            }
        }
        if (debug){
            for (int i=0;i<N;i++) System.out.println(Arrays.toString(graph[i]));
        }

        //dp[node set][last node] = number of ways to assign cycles to node set
        //where last node is the last node of the only open cycle
        int sets = 1<<N;
        long[][] dp = new long[sets][N];
        //store finished cycle sets
        long[] ans = new long[sets];
        //base case
        for (int i=0;i<N;i++) dp[0][i]=1;
        //bitset dp
        for (int set=0;set<sets-1;set++){
            for (int last=0;last<N;last++){
                for (int add=0;add<N;add++){
                    if ((set&(1<<add))==0){

                    }
                }
            }
        }

        //handle queries
        int Q = Integer.parseInt(br.readLine());
        for (int q=0;q<Q;q++){
            String str = br.readLine();
            int H = 0;
            int G = 0;
            for (int i=0;i<N;i++){
                if (str.charAt(i)=='H') H+=(1<<i);
                else G+=(1<<i);
            }
            out.println(ans[H]*ans[G]);
        }
        out.close();
    }
    public static String str(int set){
        String ret = "";
        for (int i=0;i<N;i++){
            if (((1<<i)&set)!=0)ret+=1;
            else ret+=0;
        }
        return ret;
    }




















    static BufferedReader br;
    static PrintWriter out;
    public static void setup(String fileName) throws IOException {
        if (submission) {
            br = new BufferedReader(new FileReader(fileName+".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
    }
}
