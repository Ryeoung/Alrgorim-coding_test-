package programmers;

public class Solution_다트게임 {
    public static void main(String[] args) {
        int a = new Solution().solution("1S2D*3T");
        System.out.println(a);
    }
    static class Solution {
        public int solution(String dartResult) {
            int answer = 0;
            int[] score = new int[3];
            int scoreIdx = 0;
            for(int idx = 0; idx < dartResult.length(); idx++ ) {
                char c = dartResult.charAt(idx);
                if(c >= '0' && c <= '9') {
                    if(c == '1' && dartResult.charAt(idx + 1) == '0') {
                        score[scoreIdx] = 10;
                        idx++;
                    } else {
                        score[scoreIdx] = Integer.parseInt(c + "");
                    }


                } else if (c == 'S' || c == 'D' || c == 'T' || c == '*' || c == '#') {
                    if(c == 'D') {
                        score[scoreIdx] = (int)Math.pow(score[scoreIdx], 2);
                        scoreIdx++;
                    } else if(c == 'T') {
                        score[scoreIdx] = (int)Math.pow(score[scoreIdx], 3);
                        scoreIdx++;
                    } else if(c == 'S') {
                        scoreIdx++;
                    } else if(c == '*') {
                        for(int cnt = 1; cnt <= 2; cnt++) {
                            if(scoreIdx - cnt < 0) {
                                break;
                            }
                            score[scoreIdx - cnt] *= 2;
                        }
                    } else if(c == '#') {
                        score[scoreIdx - 1] *= -1;
                    }
                }
            }
            System.out.println(score[0] + " " + score[1] + " " + score[2]);
            return answer = score[0] + score[1] + score[2];
        }



    }
}
