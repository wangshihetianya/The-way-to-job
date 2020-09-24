package tree;

import util.TreeNode;

public class T617_mergeTrees {
    /*
    题目：合并两个二叉树

    思路：同时遍历两个二叉树，前中后序都可以
    注意：不要断链！！！

     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        }
        TreeNode tree;
        int val = 0;

        if (t1 != null && t2 != null) {
            val = t1.val + t2.val;
            tree = new TreeNode(val);
            tree.left = mergeTrees(t1.left, t2.left);
            tree.right = mergeTrees(t1.right, t2.right);
        } else if (t2 != null) {
            val = t2.val;
            tree = new TreeNode(val);
            tree.left = mergeTrees(null, t2.left);
            tree.right = mergeTrees(null, t2.right);
        } else {
            val = t1.val;
            tree = new TreeNode(val);
            tree.left = mergeTrees(t1.left, null);
            tree.right = mergeTrees(t1.right, null);
        }
        return tree;
    }

    public TreeNode mergeTrees_1(TreeNode t1, TreeNode t2) {
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        t1.val += t2.val;
        t1.left = mergeTrees_1(t1.left, t2.left);
        t1.right = mergeTrees_1(t1.right, t2.right);
        return t1;
    }
}
