package leetcode;

// https://leetcode.com/explore/challenge/card/march-leetcoding-challenge-2021/589/week-2-march-8th-march-14th/3665/
public class RemovePalindromicSubsequences {
    public int removePalindromeSub(String s) {
        String temp;
        int count = 0;
        if(s.equals("")) return 0;
        if(isPalindrome(s)) return 1;
        return 2;
    }

    boolean isPalindrome(String str) {
        for(int i = 0; i < str.length()/2; i++) {
            if(str.charAt(i) != str.charAt(str.length()-i-1)) {
                return false;
            }
        }
        return true;
    }
}
