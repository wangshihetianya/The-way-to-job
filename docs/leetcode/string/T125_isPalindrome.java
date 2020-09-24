package string;

public class T125_isPalindrome {
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        s = s.toLowerCase(); //注意大小写匹配
        while (left < right) {
            char lc = s.charAt(left);
            char rc = s.charAt(right);
            if (lc<'0'||(lc>'9'&&lc<'A')||(lc>'Z'&&lc<'a')||(lc>'z')) {
                left++;
                continue;
            }
            if (rc<'0'||(rc>'9'&&rc<'A')||(rc>'Z'&&rc<'a')||(rc>'z')) {
                right--;
                continue;
            }

            /*
            注：P - 0 == a - A，靠差值不能判断是不是大小写区别！！！！
             */
            if (lc != rc) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public boolean isPalindrome_regex(String s) {
        s = s.replaceAll("[^a-zA-Z0-9]", "");
        s = s.toLowerCase();
        System.out.println(s);
        int left =0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }



    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        System.out.println(new T125_isPalindrome().isPalindrome_regex(s));
    }
}
