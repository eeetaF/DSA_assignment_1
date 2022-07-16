public class Node<E> {
    E data;
    Node<E> prevNode;
    Node<E> nextNode;
    public Node(E e){
        this.data = e;
        this.prevNode = null;
        this.nextNode = null;
    }
}