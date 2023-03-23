package ExamTaskV2;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Program {
    private static final EmployeeList employeeList = new EmployeeList();
    private static final EmployeeList dismissEmployeeList = new EmployeeList();

    public static Scanner sc = Main.sc;

    public static EmployeeList getEmployeeList() {
        return employeeList;
    }

    public static EmployeeList getDismissEmployeeList() {
        return dismissEmployeeList;
    }

    public static void recruit(){
        Employee newEmp = new Employee();
        try {
            System.out.println("Enter employee name");
            newEmp.setName(sc.nextLine());
            System.out.println("Enter employee surname");
            newEmp.setSurname(sc.nextLine());
            System.out.println("Enter employee date of birthday");
            newEmp.setDob(sc.nextLine());
            System.out.println("Enter employee gender (male/female)");
            newEmp.setGender(sc.nextLine());
            System.out.println("Enter employee phone number");
            newEmp.setPhoneNumber(sc.nextLine());
            System.out.println("Enter employee job title");
            newEmp.setJobTitle(sc.nextLine());
            System.out.println("Enter department name");
            newEmp.setDepName(sc.nextLine());
            System.out.println("Enter the name of the employee boss");
            newEmp.setBossName(sc.nextLine());
            System.out.println("Enter the surname of the employee boss");
            newEmp.setBossSurname(sc.nextLine());
            System.out.println("Enter the year of employment");
            int year = sc.nextInt();
            System.out.println("Enter the month of employment");
            int month = sc.nextInt();
            System.out.println("Enter the day of employment");
            int day = sc.nextInt();
            newEmp.setEmpDate(new GregorianCalendar(year, (month - 1), day));
            System.out.println("Enter employee salary");
            newEmp.setSalary(sc.nextInt());
            sc.nextLine();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        employeeList.getBaseList().add(newEmp);
    }

    public static void dismiss(){
        try {
            int disLine = searchEmployee();
            dismissEmployeeList.getBaseList().add(employeeList.getBaseList().get(disLine));
            employeeList.getBaseList().remove(disLine);
        }catch (Exception e){
            System.out.println("An error occurred, please try again");
        }
    }

    public static void showEmployersAtConsole(List<Employee> empList) {
       try {
           int count = 0;
            for (Employee worker : empList) {
                System.out.printf("%d. Name: %s,  surname: %s, date of birthday: %s, gender: %s, phone number: %s, " +
                                "job title: %s, department name: %s, boss name: %s, boss surname: %s," +
                                " data of employment(m/d/y): %tD, salary: %d\n",
                        count++, worker.getName(), worker.getSurname(), worker.getDob(), worker.getGender(),
                        worker.getPhoneNumber(), worker.getJobTitle(), worker.getDepName(), worker.getBossName(),
                        worker.getBossSurname(), worker.getEmpDate(), worker.getSalary());
            }
       }catch (Exception e) {
            System.out.println("Bad iteration");
       }
       System.out.println();
    }

    public static void changeEmployee() {
        Integer changeEmpString = searchEmployee();
        if(changeEmpString != -1) {
            System.out.println("""
                    What information about the employee you want change? Choice the number:
                    1. Name;
                    2. Surname;
                    3. Date of birthday
                    4. Gender;
                    5. Phone Number;
                    6. Job title;
                    7. Department name
                    8. BossName;
                    9. BossSurname;
                    10. Employment date
                    11. Salary""");
            int num;
            while (true) {      //continue changing the fields of the employee object?
                while (true) {      // dialog to select changes
                    try {
                        num = sc.nextInt();
                        sc.nextLine();
                        if (num >= 1 && num <= 11)
                            break;
                        else System.out.println("The number of string out of range. Try again");
                    } catch (Exception e) {
                        System.out.println("Your choice isn't a number. Try again");
                    }
                }
                switch (num) {
                    case 1 -> {
                        System.out.println("You change Name to the following: ");
                        employeeList.getBaseList().get(changeEmpString).setName(sc.nextLine());  //setName(sc.nextLine());
                    }
                    case 2 -> {
                        System.out.println("You change Surname to the following: ");
                        employeeList.getBaseList().get(changeEmpString).setSurname(sc.nextLine());
                    }
                    case 3 -> {
                        System.out.println("You change Date of birthday to the following: ");
                        employeeList.getBaseList().get(changeEmpString).setDob(sc.nextLine());
                    }
                    case 4 -> {
                        System.out.println("You change Gender to the following: ");
                        employeeList.getBaseList().get(changeEmpString).setGender(sc.nextLine());
                    }
                    case 5 -> {
                        System.out.println("You change Phone Number to the following: ");
                        employeeList.getBaseList().get(changeEmpString).setPhoneNumber(sc.nextLine());
                    }
                    case 6 -> {
                        System.out.println("You change Job title to the following: ");
                        employeeList.getBaseList().get(changeEmpString).setJobTitle(sc.nextLine());
                    }
                    case 7 -> {
                        System.out.println("You change Department name to the following: ");
                        employeeList.getBaseList().get(changeEmpString).setDepName(sc.nextLine());
                    }
                    case 8 -> {
                        System.out.println("You change boss name to the following: ");
                        employeeList.getBaseList().get(changeEmpString).setBossName(sc.nextLine());
                    }
                    case 9 -> {
                        System.out.println("You change boss surname to the following: ");
                        employeeList.getBaseList().get(changeEmpString).setBossSurname(sc.nextLine());
                    }
                    case 10 -> {
                        System.out.println("Enter changed year of employment");
                        int year = sc.nextInt();
                        System.out.println("Enter changed month of employment");
                        int month = sc.nextInt();
                        System.out.println("Enter changed day of employment");
                        int day = sc.nextInt();
                        sc.nextLine();
                        employeeList.getBaseList().get(changeEmpString).setEmpDate(new GregorianCalendar(year,
                                (month - 1), day));
                    }
                    case 11 -> {
                        System.out.println("You change salary to the following: ");
                        employeeList.getBaseList().get(changeEmpString).setSalary(sc.nextInt());
                    }
                }
                System.out.println("Continue employee changing?(y/n):");
                if(!sc.nextLine().equalsIgnoreCase("y")) break;
                else System.out.println("Choice the number of changed field");
            }
        }
    }

    /*public Integer searchEmployee(String searchSurname) {
        Integer count = 0;
        HashMap<Integer,Employee> searchList = new HashMap<>();
        for (Employee employee:employeeList.getBaseList()) {
            if (employee.getSurname().equalsIgnoreCase(searchSurname)) {
                searchList.put(count,employee);
            }
            count++;
        }
        if(searchList.isEmpty()){
            System.out.println("The employee is not found");
            return -1;
        }
        else {
            searchList.forEach((sn, worker) -> {
                System.out.printf("%d. Name: %s,  surname: %s, date of birthday: %s, gender: %s, phone number: %s, " +
                                "job title: %s, department name: %s, boss name: %s, boss surname: %s," +
                                " data of employment(m/d/y): %tD, salary: %d\n",
                        sn, worker.getName(), worker.getSurname(), worker.getDob(), worker.getGender(),
                        worker.getPhoneNumber(), worker.getJobTitle(), worker.getDepName(), worker.getBossName(),
                        worker.getBossSurname(), worker.getEmpDate(), worker.getSalary());
            });
            if(searchList.size() == 1) {
                return (Integer) searchList.keySet().toArray()[0];
            }
            else {
                int num;
                while (true) {
                System.out.println("Select serial number need employee");
                    try {
                        num = sc.nextInt();
                        sc.nextLine();
                        if(searchList.containsKey(num)) break;
                        else System.out.println("Serial number is not exist. Try again");
                    } catch (Exception e) {
                        System.out.println("You are select isn't a number. Try again");
                    }
                }
                return num;
            }
        }
    }*/
    public static Integer searchEmployee() {
        boolean searchBoolean = false;              //boolean to save of the comparison of the searched field with the entered word
        String searchWord;                          //String to save entered word of search parameter
        int selAct;                                 //int to save selected parameter
        try {                                       //Selection of search parameter
            System.out.println("""
                    Select search option number:
                    1. Employee surname.
                    2. Employee job title.
                    3. Department.
                    4. Boss surname.
                    5. Exit
                    """);
            if (sc.hasNextLine()) {
                selAct = Integer.parseInt(sc.nextLine());       // Parameter is select
                if (selAct != 5) {                              // Selected parameter isn't Exit
                    System.out.println("Enter selected parameter");
                    searchWord = sc.nextLine();                 //Enter word of parameter
                    Integer count = 0;                          //Integer for HashMap to saving number of Employee position in EmployeeList
                    HashMap<Integer, Employee> searchList = new HashMap<>();
                    for (Employee employee:employeeList.getBaseList()) {
                        switch (selAct) {
                            case 1 -> {searchBoolean = employee.getSurname().equalsIgnoreCase(searchWord);}
                            case 2 -> {searchBoolean = employee.getJobTitle().equalsIgnoreCase(searchWord);}
                            case 3 -> {searchBoolean = employee.getDepName().equalsIgnoreCase(searchWord);}
                            case 4 -> {searchBoolean = employee.getBossSurname().equalsIgnoreCase(searchWord);}
                        }
                        if (searchBoolean) searchList.put(count,employee);  //if we find search word in list
                        count++;
                    }
                    // Processing list with search results
                    if(searchList.isEmpty()){                                   //the list is empty
                        System.out.println("The employee is not found");
                        return -1;
                    }
                    else {                                                      // if the list is not empty, display its contents on the screen
                        searchList.forEach((sn, worker) -> {
                            System.out.printf("%d. Name: %s,  surname: %s, date of birthday: %s, gender: %s, phone number: %s, " +
                                            "job title: %s, department name: %s, boss name: %s, boss surname: %s," +
                                            " data of employment(m/d/y): %tD, salary: %d\n",
                                    sn, worker.getName(), worker.getSurname(), worker.getDob(), worker.getGender(),
                                    worker.getPhoneNumber(), worker.getJobTitle(), worker.getDepName(), worker.getBossName(),
                                    worker.getBossSurname(), worker.getEmpDate(), worker.getSalary());
                        });
                        if(searchList.size() == 1)
                            return (Integer) searchList.keySet().toArray()[0];
                        else {
                            int num;
                            while (true) {
                                System.out.println("Select serial number need employee");
                                try {
                                    num = sc.nextInt();
                                    sc.nextLine();
                                    if(searchList.containsKey(num)) break;
                                    else System.out.println("Serial number is not exist. Try again");
                                } catch (Exception e) {
                                    System.out.println("You are select isn't a number. Try again");
                                }
                            }
                            return num;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return  -1;
    }
}
