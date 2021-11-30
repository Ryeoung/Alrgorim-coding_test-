package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @Domain  : 백준
 * @Num     : 
 * @Title   : HTML
 * @Date    : 2021/11/30
 * @WepLink : https://www.acmicpc.net/problem/6581
 */
public class Main_HTML {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "\\s";
        int letterCnt;
        String oneLine = "";
        while ((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);

            while(st.hasMoreTokens()) {
                String word = st.nextToken();
                if(word.equals("<br>")) {
                    nextLine(sb, oneLine);
                    oneLine = "";
                } else if(word.equals("<hr>")) {
                    nextLine(sb, oneLine);
                    for(int cnt = 0; cnt < 8; cnt++) {
                        sb.append("----------");
                    }
                    oneLine = "";

                } else {
                    //일반 단어들
                    if(word.length() + oneLine.length() > 80) {
                        nextLine(sb, oneLine);
                        oneLine = "";
                    }
                    oneLine += word + " ";
                }
            }
        } //while

        System.out.print(sb.append(oneLine.trim()));

    }
    static void nextLine(StringBuilder sb, String input) {
        sb.append(input.trim()).append("/n");

    }
}
