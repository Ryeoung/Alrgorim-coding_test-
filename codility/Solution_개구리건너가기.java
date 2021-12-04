package codility;

import java.util.HashSet;

class Solution_개구리건너가기 {
    public int solution(int X, int[] A) {
        HashSet<Integer> leaves = new HashSet<>();
        int ans = -1;
        for(int idx = 0; idx < A.length; idx++) {
            if(X < A[idx]) {
                continue;
            }

            leaves.add(A[idx]);
            if(leaves.size() == X) {
                ans = idx;
                break;
            }
        }
        return ans;
    }
}