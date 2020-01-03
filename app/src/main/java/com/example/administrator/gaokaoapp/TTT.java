package check;

import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class TTT {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        char[] correctAnswer = new char[60];
        char[] answer = new char[60];
        int[] subTerm = {5,7,6,9,3,41,15,2,35};
        for(int i = 0; i<60; i++) {
            correctAnswer[i] = 'n';
            answer[i] = correctAnswer[i];
        }
        for(int i=0; i<subTerm.length; i++) {
            correctAnswer[subTerm[i]] = 'y';
            answer[subTerm[i]] = correctAnswer[subTerm[i]];
        }


        HashMap<String, Integer> map = new HashMap<>();
        String[] classes = {"C", "R", "I", "E", "S", "A"};

        for(int i = 0; i<6; i++) {
            int tempGrade = 0;
            for(int j = i*10; j<i*10+10; j++) {
                if(answer[j] == correctAnswer[j]) {
                    tempGrade++;
                }
            }
            map.put(classes[i], tempGrade);
        }

        Set<Entry<String, Integer>> set = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iter = set.iterator();
        while(iter.hasNext()) {
            Map.Entry<String, Integer> item = iter.next();
            System.out.println(item.getKey());
        }

        List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(set);

    }

}
