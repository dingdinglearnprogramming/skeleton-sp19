public class ArrayDeque<EleType> {
    private EleType[] items;
    private int front;
    private int end;
    private int size;

    public ArrayDeque(){
        items = (EleType []) new Object[8];
        front = 0;
        end = 0;
        size = 0;
    }

    public ArrayDeque(ArrayDeque other){
        items = (EleType []) new Object[8];
        front = 0;
        end = 0;

        for(int index = 0;index < other.size();index ++){
            EleType temp = (EleType) other.get(index);
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
        EleType[] temp = (EleType[]) new Object[items.length*2];
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
        if(items.length==8 || size/items.length>=0.3){
            return;
        }
        EleType[] temp = (EleType[]) new Object[items.length/2];
        for(int i = 0; i < size; i++){
            temp[i] = items[RightIndex(front+i)];
        }
        front = 0;
        end = size - 1;
        items = temp;
    }

    public void addFirst(EleType item){
        if(size == items.length){
            resizing();
        }
        front = RightIndex(front - 1);
        items[front] = item;
        size += 1;
    }

    public void addLast(EleType item){
        if(size == items.length){
            resizing();
        }
        items[end] = item;
        end += 1;
        size += 1;
    }

    public EleType removeFirst(){
        EleType result = items[front];
        items[front] = null;
        front = RightIndex(front+1);
        size -= 1;
        return result;
    }

    public EleType removeLast(){
        int lastIndex = RightIndex(end-1);
        EleType result = items[lastIndex];
        items[lastIndex] = null;
        end = lastIndex;
        size -= 1;
        return result;
    }

    public EleType get(int i){
        return items[RightIndex(front+i)];
    }

    public int size(){
        return size;
    }

/*    public static void main(String[] args){
        ArrayDeque<Integer> a = new ArrayDeque<Integer>();
        a.addLast(5);
        a.addLast(6);
        a.addFirst(4);
        a.addFirst(3);
        a.addLast(7);
        a.addLast(8);
        a.addLast(9);
        a.addLast(10);
        a.addFirst(2);
        a.addFirst(1);
        a.removeFirst();
        a.removeLast();
        a.addLast(11);
        for (int i = 0; i < a.size; i++) {
            System.out.println(a.get(i));
        }
    }*/
}
