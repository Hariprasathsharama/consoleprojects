import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ComapanyAdmin {
    Scanner input = new Scanner(System.in);

    public void adminchoice() throws Exception {
        byte option = 0;
        choicelist();
        WHILE_LABEL: while (true) {
            try {
                option = input.nextByte();
                if (option < 1 || option > 3) {
                    System.out.println("Enter valid number");
                } else {
                    break WHILE_LABEL;
                }
            } catch (InputMismatchException e) {
                System.out.println("Non-Valid option");
                input.nextLine();
            }
        }
        switch (option) {
            case 1:
                viewstatus();
                break;
            case 2:
                updatestatus();
                break;
            case 3:
                break;
            default:
                break;
        }

    }

    public void choicelist() {
        System.out.println("Enter the choice");
        System.out.println("1) View the status");
        System.out.println("2) Update the status");
    }

    public void viewstatus() throws Exception {

        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/candidatetable", "root", "Chrisevans@2309");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from candidateslist");
        System.out.println("The candidates List");
        while (rs.next()) {
            System.out.println("ID - " + rs.getString(1) + "   Name - " + rs.getString(2) + "   Age - "
                    + rs.getString(3) + "   Gender - " + rs.getString(4) + "   Yearofgraudation - "
                    + rs.getString(5) + "   Degree " + rs.getString(6) + " " + "Job-role" + rs.getString(7));

        }

        con.close();
        adminchoice();
    }

    public void updatestatus() throws Exception {
        int id = 0, choice = 0;
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/candidatetable", "root", "Chrisevans@2309");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select id ,name from candidateslist");
        while (rs.next()) {
            System.out.println("ID-> " + rs.getInt(1) + " " + "NAME-> " + rs.getString(2));
        }
        System.out.println("1) Hire the candidate");
        System.out.println("2) Reject the candiate");
        while (true) {

            try {
                System.out.println("Enter the id ");
                id = input.nextInt();
                System.out.println("Enter the choice");
                choice = input.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("enter numeric value");
                input.nextLine();
                updatestatus();
            }

            switch (choice) {
                case 1:
                    stmt.executeUpdate("update candidateslist set status='Selected' where id=" + id + "");
                    System.out.println("Candidate profile is updated");
                    return;
                case 2:
                    stmt.executeUpdate("update candidateslist set status='Rejected' where id=" + id + "");
                    System.out.println("Candidate profile is updated");
                    return;
                case 3:
                    break;
                default:
                    break;
            }

        }

    }
}