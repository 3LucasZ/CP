package Gold.Training;

import java.io.*;
import java.util.*;
/*
PROB: CircularBarn
LANG: JAVA
*/
public class CircularBarn {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static int[] cows;
    public static void main(String[] args) throws IOException {
        //parse
        setup("cbarn");
        N = Integer.parseInt(br.readLine());
        cows = new int[N];
        for (int i=0;i<N;i++) cows[i]=Integer.parseInt(br.readLine());

        //find "pick up point"
        boolean[] hasCow = new boolean[N];
        int run = 0;
        for (int i=0;i<N;i++){
            run+=cows[i];
            if (run>0) {
                hasCow[i]=true;
                run--;
            }
        }
        int s=0;
        for (int i=1;i<N;i++){
            if (hasCow[i]&&!hasCow[i-1])s=i;
        }

        //pick up cows in a sweep and drop off the earliest one
        Queue<Integer> queue = new LinkedList<>();
        long cost = 0;
        for (int i=0;i<N;i++){
            int j=(s+i)%N;
            for (int k=0;k<cows[j];k++)queue.add(i);
            int drop = queue.poll();
            cost+=(long)(drop-i)*(drop-i);
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