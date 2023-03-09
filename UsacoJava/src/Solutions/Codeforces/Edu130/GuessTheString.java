package Solutions.Codeforces.Edu130;

import java.io.*;
import java.util.*;

public class GuessTheString {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;
    //param
    static int N;
    static char[] ans; //char ans array for 1..N
    static int[][] distinct; //distinct chars in range l..r
    static int[] last; //last index of char[i] a..z ASCII type

    public static void main(String[] args) throws IOException{
        //parse
        N=Integer.parseInt(br.readLine());
        ans = new char[N+1];
        distinct = new int[N+1][N+1];
        last = new int['z'+1];
        //get first letter
        ans[1]=get(1);
        distinct[1][1]=1;
        last[ans[1]]=1;
        //build each letter one by one
        for (int i=2;i<=N;i++){
            //if new letter
            int q = range(1,i);
            if (distinct[1][i-1]==q-1){
                ans[i]=get(i);
            }
            //else find the old letter
            else {
                ArrayList<Integer> search = new ArrayList<>();
                for (int c='a';c<='z';c++){
                    if (last[c]!=0) search.add(last[c]);
                }
                Collections.sort(search);
                if (debug){
                    System.out.println("search: "+search);
                }
                int lo = 0;
                int hi = search.size()-1;
                while (lo<hi){
                    int mid = (lo+hi+1)/2;
                    int A = distinct[search.get(mid)][i-1];
                    int B = range(search.get(mid),i);
                    if (A==B){
                        lo=mid;
                    } else {
                        hi=mid-1;
                    }
                }
                ans[i]=ans[search.get(lo)];
            }
            //update last
            last[ans[i]]=i;
            //update distinct
            HashSet<Character> found = new HashSet<>();
            for (int l=i;l>=1;l--){
                found.add(ans[l]);
                distinct[l][i]=found.size();
            }
            if (debug){
                System.out.println(Arrays.toString(ans));
                System.out.println(Arrays.toString(last));
            }
        }
        //ret
        System.out.print("! ");
        for (int i=1;i<=N;i++) System.out.print(ans[i]);
        System.out.println();
    }
    static char get(int i) throws IOException{
        System.out.println("? 1 "+i);
        return br.readLine().charAt(0);
    }
    static int range(int l, int r) throws IOException {
        System.out.println("? 2 "+l+" "+r);
        return Integer.parseInt(br.readLine());
    }
}
/*
5
g
2
u
3
e
4
s
4
1
3
 */
