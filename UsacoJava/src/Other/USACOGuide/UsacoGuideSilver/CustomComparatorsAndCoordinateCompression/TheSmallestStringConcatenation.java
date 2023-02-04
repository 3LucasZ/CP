package Other.USACOGuide.UsacoGuideSilver.CustomComparatorsAndCoordinateCompression;

import java.io.*;
import java.util.*;

/*
Educational Codeforces  Round 9 C.
The Smallest String Concatenation
Custom Comparators + Coordinate Compression - Normal
Concepts: Transitivity, swap, string -> base 26 numbers for proofs
Copy Cat Sad :(
 */
public class TheSmallestStringConcatenation {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static int N;
    static String[] strings;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        strings = new String[N];
        for (int i=0;i<N;i++) {
            strings[i] = br.readLine();
        }
        Arrays.sort(strings, (a,b)-> {
            return (a + b).compareTo(b + a);
        });
        //out.println(Arrays.toString(strings));
        for (String phrase : strings){
            out.print(phrase);
        }
        out.close();
    }
}
