package Other.USACOGuide.UsacoGuideSilver.SilverGreedy;

import java.io.*;
import java.util.*;

/*
CSES Problem Set
Apartments
USACO Silver Guide - Greedy Algorithms with Sorting - Easy
 */
public class Apartments {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static int N;
    static int applicant[];
    static int M;
    static int apartment[];
    static int K;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        applicant = new int[N];
        M = Integer.parseInt(st.nextToken());
        apartment = new int[M];
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++){
            applicant[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<M;i++) {
            apartment[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(apartment);
        Arrays.sort(applicant);
        int i=M-1;
        int j=N-1;
        int ans = 0;
        while (true){
            while (applicant[j] > apartment[i] + K) {
                j--;
                if (j<0) break;
            }
            if (j<0) break;
            if (Math.abs(applicant[j]-apartment[i]) <= K) {
                j--;
                ans++;
            }
            i--;
            if (j<0 || i<0) break;
        }
        out.println(ans);
        out.close();
    }
}
