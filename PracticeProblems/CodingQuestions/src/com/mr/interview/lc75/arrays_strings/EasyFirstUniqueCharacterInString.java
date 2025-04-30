import java.util.HashMap;

public class EasyFirstUniqueCharacterInString {

    /**
     * 387. First Unique Character in a String
     * Easy

     * Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.
     * Example 1:
     * Input: s = "leetcode"
     * Output: 0
     * Explanation: The character 'l' at index 0 is the first character that does not occur at any other index.
     *
     * Example 2:
     * Input: s = "loveleetcode"
     * Output: 2
     * Example 3:
     * Input: s = "aabb"
     * Output: -1
     *
     * Constraints:
     * 1 <= s.length <= 105
     * s consists of only lowercase English letters.
     */
    public int firstUniqueChar(final String s) {
        if (s == null || s.isEmpty()) {
            return -1;
        }

        final int len = s.length();

        // Create a hashmap to store the count of each letter in the String s
        final HashMap<Character, Integer> countMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            final char c = s.charAt(i);
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }

        // Now find the index of the first char that is not repeated
        for (int i = 0; i< len; i++) {
            if (countMap.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String... args) {
        EasyFirstUniqueCharacterInString myClass = new EasyFirstUniqueCharacterInString();
        String testStr = "leetcode";
        System.out.println("First unique character in string: " + testStr + " is in index: " + myClass.firstUniqueChar(testStr));

        testStr = "leetcode love";
        System.out.println("First unique character in string: " + testStr + " is in index: " + myClass.firstUniqueChar(testStr));

    }
}
