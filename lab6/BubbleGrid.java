public class BubbleGrid {
    private int[][] grid;
    private UnionFind union;

    public BubbleGrid(int[][] bubbleGrid) {
        grid = bubbleGrid;
        connect();
    }

    private void connect() {
        union = new UnionFind(grid.length * grid[0].length);
        for (int i = 1; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (isStuck(i,j) > 0) {
                    int[] temp = new int[]{i,j};
                    union.union(getIndex(temp),isStuck(i,j));
                }
            }
        }
    }

    private void fall(int[] dart) {
        if (grid[dart[0]][dart[1]] == 0) {
            return;
        }
        grid[dart[0]][dart[1]] = 0;
        for (int i = 1; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (isStuck(i,j) < 0) {
                    grid[i][j] = 0;
                }
            }
        }
        grid[dart[0]][dart[1]] = 1;
    }

    private int getIndex(int[] xy) {
        return xy[0] * grid[0].length + xy[1];
    }

    private int isStuck(int x, int y) {
        if (grid[x][y] == 0) {
            return -1;
        }

        if (grid[x-1][y]==1) {
            return (x - 1) * grid[0].length + y;
        }
        else if (y!= 0 && grid[x][y-1] == 1) {
            return x * grid[0].length + y - 1;
        }
        else {
            return -1;
        }
    }

    public int[] popBubbles(int[][] darts) {
        int[] r = new int[darts.length];
        for (int i = 0; i < darts.length; i++) {
            int be = union.sizeOf(getIndex(darts[i]));
            fall(darts[i]);
            connect();
            int af = union.sizeOf(getIndex(darts[i]));
            r[i] = be - af;
        }
        return r;
    }

    public static void main(String[] args) {
        int[][] grid = new int[4][3];
        grid[0] = new int[]{1,1,0};
        grid[1] = new int[]{1,0,0};
        grid[2] = new int[]{1,1,0};
        grid[3] = new int[]{1,1,1};
        BubbleGrid b = new BubbleGrid(grid);
        int[][] darts = new int[2][];
        darts[0] = new int[]{2,2};
        darts[1] = new int[]{2,0};
        int[] r = b.popBubbles(darts);
        System.out.println(r[1]);
    }
}
