package programmers;
import java.util.*;

class Solution_순위검색 {
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        P[] database = new P[info.length];
        int databaseSize = database.length;

        //파싱해서 P에 저장
        for(int idx = 0; idx < databaseSize; idx++) {
            database[idx] = new P(info[idx]);
        }

        Arrays.sort(database, new Comparator<P>() {

            @Override
            public int compare(P t1, P t2) {
                return t1.score - t2.score;
            }
        });


        int querySize = query.length;
        for(int idx = 0; idx < querySize; idx++) {
            String[] splitArray = query[idx].split("and");
            String lang = splitArray[0].trim();
            String job = splitArray[1].trim();
            String carrer = splitArray[2].trim();

            String[] foodAndScore = splitArray[3].trim().split(" ");
            String food = foodAndScore[0];
            int targetScore = Integer.parseInt(foodAndScore[1]);

            int start = 0;
            int end = databaseSize - 1;
            while(start < end) {
                int mid = (start + end) / 2;
                int curScore = database[mid].score;

                if( targetScore <= curScore ) {
                    end = mid;

                } else {
                    start = mid + 1;
                }
            }
            int cnt = 0;
            for(int databaseIdx = start; databaseIdx < databaseSize; databaseIdx++ ) {
                if(database[databaseIdx].isCorrect(lang, job, carrer, food)) {
                    cnt++;
                }
            }
            answer[idx] = cnt;

        }



        return answer;
    }

    class P {
        String lang;
        String job;
        String carrer;
        String food;
        int score;

        public P(String lang, String Job, String carrer, String food, String score) {
            this.lang = lang;
            this.job = job;
            this.carrer = carrer;
            this.food = food;
            this.score = Integer.parseInt(score);
        }

        public P(String info) {
            String[] splitArray = info.split(" ");
            this.lang = splitArray[0];
            this.job = splitArray[1];
            this.carrer = splitArray[2];
            this.food = splitArray[3];
            this.score = Integer.parseInt(splitArray[4]);
        }

        public boolean isCorrect(String lang, String job, String carrer, String food) {
            if( (this.lang.equals(lang) || lang.equals("-")) &&
                    (this.job.equals(job) || job.equals("-") ) &&
                    (this.carrer.equals(carrer) || carrer.equals("-") ) &&
                    (this.food.equals(food) || food.equals("-") )) {
                return true;
            }

            return false;
        }

        // @Override
        // public int compareTo(P t) {
        //     return this.score - t.score;
        // }
    }
}