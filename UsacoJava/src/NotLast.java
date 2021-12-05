import java.io.*;
import java.util.*;

public class NotLast {
    //default
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int n;
    static ArrayList<String> entries = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        //setup
        if (submission) {
            f = new BufferedReader(new FileReader("notlast.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("notlast.out")));
            n = Integer.parseInt(f.readLine());
            for (int i=0;i<n;i++){
                entries.add(f.readLine());
            }
        }
        else {
            n = 4;
            entries.add("Bessie 1");
            entries.add("Maggie 13");
            entries.add("Elsie 3");
            entries.add("Elsie 4");
            System.out.println(n);
            System.out.println(entries);
        }
        //logic
        Map<String, Integer> cowMilk = new TreeMap<String, Integer>();
        ArrayList<String> cowNames = new ArrayList<>(Arrays.asList("Bessie", "Elsie", "Daisy", "Gertie", "Annabelle", "Maggie", "Henrietta"));
        for (String name : cowNames) {
            cowMilk.put(name, 0);
        }

        for (int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(entries.get(i));
            String cow = st.nextToken();
            int milk = Integer.parseInt(st.nextToken());
            cowMilk.put(cow, cowMilk.get(cow) + milk);
        }

        ArrayList<Integer> sortedMilk = new ArrayList<>(cowMilk.values());
        Collections.sort(sortedMilk);
        int milkNum = -1;
        String answer = "";
        for (int i=1;i<sortedMilk.size();i++){
            if (sortedMilk.get(i) > sortedMilk.get((i-1))) {
                milkNum = sortedMilk.get(i);
                if (i+1 < sortedMilk.size() && sortedMilk.get(i+1) == sortedMilk.get(i)) {
                    answer = "Tie";
                }
                break;
            }
        }
        if (milkNum == -1) {
            answer = "Tie";
        }
        if (answer != "Tie") {
            for (String c : cowMilk.keySet()) {
                if (cowMilk.get(c) == milkNum) {
                    answer = c;
                }
            }
        }
        //print
        if (submission) {
            out.println(answer);
            f.close();
            out.close();
        }
        else {
            System.out.println(answer);
        }
    }
}