import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SubarraySumsI {
    //io
    static BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int n;
    static int x;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(f.readLine());
        for (int i=0;i<n;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int p1 = 0, p2 = 0;
        int runsum = arr[0];
        int ans = 0;
        while (p1 < n && p2 < n) {
            if (runsum > x) {
                if (p1 == p2) {
                    p1++;
                    p2++;
                    runsum = arr[p1];
                }
                else {
                    runsum -= arr[p1];
                    p1++;
                }
            }
            else if (runsum < x) {
                p2++;
                runsum+=arr[p2];
            }
            else {
                ans++;
                p1++;
                p2++;
            };
        }
        //out.println("p2: " + p2 + ", p1: " + p1);
        out.println(ans);
        out.close();
        f.close();
    }
}
