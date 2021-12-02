package programmers;

import java.util.*;
class Solution_카드짝맞추기 {
    List<int[]> orders;
    Map<Integer, List<Pos>> map;
    int min;
    public int solution(int[][] board, int r, int c) {
        int answer = 0;
        map = new HashMap<>();
        for(int row = 0; row < board.length; row ++) {
            for(int col = 0; col < board[0].length; col++) {
                if(board[row][col] > 0) {
                    Pos pos = new Pos(row, col, board[row][col], 0);
                    if(map.containsKey(board[row][col])) {
                        map.get(board[row][col]).add(pos);
                    } else {
                        LinkedList<Pos> list = new LinkedList<>();
                        list.add(pos);
                        map.put(board[row][col], list);
                    }
                }
            }
        }
        boolean[] visited = new boolean[map.size()];
        int[] arr = new int[map.size()];
        orders = new LinkedList<>();
        for(int idx = 0; idx < map.size(); idx++) {
            arr[idx] = idx+1;
        }
        int[] orderCardId = new int[map.size()];
        perm(orderCardId, visited , 0);
        min = Integer.MAX_VALUE;
        for(int[] o: orders) {
            int[][] copied = copyOfBoard(board);
            dfs(copied, new Pos(r, c, 0 ,0), 0, o, 0);
        }

        return answer = min;
    }

    private void dfs(int[][] copied, Pos start, int depth, int[] order, int cnt) {
        if(depth == order.length) {
            if(min > cnt) {
                min = cnt;
            }
            return ;
        }
        int num = order[depth]; // 1~ 3 중 하나
        Pos a = map.get(num).get(0);
        Pos b = map.get(num).get(1);
        int calcCnt = cnt;
        //start -> 1A -> 1B
        int one = bfs(copied, start, a) + bfs(copied, a, b) + 2;
        int two = bfs(copied, start, b) + bfs(copied, b, a) + 2;

        copied[a.row][a.col] = 0;
        copied[b.row][b.col] = 0;

        dfs(copied, b, depth + 1, order, calcCnt + one);
        dfs(copied, a, depth + 1, order, calcCnt + two);

        copied[a.row][a.col] = a.num;
        copied[b.row][b.col] = a.num;


        //bfs() // start -> 1B -> 1A

    }

    int bfs(int[][] board, Pos start, Pos des) {
        int cnt = 0;

        LinkedList<Pos> queue = new LinkedList<>();
        boolean[][] visited = new boolean[board.length][board.length];
        queue.add(start);
        int[] drow = {0, 1, 0, -1};
        int[] dcol = {1, 0, -1, 0};

        while(!queue.isEmpty()) {
            Pos from = queue.poll();
            if(from.row == des.row && from.col == des.col) {
                return from.cnt;
            }

            for(int dir = 0; dir < 4; dir++) {
                int trow = from.row + drow[dir];
                int tcol = from.col + dcol[dir];

                if(isOutOfMap(trow, tcol, board.length, board.length)) {
                    continue;
                }

                if(!visited[trow][tcol]) {
                    visited[trow][tcol] = true;
                    queue.add(new Pos(trow, tcol, from.num, from.cnt + 1));
                }

                for(int j = 0; j < 2; j++) {
                    if(board[trow][tcol] != 0) {
                        break;
                    }

                    if(isOutOfMap(trow + drow[dir], tcol + dcol[dir],
                            board.length, board.length)) {
                        break;
                    }
                    trow += drow[dir];
                    tcol += dcol[dir];
                }
                if(!visited[trow][tcol]) {
                    visited[trow][tcol] = true;
                    queue.add(new Pos(trow, tcol, from.num, from.cnt + 1));
                }

            }


        }

        return 0;

    }
    boolean isOutOfMap(int row, int col, int rowMax, int colMax) {
        if(row < 0 || col < 0 || row >= rowMax || col >= colMax) {
            return true;
        }

        return false;
    }
    int[][] copyOfBoard(int[][] board) {
        int[][] copy = new int[board.length][board.length];
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board.length; col++) {
                copy[row][col] = board[row][col];
            }
        }
        return copy;
    }
    private void perm(int[] orderCardId, boolean[] visited, int dept) {
        if (dept == orderCardId.length) {
            orders.add(orderCardId.clone());
            return;
        }
        for(int idx = 0; idx < orderCardId.length; idx++) {
            if(visited[idx]) {
                continue;
            }

            visited[idx] = true;
            orderCardId[dept] = idx + 1;
            perm(orderCardId, visited, dept + 1);
            orderCardId[dept] = 0;
            visited[idx] = false;
        }

    }


    class Pos{
        int row;
        int col;
        int num;
        int cnt;

        public Pos(int row, int col, int num, int cnt) {
            this.row = row;
            this.col = col;
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "row=" + row +
                    ", col=" + col +
                    ", num=" + num +
                    ", cnt=" + cnt +
                    '}';
        }
    }
}