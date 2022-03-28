package algorithm.interview.gs;

import java.util.ArrayList;
import java.util.List;

public class FindTheRank {

    private static class Student {
        private int mark;
        private int order;
    }

    private static int findTheRank(int[][] performance, int rank) {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < performance.length; i++) {
            int mark = 0;
            for (int p : performance[i]) {
                mark += p;
            }
            Student student = new Student();
            student.mark = mark;
            student.order = i;
            students.add(student);
        }
        students.sort((a, b) -> a.mark != b.mark ? b.mark - a.mark : a.order - b.order);
        return students.get(rank - 1).order;
    }
}
