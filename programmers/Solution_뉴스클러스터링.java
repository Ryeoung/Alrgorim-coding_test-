package programmers;

import java.util.HashMap;
import java.util.Map;

class Solution_뉴스클러스터링 {
    public int solution(String str1, String str2) {
        int answer = 0;
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        Map<String, Integer> m1 = getMap(str1);
        Map<String, Integer> m2 = getMap(str2);
        if (m1.size() == 0 && m2.size() == 0) {
            answer = 65536;
        } else {
            double degree = getDegree(m1, m2);
            answer = (int) (degree * 65536);
        }

        return answer;
    }

    Map<String, Integer> getMap(String s) {
        Map<String, Integer> map = new HashMap<>();
        int size = s.length();
        for (int idx = 0; idx < size - 1; idx++) {
            char cur = s.charAt(idx);
            char next = s.charAt(idx + 1);
            if ((cur >= 'a' && cur <= 'z') &&
                    (next >= 'a' && next <= 'z')) {

                String str = cur + "" + next;
                if (map.containsKey(str)) {
                    map.put(str, map.get(str) + 1);
                } else {
                    map.put(str, 1);
                }
            }
        }
        return map;
    }

    double getDegree(Map<String, Integer> m1, Map<String, Integer> m2) {
        double min = 0;
        double max = 0;
        for (String str : m1.keySet()) {
            if (m2.containsKey(str)) {
                //m1 과 m2 교집합
                min += Math.min(m1.get(str), m2.get(str));

                //m1 과 m2 교집합 + 여집합... 같은 값일 경우
                max += Math.max(m1.get(str), m2.get(str));
            } else {
                // m1 - m2 여집합
                max += m1.get(str);
            }
        }
        for (String str : m2.keySet()) {
            if (!m1.containsKey(str)) {
                //m2 - m1 여집합
                max += m2.get(str);
            }
        }


        return min / max;
    }
}