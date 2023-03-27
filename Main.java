package ExamTaskV2;

import java.util.Scanner;

public class Main {
    private static int count =0;        // for authorization attempts
    public static Scanner sc = new Scanner(System.in);
    public static String path = System.getProperty("user.dir");

    public static void main(String[] args) {
        System.out.println("Enter a login");
        String login = sc.nextLine();
        System.out.println("Enter a password");
        String password = sc.nextLine();

       if(checkUser(login, password)){
        Runnable loadingFiles = () ->{      //Thread for loading current and retired employees
            System.out.println("Start the process of uploading employee data, please wait");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            LoadFile.load(path + "\\BaseEmployee.bin", Program.getEmployeeList());
            LoadFile.load(path + "\\DismissEmployee.bin", Program.getDismissEmployeeList());
            System.out.println("Employee data loading completed");
        };
        Thread loadFileThread = new Thread(loadingFiles, "loadFileThread");
        loadFileThread.start();
        chooseAction();  //choosing actions in main menu
           try {
               loadFileThread.join();
           } catch(InterruptedException e){
               System.out.printf("%s has been interrupted", loadFileThread.getName());
           }

           SaveFile.save(path + "\\BaseEmployee.bin", Program.getEmployeeList());
           SaveFile.save(path + "\\DismissEmployee.bin", Program.getDismissEmployeeList());
       }
        sc.close();
    }

    private static void chooseAction() {     //actions in main menu
        int chooseAct;   //int for saving choose
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
        switch (act) {
            case 1 -> {Program.recruit();}
            case 2 -> {Program.dismiss();}
            case 3 -> {Program.showEmployersAtConsole(Program.getEmployeeList().getBaseList());}
            case 4 -> {Program.showEmployersAtConsole(Program.getDismissEmployeeList().getBaseList());}
            case 5 -> {Program.changeEmployee();}
            case 6 -> {Program.searchEmployee();}
            case 7 -> {Reports.main();}
            case 8 -> {System.out.println("Exit the program");}
            default -> System.out.println("You choose number without action");
        }
    }

    public static boolean checkUser(String login, String password){
        if(login.equals("admin") && password.equals("12345678")) {
            System.out.println("Access is allowed\n");
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
