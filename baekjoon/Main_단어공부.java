package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

/**
 * @Domain  : 백준
 * @Num     :
 * @Title   : 단어공부
 * @Date    : 2021/11/30
 * @WepLink : https://www.acmicpc.net/problem/1157
 */
public class Main_단어공부 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] words =  br.readLine().toUpperCase().toCharArray();

        HashMap<Character, Integer> map = new HashMap<>();
        int wordLen = words.length;
        for(int idx =0 ; idx < wordLen; idx++) {
            char c = words[idx];
            if(map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        int maxValue = -1;
        int maxValueCnt = 0;
        char characterOfMaxCnt = 'x';
        Iterator<Character> itr = map.keySet().iterator();
        while(itr.hasNext()) {
            char c = itr.next();
            int cnt = map.get(c);
            if(maxValue < cnt) {
                maxValue = cnt;
                maxValueCnt = 1;
                characterOfMaxCnt = c;
            } else if(maxValue == cnt){
                maxValueCnt++;
            }
        }
        if(maxValueCnt > 1) {
            System.out.println("?");
        } else {
            System.out.println(characterOfMaxCnt);

        }


    }
}
