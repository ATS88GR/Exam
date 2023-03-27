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
             """);                      //Reports menu
        if (sc.hasNextLine()) {
            int chooseAct = Integer.parseInt(sc.nextLine());    //choose number of report
            if (chooseAct != 5) {   //if choose is not Exit
                actions(chooseAct); //go to choose action
                main();             //to choose next report or exit
            }
        }
    }

    private static void actions(int chooseAct) {
        switch (chooseAct) {
            case 1 -> {structOrg();}
            case 2 -> {averageSalary();}
            case 3 -> {topReports(1);}
            case 4 -> {topReports(2);}
        }
    }

    private static void topReports(int typeOfReport) {
        TempEmployeeList topList = new TempEmployeeList();              // top list for sort employees
        topList.addTempList(Program.getEmployeeList().getBaseList());   //copy base too topList
        Comparator <? super Employee> topComparator = null;
        String reportPath = "";
        switch (typeOfReport) {
            case 1 -> {
                topComparator = new SalaryComparator();
                reportPath = Main.path + "\\ReportTopSalary.txt";
            }
            case 2 -> {
                topComparator = new DevoteComparator();
                reportPath = Main.path + "\\ReportTopDevoted.txt";
            }
        }
        topList.getTempList().sort(topComparator);              //sort by choose comparator
        System.out.println("Save result to file? (y/n)");
        String saveToFile = sc.nextLine();        //save result of saving choose
        try (FileWriter writer = new FileWriter(reportPath, false)) {
        topList.getTempList().stream().limit(10).forEach(worker ->{         //stream for show result to console and save to file
            String strF = String.format("Name: %s,  surname: %s, job title: %s, department name: %s, boss name: %s," +
                            " boss surname: %s, data of employment(m/d/y): %tD, salary: %d\n",
                    worker.getName(), worker.getSurname(), worker.getJobTitle(), worker.getDepName(), worker.getBossName(),
                    worker.getBossSurname(), worker.getEmpDate(), worker.getSalary());
            System.out.print(strF);
            if (saveToFile.equalsIgnoreCase("y")) {     //if choose save to file
                try {
                    writer.write(strF);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        } catch (IOException e) {
        throw new RuntimeException(e);
        }
        System.out.println();
    }

    private static void averageSalary() {
        HashSet<String> departments = new HashSet<>();      //Hashset to save all department name
        for (Employee worker:Program.getEmployeeList().getBaseList())
            departments.add(worker.getDepName());
        System.out.println("Select the type of average salary by company or department(c/d)");
        String avSalaryType = sc.nextLine();    //to save choose report by company or department
        int sum = 0;                            //to save sum of salary's
        String typeAvSalary = "";               //to save result of average salary
        if(avSalaryType.equalsIgnoreCase("c") || avSalaryType.equalsIgnoreCase("d")) {  //if choose c or d
            if (avSalaryType.equalsIgnoreCase("c")) {           //if choose company
                for (Employee worker : Program.getEmployeeList().getBaseList()) sum += worker.getSalary();
                typeAvSalary = "Average salary by company " + sum / Program.getEmployeeList().getBaseList().size();
            } else if (avSalaryType.equalsIgnoreCase("d")) {    //if choose department
                System.out.println(departments);
                System.out.println("Select the department (write)");
                String selDep = sc.nextLine();
                boolean searchDepResult = false;            //to save result of search department
                int countEmp = 0;                           //count employees in department
                for (String dep : departments)              //checking the correct spelling of the department
                    if (selDep.equalsIgnoreCase(dep)) {
                        searchDepResult = true;
                        break;
                    }
                if (searchDepResult) {                      //if spelling is correct
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
            if(sc.nextLine().equalsIgnoreCase("y")){        //saving result to file
                try(FileWriter writer = new FileWriter(Main.path + "\\ReportAvSalary.txt")) {
                    writer.write(typeAvSalary);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } else System.out.println("Yur must select c or d. Try again");    //
    }

    private static void structOrg(){
        HashSet<String> depAndBoss = new HashSet<>();       //HashSet to save list of departments and boss surnames
        for (Employee worker:Program.getEmployeeList().getBaseList())
            depAndBoss.add(worker.getDepName() + ", boss surname is " + worker.getBossSurname());
        System.out.println("Select the reporting option: console or file (c/f)");
        String choice = sc.nextLine().toLowerCase();
        if (choice.equals("c")) {
            depAndBoss.forEach(System.out::println);
            System.out.println();
        } else if (choice.equals("f")) {
            try (FileWriter writer = new FileWriter(Main.path + "\\ReportOrg.txt")) {
                for (String orgInfo : depAndBoss)
                    writer.write(orgInfo + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else System.out.println("You must select console or file (c/f), try again");
    }
}
