package ExamTaskV2;

import java.io.Serializable;
import java.util.List;

public class TempEmployeeList implements Serializable {

    private List<Employee> tempBaseList;
    public TempEmployeeList(EmployeeList employeeList) {
        tempBaseList = employeeList.getBaseList();
    }

    public List<Employee> getTempBaseList() {
        return tempBaseList;
    }

    public void setTempBaseList(List<Employee> tempBaseList) {
        this.tempBaseList = tempBaseList;
    }


}
