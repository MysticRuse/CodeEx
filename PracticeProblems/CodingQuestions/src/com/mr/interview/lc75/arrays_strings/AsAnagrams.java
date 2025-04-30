import java.util.*;

public class AsAnagrams {
    /**
     * 242. Valid Anagram
     * Solved
     * Easy
     *
     * Topics
     *
     * Companies
     * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "anagram", t = "nagaram"
     *
     * Output: true
     *
     * Example 2:
     *
     * Input: s = "rat", t = "car"
     *
     * Output: false
     *
     *
     *
     * Constraints:
     *
     * 1 <= s.length, t.length <= 5 * 104
     * s and t consist of lowercase English letters.
     *
     *
     * Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
     * Answer: In that case, have to use a Hash Table and not a count array of size 26.
     */
    public boolean isAnagram(final String s, final String t) {

        if (s.length() != t.length()) {
            return false;
        }

        final int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
            counts[t.charAt(i) - 'a']--;
        }

        for (int count: counts) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * 49. Group Anagrams
     * Medium

     * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
     * Example 1:
     * Input: strs = ["eat","tea","tan","ate","nat","bat"]
     * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
     * Explanation:
     * There is no string in strs that can be rearranged to form "bat".
     * The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
     * The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.
     * Example 2:
     * Input: strs = [""]
     * Output: [[""]]
     * Example 3:
     * Input: strs = ["a"]
     * Output: [["a"]]
     * Constraints:
     * 1 <= strs.length <= 104
     * 0 <= strs[i].length <= 100
     * strs[i] consists of lowercase English letters.
     *
     * @param strs The list of strings
     * @return the list of anagrams.
     */
    public List<List<String>> groupAnagrams(List<String> strs) {
        Map<String, List<String>> result = new HashMap<>();

        for (String s : strs) {
            // Sort letters in each String
            char[] charArr = s.toCharArray();
            Arrays.sort(charArr);
            final String sortedWord = new String(charArr);

            // Add the sorted word in map as key and add its anagrams as its value - a list -
            result.putIfAbsent(sortedWord, new ArrayList<>());
            result.get(sortedWord).add(s);
        }

        return new ArrayList<>(result.values());
    }

    public static void main(final String[] args) {
        final AsAnagrams anagrams = new AsAnagrams();

        System.out.println("Is anagram: abcde, dcabe: " + anagrams.isAnagram("abcde", "dcabe"));
        System.out.println("Is anagram: abcde, edfge: " + anagrams.isAnagram("abcde", "edfge"));
    }
}
