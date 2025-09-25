package com.mr.interview.mta


data class TreeNode(
    val `val`: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null
)

fun lowestCommonAncestorBinaryTree(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
    if (root == null || root == p || root == q) return root

    val left = lowestCommonAncestorBinaryTree(root.left, p, q)
    val right = lowestCommonAncestorBinaryTree(root.right, p, q)
    return when {
        left != null && right != null -> root
        left != null -> left
        else -> right
    }
}

/**
 * In a BST, for any node root:
 *  - If p.val < root.val and q.val < root.val, then LCA lies in the left subtree.
 *  - If p.val > root.val and q.val > root.val, then LCA lies in the right subtree.
 *  - Otherwise, root is the LCA.
 */
fun lowestCommonAncestorBstIterative(root: TreeNode?, p: TreeNode?, q: TreeNode?) : TreeNode? {
    if (root == null || root == p || root == q) return root
    if (p == null || q == null) return null

    var current = root

    while (current != null) {
        if (p.`val` < current.`val` && q.`val` < current.`val`) {
            current = current.left
        } else if (p.`val` > current.`val` && q.`val` > current.`val`) {
            current = current.right
        } else {
            return current
        }
    }
    return null
}
fun lowestCommonAncestorBstRecursive(root: TreeNode?, p: TreeNode?, q: TreeNode?) : TreeNode? {
    if (root == null || p == null || q == null) return null
    return when {
        p.`val` < root.`val` && q.`val` < root.`val` ->
            lowestCommonAncestorBstRecursive(root.left, p, q)
        p.`val` > root.`val` && q.`val` > root.`val` ->
            lowestCommonAncestorBstRecursive(root.right, p, q)
        else -> root
    }
}

// Helper function to find largest subtree sum recursively.
private fun findLargestSubtreeSumHelper(node: TreeNode?, answer: IntArray): Int {
    // return 0 to parent node
    if (node == null) return 0

    // Subtree sum rooted at current node
    val currentSum = node.`val` + findLargestSubtreeSumHelper(node.left, answer) + findLargestSubtreeSumHelper(node.right, answer)

    // Update answer if currSum is greater than curr ans
    answer[0] = maxOf(answer[0], currentSum)

    // Return current subtree sum to parent node.
    return currentSum
}

fun findLargestSubtreeSum(root: TreeNode?): Int {
    if (root == null) return 0

    // Variable to store maximum subtree sum
    val answer = IntArray(1)
    answer[0] = Int.MIN_VALUE

    // Call recursive function to find maximum subtree sum
    findLargestSubtreeSumHelper(root, answer)
    return answer[0]
}

/**
 * 1373. Maximum Sum BST in Binary Tree
 * HARD
 * Given a binary tree root, return the maximum sum of all keys of any sub-tree which is also a Binary Search Tree (BST).
 * Example 1:
 * Input: root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
 * Output: 20
 * Explanation: Maximum sum in a valid Binary search tree is obtained in root node with key equal to 3.
 * Input: root = [4,3,null,1,2]
 * Output: 2
 * Explanation: Maximum sum in a valid Binary search tree is obtained in a single root node with key equal to 2.
 */
fun maxSumBST(root: TreeNode?) : Int {
    // Post Order DFS to explore subtrees and determine:
    //  - Is the subtree a BST?
    //  - If so, compute its sum, and update the global maximum.

    var maxSum = 0

    data class Result(val isBst: Boolean, val min: Int, val max: Int, val sum: Int)

    fun dfs(node: TreeNode?): Result {
        if (node == null) return Result(true, Int.MAX_VALUE, Int.MIN_VALUE, 0)

        val leftResult = dfs(node.left)
        val rightResult = dfs(node.right)

        // Check for BST
        if (leftResult.isBst && rightResult.isBst && node.`val` > leftResult.max && node.`val` < rightResult.min) {
            val sum = node.`val` + leftResult.sum + rightResult.sum
            maxSum = maxOf(maxSum, sum)
            return Result(true, minOf(leftResult.min, node.`val`), maxOf(rightResult.max, node.`val`), sum)
        }

        return Result(false, 0,0, 0)
    }

    dfs(root)
    return maxSum
}



fun main() {
    testLowestCommonAncestorBinaryTree()
    testLowestCommonAncestorBst()

    testFindLargestSubtreeSum()
    testMaxSumBst()
}

fun testMaxSumBst() {
    val root = TreeNode(1)
    root.left = TreeNode(4)
    root.right = TreeNode(3)
    root.left?.left = TreeNode(2)
    root.left?.right = TreeNode(4)
    root.right?.left = TreeNode(2)
    root.right?.right = TreeNode(5)
    root.right?.right?.left = TreeNode(4)
    root.right?.right?.right = TreeNode(6)

    /**
     *           1
     *         /   \
     *       4       3
     *      / \     / \
     *     2   4   2   5
     *                / \
     *               4   6
     */

    val result = maxSumBST(root)
    println("Maximum Sum BST in Tree: $result")

}

fun testFindLargestSubtreeSum() {
    // Representation of the given tree
    //          1
    //        /   \
    //      -2     3
    //      / \   / \
    //     4   5 -6  2
    val root = TreeNode(1)
    root.left = TreeNode(-2)
    root.right = TreeNode(3)
    root.left!!.left = TreeNode(4)
    root.left!!.right = TreeNode(5)
    root.right!!.left = TreeNode(-6)
    root.right!!.right = TreeNode(2)

    println("Find largest subtree sum: ${findLargestSubtreeSum(root)}")
}

fun testLowestCommonAncestorBst() {
    val root = TreeNode(6)
    val n2 = TreeNode(2)
    val n8 = TreeNode(8)
    val n0 = TreeNode(0)
    val n4 = TreeNode(4)
    val n7 = TreeNode(7)
    val n9 = TreeNode(9)

    root.left = n2
    root.right = n8
    n2.left = n0
    n2.right = n4
    n8.left = n7
    n8.right = n9

    val lca = lowestCommonAncestorBstIterative(root, n2, n8)
    println("LCA BST Iterative of 2 and 8 is: ${lca?.`val`}") // Output: 6

    val lcaRec = lowestCommonAncestorBstRecursive(root, n2, n8)
    println("LCA BST Recursive of 2 and 8 is: ${lcaRec?.`val`}") // Output: 6

}

fun testLowestCommonAncestorBinaryTree() {
    val root = TreeNode(3)
    val node5 = TreeNode(5)
    val node1 = TreeNode(1)
    val node6 = TreeNode(6)
    val node2 = TreeNode(2)
    val node0 = TreeNode(0)
    val node8 = TreeNode(8)

    root.left = node5
    root.right = node1
    node5.left = node6
    node5.right = node2
    node1.left = node0
    node1.right = node8

    val lca = lowestCommonAncestorBinaryTree(root, node5, node1)
    println("LCA of 5 and 1 is: ${lca?.`val`}")  // Output: 3
}
