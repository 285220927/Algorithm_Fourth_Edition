/**
 * @FileName: AVLTree.java
 * @Author: zzc
 * @Date: 2020年10月06日 13:50:37
 * @Version V1.0.0
 */

package s3_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings({"rawtypes", "unchecked"})
public class AVLTree<K extends Comparable, V> {
    private Node root;

    private class Node {
        private final K key;
        private V value;
        private Node left;
        private Node right;
        private int height;

        private Node(K k, V v) {
            this.key = k;
            this.value = v;
            this.height = 1;
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    private int getHeight(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    private int getBalanceFactor(Node node) {
        // 树的平衡因子
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    private boolean isBstTree(Node node) {
        List<K> list = new ArrayList<>();
        isBstTree(node, list);
        for (int i = 1; i < list.size(); i++)
            if (list.get(i).compareTo(list.get(i - 1)) < 0)
                return false;
        return true;
    }

    private boolean isBalancedTree(Node node) {
        if (node == null)
            return true;
        if (Math.abs(getBalanceFactor(node)) > 1)
            return false;
        return isBalancedTree(node.left) && isBalancedTree(node.right);
    }

    private void isBstTree(Node node, List<K> list) {
        if (node == null)
            return;
        isBstTree(node.left, list);
        list.add(node.key);
        isBstTree(node.right, list);
    }

    private Node leftRotation(Node node) {
        // 左旋转
        Node x = node.right;
        Node y = x.left;

        x.left = node;
        node.right = y;

        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    private Node rightRotation(Node node) {
        // 右旋转
        Node x = node.left;
        Node y = x.right;

        x.right = node;
        node.left = y;

        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    public void put(K k, V v) {
        root = put(k, v, root);
    }

    private Node put(K k, V v, Node node) {
        if (node == null) {
            return new Node(k, v);
        }
        int cmp = k.compareTo(node.key);
        if (cmp < 0)
            node.left = put(k, v, node.left);
        else if (cmp > 0)
            node.right = put(k, v, node.right);
        else {
            node.value = v;
        }
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

        int balance = getBalanceFactor(node);
        if (balance > 1 && getBalanceFactor(node.left) >= 0)
            return rightRotation(node);
        if (balance < -1 && getBalanceFactor(node.right) <= 0)
            return leftRotation(node);
        if (balance > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotation(node.left);
            return rightRotation(node);
        }
        if (balance < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotation(node.right);
            return leftRotation(node);
        }
        return node;
    }

    public V get(K k) {
        if (isEmpty())
            throw new IllegalArgumentException();
        return get(k, root);
    }

    private V get(K k, Node node) {
        if (node == null)
            return null;
        int cmp = k.compareTo(node.key);
        if (cmp < 0)
            return get(k, node.left);
        else if (cmp > 0)
            return get(k, node.right);
        else
            return node.value;
    }

    public void remove(K k) {
        root = remove(k, root);
    }

    private Node remove(K k, Node node) {
        if (node == null)
            return null;
        int cmp = k.compareTo(node.key);
        Node retNode;
        if (cmp < 0) {
            node.left = remove(k, node.left);
            retNode = node;
        } else if (cmp > 0) {
            node.right = remove(k, node.right);
            retNode = node;
        } else {
            if (node.right == null) {
                Node l = node.left;
                node.left = null;
                retNode = l;
            } else if (node.left == null) {
                Node r = node.right;
                node.right = null;
                retNode = r;
            } else {
                Node t = node;
                node = min(node.right);
                node.right = removeMin(node.right);
                node.right = t.left;
                retNode = node;
            }
        }
        if (retNode == null)
            return null;

        retNode.height = Math.max(getHeight(retNode.left), getHeight(retNode.right)) + 1;

        int balance = getBalanceFactor(retNode);
        if (balance > 1 && getBalanceFactor(retNode.left) >= 0)
            return rightRotation(retNode);
        if (balance < -1 && getBalanceFactor(retNode.right) <= 0)
            return leftRotation(retNode);
        if (balance > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotation(retNode.left);
            return rightRotation(retNode);
        }
        if (balance < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotation(retNode.right);
            return leftRotation(retNode);
        }
        return retNode;
    }

    private Node min(Node node) {
        if (node.left == null)
            return node;
        return min(node.left);
    }

    private Node removeMin(Node node) {
        if (node == null)
            return null;
        if (node.left == null)
            return node.right;
        node.left = removeMin(node.left);
        return node;
    }

    public static void main(String[] args) {
        AVLTree<Integer, Integer> avlTree = new AVLTree<>();

        for (int i = 0; i < 1000; i++)
            avlTree.put(new Random().nextInt(), new Random().nextInt());
        avlTree.put(1, 1);
        avlTree.put(99999, 99999);
        System.out.println("是否是二分搜索数: " + avlTree.isBstTree(avlTree.root));
        System.out.println("是否是平衡二叉树: " + avlTree.isBalancedTree(avlTree.root));
        System.out.println(avlTree.get(1));
        System.out.println(avlTree.get(99999));
        avlTree.remove(1);
        avlTree.remove(99999);
        avlTree.remove(0);
        System.out.println(avlTree.get(1));
        System.out.println(avlTree.get(99999));
        System.out.println(avlTree.get(0));
        System.out.println("是否是二分搜索数: " + avlTree.isBstTree(avlTree.root));
        System.out.println("是否是平衡二叉树: " + avlTree.isBalancedTree(avlTree.root));
    }
}
