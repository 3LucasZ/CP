package Experiments.BuiltinJava;

import java.util.*;

public class ListIteratorOperations {
    public static void main(String[] args) {
        //init list
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(21);
        list.add(16);
        list.add(777);
        list.add(98);
        ListIterator<Integer> it = list.listIterator();
        System.out.println(list);

        //operations
        int ops = 0;
        while (it.hasNext()){
            ops++;
            int next = it.next();
            System.out.println(next);
            if (ops%2==0) {
                it.add(100);
                it.previous();
            }
        }
        System.out.println(list);
    }
}
