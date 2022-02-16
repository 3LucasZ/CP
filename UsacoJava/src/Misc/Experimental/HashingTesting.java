package Misc.Experimental;

import java.util.HashSet;

public class HashingTesting {
    public static void main(String[] args){
        HashSet<CustomObject> hs = new HashSet<>();
        hs.add(new CustomObject(8));
        hs.add(new CustomObject(9));
        hs.add(new CustomObject(8));
        System.out.println(hs.size());
    }
    private static class CustomObject {
        int temp;
        public CustomObject(int t){
            temp=t;
        }

    }
}
