public class OffByN implements CharacterComparator {
    static int N;

    public OffByN(int n){
        N = n;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int asciix = x;
        int asciiy = y;
        return y - x == N || y - x == -N;
    }
}
