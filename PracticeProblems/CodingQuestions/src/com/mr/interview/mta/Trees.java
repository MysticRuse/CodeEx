package com.mr.interview.mta;


import java.util.*;

public class Trees {

    static class TreeNode {
        final Integer value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.value = val;
            this.left = null;
            this.right = null;
        }
    }
    /////////////////////////////////////////////////////////////////////////////

    /**
     * Inserting node into a binary search tree
     */
    public TreeNode insert(TreeNode root, int data) {
        TreeNode iNode =  new TreeNode(data);
        if (root == null) {
            root = iNode;
        } else if (data < root.value) {
            root.left = insert(root.left, data);
        } else {
            root.right = insert(root.right, data);
        }

        return root;
    }

    /**
     * Height of a binary Tree
     */
    public Integer binaryTreeHeight(TreeNode root) {
        // Write your code here.
        if (root == null) {
            return 0;
        }
        return  1 + Math.max(binaryTreeHeight(root.left), binaryTreeHeight(root.right)) ;
    }
    /////////////////////////////////////////////////////////////////////////////

    /**
     * Is Symmetric Tree
     *
     * Given a binary tree, check whether it is a mirror of itself i.e. symmetric around its centre.
     * left node value == right node value && left.left mirror of right.right  and left.right is mirror or right.left
     *
     */
    public boolean isSymmetric(final TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetricHelper(root.left, root.right);
    }

    private boolean isSymmetricHelper(TreeNode t1, TreeNode t2) {
        // If both trees null, symmetric
        if (t1 == null && t2 == null) {
            return true;
        }
        // If one of them null, not symmetric
        if (t1 == null || t2 == null) {
            return false;
        }
        // If values are not equal, not symmetric
        if (!t1.value.equals(t2.value)) {
            return false;
        }

        // Children should be symmetric
        return (isSymmetricHelper(t1.left, t2.right) && isSymmetricHelper(t1.right, t2.left));
    }
    ////////////////////////////////////////////////////////////////////////////

    /**
     * Binary Tree Diameter
     * Given the root of a binary tree, return the length of the diameter of the tree.
     * The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
     * The length of a path between two nodes is represented by the number of edges between them.
     */
    static int diameter_binary_tree;
    public int binaryTreeDiameter(TreeNode root) {
        diameter_binary_tree = 0;
        longestPath(root);
        return diameter_binary_tree;
    }

    private int longestPath(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // Recursively find longest path for left subtree and right subtree
        final int leftPath = longestPath(node.left);
        final int rightPath = longestPath(node.right);

        // Add the paths and save if greater than diameter
        diameter_binary_tree = Math.max(diameter_binary_tree, leftPath + rightPath);

        // return the max of left and right path and add 1 for the connection of node to parent
        return Math.max(leftPath, rightPath) + 1;
    }

    /// ////////////////////////////////
    /**
     * Get left side view
     */
    List<Integer> getLeftSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()) {
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                TreeNode node = q.pop();
                if (i == 0) {
                    result.add(node.value);
                }
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
        }

        return result;
    }

    /////////////////////////////////////////////////////////////////////////////
    /**
     * Find the number of unival subtrees
     * Given a binary tree, find the number of unival subtrees.
     * An unival (single value) tree is a tree that has the same value in every node.
     *
     * Binary tree rooted in node X is unival if the following is true for both left and right children of X:
     *  - 1.either the child is NULL or
     *  - or
     *  - the child is root of a unival tree AND child's node value is same as X's node value
     *  Kind of post order traversal.
     */
    static int univalTreeCount = 0;
    static boolean isUnivalTreeRecursiveHelper(final TreeNode node) {
        if (node == null) {
            return true;
        }

        final boolean isLeftUnival = isUnivalTreeRecursiveHelper(node.left);
        final boolean isRightUnival= isUnivalTreeRecursiveHelper(node.right);

        if ((node.left == null
                || (isLeftUnival && node.value.equals(node.left.value))
                && (node.right == null || (isRightUnival && node.value.equals(node.right.value))))) {
            univalTreeCount++;
            return true;
        }
        return false;
    }

    public static int findSingleValueTrees(final TreeNode root) {
        isUnivalTreeRecursiveHelper(root);
        return univalTreeCount;
    }

    /////////////////////////////////////////////////////////////////////////////
    /**
     * Given a binary tree, list the node values level by level from left to right.
     * Tree: [0, 1, 2, 3, null, 4, null]
     *          0
     *      1 /  \2
     *     3/   4/
     * Output: [[0], [1, 2], [3, 4]]
     * @param root the root node of the tress
     * @return each level nodes in a list.
     */
    public List<List<Integer>> levelOrderTraversal(final TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) {
            return results;
        }

        final Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            // Find the size of the queue:
            final int qSize = q.size();
            final List<Integer> levelResult = new ArrayList<>();

            for (int i = 0; i<qSize; i++) {
                final TreeNode node = q.poll();
                if (node == null) {
                    continue;
                }
                levelResult.add(node.value);

                // Add the children to the queue to traverse BFS.
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
            results.add(levelResult);
        }
        return results;
    }

    public List<List<Integer>> reverseLevelOrderTraversal(final TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        final Stack<List<Integer>> resultStack = new Stack<>();
        final Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            // Get size of queue and create result
            final int size = q.size();
            List<Integer> levelResult = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                final TreeNode node = q.poll();
                if (node != null) {
                    levelResult.add(node.value);

                    if (node.left != null) {
                        q.add(node.left);
                    }
                    if (node.right != null) {
                        q.add(node.right);
                    }
                }
            }
            resultStack.add(levelResult);
        }

        while (!resultStack.isEmpty()) {
            results.add(resultStack.pop());
        }
        return results;
    }

    ///////////////////////////////////////////////////////////////////////////////
    /**
     * Given a binary tree, check if it is a binary search tree (BST). A valid BST does not have to be complete or balanced.
     * Consider this definition of a BST:
     * All nodes values of left subtree are less than or equal to parent node value.
     * All nodes values of right subtree are greater than or equal to parent node value.
     * Both left subtree and right subtree must be BSTs.
     * NULL tree is a BST.
     * Single node trees (including leaf nodes of any tree) are BSTs.
     * @param root the tree root
     * @return tru if binary search tree else false
     */
    public boolean isBST(final TreeNode root) {
        if (root == null) {
            return true;
        }
        return isBst(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBst(final TreeNode node, final int min, final int max) {
        if (node == null) {
            return true;
        }
        if (node.value < min|| node.value > max) {
            return false;
        }
        return (isBst(node.left, min, node.value)
                && isBst(node.right, node.value, max));
    }
    /////////////////////////////////////////////////////////

    public static void main(final String[] args) {

    }

}
