package string;

import java.util.HashMap;
import java.util.Map;

public class T205_isIsomorphic {
    /*
    同构字符串：（1 - 1 映射）
    解题思路很妙！！！
    通过map映射结构来搞定它

    容易犯的错误：只验证了一个方向
    eg：s=ab,t=aa
    选择s作为主遍历对象，第一次加入映射（a,a），第二次因为不存在key-b,又加入（b,a）,结果错误

    ==>只验证一个方向是不行的，会出现多个字母对应了一个字母的情况（N - 1）
    将验证函数抽出来，主函数分别验证s,t 和 t,s
    两个方向都能保证（N-1），即可保证 （1-1）
     */
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length())
            return false;
        return isIsomorphic_helper(s, t) && isIsomorphic_helper(t, s);
    }

    private boolean isIsomorphic_helper (String s, String t) {
        Map<Character, Character> map =  new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                if (t.charAt(i) != map.get(s.charAt(i))) {
                    return false;
                }
            } else {
                map.put(s.charAt(i), t.charAt(i));
            }
        }
        return true;
    }


    /*
    思路：将s和t同样的位置映射成数字(用下标+1表示，0位默认——代表第一次见到这个字母)

     */
    public boolean isIsomorphic_fast(String s, String t) {
        int len = s.length();
        int[] mapS = new int[128];
        int[] mapT = new int[128];
        for (int i = 0; i < len; i++) {
            char cs = s.charAt(i);
            char ct = t.charAt(i);
            if (mapS[cs] != mapT[ct]) {
                return false;
            }
            if (mapS[cs] == 0) {
                mapS[cs] = i + 1;
                mapT[ct] = i + 1;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new T205_isIsomorphic().isIsomorphic("ab", "aa"));
    }
}
