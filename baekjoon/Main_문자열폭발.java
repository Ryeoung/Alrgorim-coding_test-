package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @Domain  : 백준
 * @Num     : 
 * @Title   : 문자열 폭발
 * @Date    : 2021/11/30
 * @WepLink : https://www.acmicpc.net/problem/ 9935
 *
 * 처음 replace를 사용했을 때 메모리 초과
 * StringBuilder delete를 사용 했을 때 시간 초과
 * stack + arry + list 사용 했을 때 메모리 초과
 * stack + arry 사용 통과
 */
public class Main_문자열폭발 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] word = br.readLine().toCharArray();
        char[] rmWord = br.readLine().toCharArray();
        Stack<Character> stack= new Stack<>();
        int wordLen = word.length;
        int rmWordLen = rmWord.length;
        char checkBoomChar = rmWord[rmWordLen - 1];
        LinkedList<Character> tempList = new LinkedList<>();

        for(int idx = 0; idx < wordLen; idx++) {
            char curChar = word[idx];
            // 폭발 문자열 마지막 문자로 stack에 넣을 지 아닐지 체크
            stack.push(curChar);
            if (curChar != checkBoomChar || stack.size() < rmWordLen - 1 ) {
                continue;
            }

            boolean isBoom = true;
            int stackSize = stack.size();
            int stackPoint = stackSize - 1;
            for(int rmWordPoint = rmWordLen - 1; rmWordPoint >= 0;  rmWordPoint--) {
                   char checkChar = rmWord[rmWordPoint];
                   //폭발 문자 체크
                   if(stack.get(stackPoint--) != checkChar) {
                       isBoom = false;
                       break;
                   }
            }

            //터지면 문자열 삭제
            if(isBoom) {
                for(int popCnt = 0; popCnt < rmWordLen; popCnt++) {
                    stack.pop();
                }
            }
        }

        // 스택에 있는 문자를 합치기
        StringBuilder sb = new StringBuilder();
        for(Character c : stack) {
            sb.append(c);
        }

        if(sb.length() > 0 ){
            System.out.println(sb);
        } else {
            System.out.println("FRULA");
        }
    }
}

