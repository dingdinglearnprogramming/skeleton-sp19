package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

import java.util.ArrayList;
import java.util.List;

public class PercolationStats {
    private double[] accu;
    private int t;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N < 0 || T < 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        t = T;
        accu = new double[T];

        int[] indexes = new int[N * N];
        for (int i = 0; i < N * N; i++){
            indexes[i] = i;
        }

        int row;
        int col;

        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            StdRandom.shuffle(indexes);
            int step = 0;
            while (! p.percolates()) {
                row = indexes[step] / N;
                col = indexes[step] % N;
                p.open(row, col);
                step += 1;
            }
            accu[i] = (double) step / (N * N);
        }
    }

    public double mean() {
        return StdStats.mean(accu);
    }

    public double stddev() {
        return StdStats.stddev(accu);
    }

    public double confidenceLow() {
        return (mean() - 0.96 * stddev() / Math.sqrt(t));
    }

    public double confidenceHigh() {
        return (mean() + 0.96 * stddev() / Math.sqrt(t));
    }

    public static void main(String[] args) {
        PercolationStats test = new PercolationStats(100, 10, new PercolationFactory());
        System.out.println(test.mean());
    }
}
