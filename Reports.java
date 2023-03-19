package ExamTaskV2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

import static ExamTaskV2.Main.sc;

public class Reports {
    public static void main() {
        System.out.println("""
             Select report type:
             1.Structure organization.
             2.Average salary.
             3.Top employee salary.
             4.Top devoted employers.
             5.Exit""");
        if (sc.hasNextLine()) {
            int chooseAct = Integer.parseInt(sc.nextLine());
            if (chooseAct != 5) {
                actions(chooseAct);
                main();
            }
        }
    }

    private static void actions(int chooseAct) {
        switch (chooseAct){
            case 1: {
                System.out.println("");
                structOrg();
            }
        }
    }

    private static void structOrg(){
        HashSet<String> departments = new HashSet<>();
        for (Employee worker:Program.getEmployeeList().getBaseList())
            departments.add(worker.getDepName() + ", boss surname is " + worker.getBossName());
        System.out.println("Select the reporting option: console or file(c/f)");
        String choice = sc.nextLine().toLowerCase();
        if(choice.equals("c"))
            departments.forEach(System.out::println);
        else if(choice.equals("f")){
            try (FileWriter writer = new FileWriter("C:\\Users\\user\\eclipse-workspace\\Exam\\ReportOrg.txt")) {
                for(String orgInfo: departments)
                    writer.write(orgInfo);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else System.out.println("You must select console or file(c/f), try again");
    }
}
