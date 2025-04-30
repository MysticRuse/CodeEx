class As1MergeStringsAlternately {
    /**
     * 1768. Merge Strings Alternately
     *
     * You are given two strings word1 and word2. Merge the strings by adding letters in alternating order, starting with word1. If a string is longer than the other, append the additional letters onto the end of the merged string. Return the merged string.
     * Example 1:
     * Input: word1 = "abc", word2 = "pqr"
     * Output: "apbqcr"
     * Explanation: The merged string will be merged as so:
     * word1:  a   b   c
     * word2:    p   q   r
     * merged: a p b q c r
     *
     * Example 2:
     * Input: word1 = "ab", word2 = "pqrs"
     * Output: "apbqrs"
     * Explanation: Notice that as word2 is longer, "rs" is appended to the end.
     * word1:  a   b
     * word2:    p   q   r   s
     * merged: a p b q   r   s
     *
     * Constraints:
     * 1 <= word1.length, word2.length <= 100
     * word1 and word2 consist of lowercase English letters.
     */

    public String mergeAlternatelyTwoPointer(final String word1, String word2) {
        final int w1Len = word1.length();
        final int w2Len = word2.length();

        StringBuilder result = new StringBuilder();

        int i = 0;
        int j = 0;

        while ( i < w1Len || j < w2Len) {
            if (i < w1Len) {
                result.append(word1.charAt(i++));
            }

            if ( j < w2Len) {
                result.append(word2.charAt(j++));
            }
        }

        return result.toString();
    }

    public String mergeAlternately1Pointer(final String word1, final String word2) {
        final int w1Len = word1.length();
        final int w2Len = word2.length();

        final StringBuilder result = new StringBuilder();

        final int maxLength = Math.max(w1Len, w2Len);

        for (int i = 0; i < maxLength; i++) {
            if (i < w1Len) {
                result.append(word1.charAt(i));
            }

            if (i < w2Len) {
                result.append(word2.charAt(i));
            }
        }
        return result.toString();

    }

    public static void main(String... args) {

        As1MergeStringsAlternately msa = new As1MergeStringsAlternately();
        System.out.println(msa.mergeAlternately1Pointer("abc", "pqr"));
        System.out.println(msa.mergeAlternately1Pointer("ab", "pqrs"));
        System.out.println(msa.mergeAlternately1Pointer("abcd", "pq"));
    }
}

