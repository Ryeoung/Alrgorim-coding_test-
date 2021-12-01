package programmers;

import java.util.*;

// 누적 합 최적화,
class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        int playTimeSec =  getSecOfTime(play_time);
        int startTime = 0;
        long[] time = new long[playTimeSec + 1];
        for (int idx = 0; idx < logs.length; idx++) {
            String[] parseStr = logs[idx].split("-");
            startTime = getSecOfTime(parseStr[0]);
            int endTime = getSecOfTime(parseStr[1]);

            time[startTime] += 1;
            time[endTime] -= 1;

        }

        //누적합 최적화 방법, 맨압과 뒤에 표시된 것만으로 누적합 계산
        for(int idx = 1; idx < playTimeSec; idx++) {
            time[idx] = time[idx] + time[idx - 1];
        }
        // dequeue
        long sum = 0;
        int advTime = getSecOfTime( adv_time);
        for(int idx = 0; idx < advTime; idx++) {
            sum += time[idx];
        }

        long max = sum;
        int advStartTime = 0;
        for(int idx = advTime; idx <= playTimeSec; idx++) {

            sum += time[idx];
            sum -= time[idx - advTime];

            if(sum > max) {
                max = sum;
                advStartTime = idx - advTime + 1;
            }
        }
        return getTimeOfSec(advStartTime);

    }

    int getSecOfTime(String time) {
        String[] timeSplit = time.split(":");
        return ((Integer.parseInt(timeSplit[0])) * 60 * 60) + ((Integer.parseInt(timeSplit[1])) * 60) +
                Integer.parseInt(timeSplit[2]);
    }

    String getTimeOfSec(int total) {
        int s = (total % 60);
        int m= ((total / 60) % 60);
        int h = (total / 3600);

        return String.format("%02d:%02d:%02d", h, m, s);

    }


}