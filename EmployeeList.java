package ExamTaskV2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EmployeeList implements Serializable {
    private List<Employee> baseList = new ArrayList<>();

    public EmployeeList() {
    }

    public List<Employee> getBaseList() {
        return baseList;
    }

    public void setBaseList(List<Employee> baseList) {
        this.baseList = baseList;
    }
}
