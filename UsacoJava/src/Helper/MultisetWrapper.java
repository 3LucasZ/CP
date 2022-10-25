package Helper;

import java.util.*;

public class MultisetWrapper {
    private static class Multiset {
        TreeMap<Integer, Integer> ms = new TreeMap<>();
        public void add(int x){
            if (!ms.containsKey(x))ms.put(x,0);
            ms.put(x,ms.get(x)+1);
        }
        public void rem(int x){
            ms.put(x,ms.get(x)-1);
            if (ms.get(x)==0) ms.remove(x);
        }
    }
}
