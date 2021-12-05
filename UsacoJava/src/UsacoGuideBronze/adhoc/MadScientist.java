package UsacoGuideBronze.adhoc;

import java.io.*;

public class MadScientist {
    //default
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int n;
    static String requested;
    static String received;

    public static void main(String[] args) throws IOException {
        //setup
        if (submission) {
            f = new BufferedReader(new FileReader("breedflip.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("breedflip.out")));
            n = Integer.parseInt(f.readLine());
            requested = f.readLine();
            received = f.readLine();
        }
        else {
            n = 7;
            requested = "GHHHGHH";
            received = "HHGGGHH";
        }
        //logic
        int counter = 0;
        for (int i=0;i<n;i++) {
            if (requested.charAt(i) != received.charAt(i)) {
                if (i+1 >= n) {
                    counter += 1;
                }
                else if (requested.charAt(i+1) == received.charAt(i+1)) {
                    counter += 1;
                }
            }
            //System.out.println(i + ": " + counter);
        }
        //print
        if (submission) {
            out.println(counter);
            f.close();
            out.close();
        }
        else {
            System.out.println(counter);
        }
    }
}