/**
 * @FileName: DoubleLinkedList.java
 * @Author: zzc
 * @Date: 2020年09月14日 21:12:15
 * @Version V1.0.0
 */

package utils;

import java.util.Iterator;

@SuppressWarnings("all")
public class DoubleLinkedList<T> implements Iterable<T> {

    private Node head;
    private int size;

    public DoubleLinkedList() {
        this.size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {
        private Node node = head;

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public T next() {
            T val = node.item;
            node = node.next;
            return val;
        }
    }

    class Node {
        private T item;
        private Node next;
        private Node prev;

        Node(T item) {
            this.item = item;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null; // size() == 0;
    }

    public T append(T item) {
        Node node = new Node(item);
        if (head == null) {
            head = node;
        } else {
            Node cur = head;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = node;
            node.prev = cur;
        }
        size++;
        return item;
    }

    public T insert(int index, T item) {
        assert index >= 0 && index < size() + 1;

        if (index == size()) {
            append(item);
        } else {
            Node node = new Node(item);
            if (head == null) {
                // index == 0
                head = node;
            } else if (index == 0) {
                head.prev = node;
                node.next = head;
                head = node;
            } else {
                Node cur = head;
                int count = 1;
                while (count < index) {
                    cur = cur.next;
                    count++;
                }
                node.next = cur.next;
                cur.next.prev = node;
                cur.next = node;
                node.prev = cur;
            }
            size++;
        }
        return item;
    }

    public T remove(int index) {
        assert !isEmpty() && index >= 0 && index < size();

        T ret;
        if (index == 0) {
            ret = head.item;
            head = head.next;
        } else {
            Node cur = head;
            int count = 0;
            while (count < index) {
                count++;
                cur = cur.next;
            }
            ret = cur.item;
            if (cur.next != null) {
                cur.next.prev = cur.prev;
            }
            cur.prev.next = cur.next;
        }
        size--;
        return ret;
    }

    public int find(T item) {
        assert !isEmpty();
        int count = 0;
        Node cur = head;
        while (cur != null) {
            if (cur.item.equals(item)) {
                return count;
            }
            cur = cur.next;
            count++;
        }
        return -1;
    }

    public DoubleLinkedList reverse1() {
        // 头节点插入法
        // 定义一个新的双向链表
        DoubleLinkedList<T> list = new DoubleLinkedList<>();
        if (isEmpty()) return list;
        Node cur = head;
        while (cur != null) {
            list.insert(0, cur.item);
            cur = cur.next;
        }
        return list;
    }

    public void reverse2() {
        // 就地反转
        // https://www.cnblogs.com/moris5013/p/11630078.html
        if (head == null || head.next == null) return;
        Node prev = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.prev = prev;
            head.next = next;
            prev = head;
            head = next;
        }
        head = prev;
    }

    public void reverse3() {
        // 就地反转递归实现
        // 《Algorithms Fourth Edition》 P104
        // https://developer.51cto.com/art/202003/613490.htm
        if (head == null || head.next == null) return;

        reverse3(head);
    }

    public Node reverse3(Node node) {
        if (node == null || node.next == null) return node;
        Node recursive = reverse3(node.next);
        node.next.next = node;
        node.prev = node.next;
        node.next = null;
        return recursive;
    }

    public T max1() {
        if (head == null) return null;
        if (head.next == null) return head.item;
        if (!(head.item instanceof Comparable)) throw new IllegalArgumentException("the argument cannot be compared");

        T maxVal = head.item;
        Node cur = head.next;
        while (cur != null) {
            if (((Comparable) cur.item).compareTo(maxVal) > 0) {
                maxVal = cur.item;
            }
            cur = cur.next;
        }
        return maxVal;
    }

    public T max2() {
        // 递归实现
        if (head == null) return null;
        if (head.next == null) return head.item;
        if (!(head.item instanceof Comparable)) throw new IllegalArgumentException("the argument cannot be compared");

        return max2(head, head.item);
    }

    private T max2(Node node, T maxVal) {
        if (node == null) return maxVal;
        return max2(node.next, ((Comparable) node.item).compareTo(maxVal) > 0 ? node.item : maxVal);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (head == null) return "";
        Node cur = head;
        while (cur != null) {
            sb.append(cur.item);
            if (cur.next != null) {
                sb.append(" -> ");
            }
            cur = cur.next;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.append(1);
        System.out.println(list);
        list.append(3);
        list.append(5);
        list.append(7);
        list.append(9);
        list.append(11);
        System.out.println(list);
        System.out.println("size: " + list.size());
        list.insert(0, -1);
        list.insert(2, 21);
        list.insert(7, 23);
        list.insert(9, 25);
        System.out.println(list);
        list.remove(0);
        System.out.println(list);
        list.remove(5);
        list.remove(list.size() - 1);
        System.out.println(list);
        System.out.println("size: " + list.size());
        System.out.println("find 1: " + list.find(1));
        System.out.println("find 5: " + list.find(5));
        System.out.println("find 11: " + list.find(11));
        System.out.println("find 9: " + list.find(9));
        System.out.println(list);
        System.out.println("======================");
        list.insert(0, 888);
        System.out.println(list);
        System.out.println(list.max2());
        list.insert(3, 949);
        System.out.println(list);
        System.out.println(list.max2());
        list.append(999);
        System.out.println(list);
        System.out.println(list.max2());
        System.out.println("======================");
        System.out.println(list.reverse1());

        System.out.println("**************************************************");

        DoubleLinkedList<Integer> list2 = new DoubleLinkedList<>();
        list2.append(1);
        list2.append(2);
        list2.append(3);
        list2.reverse3();
        System.out.println(list2);
    }
}
