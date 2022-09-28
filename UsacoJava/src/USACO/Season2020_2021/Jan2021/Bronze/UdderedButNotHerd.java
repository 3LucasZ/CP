package USACO.Season2020_2021.Jan2021.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class UdderedButNotHerd {
    //param
    static int n;
    static char[] cowAlphabet;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        cowAlphabet = new char[26];
        String entry = f.readLine();
        for (int i=0;i<26;i++) {
            cowAlphabet[i] = entry.charAt(i);
        }
        String heard = f.readLine();
        int lastIndex = 0;
        int counter = 1;
        for (int i=0;i<heard.length();i++) {
            int newIndex = indexOf(heard.charAt(i));
            //System.out.println(newIndex);
            if (newIndex <= lastIndex) {
                counter ++;
            }
            lastIndex = newIndex;
        }

        //turn in answer
        out.println(counter);
        out.close();
        f.close();
    }
    public static int indexOf(char c) {
        for (int i=0;i<26;i++) {
            if (c == cowAlphabet[i]) return i;
        }
        return -1;
    }
}
