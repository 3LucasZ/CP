package USACO.Bronze.RandomBronze;/*
ID: Lucas Zheng [lucas.z4]
LANG: JAVA
TASK:
*/
import java.io.*;
import java.util.*;
import java.awt.Rectangle;

public class billboard {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("billboard.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("billboard.out")));
        int x1, y1, x2, y2;
        StringTokenizer coordinate = new StringTokenizer(f.readLine());
        x1 = Integer.parseInt(coordinate.nextToken()); y1 = Integer.parseInt(coordinate.nextToken()); x2 = Integer.parseInt(coordinate.nextToken()); y2 = Integer.parseInt(coordinate.nextToken());
        Rectangle mowerBoard = new Rectangle(x1, -y2, x2-x1, y2-y1);
        int x11, y11, x12, y12;
        coordinate = new StringTokenizer(f.readLine());
        x11 = Integer.parseInt(coordinate.nextToken()); y11 = Integer.parseInt(coordinate.nextToken()); x12 = Integer.parseInt(coordinate.nextToken()); y12 = Integer.parseInt(coordinate.nextToken());
        Rectangle feedBoard = new Rectangle(x11, -y12, x12-x11, y12-y11);
        int corners = 0;
        if (feedBoard.contains(x1,-y1)) {
            corners ++;
        }
        if (feedBoard.contains(x1,-y2)) {
            corners ++;
        }
        if (feedBoard.contains(x2,-y1)) {
            corners ++;
        }
        if (feedBoard.contains(x2,-y2)) {
            corners ++;
        }
        if (corners >= 2) {
            out.println((int)((mowerBoard.getWidth() * mowerBoard.getHeight()) - mowerBoard.intersection(feedBoard).getWidth() * (mowerBoard.intersection(feedBoard).getHeight())));
        }
        else {
            out.println((int) (mowerBoard.getWidth() * mowerBoard.getHeight()));
        }
        out.close();

    }
}
