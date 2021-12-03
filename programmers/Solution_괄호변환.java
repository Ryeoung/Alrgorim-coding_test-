package programmers;

import java.util.*;

class Solution_괄호변환 {
    public String solution(String p) {
        String answer = "";
        return answer = convert(p);
    }

    String convert(String p) {
        if (p.length() < 0 || isCorrectString(p)) {
            // 빈문자열일경우 다시 반환
            return p;
        }

        int openCnt = 0;
        int closeCnt = 0;
        int strSize = p.length();
        String u = "", v = "";

        for (int idx = 0; idx < strSize; idx++) {
            char ch = p.charAt(idx);
            if (ch == '(') {
                openCnt++;
            } else {
                closeCnt++;
            }

            // 균형잡인 문자열일 경우
            if (openCnt != 0 && (openCnt == closeCnt)) {
                u = p.substring(0, idx + 1);
                v = p.substring(idx  + 1);
                break;
            }
        }
        if (isCorrectString(u)) {
            //u가 올바른 괄호 문자열이라면
            u += convert(v);
        } else {
            String str = '(' + convert(v) + ')';
            // u 첫번째 문자 마지막 문자 빼고 뒤집기
            for (int idx = 1; idx < u.length() - 1; idx++) {
                char ch = u.charAt(idx);
                if (ch == '(') {
                    str += ')';
                } else {
                    str += '(';
                }

            }
            //최종 u에다 저장
           u = str;

        }

        return u;

    }

    boolean isCorrectString(String str) {
        Stack<Character> stack = new Stack<>();
        int strSzie = str.length();
        for (int idx = 0; idx < strSzie; idx++) {
            char ch = str.charAt(idx);
            if (ch == '(') {
                stack.push(ch);
            } else {
                if (stack.size() > 0) {
                    stack.pop();
                }

            }
        }

        if (stack.size() > 0) {
            return false;
        }
        return true;
    }

}