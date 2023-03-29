package ExamTaskV2;

import java.util.Comparator;

public class DevoteComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return Integer.compare(o1.getEmpDate().compareTo(o2.getEmpDate()), 0);
    }
}
