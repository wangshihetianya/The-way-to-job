package string;

public class T459_repeatedSubstringPattern {
    public boolean repeatedSubstringPattern(String s) {
        StringBuilder sub = new StringBuilder(s.charAt(0)+"");
        int j = 0;
        for (int i = 1; i < s.length(); i++) {
            if (sub.charAt(j) == s.charAt(i)) {
                j++;
            } else {
                sub.append(s.charAt(i));
            }

        }
        return sub.length() != s.length();
    }

    public static void main(String[] args) {
        String s = "aba";
        System.out.println(new T459_repeatedSubstringPattern().repeatedSubstringPattern(s));
    }
}
