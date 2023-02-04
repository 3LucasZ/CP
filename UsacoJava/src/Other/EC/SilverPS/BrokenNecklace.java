package Other.EC.SilverPS;

import java.io.*;

public class BrokenNecklace {
    //io
    static boolean submission = false;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static char[] necklace;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("beads.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        necklace = new char[2*N];
        String str = br.readLine();
        for (int i=0;i<N;i++) {
            necklace[i] = str.charAt(i);
            necklace[N+i] = str.charAt(i);
        }
        //out.println(necklace);
        //logic
        int ans = 0;
        //let i be the cutting point
        for (int i=0;i<2*N;i++) {
            int lsize = 0;
            int rsize = 0;
            char lcolor = '?';
            char rcolor = '?';
            for (int l=0;l<2*N;l++) {
                if (i-l < 0) break;
                int search = (i-l);
                if (lcolor == '?') {
                    lsize++;
                    if (necklace[search] == 'w');
                    else lcolor = necklace[search];
                }
                else {
                    if (necklace[search] == 'w' || necklace[search] == lcolor) {
                        lsize++;
                    }
                    else {
                        break;
                    }
                }
            }

            for (int r=0;r<2*N;r++) {
                if (i+1+r >= 2*N) break;
                int search = (i+1+r);
                if (rcolor == '?') {
                    rsize++;
                    if (necklace[search] == 'w');
                    else rcolor = necklace[search];
                }
                else {
                    if (necklace[search] == 'w' || necklace[search] == rcolor) {
                        rsize++;
                    }
                    else {
                        break;
                    }
                }
            }
            ans = Math.max(ans, lsize+rsize);
        }
        //turn in answer
        out.println(Math.min(N, ans));
        out.close();
    }
}
