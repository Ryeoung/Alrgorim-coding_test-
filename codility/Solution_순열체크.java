package codility;

import java.util.HashSet;

class Solution_순열체크 {
    public int solution(int[] A) {
        HashSet<Integer> perm = new HashSet<>();
        int maxValue = A.length;
        for(int idx = 0; idx < A.length; idx++) {
            if(maxValue < A[idx]) {
                continue;
            }

            perm.add(A[idx]);
        }

        if(perm.size() == A.length) {
            return 1;
        } else {
            return 0;
        }
    }
}

