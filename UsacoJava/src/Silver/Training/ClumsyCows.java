package Silver.Training;

import java.io.*;

/*
USACO 2012 November Contest, Silver
Problem 1. Clumsy Cows
USACO Silver Training
Type: ??? adhoc or greedy
Nice problem!
 */

public class ClumsyCows {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static String str;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("clumsy.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("clumsy.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        str = br.readLine();
        //logic
        int lcnt = 0;
        int changes = 0;
        for (int i=0;i<str.length();i++) {
            if (str.charAt(i)=='(') {
                lcnt++;
            }
            else {
                //have we run out of ( ?
                if (lcnt==0) {
                    changes++;
                    lcnt++;
                } else {
                    lcnt--;
                }
            }
        }
        changes += lcnt/2;
        //turn in answer
        out.println(changes);
        out.close();
    }
}
