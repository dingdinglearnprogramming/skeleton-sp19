public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque result = new LinkedListDeque();
        for (int i = 0; i < word.length(); i++) {
            result.addLast(word.charAt(i));
        }
        return result;
    }

    private boolean isPalindrome(Deque d){
        if (d.size() < 2) {
            return true;
        }
        boolean temp = d.removeFirst() == d.removeLast() && isPalindrome(d);
        return temp;
    }

    public boolean isPalindrome(String word){
        Deque w = wordToDeque(word);
        return isPalindrome(w);
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        if (word.length() < 2) {
            return true;
        }
        return cc.equalChars(word.charAt(0),word.charAt(word.length() - 1))
                && isPalindrome(word.substring(1, word.length()-1), cc);
    }
}
