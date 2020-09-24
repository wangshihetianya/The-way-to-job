package string;

public class T1400_canConstruct {
    public boolean canConstruct(String s, int k) {
        if (s.length() < k) {
            return false;
        }
        int[] letter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            letter[s.charAt(i) - 'a']++;
        }
        int odd = 0;
        for (int i = 0; i < letter.length; i++) {
//            if (letter[i] % 2 != 0) {
//                odd++;
//            }
            odd += letter[i] & 1;

        }
        return odd <= k;
    }

    public static void main(String[] args) {
        String s = "leetcode";
        System.out.println(new T1400_canConstruct().canConstruct(s, 3));
    }
}
