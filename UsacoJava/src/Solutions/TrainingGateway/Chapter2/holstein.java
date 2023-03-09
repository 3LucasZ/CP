package Solutions.TrainingGateway.Chapter2;

import java.io.*;
import java.util.*;

/*
LANG: JAVA
TASK: holstein
 */
public class holstein {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int V;
    static int[] req;
    static int G;
    static int[][] supply;

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("holstein.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        V = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        req = new int[V];
        for (int i=0;i<V;i++) req[i] = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(br.readLine());
        supply = new int[G][V];
        for (int feed=0;feed<G;feed++){
            st = new StringTokenizer(br.readLine());
            for (int vit=0;vit<V;vit++){
                supply[feed][vit] = Integer.parseInt(st.nextToken());
            }
        }
        if (!submission){
            System.out.println(Arrays.toString(req));
            for (int i=0;i<G;i++) System.out.println(Arrays.toString(supply[i]));
        }
        //logic
        //bash every combo for each vit
        int[] ans = new int[G+1];
        for (int i=0;i<Math.pow(2,G);i++){
            int[] amt = new int[V];
            int j=i;
            int cnt=0;
            while (j > 0){
                if (j%2==1) {
                    for (int k=0;k<V;k++){
                        amt[k] += supply[cnt][k];
                    }
                }
                cnt++;
                j/=2;
            }
            boolean bad = false;
            for (int k=0;k<V;k++){
                if (amt[k] < req[k]) bad = true;
            }
            int scoops = Integer.bitCount(i);
            if (!bad && ans[scoops] == 0) ans[scoops] = i;
        }
        if (!submission){
            System.out.println(Arrays.toString(ans));
        }
        for (int i=1;i<=G;i++){
            if (ans[i]!=0){
                //turn in answer
                out.print(i+" ");
                int j=ans[i];
                int cnt=1;
                while (j > 0){
                    if (j%2==1) {
                        out.print(cnt);
                        if (j > 1) out.print(" ");
                    }
                    j/=2;
                    cnt++;
                }
                out.println();
                out.close();
            }
        }
    }
}
