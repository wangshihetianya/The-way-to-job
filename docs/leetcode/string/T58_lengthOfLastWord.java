package string;

public class T58_lengthOfLastWord {
    public static void main(String[] args) {
        String s = "hello world";
        System.out.println(new T58_lengthOfLastWord().lengthOfLastWord_1(s));
    }

    /*
    测试用例：
    1、输入："a "  ==>   输出应为： 1
     */
    public int lengthOfLastWord(String s) {
        s = s.trim();
        if (s.length() == 0) {
            return 0;
        }
        int pos = s.lastIndexOf(" ");
        if (pos != -1) {
            return s.length() - pos - 1;
        } else {
            return s.length();
        }
    }

    public int lengthOfLastWord_1(String s){
        int length = 0;
        for (int i = s.length() - 1; i >= 0 ; i--) {
            if (length != 0 && s.charAt(i)==' ') {
                break;
            } else if (s.charAt(i) != ' ') {
                length++;
            }
        }
        return length;
    }
}
