public class UnionFindwithPC extends UnionFind {

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFindwithPC(int n) {
        // TODO
        super(n);
    }

    @Override
    public void union(int v1, int v2) {
        // TODO
        if (connected(v1,v2)) {
            return;
        }
        int high = find(v1);
        int low = find(v2);

        if (items[high] > items[low]) {
            int temp = high;
            high = low;
            low = temp;
        }

        int rh = high;

        while (low > 0) {
            int next = items[low];
            items[low] = rh;
            low = next;
        }
    }
}
