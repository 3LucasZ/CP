package Other.USACO.Season2020_2021.Dec2020.Bronze;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class StuckInARut {
    //default
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int N;
    static ArrayList<Cow> original = new ArrayList<>();
    static ArrayList<Cow> eastCows = new ArrayList<Cow>();
    static ArrayList<Cow> northCows = new ArrayList<Cow>();
    public static void main(String[] args) throws IOException {
        f = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        //parse input
        N = Integer.parseInt(f.readLine());
        for (int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            String type = st.nextToken();
            Cow toAdd =  new Cow(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            if (type.equals("E")) {
                eastCows.add(toAdd);
            }
            if (type.equals("N")) {
                northCows.add(toAdd);
            }
            original.add(toAdd);
        }
        Collections.sort(eastCows, (a, b) -> a.y - b.y);
        Collections.sort(northCows, (a, b) -> a.x - b.x);

        //System.out.println(eastCows);
        //System.out.println(northCows);
        //logic
        for (Cow ECow : eastCows) {
            for (Cow NCow : northCows){
                if (NCow.finished) continue;
                int intersectionXLength = NCow.x - ECow.x;
                int intersectionYLength = ECow.y - NCow.y;
                if (intersectionXLength >= 0 && intersectionYLength >= 0) {
                    if (intersectionXLength > intersectionYLength) {
                        ECow.eaten += intersectionXLength;
                        ECow.finished = true;
                        break;
                    }
                    else if (intersectionXLength == intersectionYLength) {
                        //ECow.eaten -= 0.5;
                        //NCow.eaten -= 0.5;
                    }
                    else {
                        NCow.eaten += intersectionYLength;
                        NCow.finished = true;
                    }
                }
            }
        }
        //turn in answer
        for (int i=0;i<N;i++) {
            if (original.get(i).eaten == 0) {
                out.println("Infinity");
            }
            else {
                out.println(original.get(i).eaten);
            }
        }
        out.close();
        f.close();
    }
}
class Cow {
    int x;
    int y;
    int eaten = 0;
    boolean finished = false;
    public Cow(int x1, int y1) {
        x = x1;
        y = y1;
    }
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}