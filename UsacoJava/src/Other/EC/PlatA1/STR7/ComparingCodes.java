package Other.EC.PlatA1.STR7;

import java.io.*;
import java.util.*;

public class ComparingCodes {
    static boolean submission = false;
    static boolean debug = true;

    static int R;
    static String[][] Rvar;

    static int H;
    static String[][] Hvar;

    static HashSet<Integer> Rhash = new HashSet<>();
    static long[] pow;

    static int ans = 0;

    static long A = 2000000033;
    static long B = 2000000011;

    public static void main(String[] args) throws IOException {
        //parse
        setup("");
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        Rvar = new String[R][3];
        Hvar = new String[H][3];
        for (int i=0;i<R;i++){
            st = new StringTokenizer(br.readLine());
            Rvar[i][0]=st.nextToken();st.nextToken();
            Rvar[i][1]=st.nextToken();st.nextToken();
            Rvar[i][2]=st.nextToken();
        }
        for (int i=0;i<H;i++){
            st = new StringTokenizer(br.readLine());
            Hvar[i][0]=st.nextToken();st.nextToken();
            Hvar[i][1]=st.nextToken();st.nextToken();
            Hvar[i][2]=st.nextToken();
        }

        //pow
        pow = new long[5001]; pow[0]=1;
        for (int i=1;i<=5000;i++) pow[i]=(pow[i-1]*A)%B;

        //rgen
        genHash(true);
        genHash(false);

        out.println(ans);
        out.close();
    }
    public static void genHash(boolean Rmode){
        int N = Rmode?R:H;
        String[][] Nvar = Rmode?Rvar:Hvar;

        HashMap<String,Integer> map = new HashMap<>();
        for (int i=0;i<N;i++){
            map.clear();
            int next = 1;
            long hash = 0;
            for (int j=i;j<N;j++){
                if (!map.containsKey(Nvar[j][0])){
                    map.put(Nvar[j][0],next);
                    next++;
                }
                int l = map.getOrDefault(Nvar[j][1],2000000063);
                int r = map.getOrDefault(Nvar[j][2],2000000063);
                if (l>r) {
                    String tmp = Nvar[j][1];
                    Nvar[j][1]=Nvar[j][2];
                    Nvar[j][2]=tmp;
                }
                for (int v=0;v<3;v++){
                    long p = pow[3*(j-i)+v];
                    hash = (hash + p * map.getOrDefault(Nvar[j][v], 2000000063)) % B;
                }
                handleHash(Rmode, hash,j-i+1);
            }
        }
    }
    public static void handleHash(boolean Rmode, long hash, int len){
        int h = (int) hash;
        if (Rmode) Rhash.add(h);
        else if (Rhash.contains(h)) ans=Math.max(ans,len);
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
