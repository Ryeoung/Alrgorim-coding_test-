package programmers;

import java.util.*;

class Solution_리틀프렌즈사천성 {
    public static void main(String[] args) {
        System.out.println(new Solution_리틀프렌즈사천성().solution( 3, 3, new String[]{
                "BAB", ".A.", "C.C"
        }));
    }
    public String solution(int m, int n, String[] board) {
        String answer = "";
        char[][] map = new char[m][n];
        Map<Character, List<Pos>> posMap = new HashMap<>();

        for(int row = 0; row < m; row++) {
            map[row] = board[row].toCharArray();
            for(int col = 0; col < n; col++) {
                char ch = map[row][col];
                if(ch >= 'A' && ch <= 'Z') {
                    List<Pos> list = null;
                    if(!posMap.containsKey(ch)) {
                        list = new ArrayList<>();

                    } else {
                        list = posMap.get(ch);
                    }
                    list.add(new Pos(row, col));
                    posMap.put(ch, list);

                }
            }
        }
        int brokenCharLen = posMap.size();
        PriorityQueue<Character> pq = new PriorityQueue();
        StringBuilder sb = new StringBuilder();
        do{
            if(!pq.isEmpty()) {
                char brokenChar = pq.poll();
                sb.append(brokenChar);
                for(Pos p :posMap.get(brokenChar)) {
                    map[p.row][p.col] = '.';
                }
                posMap.remove(brokenChar);
                pq.clear();
            }
            for(Character startChar: posMap.keySet()) {
                Pos startPos = posMap.get(startChar).get(0);
                Pos targetPos = posMap.get(startChar).get(1);
                boolean isFind = bfs(startPos,targetPos , map);

                if(isFind) {
                    pq.add(startChar);

                }

            }


        } while(!pq.isEmpty());
        if(sb.length() != brokenCharLen) {
            sb = new StringBuilder();
            sb.append("IMPOSSIBLE");
        }

        return answer = sb.toString();
    }

    boolean bfs(Pos start, Pos fin, char[][] map) {
        LinkedList<Pos> queue = new LinkedList<>();
        queue.add(new Pos(start.row, start.col, 0, 0));
        queue.add(new Pos(start.row, start.col, 1, 0));
        queue.add(new Pos(start.row, start.col, 2, 0));
        queue.add(new Pos(start.row, start.col, 3, 0));



        int[] drow = {0, 1, 0, -1, 0};
        int[] dcol = {1, 0, -1, 0};
        boolean match = false;
        while(!queue.isEmpty()) {
            Pos cur = queue.poll();
            if(cur.dirCnt  > 1) {
                continue;
            }

            for(int dir = 0; dir < 4; dir++) {
                int trow = cur.row  + drow[dir];
                int tcol = cur.col  + dcol[dir];
                int dirCnt = cur.dirCnt;
                if(cur.dir != dir){
                    dirCnt +=1;
                }

                if(isOutOfRange(trow, tcol, map.length, map[0].length)) {
                    continue;
                }
                if(map[trow][tcol] == '*') {
                    continue;
                }

                if(map[trow][tcol] == '.' || map[trow][tcol] == map[fin.row][fin.col]) {
                    if(trow == fin.row && tcol == fin.col) {
                        return true;
                    }
                    queue.add(new Pos(trow, tcol, dir, dirCnt));
                }





            }

        }


        return false;

    }
    boolean isOutOfRange(int row, int col, int height, int width) {
        if(row < 0 || col < 0 || row >= height || col >= width) {
            return true;
        }

        return false;
    }

    class Pos{
        int row;
        int col;
        int dir;
        int dirCnt;
        public Pos(int row , int col) {
            this.row = row;
            this.col = col;
        }

        public Pos(int row , int col, int dir, int dirCnt) {
            this.row = row;
            this.col = col;
            this.dir = dir;
            this.dirCnt = dirCnt;
        }
    }
}