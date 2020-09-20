/**
 * @FileName: TwoStackQueue.java
 * @Author: zzc
 * @Date: 2020年09月20日 10:19:37
 * @Version V1.0.0
 */

package s1_4;

import java.util.Stack;

@SuppressWarnings({"unchecked", "WeakerAccess"})
public class TwoStackQueue {
    // P134 1.4.27
    private Stack<Integer> stack1 = new Stack<>(); // 模拟入队
    private Stack<Integer> stack2 = new Stack<>(); // 模拟出队

    public int size() {
        return stack1.size() + stack2.size();
    }

    public void enqueue(Integer e) {
        stack1.push(e);
    }

    public Integer dequeue() {
        if (stack1.isEmpty() && stack2.isEmpty()) throw new IllegalArgumentException();
        if (stack2.isEmpty()){
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    @Override
    public String toString() {
        String s = "";
        Stack<Integer> s1 = (Stack<Integer>) stack1.clone();
        Stack<Integer> s2 = (Stack<Integer>) stack2.clone();
        while (!s2.isEmpty()) {
            s = s.concat(String.valueOf(s2.pop()));
            if (s2.size() >= 1) {
                s = s.concat(" ");
            }
        }
        while (!s1.isEmpty()) {
            s = String.valueOf(s1.pop()).concat(s);
            if (s1.size() >= 1) {
                s = " ".concat(s);
            }
        }
        s = "[".concat(s).concat("]");
        return s;
    }

    public static void main(String[] args) {
        TwoStackQueue queue = new TwoStackQueue();
        System.out.println("size: " + queue.size());
        queue.enqueue(1);
        System.out.println("queue:" + queue);
        System.out.println("size: " + queue.size());
        for (int i = 2; i < 5; i++) {
            queue.enqueue(i);
        }
        System.out.println("queue:" + queue);
        System.out.println("size: " + queue.size());

        System.out.println("dequeue: " + queue.dequeue());
        System.out.println("dequeue: " + queue.dequeue());

        System.out.println("queue:" + queue);
        System.out.println("size: " + queue.size());

        System.out.println("dequeue: " + queue.dequeue());
        System.out.println("dequeue: " + queue.dequeue());

        System.out.println("queue:" + queue);
        System.out.println("size: " + queue.size());
    }
}
