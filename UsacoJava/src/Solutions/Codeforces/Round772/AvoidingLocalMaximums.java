package Solutions.Codeforces.Round772;

import java.io.*;
import java.util.*;

public class AvoidingLocalMaximums {
    //io
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> locMax = new ArrayList<>();
        for (int i=1;i<arr.length-1;i++){
            if (arr[i]>arr[i-1]&&arr[i]>arr[i+1]) locMax.add(i);
        }
        if (debug) System.out.println(locMax);

        int ops = 0;
        for (int i=0;i<locMax.size();i++){
            if (i<locMax.size()-1&&locMax.get(i+1)-locMax.get(i)==2){
                arr[locMax.get(i)+1]=Math.max(arr[locMax.get(i)],arr[locMax.get(i+1)]);
                i++;
                ops++;
            }
            else {
                arr[locMax.get(i)]=Math.max(arr[locMax.get(i)-1],arr[locMax.get(i)+1]);
                ops++;
            }
        }

        out.println(ops);
        for (int i=0;i<arr.length;i++) out.print(arr[i]+" ");
        out.println();
    }
}
