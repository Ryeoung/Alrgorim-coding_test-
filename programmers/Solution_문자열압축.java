package programmers;

import java.util.*;

class Solution_문자열압축 {
    public int solution(String s) {
        int answer = 0;
        int strLen = s.length();
        answer = strLen;
        int max = strLen / 2;

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for(int divd = max; divd >= 1; divd--) {
            String pre = s.substring(0, divd);
            int cnt = 1;
            String fin = "";
            String cur = "";
            for(int idx =divd; idx < strLen; idx = idx + divd) {
                if(idx + divd > strLen) {
                    continue;
                }
                cur = s.substring(idx, idx + divd);
                if(pre.equals(cur)) {
                    cnt++;
                } else {
                    if(cnt > 1) {
                        fin += cnt;
                    }
                    fin += pre;
                    pre = cur;
                    cnt = 1;
                }
            }
            if(cnt > 1) {
                fin += cnt;
            }
            fin += pre;
            fin += s.substring(strLen - (int)(strLen % divd), strLen);
            if(answer > fin.length()) {
                answer = fin.length();
            }
        }// divid
        return answer;
    }
}