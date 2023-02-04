package Other.UnsortedTraining.SilverTraining;

import java.util.*;
import java.io.*;
/*
USACO 2017 January Contest, Silver
Problem 3. Secret Cow Code
USACO Silver Training - Normal
 */
public class SecretCowCode {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static long K;
    static long N;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("cowcode.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("cowcode.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        String str = st.nextToken();
        K = str.length();
        if (K == 1 || K == 0) {
            out.println(str);
            out.close();
            return;
        }
        N = Long.parseLong(st.nextToken());
        //unit test
        //System.out.println(getCodeSize(1000));
        //logic
        long i = N;
        //out.println(i);
        while (i > K){
            i = nextIndex(i);
            //System.out.println(i);
        }
        //turn in answer
        out.println(str.charAt((int)i-1));
        out.close();
    }
    public static long nextIndex(long i){
        if (i-getCodeSize(i)/2==1){
            return i-1;
        }
        else {
            return i-getCodeSize(i)/2-1;
        }
    }
    public static long getCodeSize(long num) {
        for (int i=0;i<65;i++) {
            if (num > (long)Math.pow(2,i)*K && num <= (long)Math.pow(2,i+1)*K) return (long)Math.pow(2,i+1)*K;
        }
        return 0;
    }
}
