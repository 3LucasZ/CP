package Solutions.Codeforces.PickNPlay;

import java.io.*;
import java.util.*;

/*
Codeforces Round #799 Div. 4
Random Problem Playing
H. Gambling
 */
public class Gambling {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) solve();
        out.close();
    }

    public static void solve() throws IOException {
        int N = Integer.parseInt(br.readLine());
        HashMap<Integer, ArrayList<Integer>> indexList = new HashMap<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++){
            int num = Integer.parseInt(st.nextToken());
            if (!indexList.containsKey(num)) indexList.put(num,new ArrayList<>());
            indexList.get(num).add(i);
        }
        if (debug){
            System.out.println(indexList);
        }

        int ans = 0;
        String ret = null;

        for (int num : indexList.keySet()){
            ArrayList<Integer> list = indexList.get(num);
            int l = 0;
            int run = 1;

            if (ret==null) ret=num+" "+(list.get(0)+1)+" "+(list.get(0)+1);

            for (int i=1;i<list.size();i++){
                int newRun = run+2-(list.get(i)-list.get(i-1));
                if (newRun < 1) {
                    l=i;
                    run = 1;
                } else {
                    run = newRun;
                }
                if (run > ans){
                    ans = run;
                    ret = num+" "+(list.get(l)+1)+" "+(list.get(i)+1);
                }
                if (debug){
                    System.out.println("("+num+" "+(l+1)+" "+(i+1)+"): "+run);
                }
            }
        }

        out.println(ret);
    }
}
