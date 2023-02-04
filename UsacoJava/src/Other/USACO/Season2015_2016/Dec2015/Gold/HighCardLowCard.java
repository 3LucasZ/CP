package Other.USACO.Season2015_2016.Dec2015.Gold;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/*
USACO 2015 December Contest, Gold
Problem 1. High Card Low Card (Gold)
USACO Guide Greedy+Sorting Easy
Had to copy solution cuz hard :(
 */
public class HighCardLowCard {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //
    static boolean forElsie[];
    static ArrayList<Integer> bessie = new ArrayList<>();
    static ArrayList<Integer> elsieSet1 = new ArrayList<>();
    static ArrayList<Integer> elsieSet2 = new ArrayList<>();
    //param
    static int n;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            f = new BufferedReader(new FileReader("cardgame.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("cardgame.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        n = Integer.parseInt(f.readLine());
        forElsie = new boolean[2*n];
        for (int i=0;i<n/2;i++) {
            int num = Integer.parseInt(f.readLine());
            elsieSet1.add(num);
            forElsie[num-1] = true;
        }
        for (int i=0;i<n/2;i++) {
            int num = Integer.parseInt(f.readLine());
            elsieSet2.add(num);
            forElsie[num-1] = true;
        }
        for (int i=0;i<2*n;i++) {
            if (!forElsie[i]) {
                bessie.add(i+1);
            }
        }
        Collections.sort(elsieSet1);
        Collections.sort(elsieSet2);
        //System.out.println(bessie);
        //System.out.println(elsieSet1);
        //System.out.println(elsieSet2);
        //logic
        int count = 0;
        int ix = bessie.size()-1;
        for (int i=elsieSet1.size()-1;i>=0;i--) {
            if (elsieSet1.get(i) < bessie.get(ix)) {
                count++;
                ix--;
            }
        }
        ix = 0;
        for (int i=0;i<elsieSet2.size();i++) {
           if (bessie.get(ix) < elsieSet2.get(i))  {
               ix++;
               count++;
           }
        }
        //turn in answer
        out.println(count);
        out.close();
        f.close();
    }
}
