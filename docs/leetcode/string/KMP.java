package string;

public class KMP {
    public void getNext (String pattern, int[] next) {
        int j = 1;
        int t = 0;
        next[1] = 0;
        while (j < pattern.length()) {
            if (t == 0 || pattern.charAt(j) == pattern.charAt(t)) {
                next[j + 1] = t + 1;
                t++;
                j++;
            }else {
                t = next[t];
            }
        }
    }

    public int KMP (String text, String pattern, int[] next) {
        int i = 1, j = 1;
        while (i <= text.length() && j <= pattern.length()) {
            if (j == 0 || text.charAt(i)== pattern.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j > pattern.length()) {
            return i - pattern.length();
        } else
            return 0;
    }

    public static void main(String[] args) {
        String s = "ababaa";
        KMP t = new KMP();
        int[] next = new int[s.length() + 1];
        t.getNext(s,next);
        for (int n:next) {
            System.out.print(n+" ");
        }
    }
}
