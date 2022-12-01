package UnsortedTraining.BronzeTraining;/*
ID: Lucas Zheng [lucas.z4]
LANG: JAVA
TASK: measurement
*/

import java.io.*;
import java.util.*;

public class measurement {
    public static void main(String[] args) throws IOException {
        //IO
        BufferedReader f = new BufferedReader(new FileReader("measurement.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("measurement.out")));

        int n = Integer.parseInt(f.readLine());
        ArrayList<String[]> entries = new ArrayList<>();
        for(int i=0;i<n;i++) {
            StringTokenizer entry = new StringTokenizer(f.readLine());
            entries.add(new String[]{entry.nextToken(), entry.nextToken(), entry.nextToken()});
        }
        Collections.sort(entries,new Comparator<String[]>() {
            public int compare(String[] strings, String[] otherStrings) {
                return Integer.parseInt(strings[0]) - Integer.parseInt(otherStrings[0]);
            }
        });
        //{Bessie,Elsie,Mildred}
        int[] milkAr = {7,7,7};
        int counter = 0;
        boolean[] prevHighest = {true,true,true};

        for (String[] entry : entries) {
            if (entry[1].equals("Bessie")){
                milkAr[0] += Integer.parseInt(entry[2]);
            }
            else if (entry[1].equals("Elsie")){
                milkAr[1] += Integer.parseInt(entry[2]);
            }
            else {
                milkAr[2] += Integer.parseInt(entry[2]);
            }

            int max = Arrays.stream(milkAr).max().getAsInt();
            boolean[] highest = {milkAr[0]==max,milkAr[1]==max,milkAr[2]==max};
            if (!Arrays.equals(highest, prevHighest)) {
                counter ++;
                prevHighest = highest;
            }

        }
        out.println(counter);
        out.close();
    }
}

