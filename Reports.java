package ExamTaskV2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

import static ExamTaskV2.Main.sc;

public class Reports {
    private static final HashSet<String> departments = new HashSet<>();
    public static void main() {
        System.out.println("""
             Select report type:
             1.Structure organization.
             2.Average salary.
             3.Top employee salary.
             4.Top devoted employers.
             5.Exit
             """);
        if (sc.hasNextLine()) {
            int chooseAct = Integer.parseInt(sc.nextLine());
            if (chooseAct != 5) {
                actions(chooseAct);
                main();
            }
        }
    }

    private static void actions(int chooseAct) {
        switch (chooseAct) {
            case 1 -> {structOrg(null);}
            case 2 -> {averageSalary();}
        }
    }

    private static void averageSalary() {
        System.out.println("Select the type of average salary by company or department(c/d)");
        String avSalaryType = sc.nextLine();
        int sum = 0;
        String typeAvSalary = "";
        if(avSalaryType.equalsIgnoreCase("c") || avSalaryType.equalsIgnoreCase("d")) {
            if (avSalaryType.equalsIgnoreCase("c")) {
                for (Employee worker : Program.getEmployeeList().getBaseList()) sum += worker.getSalary();
                typeAvSalary = "Average salary by company " + sum / Program.getEmployeeList().getBaseList().size();
            } else if (avSalaryType.equalsIgnoreCase("d")) {
                structOrg("departments");
                System.out.println(departments);
                System.out.println("Select the department (write)");
                String selDep = sc.nextLine();
                boolean searchDepResult = false;
                int countEmp = 0;
                for (String dep : departments)
                    if (selDep.equalsIgnoreCase(dep)) {
                        searchDepResult = true;
                        break;
                    }
                if (searchDepResult) {
                    for (Employee worker : Program.getEmployeeList().getBaseList())
                        if (selDep.equalsIgnoreCase(worker.getDepName())) {
                            sum += worker.getSalary();
                            ++countEmp;
                        }
                    typeAvSalary = "Average salary by department " + selDep + " is " + sum/countEmp;
                } else System.out.println("Department is not found. Try again");
            }
            System.out.println(typeAvSalary);
        System.out.println("Save result to file? (y/n)");
            if(sc.nextLine().equalsIgnoreCase("y")){
                try(FileWriter writer = new FileWriter("C:\\Users\\user\\eclipse-workspace\\Exam\\ReportAvSalary.txt")) {
                    writer.write(typeAvSalary);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } else System.out.println("Yur must select c or d. Try again");
    }

    private static void structOrg(String purpose){
        HashSet<String> depAndBoss = new HashSet<>();
        for (Employee worker:Program.getEmployeeList().getBaseList()) {
            depAndBoss.add(worker.getDepName() + ", boss surname is " + worker.getBossSurname());
            departments.add(worker.getDepName());
        }
        if(purpose==null) {
            System.out.println("Select the reporting option: console or file (c/f)");
            String choice = sc.nextLine().toLowerCase();
            if (choice.equals("c")) {
                depAndBoss.forEach(System.out::println);
                System.out.println();
            } else if (choice.equals("f")) {
                try (FileWriter writer = new FileWriter("C:\\Users\\user\\eclipse-workspace\\Exam\\ReportOrg.txt")) {
                    for (String orgInfo : depAndBoss)
                        writer.write(orgInfo + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else System.out.println("You must select console or file (c/f), try again");
        }
    }
}
