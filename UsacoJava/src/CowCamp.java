import java.io.*;
import java.util.*;
/*
PROB: CowCamp
LANG: JAVA
*/
public class CowCamp {
    static boolean submission = false;
    static boolean debug = false;

    static int T;
    static int K;

    public static void main(String[] args) throws IOException {
        //parse
        setup("CowCamp");
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken())-1;
        K = Integer.parseInt(st.nextToken());

        //precomp: prob[t][x] => probability of x correct guesses out of t => t choose k / 2^t
        double[][] prob = new double[T+1][T+1];
        prob[0][0]=1.0;
        for (int t=1;t<=T;t++){
            prob[t][0]=prob[t-1][0]/2.0;
            for (int x=1;x<=T;x++){
                prob[t][x]=(prob[t-1][x-1]+prob[t-1][x])/2.0;
            }
        }
        if (debug) System.out.println("probT: "+Arrays.toString(prob[T]));

        //precomp xpx = sum(0...x*prob[t][0...x])
        double[] xpx = new double[T+1];
        xpx[0]=0; for (int i=1;i<=T;i++) xpx[i]=xpx[i-1]+i*prob[T][i];
        if (debug) System.out.println("xpx: "+Arrays.toString(xpx));

        //precomp px = sum(prob[t][0...x])
        double[] px = new double[T+1];
        px[0]=prob[T][0]; for (int i=1;i<=T;i++) px[i]=px[i-1]+prob[T][i];
        if (debug) System.out.println("px: "+Arrays.toString(px));

        //Task3
        //propagation until i==K
        double prevE = 0;
        int i = 0;
        while (true){
            //find x,c,a,b
            double x = prevE;
            int c = (int) Math.floor(x);
            double a = xpx[T]-xpx[c];
            double b = px[c];
            //bin search q
            int lo=1;int hi = K;
            while (lo<hi){
                int mid = (lo+hi)/2;
                if (f(x,a,b,mid)<c+1) lo=mid+1;
                else hi=mid;
            }
            //overflow break
            if (i+lo>K){
                prevE=f(x,a,b,K-i);
                break;
            }
            //update aka propagate
            i+=lo;
            prevE=f(x,a,b,lo);
            if (debug)System.out.println("i: "+i+", E[i]: "+prevE);
        }

        //ret
        out.println(1+prevE);
        out.close();
    }
    public static double f(double x, double a, double b, int q){
        return a*(1-pow(b,q))/(1-b)+pow(b,q)*x;
    }
    public static double pow(double x, int p){
        if(p==0) return 1;
        if(p%2==1)return x*pow(x,p-1);
        else return pow(x*x,p/2);
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

/*
//Task1
double[] E = new double[K+1];
E[0]=0;
for (int i=1;i<=K;i++){
    for (int x=0;x<=T;x++){
        if (x>=E[i-1]) E[i]+=x*prob[T][x];
        else E[i]+=E[i-1]*prob[T][x];
    }
}
if (debug) System.out.println(Arrays.toString(E));
out.println(E[K]+1);
 */

/*
//Task2
double[] E = new double[K+1];
E[0]=0;
for (int i=1;i<=K;i++){
    int c = (int) Math.floor(E[i-1]);
    E[i]=xpx[T]-xpx[c]+E[i-1]*px[c];
}
if (debug) System.out.println(Arrays.toString(E));
out.println(E[K]+1);
 */