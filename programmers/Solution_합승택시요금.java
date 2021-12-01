package programmers;
import java.util.*;

class Solution_합승택시요금 {
    int[][] dp;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        LinkedList<Node>[] graph = new LinkedList[n+1];

        for(int idx = 1; idx <= n; idx++) {
            graph[idx] = new LinkedList<>();
        }

        for(int row = 0; row < fares.length; row++) {
            int start = fares[row][0];
            int end = fares[row][1];
            int dist = fares[row][2];
            graph[start].add(new Node(end, dist));
            graph[end].add(new Node(start, dist));
        }
        dp = new int[n + 1][n + 1];

        int[] pos = new int[3];
        pos[0] = s;
        pos[1] = a;
        pos[2] = b;
        for(int posIdx = 0; posIdx < 3; posIdx++) {
            Arrays.fill(dp[pos[posIdx]], Integer.MAX_VALUE);
            bfs(graph, pos[posIdx]);
        }

        answer = Integer.MAX_VALUE;
        for(int num = 1; num <= n; num++) {
            if(dp[s][num] == Integer.MAX_VALUE ||
                    dp[a][num] == Integer.MAX_VALUE ||
                    dp[b][num] == Integer.MAX_VALUE) {
                continue;
            }
            int total = dp[s][num] + dp[a][num] + dp[b][num];
            answer = Math.min(answer, total);
        }

        return answer;
    }

    public void bfs(LinkedList<Node>[] graph, int s) {
        PriorityQueue<Node> queue = new PriorityQueue<>();

        dp[s][s] = 0;
        queue.add(new Node(s, 0));

        Node startNode = null;

        while(!queue.isEmpty()) {
            startNode = queue.poll();
            if(dp[s][startNode.num] < startNode.dist) continue;
            for(Node endNode: graph[startNode.num]) {
                if(dp[s][endNode.num] > dp[s][startNode.num] + endNode.dist) {
                    dp[s][endNode.num] = dp[s][startNode.num] + endNode.dist;
                    queue.add(new Node(endNode.num, dp[s][startNode.num] + endNode.dist));
                }
            }
        }



    }

    class Node implements Comparable<Node>{
        int num;
        int dist;

        public Node(int num, int dist) {
            this.num = num;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node t) {
            return this.dist - t.dist;
        }
    }
}