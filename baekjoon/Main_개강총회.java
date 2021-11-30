package baekjoon;

/**
 * @Domain  : 백준
 * @Num     : 
 * @Title   : 싸이버개강총회
 * @Date    : 2021/11/30
 * @WepLink : https://www.acmicpc.net/problem/19583
 *
 * && 을 사용할 때 조심핮...
 */

import java.io.*;
import java.util.*;
public class Main_개강총회 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String start = st.nextToken();
        String fin = st.nextToken();
        String streamingFin = st.nextToken();
        HashMap<String, boolean[]> peoples = new HashMap<>();

        String log = null;
        while (true) {
            log = br.readLine();
            if(log == null) {
                break;
            }
            st = new StringTokenizer(log);
            String inTime = st.nextToken();
            String name = st.nextToken();

            // 입장 체크
            if (timeCompare(inTime, start) <= 0) {
                if (peoples.containsKey(name)) {
                    peoples.get(name)[0] = true;
                } else {
                    boolean[] check = new boolean[2];
                    check[0] = true;
                    peoples.put(name, check);
                }
            }

            if (timeCompare(inTime, fin) >= 0 && timeCompare(inTime, streamingFin) <= 0) {
                if (peoples.containsKey(name) ) {
                        peoples.get(name)[1] = true;

                } else {
                    boolean[] check = new boolean[2];
                    check[1] = true;
                    peoples.put(name, check);
                }
            }
        }

        int cnt = 0;
        Iterator<String> itr = peoples.keySet().iterator();
        while (itr.hasNext()) {
            String name = itr.next();
            if (peoples.get(name)[0] && peoples.get(name)[1]) {
                cnt++;
                System.out.println(name);
            }
        }

        System.out.println(cnt);
    }

    // t1이 빠르면 음수
    // t2가 빠르면 양수
    // 같으면 0
    static int timeCompare(String t1, String t2) {
        int h1 = Integer.parseInt(t1.substring(0, 2));
        int m1 = Integer.parseInt(t1.substring(3, 5));

        int h2 = Integer.parseInt(t2.substring(0, 2));
        int m2 = Integer.parseInt(t2.substring(3, 5));

        if (h1 > h2) {
            return 1;
        } else if (h1 < h2) {
            return -1;
        } else {
            if (m1 > m2) {
                return 1;
            } else if (m1 < m2) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}