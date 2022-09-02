package USACO.Bronze.RandomBronze;/*
ID: Lucas Zheng [lucas.z4]
LANG: JAVA
TASK: blocks
*/
import java.io.*;
import java.util.*;

public class blocks {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("blocks.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("blocks.out")));
        int N = Integer.parseInt(f.readLine());
        ArrayList<board> boards = new ArrayList<>();
        for(int i=0;i<N;i++){
            StringTokenizer line = new StringTokenizer(f.readLine());
            boards.add(new board(line.nextToken(),line.nextToken()));
        }
        for (int i=0;i<26;i++){
            int counter = 0;
            for (board b : boards) {
                counter += b.letterCount((char)('a'+i));
            }
            out.println(counter);
        }
        out.close();
    }
}
class board {
    String wordOne;
    String wordTwo;

    board(String o,String t){
        wordOne = o;
        wordTwo = t;
    }
    public Integer letterCount(char c){
        //return Math.max(wordOne.length(),4);
        //return Math.max(wordOne.length() - wordOne.replaceAll(String.valueOf(c), "").length(), 1);
        return Math.max(wordOne.length() - wordOne.replaceAll(String.valueOf(c),"").length(),wordTwo.length() - wordTwo.replaceAll(String.valueOf(c),"").length());
    }
}
