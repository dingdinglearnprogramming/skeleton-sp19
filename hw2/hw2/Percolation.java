package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


import java.util.Set;
import java.util.TreeSet;

public class Percolation {
    private WeightedQuickUnionUF wqu;
    private int openNum;
    private int[][] openGrid;
    //private Set<Integer> s;
    //private Set<Integer> p;
    //private boolean isPercolate;
    private int n;

    public Percolation(int N) {
        wqu = new WeightedQuickUnionUF(N * N + 2);
        openNum = 0;
        openGrid = new int[N][N];
        //s = new TreeSet<>();
        //p = new TreeSet<>();
        n = N;
    }

    public void open(int row, int col) {
        if (isOpen(row, col)) {
            return;
        }

        openGrid[row][col] = 1;
        openNum += 1;
        int index = row * n + col;

        if (row == 0) {
            wqu.union(index, n * n);
        }
        if (row == n - 1) {
            wqu.union(index, n * n + 1);
        }
        if (row != 0 && isOpen(row - 1, col)){
            wqu.union(index,index - n);
        }
        if (row != n - 1 && isOpen(row + 1, col)) {
            wqu.union(index,index + n);
        }
        if (col != 0 && isOpen(row, col - 1)) {
            wqu.union(index,index - 1);
        }
        if (col != n - 1 && isOpen(row, col + 1)) {
            wqu.union(index,index + 1);
        }
    }

    public boolean isOpen(int row, int col) {
        return openGrid[row][col] == 1;
    }
    public boolean isFull(int row, int col) {
        return wqu.connected(row * n + col, n*n);
        //return s.contains(wqu.find(row * n + col));
    }
    public int numberOfOpenSites() {
        return openNum;
    }
    public boolean percolates() {
        return wqu.connected(n*n,n*n+1);
        //return isPercolate;
    }
    public static void main(String[] args) {

    }

    /*
        private void uni(int a, int b) {
        int ra = wqu.find(a);
        int rb = wqu.find(b);

        if ((s.contains(ra) && p.contains(rb)) || (s.contains(rb) && p.contains(ra))) {
            isPercolate = true;
        }

        wqu.union(a,b);
        int r = wqu.find(a);
        if (s.contains(ra)) {
            s.remove(ra);
            s.add(r);
        }
        if (p.contains(ra)) {
            p.remove(ra);
            p.add(r);
        }
        if (s.contains(rb)) {
            s.remove(rb);
            s.add(r);
        }
        if (p.contains(rb)) {
            p.remove(rb);
            p.add(r);
        }
    }

    public void open(int row, int col) {
        if (isOpen(row, col)) {
            return;
        }

        openGrid[row][col] = 1;
        openNum += 1;
        int index = row * n + col;

        if (row == 0) {
            s.add(index);
        }
        if (row == n - 1) {
            p.add(index);
        }

        if (row != 0 && isOpen(row - 1, col)){
            uni(index,index - n);
        }
        if (row != n - 1 && isOpen(row + 1, col)) {
            uni(index,index + n);
        }
        if (col != 0 && isOpen(row, col - 1)) {
            uni(index,index - 1);
        }
        if (col != n - 1 && isOpen(row, col + 1)) {
            uni(index,index + 1);
        }
    }
     */
}
