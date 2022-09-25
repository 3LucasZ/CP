package USACOGuide.UsacoGuideBronze.GraphIntro;

import java.io.*;
import java.util.*;

public class FamilyTree {
    //default
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int n;
    static String cow1;
    static String cow2;
    //manip

    static TreeMap<String, String> familyTree = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        //setup

        f = new BufferedReader(new FileReader("family.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("family.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        cow1 = st.nextToken();
        cow2 = st.nextToken();
        for (int i=0;i<n;i++) {
            StringTokenizer entry = new StringTokenizer(f.readLine());
            String parent = entry.nextToken();
            String kid = entry.nextToken();
            familyTree.put(kid, parent);
        }
        //print
        if (submission) {
            out.println(logic());
            f.close();
            out.close();
        }
        else {
            System.out.println(logic());
        }
    }
    public static String logic() {
        //logic
        //get levels of cow1 and cow2
        int cow1Level = 1;
        String curCow = cow1;
        while (familyTree.containsKey(familyTree.get(curCow))) {
            curCow = familyTree.get(curCow);
            cow1Level ++;
        }
        int cow2Level = 1;
        curCow = cow2;
        while (familyTree.containsKey(familyTree.get(curCow))) {
            curCow = familyTree.get(curCow);
            cow2Level ++;
        }
        System.out.println("cow1 Level: " + cow1Level);
        System.out.println("cow2 Level: " + cow2Level);
        //check for siblings
        String higherCow;
        String lowerCow;
        int higherCowLevel;
        int lowerCowLevel;
        if (familyTree.containsKey(familyTree.get(cow1)) && familyTree.containsKey(familyTree.get(cow2)) && familyTree.get(cow1).equals(familyTree.get(cow2))) {
            return "SIBLINGS";
        }
        else if (cow1Level > cow2Level){
            higherCow = cow1;
            lowerCow = cow2;
            higherCowLevel = cow1Level;
            lowerCowLevel = cow2Level;
        }
        else {
            higherCow = cow2;
            lowerCow = cow1;
            higherCowLevel = cow2Level;
            lowerCowLevel = cow1Level;
        }
        System.out.println("higher cow: " + higherCow + " is higher by " + (higherCowLevel - lowerCowLevel));
        String lowerAncestor = lowerCow;
        String higherAncestor = higherCow;
        for (int i=0;i<higherCowLevel-lowerCowLevel;i++){

                higherAncestor = String.valueOf(familyTree.get(higherAncestor));

        }
        System.out.println("At the same level: " + lowerAncestor + ", " + higherAncestor);
        int i=0;
        while (true) {
            if (lowerAncestor.equals(higherAncestor)) {
                if (i == 0) {
                    return lowerCow + " is the " + namify("mother", higherCowLevel - lowerCowLevel - 1) + " of " + higherCow;
                }
                else if (i == 1) {
                    return lowerCow + " is the " + namify("aunt", higherCowLevel - lowerCowLevel - 1) + " of " + higherCow;
                }
                else {
                    return "COUSINS";
                }
            }
            i += 1;
            if (!familyTree.containsKey(higherAncestor) || !familyTree.containsKey(lowerAncestor)) {
                break;
            }
            lowerAncestor = String.valueOf(familyTree.get(lowerAncestor));
            higherAncestor = String.valueOf(familyTree.get(higherAncestor));
        }
        return "NOT RELATED";
    }
    public static String namify(String base, int addons) {
        String newName = base;
        for (int i=0;i<addons;i++) {
            if (i==0 && base.equals("mother")) {
                newName = "grand-" + newName;
            }
            else {
                newName = "great-" + newName;
            }
        }
        return newName;
    }
}
/*
//check for direct lineage
            curCow = cow1;
            String relation = "mother";
            for (int i=0;i<cow2Level-cow1Level;i++) {
                curCow = familyTree.get(curCow);
                if (curCow == cow2)  {
                    return cow1 + " is the " + relation + " of " + cow2;
                }
                if (i==0) {
                    relation = "grand-" + relation;
                }
                else {
                    relation = "great-" + relation;
                }
            }
 */
