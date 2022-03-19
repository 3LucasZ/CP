import java.io.*;
import java.util.*;
/*
CSES Problem Set
Advanced Techniques
Beautiful Subgrids
Thoughts:
Trial 1: very close, row1, row2, col -> O(n^3) sweep
Trial 2: win, row1, row2, O(n^2) bash
new data structure:
BitSet, holds many longs in array,
allows O(N/64) bit count (cardinality), and, or, etc
 */
public class BeautifulSubgrids {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;

    //param
    static int N;
    static BitSet[] nums;

    public static void main(String[] args) throws IOException {
        //parse input
        N = Integer.parseInt(br.readLine());
        nums = new BitSet[N];
        for (int r=0;r<N;r++){
            String str = br.readLine();
            nums[r] = new BitSet(N);
            for (int c=0;c<N;c++){
                if (str.charAt(c)=='1') nums[r].set(c);
            }
        }
        if (debug) for (int i=0;i<N;i++) System.out.println(nums[i]);

        //logic
        long ans = 0;
        for (int r1=0;r1<N;r1++){
            for (int r2=r1+1;r2<N;r2++){
                BitSet tmp = (BitSet) nums[r1].clone();
                tmp.and(nums[r2]);
                ans += f(tmp.cardinality());
            }
        }
        //turn in answer
        out.println(ans);
        out.close();
    }
    public static int f(int a){
        return (a*(a-1))/2;
    }
}
