package CodeJam.Round1C_2022;

import java.io.*;
import java.util.*;

public class LetterBlocks {
    static boolean submission = false;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        //* handle TCS
        setup();
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) solve(i);
        System.out.close();
    }

    static int N; //num strings
    static int[] f; //functional graph next[i]
    static int[] b; //functional graph back[i]
    static String[] str; //given strings
    static String[] cstr; //compressed strings
    static int[] freq; //letter frequency
    public static void solve(int tcs) throws IOException {
        System.out.print("Case #" + tcs + ": ");
        if (debug) System.out.println();
        //* parse
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        str = new String[N];
        for (int i=0;i<N;i++) str[i]=st.nextToken();
        //* build cstr and freq
        cstr = new String[N];
        freq = new int[100];
        for (int i=0;i<N;i++){
            StringBuilder sb = new StringBuilder();
            for (int j=0;j<str[i].length();j++){
                freq[str[i].charAt(j)]++;
                if (j==0||str[i].charAt(j)!=str[i].charAt(j-1)){
                    sb.append(str[i].charAt(j));
                }
            }
            cstr[i]=sb.toString();
        }
        if (debug) {
            System.out.println("str: "+Arrays.toString(str));
            System.out.println("cstr: "+Arrays.toString(cstr));
            System.out.println("freq: "+Arrays.toString(freq));
        }
        //* 1: no [A][X][A]
        for (int i=0;i<N;i++) for (int j=0;j<cstr[i].length();j++) if (cntLetter(cstr[i],cstr[i].charAt(j))!=1){
            System.out.println("IMPOSSIBLE");
            return;
        }
        if (debug) System.out.println("PASSED 1");
        //* 2: if [X][A][Y] its the only one with an A
        for (int i=0;i<N;i++) for (int j=1;j<cstr[i].length()-1;j++) for (int k=0;k<N;k++) for (int l=0;l<cstr[k].length();l++){
            if (i==k) continue;
            if (cstr[i].charAt(j)==cstr[k].charAt(l)) {
                System.out.println("IMPOSSIBLE");
                return;
            }
        }
        if (debug) System.out.println("PASSED 2");
        //* 3: at most 1 of form AX, at most 1 of form XA
        for (int i=0;i<N;i++) for (int j=i+1;j<N;j++){
            if (cstr[i].length()==1||cstr[j].length()==1) continue;
            if (cstr[i].charAt(0)==cstr[j].charAt(0)) {
                System.out.println("IMPOSSIBLE");
                return;
            }
            if (cstr[i].charAt(cstr[i].length()-1)==cstr[j].charAt(cstr[j].length()-1)) {
                System.out.println("IMPOSSIBLE");
                return;
            }
        }
        if (debug) System.out.println("PASSED 3");
        //* 4: functional graph all [X][A] to [A][Y] as nodes
        f = new int[N]; Arrays.fill(f,-1);
        b = new int[N]; Arrays.fill(b,-1);
        for (int i=0;i<N;i++) for (int j=0;j<N;j++){
            if (cstr[i].length()==1||cstr[j].length()==1||i==j) continue;
            if (cstr[i].charAt(cstr[i].length()-1)==cstr[j].charAt(0)) {
                f[i]=j;
                b[j]=i;
            }
        }
        if (debug) {
            System.out.println("f: "+Arrays.toString(f));
            System.out.println("b: "+Arrays.toString(b));
        }
        if (debug) System.out.println("PASSED 4");
        //* 5: if cycles, return impossible
        for (int i=0;i<N;i++){
            if (cstr[i].length()==1) continue;
            int cur = i;
            boolean[] in = new boolean[N];
            while (true){
                in[cur]=true;
                cur=f[cur];
                if (cur==-1) break;
                if (in[cur]){
                    System.out.println("IMPOSSIBLE");
                    return;
                }
            }
        }
        if (debug) System.out.println("PASSED 5");
        //* construct the string
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<N;i++){
            if (cstr[i].length()==1) continue;
            if (b[i]==-1){
                int cur = i;
                while (cur!=-1){
                    sb.append(cstr[cur]);
                    cur=f[cur];
                }
            }
        }
        if (debug) System.out.println("sb: "+sb);
        //* ret
        String fin = sb.toString();
        for (int i=0;i<N;i++){
            if (cstr[i].length()==1 && cntLetter(fin,cstr[i].charAt(0))==0) fin+=(cstr[i].charAt(0));
        }
        for (int i=0;i<fin.length();i++){
            if (i==0||fin.charAt(i)!=fin.charAt(i-1)){
                for (int k=0;k<freq[fin.charAt(i)];k++) System.out.print(fin.charAt(i));
            }
        }
        System.out.println();
    }
    static int cntLetter(String str, char letter){
        int ret = 0;
        for (int i=0;i<str.length();i++){
            if (str.charAt(i)==letter) ret++;
        }
        return ret;
    }


    static BufferedReader br;
    static PrintWriter out;

    public static void setup() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
}