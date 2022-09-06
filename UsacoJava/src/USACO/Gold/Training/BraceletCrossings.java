package USACO.Gold.Training;

import java.io.*;
import java.util.StringTokenizer;

/*
PROB: BraceletCrossings
LANG: JAVA
*/
public class BraceletCrossings {
    static boolean submission = false;
    static boolean debug = false;

    static int N;
    static int M;
    static int[][] f; //first occurrence of color i on jth line
    static int[][] s; //second occurrence of color i on jth line
    public static void main(String[] args) throws IOException {
        //tcs handler
        setup("BraceletCrossings");
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++)out.println(solve(i)?"YES":"NO");
        out.close();
    }
    public static boolean solve(int t) throws IOException{
        //parse
        br.readLine(); //throwaway
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        f = new int[N+1][M+1];
        s = new int[N+1][M+1];
        for (int i=1;i<=M;i++){
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            for (int j=1;j<=K;j++){
                int color = Integer.parseInt(st.nextToken());
                if (f[color][i]==0) f[color][i]=j;
                else s[color][i]=j;
            }
        }
        if (debug){
            System.out.println();
            System.out.println("Case: "+(t+1));
            for (int i=1;i<=N;i++){
                System.out.println("Color: "+i);
                for (int j=1;j<=M;j++){
                    System.out.println("f: "+f[i][j]+", s: "+s[i][j]);
                }
            }
        }

        //condition 1: color i appears in contiguous range
        for (int color=1;color<=N;color++){
            int last = -1;
            for (int line=1;line<=M;line++){
                //color on line
                if (f[color][line]!=0){
                    if (last!=-1 && line!=last+1) return false;
                    last=line;
                }
            }
        }

        //condition 2: consistency
        boolean[][] ithenj = new boolean[N+1][N+1]; //is [i] before [j]?
        boolean[][] ihasj = new boolean[N+1][N+1]; //does [i] contain [j]?
        boolean[][] inoj = new boolean[N+1][N+1]; //if [i] exists does [j] not exist?
        for (int c1=1;c1<=N;c1++){
            for (int c2=1;c2<=N;c2++){
                for (int line=1;line<=M;line++){
                    //no c2 case
                    if (f[c2][line]==0) continue;
                    //c2 but no c1 case
                    if (f[c1][line]==0){
                        inoj[c2][c1]=true;
                        continue;
                    }
                    //sandwich case
                    if (f[c1][line]<f[c2][line] && s[c1][line]>f[c2][line] && s[c1][line]<s[c2][line]) return false;
                    //nested case
                    if (f[c1][line]<f[c2][line]&&s[c1][line]>s[c2][line]) ihasj[c1][c2]=true;
                    //ordered case
                    if (s[c1][line]<f[c2][line]) ithenj[c1][c2]=true;
                }
            }
        }
        for (int c1=1;c1<=N;c1++){
            for (int c2=1;c2<=N;c2++){
                if (ithenj[c1][c2]&&(ithenj[c2][c1]||ihasj[c1][c2]||ihasj[c2][c1])) {
                    if (debug) {
                        System.out.println("[FAIL] Inconsistency with: "+c1+", "+c2);
                        System.out.println("ihasj: "+ihasj[c1][c2]);
                        System.out.println("jhasi: "+ihasj[c2][c1]);
                        System.out.println("ithenj: "+ithenj[c1][c2]);
                        System.out.println("jtheni: "+ithenj[c2][c1]);
                    }
                    return false;
                }
                if (ihasj[c1][c2]&&(ihasj[c2][c1]||ithenj[c1][c2]||ithenj[c2][c1])) {
                    if (debug) {
                        System.out.println("[FAIL] Inconsistency with: "+c1+", "+c2);
                        System.out.println("ihasj: "+ihasj[c1][c2]);
                        System.out.println("jhasi: "+ihasj[c2][c1]);
                        System.out.println("ithenj: "+ithenj[c1][c2]);
                        System.out.println("jtheni: "+ithenj[c2][c1]);
                    }
                    return false;
                }
                if (inoj[c2][c1] && ihasj[c1][c2]) return false;
            }
        }
        return true;
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