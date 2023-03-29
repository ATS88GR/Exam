package ExamTaskV2;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class LoadFile {

    public static void load (String path, EmployeeList loadEmpList) {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            loadEmpList.setBaseList(((EmployeeList) ois.readObject()).getBaseList()); // read List
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
