package Solutions.CodeJam.Round1B_2022;

import java.io.*;

public class ASeDatAbSubtask {
    static boolean submission = false;
    static boolean debug = true;

    public static void main(String[] args) throws IOException {
        //* handle TCS
        setup();
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) solve(i);
        out.close();
    }

    public static void solve(int tcs) throws IOException {
        //* parse
        int x = 4;
        while (true) {
            System.out.println(gen(x));
            x = Integer.parseInt(br.readLine());
            if (x==0) break;
        }
    }

    public static String gen(int bits){
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<bits;i++) sb.append(1);
        for (int i=bits;i<8;i++) sb.append(0);
        return sb.toString();
    }


    static BufferedReader br;
    static PrintWriter out;

    public static void setup() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
}