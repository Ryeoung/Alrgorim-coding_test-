package programmers;

import java.util.*;

class Solution_메뉴리뉴얼  {
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};

        HashMap<String, Integer> courseMap = new HashMap<>();

        // System.out.println(menuCnt);
        int orderMenuSize = orders.length;
        for(int orderIdx = 0; orderIdx < orderMenuSize; orderIdx++ ) {
            char[] orderMenu = orders[orderIdx].toCharArray();
            Arrays.sort(orderMenu);
            HashSet<String> courseMenu = new HashSet<>();
            comb(orderMenu, 0, 0, course, "", courseMenu);
            Iterator<String> itr = courseMenu.iterator();
            while(itr.hasNext()) {
                String pickCourse = itr.next();
                if(courseMap.containsKey(pickCourse)) {
                    courseMap.put(pickCourse, courseMap.get(pickCourse) + 1);
                } else {
                    courseMap.put(pickCourse, 1);
                }
            }
        }

        int courseSize = course.length;
        LinkedList<String> result = new LinkedList<>();
        for(int courseIdx = 0; courseIdx < courseSize; courseIdx++ ) {
            int menuCnt = course[courseIdx];
            LinkedList<String> maxOrderMenuList = new LinkedList<>();
            Iterator<String> itr = courseMap.keySet().iterator();
            int max = 2;
            while(itr.hasNext()) {
                String courseName = itr.next();
                int orderCnt = courseMap.get(courseName);
                if(courseName.length() == menuCnt) {
                    //최대값
                    if(max < orderCnt) {
                        max = orderCnt;
                        maxOrderMenuList.clear();
                        maxOrderMenuList.add(courseName);

                    } else if(max == orderCnt) {
                        maxOrderMenuList.add(courseName);
                    }
                }
            }

            result.addAll(maxOrderMenuList);
        }
        Collections.sort(result);
        answer = new String[result.size()];
        int answerSize = answer.length;
        for(int idx = 0; idx < answerSize; idx++) {
            answer[idx] = result.get(idx);
        }

        return answer;
    }
    void comb(char[] orderMenu, int depth, int pick, int[] course, String result, HashSet<String> courseMenu) {
        if(pick >= 2) {
            boolean flag = false;
            int courseSize = course.length;
            for(int idx = 0; idx < courseSize; idx++) {
                if(pick == course[idx]) {
                    flag = true;
                    break;
                }
            }

            if(flag) {
                courseMenu.add(result);
            }
        }

        if(depth == orderMenu.length) {
            return;
        }
        comb(orderMenu, depth + 1 , pick + 1, course, result + orderMenu[depth], courseMenu);
        comb(orderMenu, depth + 1 , pick, course, result, courseMenu);

    }
}