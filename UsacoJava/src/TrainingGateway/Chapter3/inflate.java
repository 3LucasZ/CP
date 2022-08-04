package TrainingGateway.Chapter3;/*
PROB: inflate
LANG: JAVA
 */
import java.io.*;
import java.util.*;

public class inflate {
    static boolean submission = true;
    static boolean debug = true;

    static int M;
    static int N;
    static int[] pts;
    static int[] min;
    public static void main(String[] args) throws IOException {
        setup("inflate");
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        pts = new int[N];
        min = new int[N];
        for (int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            pts[i] = Integer.parseInt(st.nextToken());
            min[i] = Integer.parseInt(st.nextToken());
        }
        int[] maxPts = new int[M+1];
        int ret = 0;
        for (int i=0;i<M;i++){
            if (i==0 || maxPts[i]>0){
                for (int j=0;j<N;j++){
                    int index = i+min[j];
                    if (index <= M){
                        maxPts[index]=Math.max(maxPts[index],maxPts[i]+pts[j]);
                        ret=Math.max(ret,maxPts[index]);
                    }
                }
            }
        }

        out.println(ret);
        out.close();
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
