package ExamTaskV2;

import java.util.Scanner;

public class Main {
    private static int count =0;

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        /*System.out.println("Enter a login");
        String login = sc.nextLine();
        System.out.println("Enter a password");
        String password = sc.nextLine();

       if(checkUser(login, password)){*/
           LoadFile.load("src//ExamTaskV2//BaseEmployee.bin", Program.getEmployeeList());
           LoadFile.load("src//ExamTaskV2//DismissEmployee.bin", Program.getDismissEmployeeList());
           chooseAction();
           SaveFile.save("src//ExamTaskV2//BaseEmployee.bin", Program.getEmployeeList());
           SaveFile.save("src//ExamTaskV2//DismissEmployee.bin", Program.getDismissEmployeeList());
      // }
        sc.close();
    }

    private static void chooseAction() {
        int chooseAct;
        try {
            System.out.println("""
                    Select the number of actions:
                    1. Recruit employee.
                    2. Dismiss employee.
                    3. Show active employers to console.
                    4. Show dismissed employers to console.
                    5. Change information about employee.
                    6. Search the employee.
                    7. Reports.
                    8. Exit.
                    """);
            if (sc.hasNextLine()) {
                chooseAct = Integer.parseInt(sc.nextLine());
                if (chooseAct != 8) {
                    actions(chooseAct);
                    chooseAction();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            chooseAction();
        }
    }

    private static void actions(int act) {
        switch (act){
            case 1: {
                Program.recruit();
                break;
            }
            case 2: {
                Program.dismiss();
                break;
            }
            case 3: {
                Program.showEmployersAtConsole(Program.getEmployeeList().getBaseList());
                break;
            }
            case 4: {
                Program.showEmployersAtConsole(Program.getDismissEmployeeList().getBaseList());
                break;
            }
            case 5: {
                Program.changeEmployee();
                break;
            }
            case 6: {
                Program.searchEmployee();
                break;
            }
            case 7: {
                System.out.println("75");
                break;
            }
            case 8: {
                System.out.println("Exit the program");
                break;
            }
            default:
                System.out.println("You choose number without action");
        }
    }

    public static boolean checkUser(String login, String password){
        if(login.equals("admin") && password.equals("12345678")) {
            System.out.println("Access is allowed");
            return true;
        }
        else {
            ++count;
            if(count <3){
                System.out.println("Access is denied. Try again");
                System.out.println("Enter a login");
                String loginAgain = sc.nextLine();
                System.out.println("Enter a password");
                String passwordAgain = sc.nextLine();
                sc.close();
                return checkUser(loginAgain, passwordAgain);
            }
            else {
                System.out.println("You entered the wrong username or password three times, access is denied");
                return false;
            }
        }
    }
}