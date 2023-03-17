package ExamTaskV2;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SaveFile {

    public static void save(String path, EmployeeList saveEmpList) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))){
            TempEmployeeList tel = new TempEmployeeList(saveEmpList); // Save serialized object in List
            oos.writeObject(tel); // Write object in file
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
