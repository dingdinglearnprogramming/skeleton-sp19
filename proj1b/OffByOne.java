public class OffByOne implements CharacterComparator {

    @Override
    public boolean equalChars(char x, char y){
        int asciix = x;
        int asciiy = y;
        return y - x == 1 || y - x == -1;
    }
}
