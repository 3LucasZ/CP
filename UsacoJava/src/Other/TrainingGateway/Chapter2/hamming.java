package Other.TrainingGateway.Chapter2;

import java.io.*;
import java.util.*;

/*
LANG: JAVA
TASK: hamming
 */
public class hamming {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int N;
    static int B;
    static int D;
    static ArrayList<Integer> words = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("hamming.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }

        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        //logic
        for (int i=0;i<Math.pow(2,B);i++){
            if (tryNum(i)) words.add(i);
            if (words.size() == N) break;
        }
        if (!submission){
            System.out.println(words);
        }

        //turn in answer
        for (int i=0;i<words.size();i++){
            out.print(words.get(i));
            if (i!=words.size()-1&&i%10!=9) out.print(" ");
            if (i%10==9) out.println();
        }
        if (words.size()%10!=0) out.println();
        out.close();
    }
    public static boolean tryNum(int num){
        for (int i=0;i<words.size();i++){
            int distance = Integer.bitCount(words.get(i) ^ num);
            if (distance < D) return false;
        }
        return true;
    }
}
