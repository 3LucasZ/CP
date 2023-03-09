package Solutions.USACO.Season2016_2017.Open2017.Gold;

import java.io.*;
import java.util.*;

public class BovineGenomics {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int N;
    static int M;

    static String[] spotted;
    static String[] plain;

    static int[] R;

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("cownomics.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("cownomics.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }

        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        spotted = new String[N];
        for (int i=0;i<N;i++) spotted[i] = br.readLine();
        plain = new String[N];
        for (int i=0;i<N;i++) plain[i] = br.readLine();

        //logic: hash + brute force
        R = new int[M];
        for (int i=0;i<M;i++) R[i] = (int)(Math.random() * 1e9)+3;

        int ans = Integer.MAX_VALUE;

        for (int i=0;i<M;i++){
            int[] hashes1 = new int[N];
            int[] hashes2 = new int[N];
            for (int j=i;j<M;j++){

                boolean good = true;
                HashSet<Integer> spotSet = new HashSet<>();

                for (int k=0;k<N;k++){
                    hashes1[k]+=R[j]*spotted[k].charAt(j);
                    spotSet.add(hashes1[k]);
                }
                for (int k=0;k<N;k++){
                    hashes2[k]+=R[j]*plain[k].charAt(j);
                    if (spotSet.contains(hashes2[k])){
                        good=false;
                    }
                }

                if (good) {
                    ans = Math.min(ans, j-i+1);
                    break;
                }
            }
        }

        //turn in answer
        out.println(ans);
        out.close();
    }
}
