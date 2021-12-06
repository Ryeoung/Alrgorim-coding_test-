package programmers;

import java.util.*;

class Solution_거리두기 {
    public int[] solution(String[][] places) {
        int[] answer = {};
        int placeSize = places.length;
        answer = new int[placeSize];

        for (int placeIdx = 0; placeIdx < placeSize; placeIdx++) {
            // place 대기실
            String[] place = places[placeIdx];
            int height = place.length;
            int width = place[0].length();
            answer[placeIdx] = 1; // 초기화

            check:
            for (int h = 0; h < height; h++) {
                for (int w = 0; w < width; w++) {
                    if (place[h].charAt(w) == 'P') {
                        // 근방 거리 2 이내 사람 체크
                        boolean isBlock = bfs(place, new Pos(h, w));
                        if (!isBlock) {
                            // System.out.println(p1.row + " " + p1.col);
                            // System.out.println(p2.row + " " + p2.col);

                            answer[placeIdx] = 0;
                            break check;
                        }
                    }

                }
            }


        }
        return answer;
    }

    boolean bfs(String[] place, Pos start) {
        LinkedList<Pos> queue = new LinkedList<>();
        boolean[][] visited = new boolean[place.length][place[0].length()];
        queue.add(start);
        visited[start.row][start.col] = true;
        int[] drow = {0, 1, 0, -1};
        int[] dcol = {1, 0, -1, 0};

        while (!queue.isEmpty()) {
            Pos from = queue.poll();

            for (int dir = 0; dir < 4; dir++) {
                int trow = from.row + drow[dir];
                int tcol = from.col + dcol[dir];

                if (trow < 0 || tcol < 0 || trow >= place.length || tcol >= place[0].length()) {
                    continue;
                }

                if (visited[trow][tcol]) {
                    continue;
                }

                if (from.cnt + 1 > 2) {
                    continue;
                }

                if (place[trow].charAt(tcol) == 'X') {
                    continue;
                }
                //
                if (place[trow].charAt(tcol) == 'P') {
                    return false;
                }


                visited[trow][tcol] = true;
                queue.add(new Pos(trow, tcol, from.cnt + 1));
            }
        }

        return true;

    }

    class Pos {
        int row;
        int col;
        int cnt = 0;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Pos(int row, int col, int cnt) {
            this.row = row;
            this.col = col;
            this.cnt = cnt;
        }
    }
}