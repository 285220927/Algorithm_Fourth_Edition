/**
 * @FileName: LinearProbingHash.java
 * @Author: zzc
 * @Date: 2020年10月11日 10:13:40
 * @Version V1.0.0
 */

package s3_2;

import java.lang.reflect.Array;

@SuppressWarnings("unchecked")
public class LinearProbingHash<K, V> {
    // 线性探测法
    private class Node {
        private final K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private int size;
    private Node[] data;

    public LinearProbingHash() {
        this(4);
    }

    public LinearProbingHash(int capacity) {
        this.capacity = capacity;
        data = (Node[]) Array.newInstance(Node.class, capacity);
    }

    public int size() {
        return size;
    }

    private int hash(K key) {
//        return key.hashCode() & 0xfffffff % capacity;
        return 1;
    }

    public void put(K key, V value) {
        if (size >= capacity / 2)
            resize(capacity * 2);
        int i = hash(key);
        while (data[i] != null) {
            if (data[i].key.equals(key)) {
                data[i].value = value;
                return;
            }
            i = (i + 1) % capacity;
        }
        data[i] = new Node(key, value);
        size++;
    }

    public V get(K key) {
        int i = hash(key);
        while (data[i] != null) {
            if (data[i].key.equals(key))
                return data[i].value;
            i = (i + 1) % capacity;
        }
        return null;
    }

    public void remove(K key) {
        // 不能直接将该节点置为null，会导致该节点所在键簇后面的元素无法被get()到
        int i = hash(key);
        while (data[i] != null) {
            if (data[i].key.equals(key)) {
                size--;
                data[i] = null;
                break;
            }
            i = (i + 1) % capacity;
        }
        i = (i + 1) % capacity;
        while (data[i] != null) {
            Node node = data[i];
            data[i] = null;
            put(node.key, node.value);
            size--; // put方法里有size++ 这里不能增加size，需要用size--中和
            i = (i + 1) % capacity;
        }

        if (size <= capacity / 8)
            resize(capacity / 2);
    }

    private synchronized void resize(int newCapacity) {
        // data的使用率在1/8 到 1/2之间较为合适 使用率过高会导致探测时间过长
        capacity = newCapacity;
        Node[] newData = (Node[]) Array.newInstance(Node.class, newCapacity);
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }

    public static void main(String[] args) {
        LinearProbingHash<Integer, Character> hash = new LinearProbingHash<>();
        hash.put(1, 'a');
        hash.put(2, 'b');
        hash.put(3, 'c');
        hash.put(4, 'd');
        hash.put(5, 'e');
        hash.put(6, 'f');
        hash.put(7, 'g');
        System.out.println(hash.get(1));
        System.out.println(hash.get(2));
        System.out.println(hash.get(3));
        System.out.println(hash.get(4));
        System.out.println(hash.get(5));
        System.out.println(hash.get(6));
        System.out.println(hash.get(7));
        System.out.println(hash.get(100));
        hash.remove(1);
        System.out.println(hash.get(1));
        System.out.println(hash.get(2));
        System.out.println(hash.get(3));
    }
}
