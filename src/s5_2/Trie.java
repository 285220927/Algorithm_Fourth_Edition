/**
 * @FileName: Trie.java
 * @Author: zzc
 * @Date: 2020年11月17日 21:27:18
 * @Version V1.0.0
 */

package s5_2;

import java.util.*;

public class Trie {
    private Node root;

    private static class Node {
        public boolean isWord;
        public Map<Character, Node> next;

        public Node() {
            this(false);
        }

        public Node (boolean isWord) {
            this.isWord = isWord;
            this.next = new HashMap<>();
        }
    }

    public Trie() {
        root = new Node();
    }

    public void add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null)
                cur.next.put(c, new Node());
            cur = cur.next.get(c);
        }
        cur.isWord = true;
    }

    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    public Iterable<String> keysWithPrefix(String pre) {
        List<String> res = new ArrayList<>();
        Node cur = root;
        for (char c : pre.toCharArray()) {
            cur = cur.next.get(c);
            if (cur == null)
                break;
        }
        if (cur != null)
            keysWithPrefix(cur, pre, res);
        return res;
    }

    private void keysWithPrefix(Node node, String pre, List<String> res) {
        if (node == null)
            return;
        if (node.isWord)
            res.add(pre);
        for (Map.Entry<Character, Node> entry : node.next.entrySet())
            keysWithPrefix(entry.getValue(), pre + entry.getKey(), res);
    }

    public Iterable<String> match(String word) {
        // 通配符匹配
        List<String> res = new ArrayList<>();
        match(root, word, "", res);
        return res;
    }

    private void match(Node node, String word, String pre, List<String> res) {
        if (node == null)
            return;
        if (word.length() == pre.length() && node.isWord)
            res.add(pre);
        if (word.length() == pre.length())
            return;
        char next = word.charAt(pre.length());
        for (char c : node.next.keySet())
            if (next == '.' || next == c)
                match(node.next.get(c), word, pre + c, res);
    }

    public void remove(String word) {
        root = remove(root, word, 0);
    }

    private Node remove(Node node, String word, int index) {
        if (node == null)
            return null;
        if (index == word.length())
            node.isWord = false;
        else {
            char c = word.charAt(index);
            node.next.put(c, remove(node.next.get(c), word, index + 1));
        }
        if (!node.isWord)
            return node;
        for (char c : node.next.keySet())
            if (node.next.get(c) != null)
                return node;
        return null;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        String[] arr = {"sea", "sells", "she", "shells"};
//        String[] arr = {"aa", "ab"};
        for (String s : arr)
            trie.add(s);

        System.out.println(trie.keys());
        trie.remove("shells");
        System.out.println(trie.keys());
    }
}
