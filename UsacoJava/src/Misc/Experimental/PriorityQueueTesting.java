package Misc.Experimental;

import java.util.PriorityQueue;

public class PriorityQueueTesting {
    public static void main(String[] args){
        PriorityQueue<Integer> active = new PriorityQueue<>();
        active.add(1);
        active.add(1);
        active.add(8);
        active.add(33);
        System.out.println(active);

        PriorityQueue<Object> active2 = new PriorityQueue<>((a,b)->a.data-b.data);
        active2.add(new Object(1));
        active2.add(new Object(3));
        active2.add(new Object(5));
        System.out.println(active2);
    }
    private static class Object {
        int data;
        public Object(int d){
            data=d;
        }
        public String toString(){
            return ""+data;
        }
    }
}
