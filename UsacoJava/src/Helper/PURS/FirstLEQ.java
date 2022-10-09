package Helper.PURS;

public class FirstLEQ {
    private static class SegTree {
        //1-indexed
        //range is []
        int size;
        long[] tree;

        public SegTree(int n) {
            init(n);
        }

        public SegTree(int n, int[] arr) {
            init(n);
            for (int i = 1; i <= n; i++) {
                tree[i + size - 1] = arr[i];
            }
            for (int i = size - 1; i >= 1; i--) {
                tree[i] = Math.max(tree[i * 2], tree[i * 2 + 1]);
            }
        }

        public void init(int n) {
            size = 1;
            while (size < n) size *= 2;
            tree = new long[2 * size + 1];
        }

        void add(int k, int x) {
            set(k, tree[k + size - 1] + x);
        }

        void set(int k, long x) {
            k += size - 1;
            tree[k] = x;
            for (k /= 2; k >= 1; k /= 2) {
                tree[k] = Math.max(tree[2 * k], tree[2 * k + 1]);
            }
        }

        int nextLeqThan(int tar) {
            return search(tar, 1);
        }

        int search(int tar, int x) {
            //leaf case
            if (x >= size) {
                if (tree[x] >= tar) return x - size + 1;
                else return -1;
            }
            //not in the l, r
            if (tree[x] < tar) return -1;
            //try lx
            int l = search(tar, 2 * x);
            if (l != -1) return l;
            //else try rx
            int r = search(tar, 2 * x + 1);
            return r;
        }
    }
}
