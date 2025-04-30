public class AsPalindrome {

    /**
     * 125. Valid Palindrome
     * Easy
     * Given a string s, return true if it is a palindrome, otherwise return false.
     *
     * A palindrome is a string that reads the same forward and backward. It is also case-insensitive and ignores all non-alphanumeric characters.
     * Example 1:
     * Input: s = "Was it a car or a cat I saw?"
     * Output: true
     * Explanation: After considering only alphanumerical characters we have "wasitacaroracatisaw", which is a palindrome.
     * Example 2:
     * Input: s = "tab a cat"
     * Output: false
     * Explanation: "tabacat" is not a palindrome.
     * Constraints:
     * 1 <= s.length <= 1000
     * s is made up of only printable ASCII characters.
     */
    public boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;

        while (l<r) {
            while (l<r && isNotAlphaNumeric(s.charAt(l))) {
                l++;
            }

            while( r > l && isNotAlphaNumeric(s.charAt(r))) {
                r--;
            }

            if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) {
                return false;
            }

            l++;
            r--;
        }
        return true;
    }

    private boolean isNotAlphaNumeric(char c) {
        return ((c < 'A' || c > 'Z') &&
                (c < 'a' || c > 'z') &&
                (c < '0' || c > '9'));
    }

    public static void main(final String[] args) {
        AsPalindrome pali = new AsPalindrome();

        String myString = "abccba";
        System.out.println("Is palindrome [" + myString + "]: " + pali.isPalindrome(myString));

        myString = "abcefg";
        System.out.println("Is palindrome [" + myString + "]: " + pali.isPalindrome(myString));
    }


}
