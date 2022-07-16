public class DoublyCircularBoundedQueue<T> implements ICircularBoundedQueue {
    private final int MAXSIZE;
    private int currentsize;
    private final LinkedList<T> QLL;

    public DoublyCircularBoundedQueue(int size) {
        QLL = new LinkedList<T>();
        QLL.head.prevNode = QLL.tail;
        QLL.tail.nextNode = QLL.head;
        MAXSIZE = size;
        currentsize = 0;
        if (size<0) {
            System.out.println("Exception: the size cannot be less than zero!");
        }
    }

    @Override
    public void offer(Object value) {    // O(1) complexity
        if (currentsize == MAXSIZE) {
            QLL.add((T) value);
            QLL.head.nextNode = QLL.head.nextNode.nextNode;
            QLL.head.nextNode.prevNode = QLL.head;
        }
        else {
            QLL.add((T)value);
            currentsize++;
        }
    }

    @Override
    public T poll() {   // O(1) complexity
        try {
            if (currentsize == 0) {
                throw new NullPointerException();
            }
            Object o = QLL.get(0);
            QLL.head.nextNode = QLL.head.nextNode.nextNode;
            QLL.head.nextNode.prevNode = QLL.head;
            QLL.size--;
            currentsize--;
            return (T)o;
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public Object peek() {  // O(1) complexity
        return QLL.head.nextNode.data;
    }

    @Override
    public void flush() {  // O(n) complexity
        while (currentsize != 0) {
            QLL.remove(0);
            currentsize--;
        }
    }

    @Override
    public boolean isEmpty() {  // O(1) complexity
        return currentsize == 0;
    }

    @Override
    public boolean isFull() { // O(1) complexity
        return currentsize == MAXSIZE;
    }

    @Override
    public int size() {  // O(1) complexity
        return currentsize;
    }

    @Override
    public int capacity() { // O(1) complexity
        return MAXSIZE;
    }
}
