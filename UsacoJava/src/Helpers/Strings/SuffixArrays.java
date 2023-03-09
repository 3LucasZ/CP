package Helpers.Strings;

import java.util.*;

public class SuffixArrays {
    private static class SufArrRoll {
        String str;
        int sz;
        int height = 1;
        public int[] index;

        public SufArrRoll(String str) {
            this.str = str;
            sz = str.length();
            int len = 1;
            while (len < sz) {
                height++;
                len *= 2;
            }
            index = new int[sz];
            init();
        }

        public void init() {
            //base
            ArrayList<Pair> pairs = new ArrayList<>();
            for (int l = 0; l < sz; l++) pairs.add(new Pair(l, str.charAt(l), 0));
            Collections.sort(pairs);
            int in = 0;
            for (int l = 0; l < sz; l++) {
                if (l > 0 && !pairs.get(l).equals(pairs.get(l - 1))) in++;
                index[pairs.get(l).id] = in;
            }
            //binary jumping
            int jump = 1;
            for (int i = 1; i < height; i++) {
                pairs = new ArrayList<>();
                for (int l = 0; l < sz; l++) {
                    int r = l + jump;
                    int lv = index[l];
                    int rv = r >= sz ? -1 : index[r];
                    pairs.add(new Pair(l, lv, rv));
                }
                Collections.sort(pairs);
                in = 0;
                for (int l = 0; l < sz; l++) {
                    if (l > 0 && !pairs.get(l).equals(pairs.get(l - 1))) in++;
                    index[pairs.get(l).id] = in;
                }
                jump *= 2;
            }
        }

        public int get(int i) {
            return index[i];
        }

        private static class Pair implements Comparable<Pair> {
            int id;
            int i;
            int j;

            public Pair(int id, int i, int j) {
                this.id = id;
                this.i = i;
                this.j = j;
            }

            @Override
            public int compareTo(Pair o) {
                if (i == o.i) return j - o.j;
                return i - o.i;
            }

            @Override
            public boolean equals(Object o) {
                Pair other = (Pair) o;
                return i == other.i && j == other.j;
            }
        }
    }
}
