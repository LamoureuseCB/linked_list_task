import java.util.NoSuchElementException;

public class HandMadeLinkedList<T> {
    class Node<E> {
        public E data;
        public Node<E> next;
        public Node<E> prev;

        public Node(Node<E> prev, E data, Node<E> next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    public void addFirst(T element) {
        final Node<T> oldHead = head;
        final Node<T> newNode = new Node<>(null, element, oldHead);
        head = newNode;
        if (oldHead == null)
            tail = newNode;
        else
            oldHead.prev = newNode;
        size++;
    }

    public T getFirst() {
        final Node<T> curHead = head;
        if (curHead == null)
            throw new NoSuchElementException();
        return head.data;
    }

    public void addLast(T element) {
        final Node<T> oldTail = tail;
        final Node<T> newNode = new Node<>(tail, element, null);
        tail = newNode;
        if (oldTail == null) {
            head = newNode;
        } else {
            oldTail.next = newNode;
        }
        size++;
    }

    public T getLast() {
        final Node<T> curTail = tail;
        if (curTail == null)
            throw new NoSuchElementException();
        return tail.data;
    }

    public int size() {
        return this.size;
    }

    public void addElement(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            addFirst(element);
        } else if (index == size) {
            addLast(element);
        }
        Node<T> left = getNode(index - 1);
        Node<T> right = left.next;
        final Node<T> newNode = new Node<>(left, element, right);
        left.next = newNode;
        newNode.next = right;
        size++;
        if (right == null) {
            tail = newNode;
        }

    }

    public void removeLast() {
        if (tail == null) {
            return;
        }
        if (head == tail) {
            head = null;
            tail = null;
            return;
        }
        Node<T> current = head;
        while (current.next != tail) {
            current = current.next;
        }
        current.next = null;
        tail = current;
        size--;
    }

    public void removeFirst() {
        if (head == null) {
            return;
        }
        if (head == tail) {
            tail = null;
            head = null;
            return;
        }
        Node<T> currentHead = head;
        head = currentHead.next;
        currentHead = null;
    }

    public void removeElement(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            removeFirst();
        } else if (index == size) {
            removeLast();
        } else {
            Node<T> left = getNode(index - 1);
            Node<T> node = left.next;
            if (node == null) return;
            Node<T> right = node.next;
            left.next = right;
            if (node == tail) {
                tail = null;
            }
        }
    }

    public Node<T> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        int counter = 0;
        while (counter != index && current != null) {
            current = current.next;
            counter++;
        }
        if (index == counter) {
            return current;
        }
        throw new NoSuchElementException();
    }

    public void printAll() {
        Node<T> current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }


    public static void main(String[] args) {
        HandMadeLinkedList<Integer> integers = new HandMadeLinkedList<>();

        integers.addFirst(1);
        integers.addFirst(2);
        integers.addFirst(3);
        integers.addLast(4);
        integers.addLast(5);
        integers.addFirst(1);

        System.out.println("До добавления: ");
        integers.printAll();
        integers.addElement(2, 12565);
        integers.addElement(4, 12234);
        System.out.println();
        System.out.println("После добавления: ");
        integers.printAll();
        integers.removeElement(4);
        integers.removeFirst();
        integers.removeLast();
        System.out.println("После удалений: ");
        integers.printAll();

    }
}