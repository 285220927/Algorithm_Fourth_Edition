/**
 * @FileName: ArrayQueue.java
 * @Author: zzc
 * @Date: 2020年09月17日 19:57:49
 * @Version V1.0.0
 */

package utils;


@SuppressWarnings({"unchecked", "WeakerAccess"})
public class ArrayQueue<E> {
    private E[] elementData;
    private int front;
    private int tail;

    public ArrayQueue(int capacity) {
        elementData = (E[]) new Object[capacity];
        front = 0;
        tail = 0;
    }

    public ArrayQueue() {
        this(16);
    }

    public int size() {
        return tail - front;
    }

    private int capacity() {
        return elementData.length;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void enqueue(E e) {
        if (tail == elementData.length) {
            int newSize;
            if (size() != 0) {
                newSize = front > capacity() / 2 ? capacity() : capacity() * 2;
            } else {
                newSize = 1;
            }
            resize(newSize);
            tail = size();
            front = 0;
        }
        elementData[tail++] = e;
    }

    public E dequeue() {
        if (isEmpty()) throw new IllegalArgumentException();
        E element = elementData[front++];
        if (size() < capacity() / 4 && capacity() / 2 > 0) {
            resize(capacity() / 2);
            tail = size();
            front = 0;
        }
        return element;
    }

    public E peek() {
        if (isEmpty()) throw new IllegalArgumentException();
        return elementData[front];
    }

    private void resize(int newSize) {
        E[] newElementData = (E[]) new Object[newSize];
        for (int i = front, j = 0; i < tail; i++, j++) {
            newElementData[j] = elementData[i];
        }
        elementData = newElementData;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        for (int f = front, t = tail; f < t; f++) {
            s.append(elementData[f]);
            if (f < t - 1) {
                s.append(", ");
            }
        }
        s.append("]");
        return s.toString();
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>(2);
        System.out.println("queue: " + queue);
        System.out.println("size: " + queue.size());
        System.out.println("capacity: " + queue.capacity());
        queue.enqueue(0);
        queue.enqueue(1);
        queue.enqueue(2);
        System.out.println("queue: " + queue);
        System.out.println("peek: " + queue.peek());
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);
        queue.enqueue(9);
        System.out.println("queue: " + queue);
        System.out.println("size: " + queue.size());
        System.out.println("capacity: " + queue.capacity());
        System.out.println("dequeue: " + queue.dequeue());
        System.out.println("queue: " + queue);
        for (int i = 0; i < 5; i++) {
            queue.dequeue();
        }
        System.out.println("queue: " + queue);
        queue.dequeue();
        System.out.println("queue: " + queue);
        System.out.println("size: " + queue.size());
        System.out.println("capacity: " + queue.capacity());
        System.out.println("peek: " + queue.peek());
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        System.out.println("queue: " + queue);
        System.out.println("size: " + queue.size());
        System.out.println("capacity: " + queue.capacity());
    }
}
