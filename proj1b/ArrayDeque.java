import java.util.LinkedList;

public class ArrayDeque<Item> implements Deque<Item> {
    private Item[] items;
    private int front;
    private int end;
    private int size;

    public ArrayDeque(){
        items = (Item []) new Object[8];
        front = 0;
        end = 0;
        size = 0;
    }

    public ArrayDeque(ArrayDeque other){
        items = (Item []) new Object[8];
        front = 0;
        end = 0;

        for(int index = 0;index < other.size();index ++){
            Item temp = (Item) other.get(index);
            addLast(temp);
            end += 1;
            size += 1;
        }
    }

    private int RightIndex(int i){
        if(i<0){
            return (items.length + i);
        }
        if(i>items.length-1){
            return (i - items.length);
        }
        return i;
    }


    private void resizing(){
        Item[] temp = (Item[]) new Object[items.length*2];
        for(int i = 0; i < end; i++){
            temp[i] = items[i];
        }
        for (int j = front; j < items.length; j++){
            temp[j+items.length] = items[j];
        }
        front += items.length;
        items = temp;
    }

    private void compress(){
        if(items.length==8 || size/items.length>=0.25){
            return;
        }
        Item[] temp = (Item[]) new Object[items.length/2];
        for(int i = 0; i < size; i++){
            temp[i] = items[RightIndex(front+i)];
        }
        front = 0;
        end = size - 1;
        items = temp;
    }

    @Override
    public void addFirst(Item item){
        if(size == items.length){
            resizing();
        }
        front = RightIndex(front - 1);
        items[front] = item;
        size += 1;
    }

    @Override
    public void addLast(Item item){
        if(size == items.length){
            resizing();
        }
        items[end] = item;
        end += 1;
        size += 1;
    }

    @Override
    public Item removeFirst(){
        Item result = items[front];
        items[front] = null;
        front = RightIndex(front+1);
        size -= 1;
        return result;
    }

    @Override
    public Item removeLast(){
        int lastIndex = RightIndex(end-1);
        Item result = items[lastIndex];
        items[lastIndex] = null;
        end = lastIndex;
        size -= 1;
        return result;
    }

    @Override
    public Item get(int i){
        return items[RightIndex(front+i)];
    }

    @Override
    public int size(){
        return size;
    }
}
