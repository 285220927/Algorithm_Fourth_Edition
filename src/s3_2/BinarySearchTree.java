/**
 * @FileName: BinarySearchTree.java
 * @Author: zzc
 * @Date: 2020年10月05日 10:18:47
 * @Version V1.0.0
 */

package s3_2;

public class BinarySearchTree<K extends Comparable<K>, V> {
    // 二叉查找树
    private Node root;

    private class Node {
        private final K key;
        private V value;
        private Node left;
        private Node right;
        private int N;

        private Node(K k, V v, int N) {
            this.key = k;
            this.value = v;
            this.N = N;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null)
            return 0;
        return node.N;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public V get(K k) {
        if (k == null)
            throw new IllegalArgumentException();
        return get(k, root);
    }

    private V get(K k, Node node) {
        if (node == null)
            return null;
        int cmp = k.compareTo(node.key);
        if (cmp == 0)
            return node.value;
        else if (cmp < 0)
            return get(k, node.left);
        else
            return get(k, node.right);
    }

    public K select(int index) {
        // 找出索引为index的键 P258
        if (isEmpty())
            throw new IllegalArgumentException();
        Node node = select(index, root);
        if (node == null)
            return null;
        return node.key;
    }

    private Node select(int index, Node node) {
        if (node == null)
            return null;
        int l = size(node.left);
        if (index < l)
            return select(index, node.left);
        else if (index > l)
            return select(index - l - 1, node.right);
        else
            return node;
    }

    public int find(K k) {
        if (k == null)
            throw new IllegalArgumentException();
        return find(k, root);
    }

    public int find(K k, Node node) {
        if (node == null)
            return 0;
        int cmp = k.compareTo(node.key);
        if (cmp < 0)
            return find(k, node.left);
        else if (cmp > 0)
            return 1 + size(node.left) + find(k, node.right);
        else
            return size(node.left);
    }

    public void put(K k, V v) {
        root = put(k, v, root);
    }

    private Node put(K k, V v, Node node) {
        if (node == null)
            return new Node(k, v, 1);
        int cmp = k.compareTo(node.key);
        if (cmp == 0)
            node.value = v;
        else if (cmp < 0)
            node.left = put(k, v, node.left);
        else
            node.right = put(k, v, node.right);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    public K min() {
        if (isEmpty())
            throw new IllegalArgumentException();
        Node node = root;
        while (node.left != null)
            node = node.left;
        return node.key;
    }

    public K max() {
        if (isEmpty())
            throw new IllegalArgumentException();
        Node node = root;
        while (node.right != null)
            node = node.right;
        return node.key;
    }

    public void deleteMin() {
        if (isEmpty())
            throw new IllegalArgumentException();
        root = deleteMin(root);
    }

    private Node deleteMin(Node node) {
        if (node.left == null)
            return node.right;
        node.left = deleteMin(node.left);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    public void delete(K k) {
        if (isEmpty() || k == null)
            throw new IllegalArgumentException();
        root = delete(k, root);
    }

    private Node delete(K k, Node node) {
        if (node == null)
            return null;
        int cmp = k.compareTo(node.key);
        if (cmp < 0)
            node.left = delete(k, node.left);
        else if (cmp > 0)
            node.right = delete(k, node.right);
        else {
            if (node.left == null) // 只有右子树
                return node.right;
            if (node.right == null) // 只有左子树
                return node.left;
            else { // 左子树和右子树都有
                Node t = node;
                node = min(node.right);
                node.right = deleteMin(node.right);
                node.left = t.left;
            }
        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    private Node min(Node node) {
        if (node.left == null)
            return node;
        return min(node.left);
    }

    public static void main(String[] args) {
        /**
         *                Y
         *   A
         *      B
         *         C
         *            D
         */
        BinarySearchTree<Character, Integer> bst = new BinarySearchTree<>();
        bst.put('Y', 25);
        bst.put('A', 1);
        bst.put('B', 2);
        bst.put('C', 3);
        System.out.println(bst.get('A'));
        System.out.println(bst.get('B'));
        System.out.println(bst.get('C'));
        bst.put('C', 30);
        bst.put('D', 4);
        System.out.println(bst.get('C'));
        System.out.println(bst.size());
        System.out.println(bst.get('Z'));
        System.out.println("最小键: " + bst.min());
        System.out.println("最大键: " + bst.max());
        System.out.println("第1个索引键: " + bst.select(1));
        System.out.println("第4个索引键: " + bst.select(4));
        System.out.println("第100个索引键: " + bst.select(100));
        System.out.println("A的索引为: " + bst.find('A'));
        System.out.println("C的索引为: " + bst.find('C'));
        System.out.println("Y的索引为: " + bst.find('Y'));
        bst.deleteMin();
        System.out.println("删除最小后A的值: " + bst.get('A'));
        System.out.println("删除最小后A的值: " + bst.get('B'));
        bst.delete('C');
        System.out.println(bst.get('C'));
        System.out.println(bst.get('B'));
        System.out.println(bst.get('D'));
        System.out.println(bst.get('Y'));
    }
}
