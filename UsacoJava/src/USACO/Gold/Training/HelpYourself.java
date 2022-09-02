package USACO.Gold.Training;

import java.io.*;
import java.util.*;
/*
USACO 2020 February Contest, Gold
Problem 2. Help Yourself
Really nice problem - seems daunting at first but not bad
EXTREMELY close to answer - forgot to include certain segments - gave up a little too early
breakthrough: you have +1 complexity for every number i so that [i,i+1] is good but [i-1,i] is not
 */
public class HelpYourself {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static int[] partner;
    static int[] leftSegments;
    static int[] rightSegments;

    static long ans = 1;
    static final long MOD = (long)1e9+7;
    static long[] pow;

    public static void main(String[] args) throws IOException {
        setup("help");
        N = Integer.parseInt(br.readLine());
        partner = new int[2*N+1];
        for (int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            partner[l]=r;
            partner[r]=l;
        }

        leftSegments = new int[2*N+2];
        rightSegments = new int[2*N+2];
        for (int i=1;i<=2*N;i++)leftSegments[i]=leftSegments[i-1]+(first(i)?0:1);
        for (int i=2*N;i>=1;i--)rightSegments[i]=rightSegments[i+1]+(first(i)?1:0);


        if (debug){
            System.out.println(Arrays.toString(partner));
            System.out.println(Arrays.toString(leftSegments));
            System.out.println(Arrays.toString(rightSegments));
        }

        pow = new long[N+1]; pow[0]=1;
        for (int i=1;i<=N;i++) pow[i]=normalize(2*pow[i-1]);
        ans = pow[N]-1;

//        for (int l=1;l<=2*N;l++){
//            for (int r=l+1;r<=2*N;r++){
//                if (!first(l)&&first(r)){
//                    ans = normalize(ans+pow[leftSegments[l-1]]*pow[rightSegments[r+1]]);
//                }
//            }
//        }
        for (int l=1;l<=2*N;l++){
            if (!first(l)) {
                ans = normalize(ans+pow[leftSegments[l-1]]*(pow[rightSegments[l+1]]-1));
            }
        }
        out.println(ans);
        out.close();
    }
    static boolean first(int x){
        return partner[x]>x;
    }
    static long normalize(long x){
        return (x+MOD)%MOD;
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
