package string;

public class T38_countAndSay {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        String last = countAndSay(n - 1);
        int count = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < last.length(); i++) {
            if (i < last.length() - 1 && last.charAt(i) == last.charAt(i + 1)) {
                count++; //注：count初始值不是0，而是1！！！
            } else {
                sb.append(count);
                sb.append(last.charAt(i));
                count = 1;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new T38_countAndSay().countAndSay(5));
    }
}
