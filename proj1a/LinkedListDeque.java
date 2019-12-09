public class LinkedListDeque<Eletype> {
    private oneNode sentinel;
    private int size;

    public class oneNode{
        public Eletype item;
        public oneNode previous;
        public oneNode next;

        public oneNode(Eletype i,oneNode p,oneNode n){
            item = i;
            previous = p;
            next = n;
        }
    }

    public void addFirst(Eletype f){
        oneNode first = new oneNode(f,sentinel,sentinel.next);
        sentinel.next.previous = first;
        sentinel.next = first;
        size += 1;
    }

    public void addLast(Eletype l){
        oneNode last = new oneNode(l,sentinel.previous,sentinel);
        sentinel.previous.next = last;
        sentinel.previous = last;
        size += 1;
    }

    public void removeFirst(){
        sentinel.next = sentinel.next.next;
        sentinel.next.previous = sentinel;
        size -= 1;
    }

    public void removeLast(){
        sentinel.previous = sentinel.previous.previous;
        sentinel.previous.next = sentinel;
        size -= 1;
    }

    public boolean isEmpty(){
        if(sentinel.next == sentinel)
            return true;
        else
            return false;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        for(int i = 0;i<size-1;i++){
            System.out.print(get(i));
            System.out.print(' ');
        }
        System.out.print(get(size-1));
        System.out.println();
    }

    public Eletype get(int index){
        oneNode target =  sentinel;
        for(int i = 0;i<=index;i++){
            target = target.next;
        }
        if(target == sentinel)
            return null;
        else
            return target.item;
    }

    public LinkedListDeque(){
        sentinel = new oneNode(null,sentinel,sentinel);
        sentinel.next = sentinel;
        sentinel.previous = sentinel;
        size = 0;
    }

    public LinkedListDeque(LinkedListDeque other){
        LinkedListDeque l = new LinkedListDeque();
        for(int i = 0;i<other.size;i++){
            l.addLast(other.get(i));
        }
        size = other.size;
    }

    private oneNode pointer = sentinel;

    public Eletype getRecursive(int index){
        if(index<0||pointer.next == sentinel){
            return null;
        }
        if(index == 0){
            return pointer.next.item;
        }
        return getRecursive(index-1);
    }
}
