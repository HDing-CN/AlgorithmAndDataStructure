package LeetCode;

import Templates.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * Pattern: Preorder and inorder traversal of binary tree
 */

public class LeetCode0105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i ++) {
            inorderMap.put(inorder[i], i);
        }

        return buildTreeHelper(preorder, 0, preorder.length - 1, 0, inorder.length, inorderMap);
    }

    private TreeNode buildTreeHelper(int[] preorder, int preStart, int preEnd,
                                     int inStart, int inEnd,
                                     HashMap<Integer, Integer> inorderMap) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preStart]);
        int inorderRootIndex = inorderMap.get(preorder[preStart]);
        int numOfLeftTreeNodes = inorderRootIndex - inStart;

        root.left = buildTreeHelper(preorder, preStart + 1, preStart + numOfLeftTreeNodes,
                inStart, inorderRootIndex - 1,
                inorderMap);
        root.right = buildTreeHelper(preorder, preStart +  numOfLeftTreeNodes + 1, preEnd,
                inorderRootIndex + 1, inEnd,
                inorderMap);
        return root;
    }

    private void printTreeLevelByLevel(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(node == null) {
                System.out.print(" ");
            } else {
                System.out.print(node.val);
                queue.offer(node.left);
                queue.offer(node.right);
            }
            System.out.print(",");
        }
    }

    public static void main(String args[]) {
        int[] preorder = {1,2,4,5,3,6,7};
        int[] inorder = {4,2,5,1,6,3,7};
        LeetCode0105 leetCode0105 = new LeetCode0105();
        TreeNode root = leetCode0105.buildTree(preorder,  inorder);
        leetCode0105.printTreeLevelByLevel(root);
    }
}
