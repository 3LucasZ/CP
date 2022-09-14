package Experiments;

import java.util.HashSet;

public class HashingTesting {
    public static void main(String[] args){
        HashSet<Object> set = new HashSet<>();
        set.add(new Object(8));
        set.add(new Object(9));
        set.add(new Object(8));
        System.out.println(set);
    }
    private static class Object {
        int x;
        public Object(int x){
            this.x=x;
        }
        public String toString(){
            return ""+x;
        }
    }
}
