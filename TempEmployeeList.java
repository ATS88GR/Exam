package ExamTaskV2;

import java.util.ArrayList;
import java.util.List;

public class TempEmployeeList {
    private final List<Employee> tempList = new ArrayList<>();

    public List<Employee> getTempList() {
        return tempList;
    }

    public void addTempList(List <Employee> list){
        tempList.addAll(list);
    }
}
