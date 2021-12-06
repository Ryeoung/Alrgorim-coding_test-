package programmers;

import java.util.*;

class Solution_수식최대화 {
    public static void main(String[] args) {
        System.out.println(new Solution_수식최대화().solution(
                "100-200*300-500+20"
        ));
    }
    long max = 0;

    public long solution(String expression) {
        long answer = 0;
        HashSet<Character> opSet = new HashSet<>();
        int expressionLen = expression.length();
        for (int idx = 0; idx < expressionLen; idx++) {
            char ch = expression.charAt(idx);
            if (isOp(ch)) {
                opSet.add(ch);
            }
        }
        // 순열
        char[] op = new char[opSet.size()];
        boolean[] visited = new boolean[opSet.size()];
        char[] result = new char[opSet.size()];
        int idx = 0;
        for (char ch : opSet) {
            op[idx++] = ch;
        }
        max = 0;
        perm(op, visited, result, 0, expression);

        return answer = max;
    }

    void perm(char[] op, boolean[] visited, char[] result, int depth, String exp) {
        if (depth == op.length) {
            // 순열된상태 result..
            // for(int idx = 0; idx < result.length; idx++) {
            //     System.out.print(result[idx] + " ");
            // }
            // System.out.println();
            long value = getResultOfExpression(result, exp);
            value = Math.abs(value);
            max = Math.max(max, value);
            return;
        }

        for (int idx = 0; idx < op.length; idx++) {
            if (visited[idx]) {
                continue;
            }

            visited[idx] = true;
            result[depth] = op[idx];
            perm(op, visited, result, depth + 1, exp);

            result[depth] = ' ';
            visited[idx] = false;
        }
    }

    long getResultOfExpression(char[] result, String exp) {
        long value = 0;
        Map<Character, Integer> priorMap = new HashMap<>();
        int resultSize = result.length;

        for (int idx = 0; idx < resultSize; idx++) {
            priorMap.put(result[idx], idx);
        }

        Stack<Long> stack = new Stack<>();
        Stack<Character> opStack = new Stack<>();

        int expLen = exp.length();
        for (int idx = 0; idx < expLen; idx++) {
            char ch = exp.charAt(idx);
            if (isOp(ch)) {
                //
                stack.push(value);
                value = 0;
                System.out.println(value);
                while (opStack.size() > 0 && priorMap.get(opStack.peek()) >= priorMap.get(ch)) {
                    long i2 = stack.pop();
                    long i1 = stack.pop();
                    char op = opStack.pop();
                    long v = operation(i1, i2, op);
                    stack.push(v);
                }
                opStack.push(ch);
            } else {
                value *= 10;
                value += (ch - '0');
            } // else
        } //for exp
        stack.push(value);
        while(!opStack.isEmpty()) {
                long i2 = stack.pop();
                long i1 = stack.pop();
                char op = opStack.pop();
                long v = operation(i1, i2, op);
                stack.push(v);

        }
        value = stack.pop();
        return value;
    }

    long operation(long i1, long i2, char op) {
        long value = 0;
        if (op == '+') {
            value = i1 + i2;
        } else if (op == '-') {
            value = i1 - i2;
        } else if (op == '*') {
            value = i1 * i2;
        }
        return value;
    }

    boolean isOp(char ch) {
        if (ch == '+' || ch == '-' || ch == '*') {
            return true;
        }
        return false;
    }


}
