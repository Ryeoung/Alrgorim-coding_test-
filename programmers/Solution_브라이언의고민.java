package programmers;

import java.util.*;

class Solution_브라이언의고민 {
    public static void main(String[] args) {
        System.out.println(new Solution_브라이언의고민().solution("HaEaLaLObWORLDb"	));
    }
    public String solution(String sentence) {
        String answer = "";
        //rule 1 : 공백이 있으면 제거
        //rule 2 : 첫번째 단어에 규칙 2이 적용되면 a ~ a형태가 되어야 된다. a는 특수 문자 예시
        //rule 3 : 규칙 2 대문자 사이에 같은 단어가 있어야된다.
        //rule 4 : 동일한 규칙을 적용할 순 없다. 한단어에 규칙 1을 두번 적용하거나 규칙 2를 두번 적용할 수 없다.

        boolean isCheck = isEmptyOfRule(sentence);
        if(!isCheck){
            return "invalid";
        }
        answer = checkSentence(sentence, new HashMap<Integer, Character>(), new HashSet<Character>());


        return answer;
    }
    String checkSentence(String sentence, Map<Integer, Character> ruleMap, Set<Character> usedCharSet) {
        // 첫 문자 확인 (규칙 2가 적용 되었는 지 확인 해야되기 때문에 )
        int sentenceLen = sentence.length();
        String result = "";
        if(sentenceLen <= 0) {
            return result;
        }

        char first = sentence.charAt(0);

        Sentence rule2Sentence = null;
        // 규칙 2가 적용 되었다면
        if(first >= 'a' && first <= 'z') {
            rule2Sentence = rule2(sentence, ruleMap, usedCharSet);
            if(!rule2Sentence.isCorrect) {
                return "invalid";
            }
            Set<Character> dupSet = new HashSet<>(usedCharSet);
            // aFFFFabbbb일경우 FFFF(규칙 2체크 한경우) 따로 bbbb따로 다시 체크
            String leftSentence = checkSentence(sentence.substring(rule2Sentence.startIdx, rule2Sentence.endIdx + 1),
                    ruleMap, dupSet);

            String rightSentence = checkSentence(sentence.substring(rule2Sentence.endIdx + 2, sentenceLen),
                    new HashMap<Integer, Character>(), dupSet);

            if(leftSentence.equals("invalid") || rightSentence.equals("invalid")) {
                return "invalid";
            }
            return leftSentence.trim() + " " + rightSentence.trim();
        }

        int specialIdx = getRule1SpecialCharIdx(sentence);
        if(specialIdx < 0) {
            // 모두 대문자인 경우
            return sentence;
        }
        if(ruleMap.containsKey(2)) {
            // a ~ a 사이 문자들이기 때문에 규칙 1이 적용되었거나 다 문자들이 아닌 경우 invalid
            Sentence rule1Sentence = rule1(sentence, ruleMap, usedCharSet);
            if(rule1Sentence.isCorrect && rule1Sentence.endIdx == sentenceLen - 1) {
                return rule1Sentence.convertedWord;
            }
            return "invalid";
        }

        // 규칙 1을 먼저 적용하는 경우
        HashSet<Character> dupSet=  new HashSet<>(usedCharSet);
        result = sentence.substring(0, specialIdx - 1).trim();
        String left = sentence.substring(specialIdx - 1);
        String right = "";
        Sentence rule1Sentence = rule1(left, ruleMap, dupSet);

        if(!rule1Sentence.isCorrect) {
            return "invalid";
        } else {
            result = result + " " + rule1Sentence.convertedWord;
            right = checkSentence(left.substring(rule1Sentence.endIdx + 1), new HashMap<Integer, Character>(), dupSet);
            if(!right.equals("invalid")) {
                result  = result + " " + right;
                return result.trim();
            }

        }
        dupSet=  new HashSet<>(usedCharSet);

        //나눌 수 있는 경우 BaBaB~ 경우 B + aBaB~ 규칙 2을 먼저 적용하는 경우
        left = sentence.substring(0, specialIdx);
        right = checkSentence(sentence.substring(specialIdx, sentenceLen), new HashMap<Integer, Character>(), dupSet);
        if(right.equals("invalid")) {
           return "invalid";
        }
        return result = left.trim() + " " + right.trim();
    }
    int getRule1SpecialCharIdx(String sentence) {
        int specialIdx = -1;
        int sentenceLen = sentence.length();
        for(int idx = 0; idx <  sentenceLen; idx++) {
            char ch = sentence.charAt(idx);
            if(ch >= 'a' && ch <= 'z') {
                specialIdx = idx;
                break;
            }
        }
        return specialIdx;
    }

    Sentence rule1(String sentence, Map<Integer, Character> ruleMap, Set<Character> usedCharSet) {
        int specialIdx = -1;
        boolean fin = false;
        StringBuilder sb= new StringBuilder();
        int preIdx = 1;
        sb.append(sentence.charAt(0));
        boolean isInvalid= false;
        for(int idx = 1; idx < sentence.length(); idx = idx + 2) {
            char ch = sentence.charAt(idx);
            // 대문자일경우
            if(ch >= 'A' && ch <= 'Z') {
                // 대문자일 경우
                break;
            }

            // 특수 문자를 처음 넣었을 경우
            if(!ruleMap.containsKey(1)) {
                // 그 특수문자는 이미 쓰였을 경우
                if(usedCharSet.contains(ch)) {
                    //
                    isInvalid = true;
                    break;
                }
                ruleMap.put(1, ch);

                continue;
            }

            // 다른 특수문자가 올 경우
            if(ch != ruleMap.get(1)) {
                break;
            }
            if(sentence.charAt(idx - 1) >= 'A' && sentence.charAt(idx - 1) <= 'Z') {
                sb.append(sentence.charAt(idx - 1));
            } else {
                break;
            }

            preIdx = idx;
        }

        if(preIdx + 1 < sentence.length() && sentence.charAt(preIdx + 1) >= 'A' && sentence.charAt(preIdx + 1) <= 'Z') {
                sb.append(sentence.charAt(preIdx + 1));
        } else {
            isInvalid = true;
        }

        if(sb.length() < 2) {
            isInvalid =true;
        }
        if(!isInvalid) {
            usedCharSet.add(ruleMap.get(1));
        }
        return new Sentence(0, preIdx + 1, sb.toString(), !isInvalid);
    }
    Sentence rule2(String sentence , Map<Integer, Character> ruleMap, Set<Character> usedCharSet) {
        int wordEndIdx = 0;
        int wordStartIdx = 0;
        char first = sentence.charAt(0);
        int sentenceLen = sentence.length();
        StringBuilder sb = new StringBuilder();
        if(ruleMap.containsKey(2) || usedCharSet.contains(first)) {
            //이미 이 문자에 규칙 2가 적용 되었을 경우 나 중복된 특수문자를 사용했을 경우

            return new Sentence(0, 0, "invalid", false);
        }

        // 규칙 2 적용되었으니 제대로 적용되었는 지 확인
        for(int idx = 1; idx < sentenceLen; idx++) {
            char ch = sentence.charAt(idx);
            if(ch == first) {
                // 규칙 2가 제대로 적용 되었다는 조건
                wordEndIdx = idx - 1;
                break;
            }
            sb.append(ch);
        }

        // 규칙 2가 제대로 적용이 안되었다면 ....
        if(wordEndIdx == 0 || sb.length() <= 0) {
            //나중에 생각해보자.
            return new Sentence(0, 0, "invalid", false);
        }

        usedCharSet.add(first);
        ruleMap.put(2, first);


        return new Sentence(wordStartIdx + 1, wordEndIdx, sb.toString(), true);
    }

    boolean isEmptyOfRule(String str) {
        boolean isCheck = true;
        int strLen = str.length();
        for(int idx = 0; idx < strLen; idx++) {
            char ch = str.charAt(idx);
            if(ch == ' ') {
                isCheck = false;
                break;
            }
        }

        return isCheck;
    }

    class Sentence{
        int startIdx;
        int endIdx;
        String convertedWord;
        boolean isCorrect;

        public Sentence(int startIdx, int endIdx, String convertedWord, boolean isCorrect) {
            this.startIdx = startIdx;
            this.endIdx = endIdx;
            this.convertedWord = convertedWord;
            this.isCorrect = isCorrect;
        }
    }
}