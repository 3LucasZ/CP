package USACO.Silver.Contest.Practice.Usaco2020_2021_Silver.Silver2021_3;

import java.io.*;
import java.util.*;
/*
USACO 2021 February Contest, Silver
Problem 2. Year of the Cow
 */
public class YearOfTheCow {
    //io
    static BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int N;
    static int K;
    static TreeSet<Integer> heads = new TreeSet<>();
    static Integer[] diff;
    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        heads.add(0);
        for (int i=0;i<N;i++) {
            int ancestor = Integer.parseInt(f.readLine());
            heads.add(12 + ancestor - ancestor % 12);
        }
        //logik
        Integer[] headsArr = new Integer[heads.size()];
        diff = new Integer[heads.size()-1];
        headsArr = heads.toArray(headsArr);
        for (int i=1;i< heads.size();i++) {
            diff[i-1] = headsArr[i] - headsArr[i-1] - 12;
        }
        int ans = heads.last();
        Arrays.sort(diff, Comparator.reverseOrder());
        for (int i=0;i<K-1;i++) {
            ans -= diff[i];
        }
        //turn in answer
        //out.println(heads);
        //out.println(Arrays.toString(diff));
        out.println(ans);
        out.close();
        f.close();
    }
}
