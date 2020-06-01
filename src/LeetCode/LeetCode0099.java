package LeetCode;

import Templates.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Pattern: Inorder traversal of binary tree
 * https://leetcode.com/problems/recover-binary-search-tree/solution/
 */

public abstract class LeetCode0099 {
    public abstract void recoverTree(TreeNode root);
}

class LeetCode0099_BasicSolution extends LeetCode0099 {
    @Override
    public void recoverTree(TreeNode root) {
        List<Integer> inorderArray = new ArrayList<>();
        inorderTraversal(root, inorderArray);
        int[] unsortedPair = findUnsortedPair(inorderArray);
        recoverHelper(root, 2, unsortedPair);
    }

    public void recoverHelper(TreeNode root, int counter, int[] unsortedPair) {
        if (root != null) {
            if (root.val == unsortedPair[0]) {
                root.val = unsortedPair[1];
                counter -= 1;
            } else if (root.val == unsortedPair[1]) {
                root.val = unsortedPair[0];
                counter -= 1;
            }

            if (counter > 0) {
                recoverHelper(root.left, counter, unsortedPair);
                recoverHelper(root.right, counter, unsortedPair);
            }
        }
    }

    public void inorderTraversal(TreeNode root, List<Integer> inorderArray) {
        if (root == null) {
            return ;
        }
        inorderTraversal(root.left, inorderArray);
        inorderArray.add(root.val);
        inorderTraversal(root.right, inorderArray);
    }

    public int[] findUnsortedPair(List<Integer> inorderArray) {
        int x = -1, y = -1;
        for (int i = 0; i < inorderArray.size() - 1; i ++) {
            if (inorderArray.get(i) > inorderArray.get(i + 1)) {
                if (x == -1) {
                    x = inorderArray.get(i);
                }
                y = inorderArray.get(i + 1);
            }
        }
        return new int[]{x, y};
    }

    public static void main(String args[]) {
        LeetCode0099_BasicSolution leetCode0099 = new LeetCode0099_BasicSolution();
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3);
        root.right = new TreeNode(1);

        List<Integer> inorderArray = new ArrayList<>();
        leetCode0099.inorderTraversal(root, inorderArray);
        System.out.println(inorderArray.toString());

        leetCode0099.recoverTree(root);

        inorderArray.clear();
        leetCode0099.inorderTraversal(root, inorderArray);
        System.out.println(inorderArray.toString());
    }
}

class LeetCode0099_IterativeSolution extends LeetCode0099 {
    @Override
    public void recoverTree(TreeNode root) {
        TreeNode x = null, y = null, prev = null;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.removeLast();
            if (prev != null && prev.val > root.val) {
                if (x == null) {
                    x = prev;
                }
                y = root;
            }
            prev = root;
            root = root.right;
        }

        int temp = x.val;
        x.val = y.val;
        y.val = temp;
    }

    public static void main(String args[]) {
        LeetCode0099_IterativeSolution leetCode0099_iterativeSolution = new LeetCode0099_IterativeSolution();
        LeetCode0099_BasicSolution leetCode0099_basicSolution = new LeetCode0099_BasicSolution();

        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3);
        root.right = new TreeNode(1);

        List<Integer> inorderArray = new ArrayList<>();
        leetCode0099_basicSolution.inorderTraversal(root, inorderArray);
        System.out.println(inorderArray.toString());

        leetCode0099_iterativeSolution.recoverTree(root);

        inorderArray = new ArrayList<>();
        leetCode0099_basicSolution.inorderTraversal(root, inorderArray);
        System.out.println(inorderArray.toString());
    }
}