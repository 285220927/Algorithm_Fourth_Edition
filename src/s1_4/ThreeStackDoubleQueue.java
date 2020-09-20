/**
 * @FileName: ThreeStackDoubleQueue.java
 * @Author: zzc
 * @Date: 2020年09月20日 11:06:23
 * @Version V1.0.0
 */

package s1_4;

import java.util.Stack;

@SuppressWarnings("WeakerAccess")
public class ThreeStackDoubleQueue {
    // P134 1.4.31
    // https://www.cnblogs.com/longjin2018/p/9854499.html
    private Stack<Integer> sLeft = new Stack<>();
    private Stack<Integer> sRight = new Stack<>();
    private Stack<Integer> temp = new Stack<>();
    private boolean tempLeft = true; // temp栈是左栈内容还是右栈内容

    public int size() {
        return sLeft.size() + sRight.size() + temp.size();
    }

    public void pushFront(int e) {
        sLeft.push(e);
    }

    public void pushBack(int e) {
        sRight.push(e);
    }

    public int popFront() {
        if (sLeft.size() == 0 && sRight.size() == 0 && temp.size() == 0) throw new IllegalArgumentException();

        if (!sLeft.isEmpty()) return sLeft.pop();
        else if (!temp.isEmpty() && !tempLeft) return temp.pop(); // 左栈无内容，中转栈有内容，且中转栈是右栈，则直接弹出
        else if (!temp.isEmpty()) { // 左栈无内容，中转栈有内容，且中转栈是左栈，从中转栈倒入到左栈中弹出
            // 把中转栈倒入到左栈
            while (!temp.isEmpty()) {
                sLeft.push(temp.pop());
            }
            return sLeft.pop();
        }
        else if (temp.isEmpty() && !sRight.isEmpty()) { // 左栈无内容，中转栈无内容，右栈有内容，把右栈倒入到中转栈中弹出
            while (!sRight.isEmpty()) {
                temp.push(sRight.pop());
            }
            tempLeft = false;
            return temp.pop();
        }
        throw new IllegalArgumentException();
    }

    public int popBack() {
        if (sLeft.size() == 0 && sRight.size() == 0 && temp.size() == 0) throw new IllegalArgumentException();

        if (!sRight.isEmpty()) return sRight.pop();
        else if (!temp.isEmpty() && tempLeft) return temp.pop();
        else if (!temp.isEmpty()) {
            while (!temp.isEmpty()) {
                sRight.push(temp.pop());
            }
            return sRight.pop();
        }
        else if (temp.isEmpty() && !sLeft.isEmpty()) {
            while (!sLeft.isEmpty()) {
                temp.push(sLeft.pop());
            }
            tempLeft = true;
            return temp.pop();
        }
        throw new IllegalArgumentException();
    }

    public static void main(String[] args) {
        ThreeStackDoubleQueue queue = new ThreeStackDoubleQueue();
        queue.pushFront(1);
        queue.pushFront(2);
        queue.pushFront(3);
        queue.pushFront(4);
        System.out.println("size: " + queue.size());
        System.out.println("pop front: " + queue.popFront());
        System.out.println("pop front: " + queue.popFront());
        System.out.println("pop back: " + queue.popBack());
        System.out.println("pop back: " + queue.popBack());
        System.out.println("size: " + queue.size());

        System.out.println("**********************************");

        queue.pushBack(1);
        queue.pushBack(2);
        queue.pushBack(3);
        queue.pushBack(4);
        System.out.println("size: " + queue.size());
        System.out.println("pop front: " + queue.popFront());
        System.out.println("pop front: " + queue.popFront());
        System.out.println("pop back: " + queue.popBack());
        System.out.println("pop back: " + queue.popBack());
        System.out.println("size: " + queue.size());
    }
}
