public class BoundedStack<T> implements IBoundedStack{
    /*  The idea of the implementation of the Bounded Stack using queues is the following:
        the last element that we push into the stack should be the first element in our queue.
        in this case, we convert stack's principle FILO to queue's FIFO.
        I've considered to make "pop" method cheap (O(1) complexity), instead of making "push" method cheap.
     */

    private DoublyCircularBoundedQueue<Integer> Q1;
    private DoublyCircularBoundedQueue<Integer> Q2;
    private int MAXSIZE;
    public int currentsize;

    public BoundedStack(int size) {
        Q1 = new DoublyCircularBoundedQueue<Integer>(size);
        Q2 = new DoublyCircularBoundedQueue<Integer>(size);
        MAXSIZE = size;
        currentsize = 0;
        if (size<0) {
            System.out.println("Exception: the size cannot be less than zero!");
        }
    }

    @Override
    public void push(Object value) { // O(n) time complexity
        if (currentsize == MAXSIZE) {
            while (Q1.size()!=1) {    //  transfer all (except for the oldest one) elements from Q1 to Q2
                Q2.offer(Q1.peek());
                Q1.poll();
            }
            Q1.flush();   // get rid of the oldest element
            DoublyCircularBoundedQueue<Integer> Q = Q1; // swap queues to make Q2 empty
            Q1 = Q2;
            Q2 = Q;
            currentsize--;
            push(value);
        }
        else {
            Q2.offer(value);  // add the value to empty queue Q2

            while (!Q1.isEmpty()) {    //  transfer all other elements from Q1 to Q2
                Q2.offer(Q1.peek());
                Q1.poll();
            }

            DoublyCircularBoundedQueue<Integer> Q = Q1; // swap queues to make Q2 empty
            Q1 = Q2;
            Q2 = Q;
            currentsize++;
        }
    }

    @Override
    public T pop() {  // O(1) time complexity
        try {
            currentsize--;
            return (T)Q1.poll();
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public T top() { // O(1) time complexity
        return (T) Q1.peek();
    }

    @Override
    public void flush() { // O(n) time complexity
        Q1.flush();
        currentsize = 0;
    }

    @Override
    public boolean isEmpty() { // O(1) time complexity
        return currentsize == 0;
    }

    @Override
    public boolean isFull() { // O(1) time complexity
        return currentsize == MAXSIZE;
    }

    @Override
    public int size() { // O(1) time complexity
        return currentsize;
    }

    @Override
    public int capacity() { // O(1) time complexity
        return MAXSIZE;
    }
}
