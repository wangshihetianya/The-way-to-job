package string;

public class T28_strStr {
    public static void main(String[] args) {
        String hay = "aaaaa";
        String needle = "";
        System.out.println(new T28_strStr().strStr(hay, needle));
    }
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }
}
