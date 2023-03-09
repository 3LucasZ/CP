package Solutions.TrainingGateway.Chapter1;/*
ID: Lucas Zheng [lucas.z4]
LANG: JAVA
TASK: Ad_hoc.beads
*/
import java.io.*;
import java.util.*;

public class beads {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("Ad_hoc.beads.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Ad_hoc.beads.out")));
        int numBeads = Integer.parseInt(f.readLine());
        String beads = f.readLine();
        necklace nk = new necklace(beads, numBeads);
        out.println(nk.maxBeadsFound());
        out.close();
        /*
        Ad_hoc.necklace nk = new Ad_hoc.necklace("rrr", 3);
        System.out.println(nk.maxBeadsFound());
        */
    }
    private static class necklace {

        List<String> necklaceAr;
        int numBeads;

        necklace(String beads, int num) {
            necklaceAr = new ArrayList(Arrays.asList(beads.split("")));
            numBeads = num;
        }
        public void permute() {
            necklaceAr.add(necklaceAr.remove(0));
        }
        public int beadsFound() {
            int index = 0;
            String colorOne;
            String colorTwo;
            while (necklaceAr.get(index).equals("w")) {
                index += 1;
                if (index == numBeads) {
                    return index;
                }
            }
            if (necklaceAr.get(index).equals("r")) {
                colorOne = "r";
                colorTwo = "b";
            }
            else {
                colorOne = "b";
                colorTwo = "r";
            }
            while (!necklaceAr.get(index).equals(colorTwo)) {
                index += 1;
                if (index == numBeads) {
                    return index;
                }
            }
            while (!necklaceAr.get(index).equals(colorOne)) {
                index += 1;
                if (index == numBeads) {
                    return index;
                }
            }
            return index;
        }
        public int maxBeadsFound() {
            int most = 0;
            for (int i = 0; i < numBeads; i++) {
                if (this.beadsFound() > most) {
                    most = beadsFound();
                }
                //System.out.println(this.beadsFound());
                //System.out.println(necklaceAr);
                this.permute();
            }
            return most;
        }
    }
}