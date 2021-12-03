package programmers;

import java.util.*;

class Solution_자물쇠와열쇠 {
    public static void main(String[] args) {
        System.out.println(
                new Solution_자물쇠와열쇠().solution(
                        new int[][] {
                                {0, 0, 0}, {1, 0, 0}, {0, 1, 1}
                        },
                        new int[][] {
                                {1, 1, 1}, {1, 1, 0}, {1, 0, 1}
                        }

                )
        );
    }
    int[][] map;
    public boolean solution(int[][] key, int[][] lock) {


        map = new int[lock.length + (key.length - 1) * 2][lock.length + (key.length - 1) * 2];
        for(int row = 0; row < lock.length; row++) {
            for(int col = 0; col < lock.length; col++) {
                map[row + key.length - 1][col + key.length - 1] = lock[row][col];
            }
        }
        // for(int row = 0; row < map.length; row++) {
        //     for(int col = 0; col < map.length; col++) {
        //         System.out.print(map[row][col]);
        //     }
        //     System.out.println();
        // }
        boolean answer = bfs(key, lock.length);


        return answer;
    }
    boolean bfs(int[][] key, int lockLen) {
        boolean isOk = false;
        Key k = new Key(0 , 0, key);
        LinkedList<Key> queue = new LinkedList<>();
        queue.add(k);
        boolean[][] visited = new boolean[map.length][map[0].length];
        int[] drow = {0, 1, 0, -1};
        int[] dcol = {1, 0, -1, 0};
        visited[k.row][k.col] = true;
        while(!queue.isEmpty()) {
            Key cur = queue.poll();
            if(cur.row ==3 && cur.col == 3) {
                System.out.println();
            }
            // 90 도 4번 확인

            for(int t = 0; t < 4; t++) {
                if(cur.isMatch(map, lockLen)) {
                    return true;
                }
                cur.turn();
            }

            // 4방향 이동
            for(int dir = 0; dir < 4; dir++) {
                int trow = cur.row + drow[dir];
                int tcol = cur.col + dcol[dir];

                if(trow < 0 || tcol < 0 || trow + cur.shape.length - 1 >= map.length ||
                        tcol + cur.shape.length - 1 >= map[0].length) {
                    continue;
                }

                if(visited[trow][tcol]) {
                    continue;
                }

                visited[trow][tcol] = true;
                queue.add(new Key(trow, tcol, cur.shape));

            }
        }


        return false;
    }
    class Key {
        int row;
        int col;
        int[][] shape;

        public Key(int row, int col, int[][] shape) {
            this.row = row;
            this.col = col;
            this.shape = shape;
        }
        public int[][] copy(int[][] shape) {
            int[][] temp = new int[shape.length][shape.length];
            for(int row = 0; row < shape.length; row++) {
                for(int col = 0; col < shape.length; col++) {
                    temp[row][col] = shape[row][col];
                }
            }
            return temp;
        }

        public void turn() {
            int[][] temp = new int[shape.length][shape.length];
            for(int row = 0; row < shape.length; row++) {
                for(int col = 0; col < shape.length; col++) {
                    temp[col][shape.length - 1 - row] = shape[row][col];
                }
            }
            this.shape = temp;
        }

        public boolean isMatch(int[][] map,int rockLen) {
            int change = 0;
            int[][] board = copy(map);
            for(int r = 0; r < this.shape.length; r++) {
                for(int c = 0; c < this.shape.length; c++) {
                    board[this.row + r][this.col + c] += this.shape[r][c];
                }
            }

            for(int r = 0; r < rockLen; r++) {
                for(int c = 0; c < rockLen; c++) {
                    if(board[r + this.shape.length - 1][c + this.shape.length - 1] != 1) {
                        return false;
                    }
                }
            }


            return true;
        }
    }
}