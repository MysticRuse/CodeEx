package com.mr.interview.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
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
public class AutoCompleteTrieHashMap {

    private static class TrieNode {
        final char data;
        final HashMap<Character, TrieNode> children = new HashMap<>();
        boolean isEnd = false;

        //Constructor, Time O(1), Space O(1)
        TrieNode(final char c) {
            this.data = c;
        }
    }

    static class Trie {
        TrieNode root = new TrieNode(' ');

        //Add a word to trie, Iteration, Time O(s), Space O(s), s is word length
        public void insert(final String word) {
            TrieNode node = this.root;

            for (char ch : word.toCharArray()) {
                if (!node.children.containsKey(ch)) {
                    node.children.put(ch, new TrieNode(ch));
                }
                node = node.children.get(ch);
            }
            // Set the end of the word
            node.isEnd = true;
        }

        //find the node with prefix's last char, then call helper to find all words using recursion
        //Recursion, Time O(n), Space O(n), n is number of nodes involved (include prefix and branches)
        public List<String> autocomplete(final String prefix) {
            TrieNode node = root;
            List<String> res = new ArrayList<String>();

            // Find the last node of the prefix
            for (char c : prefix.toCharArray()) {
                if (node.children.containsKey(c)) {
                    node = node.children.get(c);
                } else {
                    return res;
                }
            }
            helper(node, res, prefix.substring(0, prefix.length() - 1));
            return res;
        }

        //recursion helper, Time O(n), Space O(n), n is number of nodes in branches
        private void helper(final TrieNode node, List<String> res, String prefix) {
            if (node == null) {
                return;
            }
            if (node.isEnd) {
                res.add(prefix + node.data);
            }
            for (Character ch : node.children.keySet()) {
                helper(node.children.get(ch), res, prefix + node.data);
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

        System.out.println(t.autocomplete("amaz"));
    }
}
