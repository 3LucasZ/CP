package Solutions.USACO.Season2018_2019.Feb2019.Plat;

import java.io.*;

/*
PROB: CowDating2
LANG: JAVA
*/
public class CowDating2 {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static double[] p;

    public static void main(String[] args) throws IOException {
        //parse
        setup("cowdate");
        N = Integer.parseInt(br.readLine());
        p = new double[N];
        for (int i = 0; i < N; i++) {
            p[i] = Integer.parseInt(br.readLine()) / 1000000.0;
        }
        //2 pointer
        int l=0;
        int r=0;
        double ans = 0;
        double sum = p[0]/(1-p[0]);
        double prod = 1-p[0];
        for (;l<N;l++){
            //find optimal r while updating window
            while (r+1<N && sum<=1){
                r++;
                sum+=p[r]/(1-p[r]);
                prod*=(1-p[r]);
            }
            ans=Math.max(ans,sum*prod);
            //rem l
            sum-=(p[l]/(1-p[l]));
            prod/=(1-p[l]);
        }
        //ret
        out.println((int) (ans * 1000000));
        out.close();
    }




















    static BufferedReader br;
    static PrintWriter out;
    public static void setup(String fileName) throws IOException {
        if (submission) {
            br = new BufferedReader(new FileReader(fileName+".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
    }
}