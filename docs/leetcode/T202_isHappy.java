package math;

import java.util.HashSet;
import java.util.Set;

public class T202_isHappy {
    /*
    遇到这种题，没思路，就先列一下，多算几个，尝试找规律
    试想：为什么到不了？有循环肯定到不了


    第一种情况：
    例如:n=19
    -> 82 -> 64+4=68 -> 36+64=100 -> 1

    第二种情况：
    n=2
    -> 4 -> 16 -> 37 -> 58 -> 89 -> 145 -> 42 -> 20 -> 4
    发生了循环，也就是说，每次产生的数字都在这个循环中，永远不可以到达1

    设想第三种：
    会不会无限变大？到达一个无穷大？？？
    999 -> 243
    9999 -> 324
    99999 -> 405
    ……
    也就是说，三位数之上的，经过每位平方的变化后，位数一定会减小，
    所以，如果不是快乐数，也不会越来越大

    ======》 故：只有上两种情况，解题重点是：每位平方会不会产生环
     */


    /*
    第一种思路：
    用set来判定重复，若产生的数组已经在set中，则不是快乐数

    注：set的效率很低，所以能用别的方法，尽量不用hashset，能用hashset，就不要遍历
     */
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (n != 1 && !set.contains(n)){
            set.add(n);
            n = getNext(n);
        }
        /*
        退出循环有两种情况，1.n=1,2、有环
        所以，返回的时候需要判断一下是那种情况导致跳出循环
         */
        return n == 1;
    }


    /*
    思路2：
    类似链表有没有环的问题，用快慢指针来解决它

    只是反复取下一个数，代替链表下一个操作
     */
    public boolean isHappy_1(int n) {
        int slow = getNext(n);
        int fast = getNext(slow);
        while (fast != 1 && slow != fast) {
            slow = getNext(slow);
            /*
            注意：fast不是比slow快一步，而是每次都要向前找两个数
             */
            fast = getNext(getNext(fast));
        }
        return fast == 1;
    }

    private int getNext (int n) {
        int sum = 0;
        while (n != 0) {
            int temp = n % 10;
            sum += (temp * temp);
            n /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new T202_isHappy().isHappy_1(2));
    }
}
