package programmers;

import java.util.*;
class Solution_전화번호목록 {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        Arrays.sort(phone_book);
        String str1 = "";
        String str2 = "";
        for(int i = 0; i < phone_book.length - 1; i++) {
            if(phone_book[i + 1].length() > phone_book[i].length()) {
                str1 = phone_book[i];
                str2 = phone_book[i + 1].substring(0, str1.length());
            } else {
                str2 = phone_book[i + 1];
                str1 = phone_book[i].substring(0, str2.length());

            }

            if(str2.equals(str1)) {
                return false;
            }
        }
        return answer;
    }
}