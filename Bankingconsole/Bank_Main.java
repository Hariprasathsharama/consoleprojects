
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Bank_Main {

    public static void main(String[] args) throws SQLException {
        Scanner input = new Scanner(System.in);
        Managecustomer customer = new Managecustomer();
        System.out.println("WELCOME TO INDIAN BANK");
        System.out.println("Enter the choice 1) Signup 2) Login");
        byte option = 0;
        WelcomePage();
        WHILE_LABEL: while (true) {

            try {
                option = input.nextByte();

                if (option < 1 || option > 3)
                    System.out.println("ENTER VALID NUMBER : ");
                else
                    break WHILE_LABEL;
            } catch (InputMismatchException e) {

                System.out.println("Non-Valid option");
                System.out.println("Enter valid entry");
                input.nextLine();
            }

        }

        switch (option) {
            case 1:
                customer.addcustomer();
                break;
            case 2:
                customer.accountverify();
                break;
            case 3:
                break;
        }
        input.close();
    }

    public static void WelcomePage() {
        System.out.println("-------WELCOME TO INDIAN BANK--------");
        System.out.println("Enter the option");
        System.out.println("1) SIGN UP");
        System.out.println("2) LOGIN");
        System.out.println("3) EXIT");
    }

}
