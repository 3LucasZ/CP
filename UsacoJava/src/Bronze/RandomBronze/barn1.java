package Bronze.RandomBronze;/*
ID: Lucas Zheng [lucas.z4]
LANG: JAVA
TASK: Ad_hoc.barn1
*/

import java.io.*;
import java.util.*;
public class barn1 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("Ad_hoc.barn1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Ad_hoc.barn1.out")));
        StringTokenizer s = new StringTokenizer(f.readLine());
        int maxBoards = Integer.parseInt(s.nextToken());
        int totStalls = Integer.parseInt(s.nextToken());
        int totCows = Integer.parseInt(s.nextToken());
        ArrayList<Integer> occupiedStalls = new ArrayList<>();
        for (int i=0;i<totCows;i++) {
            occupiedStalls.add(Integer.parseInt(f.readLine()));
        }
        Collections.sort(occupiedStalls);
        ArrayList<Integer> intervals = new ArrayList<>();
        for (int i=0;i<totCows-1;i++) {
            intervals.add(occupiedStalls.get(i+1) - occupiedStalls.get(i) - 1);
        }
        int covered;
        if (occupiedStalls.size() <= maxBoards) {
            covered = occupiedStalls.size();
        }
        else {
            covered = occupiedStalls.get(occupiedStalls.size()-1) - occupiedStalls.get(0) + 1;
            for (int i=0;i<maxBoards - 1;i++) {
                covered -= Collections.max(intervals);
                intervals.remove(Collections.max(intervals));
            }
        }
        out.println(covered);
        out.close();
    }
}
