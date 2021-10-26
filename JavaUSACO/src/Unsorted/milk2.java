package Unsorted;/*
ID: Lucas Zheng [lucas.z4]
LANG: JAVA
TASK: Complete_Search.milk2
*/
import java.io.*;
import java.util.*;

public class milk2 {
    public static void main(String[] args) throws IOException {
        //storing information and input/output
        BufferedReader f = new BufferedReader(new FileReader("Complete_Search.milk2.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Complete_Search.milk2.out")));
        int numFarmers = Integer.parseInt(f.readLine());
        ArrayList<Tuple> times = new ArrayList<Tuple>();
        for (int i=0;i<numFarmers;i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            Tuple tuple = new Tuple(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            times.add(tuple);
        }
        Collections.sort(times, new Sortbystart());

        int arrIndex = 0;
        for (int i=1;i<numFarmers;i++) {
            if (times.get(arrIndex).inTupleRange(times.get(arrIndex+1).x)) {
                if (times.get(arrIndex+1).y > times.get(arrIndex).y) {
                    times.get(arrIndex).y = times.get(arrIndex+1).y;
                }
                times.remove(times.get(arrIndex+1));
            }
            else {
                arrIndex ++;
            }
        }
        int max1 = 0;
        for (Tuple time : times) {
            if (time.rangeVal() > max1) {
                max1 = time.rangeVal();
            }
        }
        int max2 = 0;
        for (Tuple time : times) {
            if (times.indexOf(time)+1 < times.size()) {
                Tuple nextTime = times.get(times.indexOf(time) + 1);
                if (nextTime.x - time.y > max2) {
                    max2 = nextTime.x - time.y;
                }
            }
        }
        out.println(max1 + " " + max2);
        out.close();
    }
}
class Tuple {
    public int x, y;
    public Tuple(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public boolean inTupleRange(int num) {
        if (num >= x && num <= y) {
            return true;
        }
        else {
            return false;
        }

    }
    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
        public int rangeVal() {
        return this.y - this.x;
    }
}
class Sortbystart implements Comparator<Tuple>
{
    public int compare(Tuple a, Tuple b)
    {
        return a.x - b.x;
    }
}
