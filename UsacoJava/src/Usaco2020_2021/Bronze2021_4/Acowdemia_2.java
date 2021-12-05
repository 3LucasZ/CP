package Usaco2020_2021.Bronze2021_4;

import java.io.*;
import java.util.*;

public class Acowdemia_2 {
    //param
    //num entries
    static int n;
    //num cows
    static int k;
    static TreeMap<String, Integer> cows = new TreeMap<>();
    //static LinkedHashMap<String, char[]> cows = new LinkedHashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(f.readLine());
        //parse input
        //num entries
        n = Integer.parseInt(st.nextToken());
        //num cows
        k = Integer.parseInt(st.nextToken());
        //parse line of cow names
        st = new StringTokenizer(f.readLine());
        char[][] sol = new char[k][k];
        for (int i=0;i<k;i++) {
            Arrays.fill(sol[i], '?');
        }
        for (int i=0;i<k;i++) {
            sol[i][i] = 'B';
            cows.put(st.nextToken(), i);
        }

        for (int i=0;i<n;i++) {
            String[] entry = new String[k];
            st = new StringTokenizer(f.readLine());
            for (int j=0;j<k;j++) {
                entry[j] = st.nextToken();
            }
            int index = 0;
            for (int j=0;j<k-1;j++) {
                if (entry[j].compareTo(entry[j+1]) > 0) {
                    //System.out.println("Not in order: " + entry[j] + " " + entry[j+1]);
                    for (int lower=0;lower<=j;lower++) {
                        for (int higher = j+1;higher<k;higher++) {
                            //System.out.println("lower: " + entry[lower] + " higher: " + entry[higher]);
                            sol[cows.get(entry[lower])][cows.get(entry[higher])] = '0';
                            sol[cows.get(entry[higher])][cows.get(entry[lower])] = '1';
                        }
                    }
                }
            }
        }
        for (int i=0;i<k;i++) {
            System.out.println(sol[i]);
        }
        out.close();
        f.close();
    }
}
