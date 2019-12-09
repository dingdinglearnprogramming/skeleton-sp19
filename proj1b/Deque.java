public interface Deque<Item> {

    public void addFirst(Item item);
    public void addLast(Item item);
    public Item removeFirst();
    public Item removeLast();
    public Item get(int i);
    public int size();

    default boolean isEmpty(){
        if (size() == 0) {
            return true;
        }
        return false;
    }
}
