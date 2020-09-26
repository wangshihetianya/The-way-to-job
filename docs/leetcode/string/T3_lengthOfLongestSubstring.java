package string;

import java.util.Arrays;
import java.util.HashMap;

public class T3_lengthOfLongestSubstring {
    public static void main(String[] args) {
        String s = " ";
        T3_lengthOfLongestSubstring t = new T3_lengthOfLongestSubstring();
        System.out.println(t.lengthOfLongestSubstring_arr(s));

    }

    /*
    思路：
    1、暴力解决
     */
    public int lengthOfLongestSubstring(String s) {
        boolean[] ch = new boolean[128];
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            int len = 0;
            Arrays.fill(ch, false);
            for (int j = i; j < s.length(); j++) {
                if (!ch[s.charAt(j)]) {
                    len++;
                    ch[s.charAt(j)] = true;
                } else {
                    break;
                }
            }
            max = Math.max(max, len);
        }
        return max;
    }

    public int lengthOfLongestSubstring_windows(String s) {
        HashMap<Character, Integer> pos = new HashMap<>();
        int start = 0, end = 0;
        int max = 0;
        while (end < s.length()) {
            char ch = s.charAt(end);
            if (pos.containsKey(ch) && pos.get(ch) >= start) {
                start = pos.get(ch) + 1;
            }
            pos.put(ch, end);
            end++;
            max = Math.max(max, end - start);
            System.out.println(start +" ------- " +end);
        }
        return max;
    }

    public int lengthOfLongestSubstring_arr (String s) {
        int[] posArr = new int[128];
        Arrays.fill(posArr, -1);
        int max = 0, start = 0, end = 0;
        while (end < s.length()) {
            char cur = s.charAt(end);
            if (posArr[cur] != -1 && posArr[cur] >= start) {
                start = posArr[cur] + 1;
            }
            posArr[cur] = end;
            end++;
            max = Math.max(max, end - start);
        }
        return max;
    }
}
