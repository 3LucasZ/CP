package Experiments.BuiltinDS;

import java.util.*;

public class DynamicTreeSet {
    public static void main(String[] args) {
        int[] cost = {5,3,2,6,7,4,5,6};
        TreeSet<Integer> set = new TreeSet<>((a,b)->{
            if (cost[a]==cost[b]) return a-b;
            return cost[a]-cost[b];
        });
        for (int i=0;i<cost.length;i++) set.add(i);

        //testing treeSet dynamic changes
        System.out.println(set);
        set.remove(0);
        cost[0]=100;
        set.add(0);
        System.out.println(set);
    }
}
