package programmers;

import java.util.*;

class Solution_게임맵최단거리 {
    public int solution(int[][] maps) {
        int answer = bfs(maps);


        return answer;
    }
    public int bfs(int[][] map) {
        LinkedList<Pos> queue = new LinkedList<>();
        boolean[][] visited = new boolean[map.length][map[0].length];
        queue.add(new Pos(0 , 0, 1));
        visited[0][0] = true;
        int[] drow = {0, 1, 0, -1};
        int[] dcol = {1, 0, -1, 0};

        while(!queue.isEmpty()) {
            Pos cur= queue.poll();
            for(int dir = 0; dir < 4; dir++) {
                int trow = cur.row + drow[dir];
                int tcol = cur.col + dcol[dir];

                if(trow < 0 || tcol < 0 || trow >= map.length || tcol >= map[0].length) {
                    continue;
                }

                if(visited[trow][tcol]) {
                    continue;
                }

                if(map[trow][tcol] == 0) {
                    continue;
                }

                if(trow == map.length - 1 && tcol == map[0].length - 1) {
                    return cur.cnt + 1;

                }


                visited[trow][tcol] = true;
                queue.add(new Pos(trow, tcol, cur.cnt + 1));
            }
        }

        return -1;
    }
    class Pos {
        int row;
        int col;
        int cnt;

        public Pos(int row, int col, int cnt) {
            this.row = row;
            this.col = col;
            this.cnt = cnt;
        }
    }
}