package TrainingGateway.Chapter3;

import java.io.*;
import java.util.*;

/*
PROB: humble
LANG: JAVA
 */
public class humble {
    static boolean submission = true;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        //parse
        setup("humble");
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[] primes = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<K;i++)primes[i] = Integer.parseInt(st.nextToken());

        //constructive: humble
        int[] humble = new int[N+1];
        int[] primej = new int[K];
        humble[0]=1;
        for (int i=1;i<=N;i++){
            int next = Integer.MAX_VALUE;
            for (int p=0;p<K;p++){
                int prime = primes[p];
                for (;primej[p]<i;primej[p]++) {
                    if (humble[primej[p]]*prime > humble[i-1]){
                        next = Math.min(humble[primej[p]]*prime,next);
                        break;
                    }
                }
            }
            humble[i]=next;
        }

        //ret
        out.println(humble[N]);
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
