package Helpers;

import java.util.*;
//TODO: Make it into an interface so that Int, Long, Char can be used interchangeably
public class MultisetWrapper {
    /*
        Overloaded TreeMap functioning as Sorted Multiset
    */
    private static class Multiset <T> {
        public TreeMap<T, Integer> ms = new TreeMap<>();
        private int sz = 0;
        public boolean contains(T x){
            return ms.containsKey(x);
        }
        public void add(T x){add(x,1);}
        public void add(T x, int freq){
            if (!ms.containsKey(x))ms.put(x,0);
            ms.put(x,ms.get(x)+freq);
            sz+=freq;
        }
        public void remove(T x){
            remove(x,1);
        }
        public void remove(T x, int freq){
            ms.put(x,ms.get(x)-freq);
            if (ms.get(x)<=0) ms.remove(x);
            sz-=freq;
        }
        public void removeKey(int x){
            ms.remove(x);
        }
        public int get(int x){
            if (ms.containsKey(x)) return ms.get(x);
            return 0;
        }
        public Iterator<T> iterator(){
            return ms.keySet().iterator();
        }
        public int size(){
            return sz;
        }
        public Set<T> keySet(){
            return ms.keySet();
        }
        public String toString(){
            return ms.toString();
        }
    }
}
