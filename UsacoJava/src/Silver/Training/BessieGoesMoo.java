package Silver.Training;

import java.util.*;
import java.io.*;
public class BessieGoesMoo {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static long ans = 0;
    static HashMap<Character, int[]> letterMapping = new HashMap<>();

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("bgm.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("bgm.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        letterMapping.put('B', new int[7]);
        letterMapping.put('E', new int[7]);
        letterMapping.put('S', new int[7]);
        letterMapping.put('I', new int[7]);
        letterMapping.put('G', new int[7]);
        letterMapping.put('O', new int[7]);
        letterMapping.put('M', new int[7]);
        //parse input
        N = Integer.parseInt(br.readLine());
        for (int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char key = st.nextToken().charAt(0);
            int num = Integer.parseInt(st.nextToken()) % 7;
            if (num < 0) num+=7;
            letterMapping.get(key)[num]++;
        }
//        for (char key : letterMapping.keySet()) {
//            out.println(key + ": " + Arrays.toString(letterMapping.get(key)));
//        }
//        out.println();
        //logic
        GenerateArrays(0, new int[7]);
        //ProcessArray(new int[]{2, 5, 0, 2, 1, 2, 5});
        //turn in answer
        out.println(ans);
        out.close();
    }
    public static void ProcessArray(int[] perm) {
        //out.println(Arrays.toString(perm));
        int B = perm[0];
        int E = perm[1];
        int S = perm[2];
        int I = perm[3];
        int G = perm[4];
        int O = perm[5];
        int M = perm[6];
        if ((B+E+S+S+I+E)%7==0||(G+O+E+S)%7==0||(M+O+O)%7==0) {
            long add = 1;
            add *= letterMapping.get('B')[B];
            add *= letterMapping.get('E')[E];
            add *= letterMapping.get('S')[S];
            add *= letterMapping.get('I')[I];
            add *= letterMapping.get('G')[G];
            add *= letterMapping.get('O')[O];
            add *= letterMapping.get('M')[M];
            ans += add;
        }
        return;
    }
    public static void GenerateArrays(int round, int[] perm) {
        if (round == 7) {
            ProcessArray(perm);
            return;
        }
        for (int i=0;i<=6;i++) {
            perm[round]=i;
            GenerateArrays(round+1, perm);
        }
    }
}

