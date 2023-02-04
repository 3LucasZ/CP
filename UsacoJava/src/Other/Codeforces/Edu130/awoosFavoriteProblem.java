package Other.Codeforces.Edu130;

import java.io.*;
import java.util.*;

public class awoosFavoriteProblem {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) out.println(solve()?"YES":"NO");
        out.close();
    }
    public static boolean solve() throws IOException {
        int N = Integer.parseInt(br.readLine());
        char[] s = br.readLine().toCharArray();
        char[] t = br.readLine().toCharArray();

        int cPointer = 0;
        int bPointer = 0;
        for (int i=0;i<N;i++){
            if (s[i]!=t[i]){
                if (s[i]=='a' && t[i]=='b'){
                    while (true){
                        bPointer++;
                        if (bPointer>=N) return false;
                        if (s[bPointer]=='c') return false;
                        if (s[bPointer]=='b') {
                            s[i]='b';
                            s[bPointer]='a';
                            break;
                        }
                    }
                }
                else if (s[i]=='b' && t[i]=='c'){
                    while (true){
                        cPointer++;
                        if (cPointer>=N) return false;
                        if (s[cPointer]=='a') return false;
                        if (s[cPointer]=='c') {
                            s[i]='c';
                            s[cPointer]='b';
                            break;
                        }
                    }
                }
                else {
                    return false;
                }
            }
            cPointer=Math.max(cPointer,i);
            bPointer=Math.max(bPointer,i);
            if (debug){
                System.out.println("Iteration: "+i);
                System.out.println(Arrays.toString(s));
                System.out.println(Arrays.toString(t));
                System.out.println();
            }
        }
        return true;
    }
}
