package Solutions.UnsortedTraining.SilverTraining;

import java.io.*;
import java.util.*;
/*
Codeforces Round #155 Div. 2
C. Anagram
USACO Silver Training
CF Random 1800 Greedy
Thoughts:
lots of debugging
unelegant code
:| think before typing !
logic: from left to right
if you have to replace a letter, replace it with the least value
if you can replace a letter so that lexico improves, replace with least value
keep track of letters left, letters needed and unneeded
-65 annoying :|
nice problem ig
 */
public class Anagram {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static StringBuilder orig;
    static StringBuilder anagram;
    static int[] need;
    static int[] left;
    static int N;

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("input.txt"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("output.txt")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }

        //parse input
        orig=new StringBuilder(br.readLine());
        anagram=new StringBuilder(br.readLine());
        N = anagram.length();
        need = new int[26];
        left = new int[26];
        for (int i=0;i<N;i++) {
            need[anagram.charAt(i)-65]++;
            need[orig.charAt(i) - 65]--;
            left[orig.charAt(i)-65]++;
        }
        if (!submission) {
            System.out.println(orig);
            System.out.println(anagram);
            System.out.println(Arrays.toString(need));
            System.out.println(Arrays.toString(left));
        }

        //logic
        Deque<Integer> add = new LinkedList<>();
        for (int i=0;i<26;i++){
            for (int j = 0; j<need[i]; j++){
                add.addLast(i);
            }
        }
        if (!submission) System.out.println(add);
        out.println(add.size());

        for (int i=0;i<N;i++){
            left[orig.charAt(i)-65]--;
            if (need[orig.charAt(i)-65]<0 && (left[orig.charAt(i)-65]+1 == -need[orig.charAt(i)-65] || orig.charAt(i)-65>add.getFirst())){
                need[orig.charAt(i)-65]++;
                orig.setCharAt(i, (char) ('A'+add.pollFirst()));
            }
            if (!submission) {
                System.out.println(orig);
                System.out.println(Arrays.toString(left));
            }
        }
//        if (!submission) {
//            System.out.println(add);
//            System.out.println(orig);
//        }
//        for (int i=N-1;i>=0;i--){
//            if (needed[orig.charAt(i)-65]<0){
//                needed[orig.charAt(i)-65]++;
//                orig.setCharAt(i, (char)('A'+add.pollLast()));
//            }
//        }
        out.println(orig);
        out.close();
    }
}
