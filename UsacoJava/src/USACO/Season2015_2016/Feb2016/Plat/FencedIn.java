package USACO.Season2015_2016.Feb2016.Plat;

import java.io.*;
import java.util.*;
/*
PROB: FencedIn
LANG: JAVA
*/
public class FencedIn {
    static boolean submission = true;
    static boolean debug = false;

    static int A;
    static int B;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        //parse
        setup("fencedin");
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] col = new int[N+1];
        for (int i=0;i<N;i++) col[i]=Integer.parseInt(br.readLine());
        Arrays.sort(col);
        for (int i=0;i<N;i++) col[i]=col[i+1]-col[i];col[N]=A-col[N];
        Arrays.sort(col);

        int[] row = new int[M+1];
        for (int i=0;i<M;i++) row[i]=Integer.parseInt(br.readLine());
        Arrays.sort(row);
        for (int i=0;i<M;i++) row[i]=row[i+1]-row[i];row[M]=A-row[M];
        Arrays.sort(row);

        if (debug) {
            System.out.println("Col: "+Arrays.toString(col));
            System.out.println("Row: "+Arrays.toString(row));
        }

        //EXTREMELY modified MST
        long cost = (long)row[0]*N+(long)col[0]*M;
        for (int c=1,r=1;c<=N&&r<=M;){
            if (row[r]<col[c]){
                cost+=(long)row[r]*(N-c+1);
                r++;
            } else{
                cost+=(long)col[c]*(M-r+1);
                c++;
            }
        }

        //ret
        out.println(cost);
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