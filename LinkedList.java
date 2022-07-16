public class LinkedList<T>{
    public Node<T> head;
    public Node<T> tail;
    public int size;

    public LinkedList() {
        head = new Node<>(null);
        tail = new Node<>(null);
        size = 0;
        head.nextNode = tail;
        tail.prevNode = head;
    }

    public void add(T data) { // O(1) complexity
        Node<T> newNode = new Node<>(data);
        newNode.nextNode = tail;
        newNode.prevNode = tail.prevNode;
        tail.prevNode.nextNode = newNode;
        tail.prevNode = newNode;
        size++;
    }

    public void add(T data, int index) { // O(n) complexity
        try {
            Node<T> newNode = new Node<>(data);
            Node<T> CurrentNode = head.nextNode;
            while (index != 0) {
                CurrentNode = CurrentNode.nextNode;
                index--;
            }
            Node<T> TempNode = CurrentNode.prevNode;
            TempNode.nextNode = newNode;
            newNode.prevNode = TempNode;
            newNode.nextNode = CurrentNode;
            CurrentNode.prevNode = newNode;
            size++;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void remove (int index) { // O(n) complexity
        try {
            Node<T> CurrentNode = head.nextNode;
            while (index != 0) {
                CurrentNode = CurrentNode.nextNode;
                index--;
            }
            CurrentNode.prevNode.nextNode = CurrentNode.nextNode;
            CurrentNode.nextNode.prevNode = CurrentNode.prevNode;
            size--;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void set(T data, int index) { // O(n) complexity
        try {
            Node<T> CurrentNode = head.nextNode;
            while (index != 0) {
                CurrentNode = CurrentNode.nextNode;
                index--;
            }
            if (CurrentNode == tail) {
                throw new ArrayIndexOutOfBoundsException();
            }
            CurrentNode.data = data;
        }
        catch (Exception e) {
            System.out.println("Exception: Wrong index");
        }
    }

    public int size() { // O(1) complexity
        return size;
    }

    public T get(int index) { // O(n) time complexity
        try {
            Node<T> CurrentNode = head.nextNode;
            while (index != 0) {
                CurrentNode = CurrentNode.nextNode;
                index--;
            }
            return CurrentNode.data;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}