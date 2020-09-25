package tree;

import util.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class T501_findMode {
    /*
    BST(二叉搜索树)==》有序数组
    Q：有序对解题，性能有什么帮助？

     */


    /*
    暴力法，把树遍历一遍，结果存在list中，
    遍历list统计频率，存在map中，
    遍历map，找出最高的频率
    再次遍历map，把最高频率对应的值放入res
    最后：如何高效实现 list -> array？？？

     */
    public int[] findMode(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        traverse(root, list);
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        for (int n: list) {
            if (map.containsKey(n)) {
                map.put(n, map.get(n) + 1);
            } else {
                map.put(n, 1);
            }
        }
        int max = 1;
        for (int n:map.keySet()) {
            if (map.get(n) > max) {
                max = map.get(n);
            }
        }
        for (int n: map.keySet()) {
            if (map.get(n) == max) {
                res.add(n);
            }
        }
        int[] resArr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            resArr[i] = res.get(i);
        }
        return resArr;
    }
    private void traverse (TreeNode t, List<Integer> list) {
        if (t == null)
            return;
        traverse(t.left, list);
        list.add(t.val);
        traverse(t.right, list);
    }


    /*
    高效版本：
    以中序遍历为模板。遍历一遍出结果
    current代表当前元素的值（保存的是之前的元素值）
    若当前元素==current，则count++，若当前元素！=current，则将current换成当前的元素值，
    统计新的元素个数。
    遍历的时候，用现有元素的count和maxcount比较，如果比maxcount大，就要清空结果集中的所有元素，
    并将当前值放入结果集中
     */
    List<Integer> mList = new ArrayList<>();//结果list
    int current = 0;//表示当前节点的值
    int count = 0;//表示当前节点的数量
    int maxCount = 0;//最大的重复数量

    public int[] findMode_fast(TreeNode root) {
        inOrderTraversal(root);
        int[] res = new int[mList.size()];
        //把集合list转化为数组
        for (int i = 0; i < mList.size(); i++) {
            res[i] = mList.get(i);
        }
        return res;
    }

    //递归方式——核心部分
    public void inOrderTraversal(TreeNode node) {
        //终止条件判断
        if (node == null)
            return;
        //遍历左子树
        inOrderTraversal(node.left);

        //下面是对当前节点的一些逻辑操作
        int nodeValue = node.val;
        if (nodeValue == current) {
            //如果节点值等于curent，count就加1
            count++;
        } else {
            //否则，就表示遇到了一个新的值，curent和count都要
            //重新赋值
            current = nodeValue;
            count = 1;
        }
        if (count == maxCount) {
            //如果count == maxCount，就把当前节点加入到集合中
            mList.add(nodeValue);
        } else if (count > maxCount) {
            //否则，当前节点的值重复量是最多的，直接把list清空，然后
            //把当前节点的值加入到集合中
            mList.clear();
            mList.add(nodeValue);
            maxCount = count;
        }

        //遍历右子树
        inOrderTraversal(node.right);
    }



}
