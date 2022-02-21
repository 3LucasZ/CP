package Gold.EC.MIDTERM;

import java.io.*;
import java.util.*;

public class Hangman {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;
    //param
    static int L;
    static int N;
    //arr
    static String[] know;
    static String hangman;
    //dp
    //length of word
    static String bestString[];
    //last word length
    static String dp[];
    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        know = new String[N];
        hangman=br.readLine();
        for (int i=0;i<N;i++) {
            know[i]=br.readLine();
        }
        //sort know for O(1) insertions
        Arrays.sort(know);
        if (debug) System.out.println(Arrays.toString(know));
        //logic: dp
        //setup
        bestString = new String[L+1];
        bestString[0] = "";

        //work
        for (int r=1;r<=L;r++){
            //keep track of best word match in l..r
            dp = new String[21];
            for (String word : know){
                //word already in here or word is too long
                if (dp[word.length()]!=null || word.length()>r) continue;
                if (matchHangman(r-word.length()+1,r,word)) dp[word.length()] = word;
            }

            //which is best concat? track this
            for (int len=1;len<=20;len++){
                //invalid concats
                if (len > r) continue;
                if (bestString[r-len]==null||dp[len]==null) continue;

                StringBuilder sb = new StringBuilder(bestString[r-len]);
                sb.append(dp[len]);
                if (bestString[r]==null || sb.toString().compareTo(bestString[r])<0){
                    bestString[r]=sb.toString();
                }
            }
            if (debug) System.out.println(Arrays.toString(dp));
        }
        //turn in answer
        if (debug) System.out.println(Arrays.toString(bestString));
        out.println(bestString[L]);
        out.close();
    }
    public static boolean matchHangman(int l, int r, String word){
        for (int i=0;i<=r-l;i++){
            if (hangman.charAt(l+i-1)!='?'&&hangman.charAt(l+i-1)!=word.charAt(i)) return false;
        }
        return true;
    }
}
