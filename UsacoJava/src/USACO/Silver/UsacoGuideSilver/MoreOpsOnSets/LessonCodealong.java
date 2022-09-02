package USACO.Silver.UsacoGuideSilver.MoreOpsOnSets;

import java.util.*;
public class LessonCodealong {
    public static void main(String[] args){
        //iterators
        Set<Integer> set = new HashSet<Integer>();
        set.add(1);set.add(3);set.add(0);set.add(-2);
        Iterator it1 = set.iterator();
        while(it1.hasNext()){
            Integer i = (Integer)it1.next();
            System.out.print(i+" "); //returns 0 1 -2 3 pseudo random order
        }
        System.out.println();
        Set<Integer> set2 = new TreeSet<Integer>();
        set2.add(1);set2.add(3);set2.add(0);set2.add(-2);
        Iterator it2 = set2.iterator();
        while(it2.hasNext()){
            Integer i = (Integer) it2.next();
            System.out.print(i+" "); //returns -2 0 1 3 sorted
        }
        //in java you can just use for-each though
        System.out.println();
        for (int i : set2) {
            System.out.print(i+" ");
        }
        System.out.println();
        //warning: do not modify sets when traversing it with iterators/for each. only modification possible is using the iterator remove() once before using next()
        //More ops!
        TreeSet<Integer> set3 = new TreeSet<Integer>();
        set3.add(1);set3.add(14);set3.add(9);set3.add(2);
        System.out.println(set3.higher(7));
        System.out.println(set3.lower(5));
        System.out.println(set3.first());
        System.out.println(set3.last());
        set3.remove(set3.higher(6));
        System.out.println(set3.higher(23));
        //Sorted Maps
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        map.put(3,5);
        map.put(11,4);
        map.put(10,491);
        System.out.println(map.firstKey());
        System.out.println(map.firstEntry());
        System.out.println(map.lastEntry());
        System.out.println(map.higherEntry(4));
        map.remove(11);
        System.out.println(map.lowerKey(4));
        //multisets
        add(1);remove(1);add(2);add(2);add(2);add(3);
        System.out.println(multiset);
        //PRIORITY QEUES! (HEAPS)
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(7);pq.add(2);pq.add(1);pq.add(5);
        System.out.println(pq);
        System.out.println(pq.peek());
    }
    static TreeMap<Integer, Integer> multiset = new TreeMap<Integer, Integer>();
    static void add(int x){
        if(multiset.containsKey(x)){
            multiset.put(x,multiset.get(x)+1);
        } else {
            multiset.put(x,1);
        }
    }
    static void remove(int x){
        multiset.put(x,multiset.get(x)-1);
        if(multiset.get(x)==0){
            multiset.remove(x);
        }
    }
}
