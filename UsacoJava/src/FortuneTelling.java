import java.io.*;
import java.util.StringTokenizer;

public class FortuneTelling {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++){
            solve();
        }
        out.close();
    }
    public static void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long X = Long.parseLong(st.nextToken());
        long Y = Long.parseLong(st.nextToken());
        //keep track of parities
        boolean a = X%2==0;
        boolean b = !a;
        boolean e = Y%2==0;
        //togglers
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++){
            int toggler = Integer.parseInt(st.nextToken());
            boolean t = toggler%2==0;
            if (a==t) a=true;
            else a=false;
            if (b==t) b=true;
            else b=false;
        }
        if (a==e) out.println("Alice");
        else out.println("Bob");
    }
}
