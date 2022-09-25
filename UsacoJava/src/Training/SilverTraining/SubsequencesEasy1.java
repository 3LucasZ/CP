package Training.SilverTraining;

import java.io.*;
import java.util.*;

/*
Codeforces Round #570 (Div. 3)
E. Subsequences (easy version)
USACO Silver Training
Thoughts:
Solved during downtime (ez)
"Set floodfill/dp" technique
add initial string to queue
keep popping queue and adding new strings based off of that (with string hashing)
once 100 new strings found, print ops used :)

REMARK:
logic and everything was spot on, but string hashing and hash set not needed.
We can check with O(K) time if a string has been visited yet ._., takes O(N) for hashing per string btw so this is
simpler implementation and same runtime... welp you win some you lose some :)
 */
public class SubsequencesEasy1 {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);

    //param
    static int N;
    static int K;
    static String in;

    //string hash
    static HashSet<StringWrapper> seen = new HashSet<>();

    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        in = br.readLine();

        //logic
        Queue<StringBuilder> subsequences = new LinkedList<>();
        StringBuilder first = new StringBuilder(in);
        subsequences.add(first);
        int hash = getHash(first.toString());
        seen.add(new StringWrapper(first.toString()));

        int ops = 0;
        while (!subsequences.isEmpty() && seen.size()!=K) {
            StringBuilder next = subsequences.poll();
            //System.out.println(next);
            for (int i=0;i<next.length();i++){
                StringBuilder child = new StringBuilder(next);
                child.deleteCharAt(i);

                StringWrapper add = new StringWrapper(child.toString());
                if (seen.contains(add)) continue;
                seen.add(add);
                ops += (N-child.length());
                subsequences.add(child);
                if (seen.size()==K) break;
            }
        }

        //turn in answer
        if (seen.size()==K) out.println(ops);
        else out.println(-1);
        out.close();
    }

    public static int getHash(String str){
        if (str.equals("")) return 0;
        long A = (long) (1e9+7);
        long B = (long) (1e9+9);
        long[] pow = new long[str.length()];
        pow[0]=1;
        for (int i=1;i<str.length();i++){
            pow[i]=(pow[i-1]*A)%B;
        }
        long hash = 0;
        for (int i=0;i<str.length();i++){
            hash = (hash + pow[str.length()-i-1]*str.charAt(i))%B;
        }
        return (int) hash;
    }

    private static class StringWrapper {
        String val;
        public StringWrapper(String v){
            val=v;
        }
        @Override
        public boolean equals(Object o){
            return ((StringWrapper) o).val.equals(val);
        }
        @Override
        public int hashCode(){
            return getHash(val);
        }
    }
}
