package USACO.Bronze.RandomBronze;/*
ID: Lucas Zheng [lucas.z4]
LANG: JAVA
TASK: Greedy_Algorithm.crypt1
*/

import java.io.*;
import java.util.*;

public class crypt1 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("Greedy_Algorithm.crypt1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Greedy_Algorithm.crypt1.out")));
        int n = Integer.parseInt(f.readLine());
        StringTokenizer s = new StringTokenizer(f.readLine());
        ArrayList<String> digits = new ArrayList<>();
        ArrayList<String> nondigits = new ArrayList<>();
        for (int i=0;i<10;i++) {
            nondigits.add(String.valueOf(i));
        }
        for (int i=0;i<n;i++) {
            String b = s.nextToken();
            digits.add(b);
            nondigits.remove(b);
        }
        //out.println(nondigits);
        int counter = 0;
        for (String a : digits) {
            if (a=="0") {
                continue;
            }
            for (String b : digits) {
                for (String c : digits) {
                    for (String d : digits) {
                        if (d=="0") {
                            continue;
                        }
                        continueloop: for (String e : digits) {
                            int p1 = Integer.parseInt(a + b + c) * Integer.parseInt(d);
                            int p2 = Integer.parseInt(a + b + c) * Integer.parseInt(e);
                            int p3 = Integer.parseInt(a + b + c) * Integer.parseInt(d + e);
                            if (String.valueOf(p1).length() != 3 || String.valueOf(p2).length() != 3 || String.valueOf(p3).length() != 4) {
                                continue;
                            }
                            ArrayList<Integer> parts = new ArrayList<>();
                            parts.add(p1);
                            parts.add(p2);
                            parts.add(p3);
                            for (int part : parts) {
                                for (String non : nondigits) {
                                    if (String.valueOf(part).contains(non)) {
                                        continue continueloop;
                                    }
                                }
                            }

                            //out.println(p1 + " " + p2 + " " + p3);
                            counter ++;
                        }
                    }
                }
            }
        }
        out.println(counter);
        out.close();
    }
}
