package com.mr.interview.mta;

public class Recursion {

    /**
     * Encrypted Words
     * You've devised a simple encryption method for alphabetic strings that shuffles the characters in such a way that
     * the resulting string is hard to quickly read, but is easy to convert back into the original string.
     * When you encrypt a string S, you start with an initially-empty resulting string R and append characters to it as follows:
     * Append the middle character of S (if S has even length, then we define the middle character as the left-most
     * of the two central characters).
     * Append the encrypted version of the substring of S that's to the left of the middle character (if non-empty)
     * Append the encrypted version of the substring of S that's to the right of the middle character (if non-empty)
     * For example, to encrypt the string "abc", we first take "b", and then append the encrypted version of "a" (which is just "a") and the encrypted version of "c" (which is just "c") to get "bac".
     * If we encrypt "abcxcba" we'll get "xbacbca". That is, we take "x" and then append the encrypted version "abc" and then append the encrypted version of "cba".
     * @param s
     * @return
     */
    public String findEncryptedWord(String s) {
        // Write your code here

        if (s.length() <= 1) {
            return s;
        }

        final int mid = (s.length() - 1)/2;

        return s.charAt(mid) + findEncryptedWord(s.substring(0, mid)) + findEncryptedWord(s.substring(mid+1,s.length()));
    }


    public static void main(String[] args) {
        final Recursion recursion = new Recursion();

        String strToEncrypt = "abc";
        System.out.println("Encryption of: " + strToEncrypt + " := " + recursion.findEncryptedWord(strToEncrypt));
        strToEncrypt = "abcxcba";
        System.out.println("Encryption of: " + strToEncrypt + " := " + recursion.findEncryptedWord(strToEncrypt));
    }
}
