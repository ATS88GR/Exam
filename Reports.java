package ExamTaskV2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
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
            case 1 -> {structOrg();}
            case 2 -> {averageSalary();}
            case 3 -> {topSalary();}
            case 4 -> {devotedEmployee();}
        }
    }

    private static void devotedEmployee() {
        TempEmployeeList topDevoted = new TempEmployeeList();
        topDevoted.addTempList(Program.getEmployeeList().getBaseList());
        Comparator <? super Employee> DevoteComparator = new DevoteComparator();
        topDevoted.getTempList().sort(DevoteComparator);
        topDevoted.getTempList().stream().limit(10).forEach(worker ->System.out.printf("Name: %s, " +
                        "surname: %s, job title: %s, department name: %s, salary: %d, employee date: %tD\n",
                worker.getName(), worker.getSurname(), worker.getJobTitle(), worker.getDepName(),
                worker.getSalary(), worker.getEmpDate()));
       /* System.out.println();
        System.out.println("Save result to file? (y/n)");
        if(sc.nextLine().equalsIgnoreCase("y")){
            try(FileWriter writer = new FileWriter("C:\\Users\\user\\eclipse-workspace\\Exam\\ReportTopDevoted.txt")) {
                for (Employee worker : topDevoted.getTempList())
                    writer.write("Name: " + worker.getName() + ", surname: " + worker.getSurname() +
                            ", job title: " + worker.getJobTitle() + ", department name: " +
                            worker.getDepName() + " salary: " + worker.getSalary() + " employement date " +
                            worker.getEmpDate() +"\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else System.out.println("You choose exit without saving the file");*/
    }

    private static void topSalary() {
        TempEmployeeList topSel = new TempEmployeeList();
        topSel.addTempList(Program.getEmployeeList().getBaseList());
        Comparator<? super Employee> SalaryComparator = new SalaryComparator();
        topSel.getTempList().sort(SalaryComparator);
        topSel.getTempList().stream().limit(10).forEach(worker ->System.out.printf("Name: %s, " +
                        "surname: %s, job title: %s, department name: %s, salary: %d\n",
                worker.getName(), worker.getSurname(), worker.getJobTitle(), worker.getDepName(), worker.getSalary()));
        System.out.println();
        System.out.println("Save result to file? (y/n)");
        if(sc.nextLine().equalsIgnoreCase("y")){
            try(FileWriter writer = new FileWriter("C:\\Users\\user\\eclipse-workspace\\Exam\\ReportTopSalary.txt")) {
                for (Employee worker : topSel.getTempList())
                    writer.write("Name: " + worker.getName() + ", surname: " + worker.getSurname() +
                                ", job title: " + worker.getJobTitle() + ", department name: " +
                        worker.getDepName() + " salary: " + worker.getSalary() +"\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else System.out.println("You choose exit without saving the file");
    }

    private static void averageSalary() {
        HashSet<String> departments = new HashSet<>();
        for (Employee worker:Program.getEmployeeList().getBaseList())
            departments.add(worker.getDepName());
        System.out.println("Select the type of average salary by company or department(c/d)");
        String avSalaryType = sc.nextLine();
        int sum = 0;
        String typeAvSalary = "";
        if(avSalaryType.equalsIgnoreCase("c") || avSalaryType.equalsIgnoreCase("d")) {
            if (avSalaryType.equalsIgnoreCase("c")) {
                for (Employee worker : Program.getEmployeeList().getBaseList()) sum += worker.getSalary();
                typeAvSalary = "Average salary by company " + sum / Program.getEmployeeList().getBaseList().size();
            } else if (avSalaryType.equalsIgnoreCase("d")) {
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

    private static void structOrg(){
        HashSet<String> depAndBoss = new HashSet<>();
        for (Employee worker:Program.getEmployeeList().getBaseList())
            depAndBoss.add(worker.getDepName() + ", boss surname is " + worker.getBossSurname());
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
