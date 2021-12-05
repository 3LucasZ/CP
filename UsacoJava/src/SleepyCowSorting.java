import java.io.*;
import java.util.StringTokenizer;

public class SleepyCowSorting {
    //param
    static int n;
    public static void main(String[] args) throws IOException {
        //default
        PrintWriter out;
        BufferedReader f;
        //setup
        boolean submission = true;
        if (submission) {
            f = new BufferedReader(new FileReader("sleepy.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("sleepy.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        n = Integer.parseInt(f.readLine());
        int cows[] = new int[n];
        StringTokenizer st = new StringTokenizer(f.readLine());
        for (int i=0;i<n;i++) {
            cows[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(consec(cows));

        //turn in answer
        out.println(n - consec(cows));
        out.close();
        f.close();
    }
    public static int consec(int[] arr) {
        int last = 101;
        for (int i=arr.length-1;i>=0;i--) {
            if (arr[i] > last) {
                return arr.length - i - 1;
            }
            last = arr[i];
        }
        return arr.length;
    }
}
