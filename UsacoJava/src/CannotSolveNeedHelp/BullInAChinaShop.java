package CannotSolveNeedHelp;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BullInAChinaShop {
    //param
    static int n;
    static int k;
    static Piece orig;
    static Piece[] pieces;
    public static void main(String[] args) throws IOException {
        //default
        PrintWriter out;
        BufferedReader f;
        //setup
        boolean submission = false;
        if (submission) {
            f = new BufferedReader(new FileReader(".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(".out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        pieces = new Piece[k];
        ArrayList<ArrayList<Character>> tempPiece;
        tempPiece = new ArrayList<>();
        for (int r=0;r<n;r++) {
            ArrayList<Character> line = new ArrayList<>();
            String str = f.readLine();
            for (int c=0;c<n;c++) {
                line.add(str.charAt(c));
            }
            tempPiece.add(line);
        }
        orig = new Piece(tempPiece);
        orig.normalize();
        for (int i=0;i<k;i++) {
            tempPiece = new ArrayList<>();
            for (int r=0;r<n;r++) {
                ArrayList<Character> line = new ArrayList<>();
                String str = f.readLine();
                for (int c=0;c<n;c++) {
                    line.add(str.charAt(c));
                }
                tempPiece.add(line);
            }
            Piece tmp = new Piece(tempPiece);
            tmp.normalize();
            pieces[i] = tmp;
        }
        System.out.println(orig);
        for (int i=0;i<k;i++) {
            System.out.println(pieces[i]);
        }
        //merge every piece
        System.out.println(new Piece(orig.pattern.size(), orig.pattern.get(0).size()));
        //turn in answer
        out.println();
        out.close();
        f.close();
    }
}
class Piece {
    public ArrayList<ArrayList<Character>> pattern;
    public Piece(int r, int c) {
        pattern = new ArrayList<ArrayList<Character>>();
        for (int i=0;i<r;i++) {
            ArrayList<Character> temp = new ArrayList<>();
            for (int j=0;j<c;j++) {
                temp.add('.');
            }
            pattern.add(temp);
        }
    }
    public Piece(ArrayList<ArrayList<Character>> signature) {
        pattern = signature;
    }
    public void normalize() {
        ArrayList<ArrayList<Character>> newPattern = new ArrayList<>();
        for (int i=0;i<pattern.size();i++) {
            newPattern.add((ArrayList<Character>) pattern.get(i).clone());
        }
        for (int r=0;r<pattern.size();r++) {
            int dots = 0;
            for (int c=0;c<pattern.get(0).size();c++) {
                if (pattern.get(r).get(c) == '.') dots ++;
            }
            if (dots == pattern.size())  newPattern.remove(0);
            else break;
        }

        for (int r=pattern.size()-1;r>=0;r--) {
            int dots = 0;
            for (int c=0;c<pattern.get(0).size();c++) {
                if (pattern.get(r).get(c) == '.') dots ++;
            }
            if (dots == pattern.size())  newPattern.remove(newPattern.size()-1);
            else break;
        }
        for (int c=0;c<pattern.get(0).size();c++) {
            int dots = 0;
            for (int r=0;r<pattern.size();r++) {
                if (pattern.get(r).get(c) == '.') dots ++;
            }
            if (dots == pattern.get(0).size()) {
                for (int r=0;r<newPattern.size();r++) {
                    newPattern.get(r).remove(0);
                }
            }
            else break;
        }
        for (int c=pattern.get(0).size()-1;c>=0;c--) {
            int dots = 0;
            for (int r=0;r<pattern.size();r++) {
                if (pattern.get(r).get(c) == '.') dots ++;
            }
            if (dots == pattern.get(0).size()) {
                for (int r=0;r<newPattern.size();r++) {
                    newPattern.get(r).remove(newPattern.size()-1);
                }
            }
            else break;
        }
        pattern = newPattern;
    }
    public boolean equals(Piece other) {
        if (pattern.size() != other.pattern.size() || pattern.get(0).size() != other.pattern.get(0).size()) return false;
        for (int r=0;r<pattern.size();r++) {
            for (int c=0;c<pattern.get(0).size();c++) {
                if (pattern.get(r).get(c) != other.pattern.get(r).get(c)) return false;
            }
        }
        return true;
    }
    public String toString() {
        String str = "";
        for (int r=0;r<pattern.size();r++) {
            for (int c=0;c<pattern.get(0).size();c++) {
                str += pattern.get(r).get(c);
            }
            str += "\n";
        }
        str += "\n";
        return str;
    }
}
