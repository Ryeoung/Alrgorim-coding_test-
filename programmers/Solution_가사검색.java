package programmers;

/**
 * 정확성은 맞는데 효율성이 1, 2, 3 틀림.... 아마 다른 문제일 듯.
 * trie 구조를 사용해서 풀어야 된다는 데 나중에 다시 해보기...
 */
class Solution_가사검색 {
    public static void main(String[] args) {
        System.out.println(new Solution_가사검색().solution(
                new String[] {
                        "frodo", "front", "frost", "frozen", "frame", "kakao"
                }, new String[] {
                        "fro??", "????o", "fr???", "fro???", "pro?"
                }
        ));
    }
    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        int querySize = queries.length;
        int start = 0, end = 0;
        for(int idx = 0; idx < querySize; idx++) {
            String query = queries[idx];
            // ?? <- 인덱스를 구해준다
            char ch = query.charAt(0);
            start = 0;
            end = query.length() - 1;
            if(ch == '?') {
                //접두사에 ?
                end = binarySearchOfUpperBound(query);
            } else {
                //접미사에 ?
                start = binarySearchOfLowerBound(query);
            }


            // binarySearch

            int search = 0;
            for(int wordIdx = 0; wordIdx < words.length; wordIdx++) {
                String word = words[wordIdx];
                //길이가 다르면
                if(word.length() != query.length()) {
                    continue;
                }
                if(start == 0 && end == word.length() - 1) {
                    //모두 다 ?로 이루어져있다. 길이만 맞으면 다 맞다.
                    answer[idx]++;
                    continue;
                }

                String a = "", b = "";
                if(start > 0) {
                    // 접미사..abc???
                    a = query.substring(0, start);
                    b = words[wordIdx].substring(0, start);
                } else {
                    //접두사. ???abc
                    a = query.substring(end + 1);
                    b = words[wordIdx].substring(end + 1);
                }
                if(a.equals(b)) {
                    answer[idx] ++;
                }
            }
        }
        return answer;
    }
    // 이게 접두사가 ???
    int binarySearchOfUpperBound(String query) {
        int left = 0;
        int right = query.length() - 1;
        while(left < right) {
            int mid = (left + right) / 2;
            // System.out.println(mid);
            if( query.charAt(mid) != '?') {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    //접미사가 ??
    int binarySearchOfLowerBound(String query) {
        int left = 0;
        int right = query.length() - 1;
        while(left < right) {
            int mid = (left + right) / 2;

            if( query.charAt(mid) == '?') {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}