package Other.TrainingGateway.Chapter3;

import java.io.*;

/*
PROB: range
LANG: JAVA
 */
public class range {
    static boolean submission = true;
    static boolean debug = true;

    static int N;
    static int[][] grass;
    public static void main(String[] args) throws IOException {
        //2d presum and parsing
        setup("range");
        N = Integer.parseInt(br.readLine());
        grass = new int[N+1][N+1];
        for (int r=1;r<=N;r++){
            String str = br.readLine();
            for (int c=1;c<=N;c++){
                int res = str.charAt(c-1)-'0';
                grass[r][c]=grass[r-1][c]+grass[r][c-1]-grass[r-1][c-1]+(1-res);
            }
        }

        //complete search

        for (int len=1;len<N;len++){
            int tot = 0;
            for (int r=1;r<=N;r++){
                for (int c=1;c<=N;c++){
                    tot+=graze(r,c,len);
                }
            }
            if (tot==0) break;
            out.println((len+1)+" "+tot);
        }
        out.close();
    }
    public static int graze(int r, int c, int len){
        if (r+len>N || c+len>N) return 0;
        if (grass[r+len][c+len]-grass[r+len][c-1]-grass[r-1][c+len]+grass[r-1][c-1]==0) return 1;
        return 0;
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
