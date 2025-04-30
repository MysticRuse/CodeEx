package com.mr.interview.datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

//import sun.misc.Queue;

public class Strings {

    /**
     * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
     * What should we return when needle is an empty string?
     * For the purpose of this problem, we will return 0 when needle is an empty string.
     * This is consistent to C's strstr() and Java's indexOf().
     * @param hayStack
     * @param needle
     * @return
     */
    public static int strStr(final String hayStack, final String needle) {
        final int hLen = hayStack.length();
        final int nLen = needle.length();

        if (nLen == 0) {
            return 0;
        } else if (nLen > hLen) {
            return -1;
        } else if (nLen < hLen) {
            for (int i = 0; i <= hLen - nLen; i++) {
                int j = i + nLen;
                if (hayStack.substring(i, j).equalsIgnoreCase(needle)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int strStr2(final String hayStack, final String needle) {
        // O(h*n)
        // for O(h+m) -> KMP solution.
        if (needle == null || hayStack == null || needle.length() == 0 || needle.length() > hayStack.length()) {
            return 0;
        }
        final int hLen = hayStack.length();
        final int nLen = needle.length();
        for (int i = 0; i < hLen; i++) {
            if(i + nLen > hLen) {
                return -1;
            }
            int m = i;
            for (int j = 0; j < nLen; j++) {
                if (hayStack.charAt(m) == needle.charAt(j)) {
                    if (j == nLen - 1) {
                        return i;
                    }
                    m++;
                } else {
                    break;
                }
            }
        }
        return -1;
    }

    //////////////////////////////////////////////////////////
    // Run Length ENcoding
    // Given an input string, write a function that returns the Run Length Encoded string for the input string.
    // For example, if the input string is “wwwwaaadexxxxxx”, then the function should return “w4a3d1e1x6”
    //////////////////////////////////////////////////////////
    public static String getRunLengthEncodingFor(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }

        final int length = str.length();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {

            // Count occurence of current char
            int count = 1;

            while (i < length - 1 && (str.charAt(i) == str.charAt(i + 1))) {
                count++;
                i++;
            }

            sb.append(str.charAt(i));
            sb.append(count);
        }

        return sb.toString();
    }



    ///////////////////////////////////////////////////////
    // Atoi
    //The algorithm for myAtoi(string s) is as follows:
    //1. Read in and ignore any leading whitespace.
    //2. Check if the next character (if not already at the end of the string) is '-' or '+'. Read this character in if it is either. This determines if the final result is negative or positive respectively. Assume the result is positive if neither is present.
    //3. Read in next the characters until the next non-digit character or the end of the input is reached. The rest of the string is ignored.
    //4. Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32). If no digits were read, then the integer is 0. Change the sign as necessary (from step 2).
    //5. If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then clamp the integer so that it remains in the range. Specifically, integers less than -231 should be clamped to -231, and integers greater than 231 - 1 should be clamped to 231 - 1.
    //6. Return the integer as the final result.
    //Note:
    //Only the space character ' ' is considered a whitespace character.
    //Do not ignore any characters other than the leading whitespace or the rest of the string after the digits.
    ////////////////////////////////////////////////////////
    public static int myAtoi(final String s) {
        int size = s.length();
        int index = 0;
        int sign = 1;
        int result = 0;

        // Discard beginning white spaces if any
        while (index < size && s.charAt(index) == ' ') {
            index++;
        }

        // Account for the sign = +1 is positive, else -1
        if (index < size && s.charAt(index) == '+') {
            sign = 1;
            index++;
        } else if (index < size && s.charAt(index) == '-') {
            sign = -1;
            index++;
        }

        // Traverse next digits of input and stop if not a digit.
        while (index < size && Character.isDigit(s.charAt(index))) {
            int digit = s.charAt(index) - '0';

            //Check overflow and underflow conditions
            if ((result > Integer.MAX_VALUE/10)
                    || (result == Integer.MAX_VALUE/10 && digit > Integer.MAX_VALUE % 10 )) {

                // If overflow, return 2^31 -1, if underflow return -2 ^ 31.
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            // Append current digit to the result.
            result = result * 10 + digit;
            index++;
        }

        // A valid number has been formed without overflow or underflow.
        // Multiply sign and return.
        return result * sign;
    }

    ///////////////////////////////////////////////////////
    // Anagram
    ///////////////////////////////////////////////////////

    // Space complexity: O(1) if heapsort is used.
    // Time complexity: O(nlogn)
    public boolean isAnagramSorting(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }

    // Time complexity: O(n)
    // Space complexity:  O(!)

    public boolean isAnagramHashTable(String s, String t) {
        //To examine if t is a rearrangement of s, we can count occurrences of each letter in the
        // two strings and compare them. Since both s and t contain only letters from a−z, a simple
        // counter table of size 26 is suffice.
        // Do we need two counter tables for comparison? Actually no, because we could increment the
        // counter for each letter in s and decrement the counter for each letter in t, then check if the counter reaches back to zero.
        if (s.length() != t.length()) {
            return false;
        }
        int[] counter = new int[26];
        for (int i =0; i<s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }

        for (int count : counter) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAnagramQuickerHashTable(String s, String t) {
        //To examine if t is a rearrangement of s, we can count occurrences of each letter in the
        // two strings and compare them. Since both s and t contain only letters from a−z, a simple
        // counter table of size 26 is suffice.
        // Do we need two counter tables for comparison? Actually no, because we could increment the
        // counter for each letter in s and decrement the counter for each letter in t, then check if the counter reaches back to zero.
        if (s.length() != t.length()) {
            return false;
        }
        int[] counter = new int[26];
        for (int i =0; i<s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
        }

        for (int j = 0; j< t.length(); j++) {
            counter[t.charAt(j) - 'a']--;
            if (counter[t.charAt(j) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    ///////////////////////////////////////////////////////
    // Reverse String
    ///////////////////////////////////////////////////////
    private static String reverseString(final String str) {
        final int length = str.length();

        char[] charStr = str.toCharArray();

        int left = 0;
        int right = length - 1;
        while(left < right) {
            char temp = charStr[left];
            charStr[left++] = charStr[right];
            charStr[right--] = temp;
        }
        return charStr.toString();
    }

    public void helper(char[] s, int left, int right) {
        if (left >= right) {
            return;
        }
        char temp = s[left];
        s[left++] = s[right];
        s[right++] = temp;
        helper(s, left, right);
    }

    public void reverseStringRecurse(char[] s) {
        helper(s, 0, s.length -1);
    }

    ///////////////////////////////////////////////////////
    // Reverse int
    ///////////////////////////////////////////////////////
    public int reverseIntStringify(int x) {
        String reversed = new StringBuilder().append(Math.abs(x)).reverse().toString();
        try {
            return (x < 0 ? Integer.parseInt(reversed) * (-1) : Integer.parseInt(reversed));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public int reverseInt(int x) {
        //Time Complexity: O(log(x)) There are roughly log(10)(x) digits in x
        //Space complexity O(1)

        // Logic behin pop > 7 and pop <-8
        // maximum value for 32-bit is 2147483647 so when rev== INT_MAX/10 i.e rev= 214748364 and
        // in case pop is 8 next rev value will be 2147483648 (as rev *10+ pop) which will overflow
        // same is the case for INT_MIN as minimum value for 32-bit is -2147483648 i.e pop can't be less -8

        int rev = 0;
        while (x != 0) {
            // pop operation
            int pop = x%10;
            x = x/10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            // Push operation
            rev = rev*10 + pop;
        }
        return rev;
    }

    ////////////////////////////////////////////////////////
    // All longest strings
    ////////////////////////////////////////////////////////

    /**
     * Given an array of strings, return another array containing all of its longest strings.
     *
     * Example
     *
     * For inputArray = ["aba", "aa", "ad", "vcd", "aba"], the output should be
     * solution(inputArray) = ["aba", "vcd", "aba"].
     * @param inputArray the array of Strings
     */
    private String[] allLongestStrings(final String[] inputArray) {
        int maxLength = 0;
        final int size = inputArray.length;
        ArrayList<String> outList = new ArrayList<String>();
        for (int i = 0; i < size; i++) {
            int currLength = inputArray[i].length();
            if (currLength > maxLength) {
                maxLength = currLength;
                outList.clear();
                outList.add(inputArray[i]);
            } else if (currLength == maxLength) {
                outList.add(inputArray[i]);
            }
        }

        return outList.toArray(new String[outList.size()]);
    }

    /**
     * Given two strings, find the number of common characters between them.
     * Example
     * For s1 = "aabcc" and s2 = "adcaa", the output should be
     * solution(s1, s2) = 3.
     * Strings have 3 common characters - 2 "a"s and 1 "c".
     * Constraints: s1, s2 belongs to set {a...z}
     * @param s1 first string
     * @param s2 second string
     * @return the number of common characters
     */
    private int commonCharacterCount(final String s1, final String s2) {
        if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0) {
            return 0;
        }

        // Keep a counter array for each character a-z.
        final int[] s1CountArr = new int[128];

        final char[] s1Arr = s1.toCharArray();
        final char[] s2Arr = s2.toCharArray();

        // Count each character in s1.
        for (char c : s1Arr) {
            s1CountArr[c - '0']++;
        }

        int commonCount = 0;
        for (char c : s2Arr) {
            // If character in s2 is found in s1 and count  > 0, subtract 1 from the counterArr value and add 1 to the
            // common count variable.
            if (s1CountArr[c-'0'] > 0) {
                s1CountArr[c-'0']--;
                commonCount++;
            }
        }

        return commonCount;
    }

    ////////////////////////////////////////////////////////
    // ReverseInParenthesis
    //Write a function that reverses characters in (possibly nested) parentheses in the input string.
    //Input strings will always be well-formed with matching ()s.
    //Example
    //For inputString = "(bar)", the output should be
    //solution(inputString) = "rab";
    //For inputString = "foo(bar)baz", the output should be
    //solution(inputString) = "foorabbaz";
    //For inputString = "foo(bar)baz(blim)", the output should be
    //solution(inputString) = "foorabbazmilb";
    //For inputString = "foo(bar(baz))blim", the output should be
    //solution(inputString) = "foobazrabblim".
    //Because "foo(bar(baz))blim" becomes "foo(barzab)blim" and then "foobazrabblim".
    //Input/Output
    //[execution time limit] 3 seconds (java)
    //[input] string inputString
    //A string consisting of lowercase English letters and the characters ( and ). It is guaranteed that all parentheses in inputString form a regular bracket sequence.
    //Guaranteed constraints:
    //0 ≤ inputString.length ≤ 50.
    //[output] string
    //Return inputString, with all the characters that were in parentheses reversed.
    ////////////////////////////////////////////////////////
    private static String reverseInParenthesisStackQueue(String inputString) {
        if (inputString == null || inputString.isEmpty()) {
            return inputString;
        }

        final Stack<Character> st = new Stack<>();
        final char[] inputArr = inputString.toCharArray();
        for (int i = 0; i < inputString.length(); i++) {
//            if (inputArr[i] == ')') {
//                // Found a closing bracket. Now add all before it upto opening bracket in Queue.
//                final Queue<Character> q = new Queue<>();
//
//                while (st.peek() != '(') {
//                    q.enqueue(st.pop());
//                }
//                // pop the '(' too
//                st.pop();
//
//                while (!q.isEmpty()) {
//                    try {
//                        st.push(q.dequeue());
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            } else {
//                st.push(inputArr[i]);
//            }
        }

        final int stackSize = st.size();
        char[] ans = new char[stackSize];

        int i = stackSize - 1;
        while (!st.empty()) {
            ans[i] = st.pop();
            i--;
        }
        return new String(ans);
    }

    public static String reverseInParenthesis(String inputString) {
        if (inputString == null || inputString.length() == 0) {
            return inputString;
        }

        // 1. find the last parens "(" -> fIndex
        // 2. find the next paren ")" after the last parems "(" -> lIndex
        // 3. get the string between fIndex and lIndex
        // 4. recreate Input String with
        //    1. firstPart -> word from start to fIndex
        //.   2. reverseString -> from steps 1-3
        //.   3. lastPart -> word from lIndex to end.
        // 5. Repeat stesp 1-4 until no parens are left

        int fIndex = inputString.lastIndexOf("(");
        int lIndex = inputString.indexOf(")", fIndex);

        while (fIndex != -1) {
            String first = inputString.substring(0, fIndex);
            String rev = new StringBuilder(inputString.substring(fIndex+1, lIndex)).reverse().toString();
            String last = inputString.substring(lIndex+1);
            inputString = first + rev + last;
            fIndex = inputString.lastIndexOf("(");
            lIndex = inputString.indexOf(")", fIndex);
        }

        return inputString;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // Add Border
    // Given a rectangular matrix of characters, add a border of asterisks(*) to it.
    //Example For
    //picture = ["abc",
    //           "ded"]
    //the output should be
    //solution(picture) = ["*****",
    //                      "*abc*",
    //                      "*ded*",
    //                      "*****"]
    ///////////////////////////////////////////////////////////////////////////////////////////////
    public static String[] borderStringMatrix(String[] picture) {

        final int arrLength = picture.length;
        final int numRows = arrLength + 2;
        final String[] bordered = new String[numRows];

        // Assumed each string is of same length as the String array is in a matrix format.
        final int numColumns = picture[0].length() + 2;

        // Intialize bordered;
        for (int i = 0; i < numRows; i++) {
            bordered[i] = "";
        }

        // Add *'s in first and last row.
        for (int i = 0; i < numColumns; i++) {
            bordered[0] = bordered[0] + "*";
            bordered[numRows - 1] = bordered[numRows - 1] + "*";
        }

        int numStarsEachSide;
        // Append and prepend the string array rows with *'s
        for (int j = 0; j < arrLength; j++) {
            int l = picture[j].length();
            numStarsEachSide = (numColumns - l)/2;
            if (numStarsEachSide > 0) {
                for (int i = 0; i< numStarsEachSide; i++) {
                    bordered[j+1] = bordered[j+1] + "*";
                }
            }
            bordered[j+1] = bordered[j+1] + picture[j];
            if (numStarsEachSide > 0) {
                for (int i = 0; i< numStarsEachSide; i++) {
                    bordered[j+1] = bordered[j+1] + "*";
                }
            }
        }
        return bordered;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Given a string, find out if its characters can be rearranged to form a palindrome.
    //Example
    //For inputString = "aabb", the output should be
    //solution(inputString) = true.
    //We can rearrange "aabb" to make "abba", which is a palindrome.
    // "z" = true
    // A set of characters can form a palindrome if at most one character occurs odd number of times and all characters occur even number of times.
    ////////////////////////////////////////////////////////////////////////////////////////////////
    static boolean palindromeRearranging(String inputString) {

        // Create a count array and initialize all values as 0
        final int[] countArr = new int[256];
        Arrays.fill(countArr, 0);

        // For each character in input strings, increment count in the corresponding count array
        for (int i = 0; i < inputString.length(); i++) {
            countArr[(int)(inputString.charAt(i))]++;
        }

        // Count odd occurring characters
        int odd = 0;
        for (int i = 0; i < 256; i++) {
            if ((countArr[i] & 1) == 1)
                odd++;

            if (odd > 1)
                return false;
        }
        return true;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //An IP address is a numerical label assigned to each device (e.g., computer, printer) participating
    // in a computer network that uses the Internet Protocol for communication. There are two versions
    // of the Internet protocol, and thus two versions of addresses. One of them is the IPv4 address.
    //Given a string, find out if it satisfies the IPv4 address naming rules.
    //Example
    //For inputString = "172.16.254.1", the output should be
    //solution(inputString) = true;
    //For inputString = "172.316.254.1", -> false
    //316 is not in range [0, 255].
    //For inputString = ".254.255.0", -> false
    //There is no first number.
    //216.5.9.00 -> false
    //216..5.9.0-> false
    ///////////////////////////////////////////////////////////////////////////////////////////////
    static boolean isIpV4Address(final String inputString) {
        if (inputString == null || inputString.isEmpty()) {
            return false;
        }

        inputString.trim();

        final char[] strArr = inputString.toCharArray();
        final int n = strArr.length;
        int i = 0;
        StringBuffer token = new StringBuffer();
        int numTokens = 0;
        int numDots = 0;
        while (i < n) {
            if (strArr[i] != '.') {
                token.append(inputString.charAt(i));
            } else {
                numDots++;
                if (!(token.toString()).isEmpty()) {
                    numTokens++;
                    if (!validToken(token.toString())) {
                        return false;
                    }
                }
                // Done with this token. Create e new token.
                token = new StringBuffer();
            }
            i++;
        }

        // Process the last token.
        if (token != null) {
            numTokens++;
            if (!validToken(token.toString())) {
                return false;
            }
        }

        return numTokens == 4 && numDots == 3;
    }

    private static boolean validToken(final String token) {
        // Check whether string has a leading zero but is not "0"
        if (token.startsWith("0")) {
            return token.length() == 1;
        }
        if (token.equals("00") || token.equals("000")) {
            return false;
        }
        try {
            int intToken = Integer.parseInt(token.toString());
            System.out.println("strToken: " + token + ", intToken = " + intToken);
            if (intToken < 0 || intToken > 255) {
                return false;
            }
        } catch (final NumberFormatException ex) {
            return false;
        }

        return true;
    }


    ///////////////////////////////////////////////////////
    // main
    ///////////////////////////////////////////////////////
    public static void main(String... args) {
        System.out.println("reverseStr(abcdef) = " + reverseString("abdxyz"));

        System.out.println("isAnagram(abcdef, defabc) = " + isAnagramQuickerHashTable("adbgsgdfljgdf", "shgfkjhsafjsafhj"));

        System.out.println("myAtoi(   -123456 Char) = " + myAtoi("   -123456 Char"));
        System.out.println("myAtoi(    123456 Char) = " + myAtoi("    123456 Char"));

        System.out.println("strStr(hello, ll) = " + strStr("hello", "ll"));
        System.out.println("strStr(aaaa, bb) = " + strStr("aaaa", "baa"));
        System.out.println("strStr(haystack, \"\") = " + strStr("haystack", ""));

        System.out.println("NeedleHaystack strStr2(hello, ll) = " + strStr2("hello", "ll"));
        System.out.println("NeedleHaystack strStr2(aaaa, bb) = " + strStr2("aaaa", "baa"));
        System.out.println("NeedleHaystack strStr2(haystack, \"\") = " + strStr2("haystack", ""));

        System.out.println("Run length encoding of aabbberrrrcc = " + getRunLengthEncodingFor("aabbberrrrcc"));
        System.out.println("Run length encoding of abbberrrrc = " + getRunLengthEncodingFor("abbberrrrc"));
        System.out.println("Run length encoding of a = " + getRunLengthEncodingFor("a"));

//        System.out.println("---reverseInParenthesisSQ((bar): " + reverseInParenthesisStackQueue("(bar)"));
//        System.out.println("---reverseInParenthesisSQ(foo(bar(baz))foo): " + reverseInParenthesisStackQueue("foo(bar(baz))foo"));

        System.out.println("reverseInParenthesis((bar): " + reverseInParenthesis("(bar)"));
        System.out.println("reverseInParenthesis(foo(bar(baz))foo): " + reverseInParenthesis("foo(bar(baz))foo"));

        final String[] toBorder = {"abc", "def", "ghi"};
        System.out.println("Input: ");
        printBordered(toBorder);
        final String[] bordered = borderStringMatrix(toBorder);
        System.out.println("Output");
        printBordered(bordered);
        System.out.println("Border string matrix: " + toBorder);

        System.out.println("Palindrome rearranged? (abba): " + palindromeRearranging("abba"));
        System.out.println("Palindrome rearranged? (a): " + palindromeRearranging("a"));
        System.out.println("Palindrome rearranged? (abcd): " + palindromeRearranging("abcd"));

        System.out.println("isIpV4Address(127.11.16.1): " + isIpV4Address("127.11.16.1"));
        System.out.println("isIpV4Address(127.11.16.00): " + isIpV4Address("127.11.16.00"));
        System.out.println("isIpV4Address(127.11.16..1): " + isIpV4Address("127.11.16.1"));
    }

    private static void printBordered(final String[] bordered) {
        for (int i = 0; i < bordered.length; i++) {
            System.out.println("    " + bordered[i]);
        }
    }
}
