import java.io.*;
import java.util.*;

public class EmailFiling {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++){
            solve();
        }
        // solve();
        out.close();
    }

    public static void solve() throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        if (K>=M || K>=N) {
            out.println("YES");
            out.close();
            return;
        }
        int[] toFolder = new int[N+1];
        int[] lastIndex = new int[M+1];

        st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++){
            int num = Integer.parseInt(st.nextToken());
            toFolder[i] = num;
            lastIndex[num] = i;
        }

        //mid logic
        Multiset lastEmails = new Multiset();
        for (int i=1;i<=N;i++) lastEmails.add(toFolder[i]);

        Multiset activeEmails = new Multiset();
        int emailPointer = 1;
        for (int foldersPointer = 1;foldersPointer<=M-K;foldersPointer++){
            for (;emailPointer<=lastIndex[foldersPointer];emailPointer++) {
                if (emailPointer-K>=1) activeEmails.remove(toFolder[emailPointer-K]);
                activeEmails.add(toFolder[emailPointer]);

                while (true) {
                    Integer nextGood = activeEmails.ms.lowerKey(foldersPointer + K);
                    if (nextGood == null) break;
                    activeEmails.remove(nextGood);
                    lastEmails.remove(nextGood);
                }
            }
        }

        //finish
        for (int i=1;i<=M-K;i++) if (lastEmails.ms.containsKey(i)) {
            out.println("NO");
            return;
        }
        out.println("YES");
    }

    private static class Multiset {
        TreeMap<Integer, Integer> ms = new TreeMap<Integer, Integer>();
        public void add(int num){
            if (!ms.containsKey(num)) ms.put(num, 0);
            ms.put(num, ms.get(num)+1);
        }
        public void remove(int num){
            if (!ms.containsKey(num)) return;
            ms.put(num, ms.get(num)-1);
            if (ms.get(num)==0) ms.remove(num);
        }
    }
}
