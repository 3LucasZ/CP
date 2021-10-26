package Unsorted;/*
ID: Lucas Zheng [lucas.z4]
LANG: JAVA
TASK: Ad_hoc.ride
*/
import java.io.*;
import java.util.*;

public class ride {
    public static void main (String [] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("Ad_hoc.ride.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Ad_hoc.ride.out")));
        // Use StringTokenizer vs. readLine/split -- lots faster
        StringTokenizer st = new StringTokenizer(f.readLine());
        // Get line, break into tokens
        ArrayList<Character> alphabet = new ArrayList<Character>(Arrays.asList('A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'));

        String firstWord = st.nextToken();
        int score1 = 1;
        char[] wordArray1=firstWord.toCharArray();
        for(char c:wordArray1) {
            score1 = score1 * (alphabet.indexOf(c)+1);
        }

        StringTokenizer st2 = new StringTokenizer(f.readLine());


        String secondWord = st2.nextToken();
        int score2 = 1;
        char[] wordArray2=secondWord.toCharArray();
        for(char c:wordArray2) {
            score2 = score2 * (alphabet.indexOf(c)+1);
        }

        if (score1%47 == score2%47) {
            out.println("GO");
        }
        else {
            out.println("STAY");
        }


        out.close();
    }
}
