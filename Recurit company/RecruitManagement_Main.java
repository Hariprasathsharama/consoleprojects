
import java.util.InputMismatchException;
import java.util.Scanner;

public class RecruitManagement_Main {

    public static void main(String[] args) throws Exception {

        byte option = 0;
        WelcomePage();
        WHILE_LABEL: while (true) {

            try {
                option = new Scanner(System.in).nextByte();

                if (option < 1 || option > 3)
                    System.out.println("ENTER VALID NUMBER : ");
                else
                    break WHILE_LABEL;
            } catch (InputMismatchException e) {

                System.out.println("Non-Valid option");

            }

        }

        switch (option) {
            case 1:
                new ComapanyAdmin().adminchoice();
                break;
            case 2:
                new Manageusers().user();
                break;
            case 3:
                break;

        }

    }

    public static void WelcomePage() {
        System.out.println("-------RECRUIT MANAGEMENT--------");
        System.out.println("Enter the option");
        System.out.println("1) ADMIN");
        System.out.println("2) USER");
        System.out.println("3) EXIT");
    }
}
