import java.io.*;
import java.util.*;

public class GuessTheString {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;
    //param
    static int N;
    static char[] ans;
    static int[] distinct;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        ans = new char[N+1];
        distinct = new int[N+1];
        for (int i=1;i<=N;i++){
            if (debug) System.out.println("Testing: "+i);
            bin(i);
            if (debug) System.out.println(Arrays.toString(ans));
            if (debug) System.out.println(Arrays.toString(distinct));
        }

        out.print("! ");
        for (int i=1;i<=N;i++) out.print(ans[i]);
        out.close();
    }
    public static void bin(int i) throws IOException {
        int lo=1;
        int hi=i;
        while (lo<hi){
            int mid = (lo+hi)/2;
            while (ans[mid]==ans[mid-1]) mid--;
            out.println("? 2 "+mid+" "+i);out.flush();
            int inclusive = Integer.parseInt(br.readLine());
            //out.println("? 2 "+mid+" "+(i-1));out.flush();
            //int exclusive = Integer.parseInt(br.readLine());
            int exclusive = 0;
            HashSet<Character> seen = new HashSet<>();
            for (int j=mid;j<=i-1;j++){
                seen.add(ans[j]);
            }
            exclusive=seen.size();
            if (inclusive==exclusive+1) hi=mid;
            else lo=mid+1;
        }
        if (lo==1){
            out.println("? 1 "+i);out.flush();
            ans[i]=br.readLine().charAt(0);
            distinct[i]=distinct[i-1]+1;
        }
        else {
            ans[i]=ans[lo-1];
            distinct[i]=distinct[i-1];
        }
    }
}
/*
5
g
2
u
2
3
e
3
4
s
2
1
 */
