package USACO.Silver.Open2022;

import java.io.*;
import java.util.*;

public class SubsetEquality {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;

    //param
    static int[] S;
    static int SL;
    static int[] T;
    static int TL;

    static int Q;

    static int[] cntS;
    static int[] cntT;

    static HashSet<Integer> badPair = new HashSet<>();

    public static void main(String[] args) throws IOException {
        //parse input
        String STMP = br.readLine();
        SL = STMP.length();
        S = new int[SL];
        for (int i=0;i<SL;i++) S[i]=STMP.charAt(i)-'a';

        String TTMP = br.readLine();
        TL = TTMP.length();
        T = new int[TL];
        for (int i=0;i<TL;i++) T[i]=TTMP.charAt(i)-'a';

        if (debug){
            System.out.println(Arrays.toString(S));
            System.out.println(Arrays.toString(T));
        }

        Q = Integer.parseInt(br.readLine());

        //preprocess "bad pairs"

        for (int i=0;i<18;i++){
            for (int j=i+1;j<18;j++){
                ArrayList<Integer> Ssb = new ArrayList<>();
                for (int k=0;k<SL;k++) if (S[k]==i || S[k]==j) Ssb.add(S[k]);
                ArrayList<Integer> Tsb = new ArrayList<>();
                for (int k=0;k<TL;k++) if (T[k]==i || T[k]==j) Tsb.add(T[k]);
                if (Ssb.size()!=Tsb.size()) {
                    badPair.add(i*100+j);
                    continue;
                }
                for (int k=0;k<Ssb.size();k++){
                    if (Ssb.get(k)!=Tsb.get(k)) {
                        badPair.add(i*100+j);
                        break;
                    }
                }
            }
        }

        cntS = new int[18];
        cntT = new int[18];
        for (int i=0;i<SL;i++) cntS[S[i]]++;
        for (int i=0;i<TL;i++) cntT[T[i]]++;

        if (debug) {
            System.out.println(badPair);
            System.out.println(Arrays.toString(cntS));
            System.out.println(Arrays.toString(cntT));
        }

        for (int i=0;i<Q;i++){
            String Query = br.readLine();
            boolean good = true;

            for(int j=0;j<Query.length();j++){
                int f=Query.charAt(j)-'a';
                if (Query.length()==1) good=cntS[f]==cntT[f];
                for (int k=j+1;k<Query.length();k++){
                    int s=Query.charAt(k)-'a';
                    if (badPair.contains(f*100+s)) good=false;
                }
            }
            out.print(good?"Y":"N");
        }

        out.close();
    }
}
/*
arrbcderbfdghik
abrdekrlmrighddddd
6
ar

air
r
k
abr
 */