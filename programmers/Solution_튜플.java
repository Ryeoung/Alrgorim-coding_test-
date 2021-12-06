package programmers;
import java.util.*;
class Solution_튜플 {
    public int[] solution(String s) {
        int[] answer = {};
        s = s.substring(1, s.length() - 1); // 가장 바깥의 {  } 제거
        String[] set = s.replace("},{", "} {").split(" ");
        int setSize = set.length;
        LinkedList<String[]> list = new LinkedList<>();
        for(int idx = 0; idx < setSize;  idx++) {
            set[idx] = set[idx].trim().substring(1, set[idx].length() - 1);
            String[] strArr = set[idx].split(",");
            list.add(strArr);
        }

        Collections.sort(list, (o1, o2) -> (o1.length - o2.length));
        LinkedList<Integer> result = new LinkedList<>();
        Set<Integer> dup = new HashSet<>();

        for(String[] arr: list) {
            for(int idx = 0 ; idx < arr.length; idx++) {
                int value = Integer.parseInt(arr[idx]);
                if(dup.contains(value)) {
                    continue;
                } else {
                    dup.add(value);
                    result.add(value);
                }
            }
        }

        answer = new int[result.size()];

        for(int idx = 0; idx < result.size(); idx++) {
            answer[idx] = result.get(idx);
        }


        return answer;
    }
}
