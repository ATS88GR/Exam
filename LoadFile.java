package ExamTaskV2;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class LoadFile {

    public static void load (String path, EmployeeList loadEmpList) {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            TempEmployeeList tel = (TempEmployeeList) ois.readObject();
            loadEmpList.setBaseList(tel.getTempBaseList()); // read List
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
