/**
 * @FileName: SeparateChainHash.java
 * @Author: zzc
 * @Date: 2020年10月10日 20:51:00
 * @Version V1.0.0
 */

package s3_2;

import java.lang.reflect.Array;

@SuppressWarnings("unchecked")
public class SeparateChainHash<K, V> {
    // 散列表-拉链法 当发生哈希冲突时，用链表来保存冲突的元素
    private class Linked {
        private K key;
        private V value;
        private Linked head;
        private Linked next;

        private Linked() {
        }

        private Linked(K k, V v) {
            this.key = k;
            this.value = v;
        }

        private void put(K key, V value) {
            if (head == null) {
                head = new Linked(key, value);
                return;
            }
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            Linked node = head;
            while (node.next != null) {
                if (node.next.key.equals(key)) {
                    node.next.value = value;
                    return;
                }
                else
                    node = node.next;
            }
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
            node.next = new Linked(key, value);
        }

        private V get(K key) {
            if (head == null)
                return null;
            Linked node = head;
            while (node != null) {
                if (node.key.equals(key))
                    return node.value;
                node = node.next;
            }
            return null;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Linked node = head;
            while (node != null) {
                sb.append("{").append(node.key).append(": ").append(node.value).append("}");
                if (node.next != null)
                    sb.append(" -> ");
                node = node.next;
            }
            return sb.toString();
        }
    }

    private final int capacity;
    private final Linked[] links;

    public SeparateChainHash() {
        this(997);
    }

    public SeparateChainHash(int capacity) {
        this.capacity = capacity;
        links = (Linked[]) Array.newInstance(Linked.class, capacity);
        for (int i = 0; i < capacity; i++) {
            links[i] = new Linked();
        }
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    public V get(K key) {
        return links[hash(key)].get(key);
    }

    public void put(K key, V value) {
        links[hash(key)].put(key, value);
    }

    public static void main(String[] args) {
        SeparateChainHash<String, Integer> hash = new SeparateChainHash<>();
        hash.put("a", 1);
        hash.put("b", 2);
        hash.put("c", 3);
        hash.put("c", 30);
        System.out.println(hash.get("a"));
        System.out.println(hash.get("b"));
        System.out.println(hash.get("c"));
    }
}
