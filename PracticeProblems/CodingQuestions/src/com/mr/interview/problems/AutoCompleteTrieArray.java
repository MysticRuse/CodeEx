package com.mr.interview.problems;

import java.util.ArrayList;
import java.util.List;

/**
 * Autocomplete is a feature that search box returns the suggestions based on what you have typed. Autocomplete with
 * trie provides an implementation of auto-complete by using data structure trie.
 *
 * A trie is a tree-like data structure in which every node stores a character. After building the trie, strings or
 * substrings can be retrieved by traversing down a path of the trie. First we define the TrieNode, which includes data,
 * children and isEnd (to mark whether this node is the last node of a word).
 *
 * Children can be implemented with Array,
 * LinkedList, and HashMap. Each implementation has its pros and cons.
 *
 * To implement autocomplete, we start from root, navigate to the end of prefix.
 * From there, we call the recursion function method to find all the nodes branched from the last node of prefix.
 * This method is the same as pre-order of the tree from the root. Therefore the complexity is O(n) as traversal of a tree.
 */
public class AutoCompleteTrieArray {
    //don't use 26 if there is space or any other special characters
    static final int NUM_OF_CHARS = 128;

    private static class TrieNode {
        final char data;
        final TrieNode[] children = new TrieNode[NUM_OF_CHARS];
        boolean isEnd = false;

        TrieNode(final char c) {
            this.data = c;
        }

    }

    private static class Trie {
        TrieNode root = new TrieNode(' ');

        //Inserts a word into the trie, Iteration
        //Time O(s), Space O(s), s is word length
        public void insert(final String word) {
            TrieNode node = this.root;
            for (final char ch: word.toCharArray()) {
                if (node.children[ch] == null) {
                    node.children[ch] = new TrieNode(ch);
                }
                node = node.children[ch];
            }
            // Set the end
            node.isEnd = true;
        }

        //Find the node with prefix's last char, then call helper to find all words using recursion
        //Time O(n), Space O(n), n is number of nodes included(prefix and branches)
        public List<String> autocomplete(final String prefix) {
            TrieNode node = this.root;
            List<String> res = new ArrayList<>();
            for (final char ch : prefix.toCharArray()) {
                node = node.children[ch];
                if (node == null) {
                    return new ArrayList<>();
                }
            }
            helper(node, res, prefix.substring(0, prefix.length()-1));
            return res;
        }

        private void helper(final TrieNode node, List<String> res, final String prefix) {
            if (node == null) {
                // Base condition
                return;
            }
            if (node.isEnd) {
                res.add(prefix + node.data);
            }

            for (TrieNode child : node.children) {
                helper(child, res, prefix+node.data);
            }
        }
    }

    public static void main(String[] args) {
        Trie t = new Trie();
        t.insert("amazon");
        t.insert("amazon prime");
        t.insert("amazing");
        t.insert("amazing spider man");
        t.insert("amazed");
        t.insert("apple");
        t.insert("anagram");

        System.out.println(t.autocomplete("amaz"));
    }
}
