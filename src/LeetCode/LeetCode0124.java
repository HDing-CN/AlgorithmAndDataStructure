package LeetCode;

import Templates.TreeNode;

/**
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 * Pattern: The post-order traverse of binary tree
 */

public class LeetCode0124 {
    private int ans = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        traverse(root);
        return ans;
    }

    private int traverse(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftSum = Math.max(0, traverse(root.left));
        int rightSum = Math.max(0, traverse(root.right));
        ans = Math.max(ans, leftSum + rightSum + root.val);
        return Math.max(leftSum, rightSum) + root.val;
    }

    public static void main(String args[]) {
        LeetCode0124 leetCode0124 = new LeetCode0124();
        TreeNode root = new TreeNode(100);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20, new TreeNode(15), new TreeNode(7));
        System.out.println(leetCode0124.maxPathSum(root));
    }
}
