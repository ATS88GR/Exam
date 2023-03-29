package ExamTaskV2;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SaveFile {

    public static void save(String path, EmployeeList saveEmpList) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))){
            oos.writeObject(saveEmpList); // Write object in file
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
