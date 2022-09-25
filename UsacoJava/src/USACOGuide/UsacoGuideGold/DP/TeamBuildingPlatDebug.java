package USACOGuide.UsacoGuideGold.DP;

import java.io.*;
import java.util.*;

/*
PROB: TeamBuilding
LANG: JAVA
*/
public class TeamBuildingPlatDebug {
    static boolean submission = false;
    static boolean debug = true;

    static int N;
    static int M;
    static int K;

    static int[] FJ;
    static int[] FP;

    static int MOD = (int)(1e9+7);

    public static void main(String[] args) throws IOException {
        //parse
        setup("TeamBuilding");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        FJ = new int[N]; FJ[0]=Integer.MIN_VALUE;
        FP = new int[M]; FP[0]=Integer.MIN_VALUE;
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++){
            FJ[i]=Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<M;i++){
            FP[i]=Integer.parseInt(st.nextToken());
        }

        //sort
        Arrays.sort(FJ); Arrays.sort(FP);
        if (debug) {
            System.out.println("FJ: "+Arrays.toString(FJ));
            System.out.println("FP: "+Arrays.toString(FP));
        }

        //complete search
        int ans = 0;
        for (int i=0;i<(1<<N);i++){
            for (int j=0;j<(1<<M);j++) {
                if (Integer.bitCount(i)!=K || Integer.bitCount(j)!=K) continue;
                ArrayList<Integer> ai = new ArrayList<>();
                for (int ii=0;ii<N;ii++){
                    if ((i&(1<<ii))!=0) ai.add(FJ[ii]);
                }
                ArrayList<Integer> aj = new ArrayList<>();
                for (int ii=0;ii<M;ii++){
                    if ((j&(1<<ii))!=0) aj.add(FP[ii]);
                }
                boolean good=true;
                for (int ii=0;ii<K;ii++){
                    if (ai.get(ii)<=aj.get(ii)) good=false;
                }
                if (good) ans++;
            }
        }

        //ret
        out.println(ans);
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