package TrainingGateway.Chapter4;

import java.io.*;
import java.util.*;
/*
PROB: job
LANG: JAVA
*/
public class job {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static int M1;
    static int M2;

    static int[] A;
    static int[] B;

    public static void main(String[] args) throws IOException {
        //parse
        setup("job");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M1 = Integer.parseInt(st.nextToken());
        M2 = Integer.parseInt(st.nextToken());
        A = new int[M1];
        B = new int[M2];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<M1;i++) A[i]=Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<M2;i++) B[i]=Integer.parseInt(st.nextToken());

        //gen end, note they are sorted
        int[] endA = sim(M1,A);
        int[] endB = sim(M2,B);
        if (debug){
            System.out.println(Arrays.toString(endA));
            System.out.println(Arrays.toString(endB));
        }

        //get ans greedy
        int ans = 0;
        for (int i=0;i<N;i++){
            ans=Math.max(endA[i]+endB[N-1-i],ans);
        }

        //ret
        out.println(endA[N-1]+" "+ans);
        out.close();
    }
    public static int[] sim(int M, int[] T){
        //setup sim
        int[] fin = new int[M];
        for (int i=0;i<M;i++)fin[i]=T[i];
        //job sim
        int[] ret = new int[N];
        for (int i=0;i<N;i++){
            int best = 0;
            for (int j=0;j<M;j++){
                if (fin[j]<fin[best])best=j;
            }
            ret[i]=fin[best];
            fin[best]+=T[best];
        }
        //ret
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