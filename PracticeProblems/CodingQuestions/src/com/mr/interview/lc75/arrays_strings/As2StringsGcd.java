public class As2StringsGcd {

    /**
     * For two strings s and t, we say "t divides s" if and only if s = t + t + t + ... + t + t (i.e., t is concatenated with itself one or more times).
     *
     * Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.
     * Example 1:
     * Input: str1 = "ABCABC", str2 = "ABC"
     * Output: "ABC"
     *
     * Example 2:
     * Input: str1 = "ABABAB", str2 = "ABAB"
     * Output: "AB"
     * Example 3:
     *
     * Input: str1 = "LEET", str2 = "CODE"
     * Output: ""
     *
     *
     * Constraints:
     *
     * 1 <= str1.length, str2.length <= 1000
     * str1 and str2 consist of English uppercase letters.
     */

    public String gcdOfStrings(String str1, String str2) {
        if (!(str1+str2).equals(str2+str1)) {
            return "";
        }

        int gcd = gcd_iterative(str1.length(), str2.length());

        return str2.substring(0, gcd);
    }

    public int gcd_recursive(int a, int b) {
        return (b==0 ? a : gcd_recursive(b, a%b));
    }

    public int gcd_iterative(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a%b;
            a = temp;
        }

        return a;
    }

    public static void main(String... args) {
        final As2StringsGcd as2StringsGcd = new As2StringsGcd();
        System.out.println(as2StringsGcd.gcdOfStrings("ABCABC", "ABC"));
        System.out.println(as2StringsGcd.gcdOfStrings("ABABAB", "ABAB"));
        System.out.println(as2StringsGcd.gcdOfStrings("LEET", "CODE"));
    }
}
