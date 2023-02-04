package Other.EC.GoldB2.MIX9;

import java.io.*;
import java.util.*;

public class ChocolateEating {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int N;
    static int D;
    static int[] A;
    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        A = new int[N];
        for (int i=0;i<N;i++){
            A[i]=Integer.parseInt(br.readLine());
        }
        //logic
        long lo=0;
        long hi=(long)5e10;
        while (lo < hi){
            long med = (lo+hi+1)/2;
            if (tryMin(med)) lo=med;
            else hi=med-1;
        }
//        for (int test=0;test<=50;test++){
//            System.out.println(test+": "+tryMin(test));
//        }
        //turn in answer
        out.println(lo);
        out.close();
    }
    public static boolean tryMin(long min){
        long tot=0;
        int j=0;
        for (int i=0;i<D;i++){
            while (j < N && tot < min) {
                tot+=A[j];
                j++;
            }
            if (tot < min) return false;
            tot/=2;
        }
        return true;
    }
}
