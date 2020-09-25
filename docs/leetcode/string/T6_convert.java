package string;

public class T6_convert {
    /*
    z字形变换
    思路1：模拟法：
    重点：忽略掉列号具体变化，重点关注行（输出的结果，是按照行扫描，
    也就是说，可以忽略列的信息，只关注某个字母在哪一行。）
    可以申请numRows个StringBuilder,逐一将字母分别放进不同的StringBuilder中

    此处： 最妙的是一个flag的应用，通过找到index的规律，确定index是+1.还是-1
     */
    public String convert(String s, int numRows) {
        if (s == null || s.length() <= numRows || numRows <= 1) {
            return s;
        }
        StringBuilder[] sbs = new StringBuilder[numRows];
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            sbs[i] = new StringBuilder();
        }
        int index = 0;
        int dir = 1; //加1，减1的参数
//        for (int i = 0; i < s.length(); i++) {
//
//            sbs[index].append(s.charAt(i));
//            index += dir;
//            if (index == 0 || index == numRows -1) {
//                dir = - dir;  //这里用dir的思想很厉害，这里取负很好用！！！
//            }
//        }

        for (char c: s.toCharArray()) {
            sbs[index].append(c);
            index += dir;
            if (index == 0 || index == numRows - 1) {
                dir = - dir;
            }
        }

        for (int i = 0; i < numRows; i++) {
            res.append(sbs[i]);
        }
        return res.toString();
    }
}
