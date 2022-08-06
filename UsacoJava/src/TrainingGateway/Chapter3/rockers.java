package TrainingGateway.Chapter3;

import java.io.*;
import java.util.StringTokenizer;
/*
PROB: rockers
LANG: JAVA
 */
public class rockers {
    static boolean submission = true;
    static boolean debug = true;

    static int N;
    static int T;
    static int M;

    static int[] L;

    public static void main(String[] args) throws IOException {
        //parse
        setup("rockers");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++){
            L[i]=Integer.parseInt(st.nextToken());
        }

        //complete search
        int ans = 0;
        for (int set=0;set<(1<<N);set++){
            int cnt = 0;
            int fill = 0;
            int on = 1;
            for (int bit=0;bit<N;bit++){
                int q = 1<<bit;
                if ((set&q)!=0){
                    if (L[bit]>T) on=2*M;
                    cnt++;
                    if (fill+L[bit]>T){
                        fill=L[bit];
                        on++;
                    } else {
                        fill+=L[bit];
                    }
                }
            }
            if (on<=M) ans=Math.max(ans,cnt);
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
