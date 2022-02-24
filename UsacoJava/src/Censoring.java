import java.io.*;
import java.util.*;
/*
USACO 2015 February Contest, Silver
Problem 1. Censoring (Silver)
USACO Silver/Gold Training
Thoughts:
Looked at solution and saw string hashing... inspired me to check out the topic on USACO Guide
Very nice journey! Solved the problem finally.
Add the letters one by one. If the suffix matches the censor remove it. Very nice/efficient trick!
But how to check if suffix matches censor in constant time? Common-naive way is O(N).
Keep running presum of string hash! :)
Hash collisions can occur. With cascading architecture if hashes equal we also check string equality in O(N),
but because its so rare it is O(1) amortized.
https://usaco.guide/gold/string-hashing?lang=java beautiful explanations of the hash stuff used here :)
 */
public class Censoring {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static StringBuilder result = new StringBuilder();
    static int M;
    static String censor;
    static long censorHash = 0;
    //string hashing algorithm
    static final long A = (long)1e9+7;
    static final long B = (long)1e9+9;
    static long[] pow;
    static long[] preHash;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("censor.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("censor.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        String str = br.readLine();
        N = str.length();
        censor = br.readLine();
        M = censor.length();
        //init
        pow = new long[N];
        preHash = new long[N];
        pow[0]=1;
        for (int i=1;i<N;i++) pow[i]=(pow[i-1]*A)%B;
        for (int i=0;i<M;i++) censorHash=(censorHash+pow[M-i-1]*censor.charAt(i))%B;
        if (!submission){
            System.out.println(str);
            System.out.println(censor);
            System.out.println(Arrays.toString(pow));
            System.out.println(censorHash);
            System.out.println();
        }
        //remove suffix when bad
        int j=0;
        for (int i=0;i<N;i++){
            if (j==0) preHash[j]=str.charAt(i);
            else preHash[j]=(preHash[j-1]*A+str.charAt(i))%B;
            result.append(str.charAt(i));
            if (badSuffix(j, result)) {
                result.delete(j-M+1,j+1);
                j-=M;
            }
            j++;
        }
        //turn in answer
        out.println(result.toString());
        out.close();
    }

    public static boolean badSuffix(int j, StringBuilder sb){
        if (j < M-1) return false;
        long suffixHash;
        if (j==M-1) suffixHash=preHash[j];
        else suffixHash = (((preHash[j]-preHash[j-M]*pow[M])%B)+B)%B;
        if (!submission) System.out.println(suffixHash);
        if(suffixHash!=censorHash) return false;
        return sb.substring(j-M+1).equals(censor);
    }
}
