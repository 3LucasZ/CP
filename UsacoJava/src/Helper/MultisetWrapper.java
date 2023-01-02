package Helper;

import java.util.*;

public class MultisetWrapper {
    private static class Multiset {
        /*
        Overloaded TreeMap functioning as Multiset
        TreeMap private
         */
        private TreeMap<Integer, Integer> ms = new TreeMap<>();
        private int sz = 0;
        public boolean contains(int x){
            return ms.containsKey(x);
        }
        public void add(int x){add(x,1);}
        public void add(int x, int freq){
            if (!ms.containsKey(x))ms.put(x,0);
            ms.put(x,ms.get(x)+freq);
            sz+=freq;
        }
        public void remove(int x){
            remove(x,1);}
        public void remove(int x, int freq){
            ms.put(x,ms.get(x)-freq);
            if (ms.get(x)<=0) ms.remove(x);
            sz-=freq;
        }
        public void removeKey(int x){
            ms.remove(x);
        }
        public int get(int x){
            return ms.get(x);
        }
        public Iterator<Integer> iterator(){
            return ms.keySet().iterator();
        }
        public int size(){
            return sz;
        }
        public Set<Integer> keySet(){
            return ms.keySet();
        }
        public String toString(){
            return ms.toString();
        }
    }
}
