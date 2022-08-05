package TrainingGateway.Chapter3;

import java.io.*;
import java.util.*;

/*
PROB: ratios
LANG: JAVA
 */

public class ratios {
    static boolean submission = true;
    static boolean debug = true;

    static int[] T,X,Y,Z;
    static int INF = Integer.MAX_VALUE;

    static int min = INF;
    static int[] ret = new int[4];

    public static void main(String[] args) throws IOException {
        //parse
        setup("ratios");
        T = new int[3];
        X = new int[3];
        Y = new int[3];
        Z = new int[3];

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        for (int i=0;i<3;i++) T[i]=Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<3;i++) X[i]=Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<3;i++) Y[i]=Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<3;i++) Z[i]=Integer.parseInt(st.nextToken());

        //complete search
        for (int x=0;x<100;x++){
            for (int y=0;y<100;y++){
                for (int z=0;z<100;z++){
                    gen(x,y,z);
                }
            }
        }

        if (min==INF){
            out.println("NONE");
        } else {
            for (int i=0;i<3;i++) out.print(ret[i]+" ");
            out.println(ret[3]);
        }
        out.close();
    }

    public static void gen(int x, int y, int z){
        int[] mult = new int[3];
        for (int i=0;i<3;i++) {
            int next = x*X[i]+y*Y[i]+z*Z[i];
            if (T[i]==0 && next==0) {
                mult[i]=INF;
            } else if (T[i]==0 && next!=0) {
                mult[i]=0;
            } else if (T[i]!=0 && next==0) {
                return;
            } else if (T[i]!=0 && next!=0) {
                if (next%T[i]!=0) return;
                else mult[i]=next/T[i];
            }
        }

        if ((mult[0]==mult[1] || mult[0]==INF || mult[1]==INF) &&
                (mult[1]==mult[2] || mult[1]==INF || mult[2]==INF) &&
                (mult[0]==mult[2] || mult[0] == INF || mult[2]==INF)) {
            if (x+y+z < min){
                min=x+y+z;
                ret = new int[]{x, y, z, mult[0]};
            }
        }
    }
    public static int gcd(int a, int b){
        if (a==0) return b;
        if (b==0) return a;
        return gcd(b,a%b);
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
