package Greedy_Algorithm;/*
ID: Lucas Zheng [lucas.z4]
LANG: JAVA
TASK: Greedy_Algorithm.milk
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class milk {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("Greedy_Algorithm.milk.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Greedy_Algorithm.milk.out")));
        StringTokenizer s = new StringTokenizer(f.readLine());
        int unitsNeeded = Integer.parseInt(s.nextToken());
        int farmers = Integer.parseInt(s.nextToken());
        ArrayList<farmer> farmerList = new ArrayList<>();
        for (int i=0;i<farmers;i++) {
            s = new StringTokenizer(f.readLine());
            farmerList.add(new farmer(Integer.parseInt(s.nextToken()),Integer.parseInt(s.nextToken())));
        }
        Collections.sort(farmerList, new Comparator<farmer>() {
            @Override public int compare(farmer f1,farmer f2) {
                return f1.price - f2.price;
            }
        });
        int bill = 0;
        for (int i=0;i<farmers;i++) {
            if (unitsNeeded >= farmerList.get(i).availableUnits) {
                unitsNeeded -= farmerList.get(i).availableUnits;
                bill += farmerList.get(i).availableUnits * farmerList.get(i).price;
            }
            else {
                bill += unitsNeeded * farmerList.get(i).price;
                break;
            }


        }
        out.println(bill);
        out.close();
    }
}
class farmer {
    int availableUnits;
    int price;
    public farmer(int p,int u){
        price = p;
        availableUnits = u;
    }
    @Override
    public String toString() {
        return "(" + price + ")";
    }

}
