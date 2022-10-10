
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Manageusers {
    Scanner input = new Scanner(System.in);

    public void user() throws SQLException {
        Userpage();
        byte option = 0;
            System.out.println("Enter the option");

                option = input.nextByte();

            switch (option) {
                case 1:

                    login();
                    break;
                case 2:

                    new CandidateRegisteration().createuser();

                    break;
                case 3:

                    break;
                default:
                    System.out.println("print valid number");
            }
        }

    public static void Userpage() {
        System.out.println();
        System.out.println("Enter the Login/Sign up");
        System.out.println("1) Login");
        System.out.println("2) Sign up");
        System.out.println("3) EXIT");
    }

    public void login() throws SQLException {

        System.out.println("Enter the email");
        String email = input.next();
        System.out.println("Enter the password");
        String password = input.next();

        if (isverify(email, password)) {
            // viewstatus();
            System.out.println("You successfully logged in");
            showstatus();
        } else {
            System.out.println("your account not detected");
            loginfailure();

        }

    }

    public boolean isverify(String mail, String pass) {

        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/candidatetable", "root", "Chrisevans@2309");
            java.sql.Statement stmt = con.createStatement();
            ResultSet resultset = stmt.executeQuery("select * from candidateslist");

            while (resultset.next()) {

                if (resultset.getString(8).equals(mail) && resultset.getString(9).equals(pass) == true) {
                    return true;

                }

            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return false;

    }

    public void showstatus() {
        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/candidatetable", "root", "Chrisevans@2309")) {
            java.sql.Statement statement = con.createStatement();
            ResultSet resultset = statement.executeQuery("select * from candidateslist");
            System.out.println("Check the status");
            System.out.println("Enter the mail");
            String mail = input.next();
            System.out.println("Enter the password");
            String password = input.next();
            while (resultset.next()) {
                try {
                    if (resultset.getString(8).equals(mail) && resultset.getString(9).equals(password) == true) {

                        if (resultset.getString(10).equals("Selected")) {
                            System.out.println("congratulations! your application is shortlisted");
                            System.out.println("we will contact you later");
                            break;
                        }
                        else if(resultset.getString(10).equals("Rejected")){
                            System.out.println("Sorry! your profile is not eligible for this role");
                            System.out.println("Hope you get a good carrer");
                        } 
                        else {
                            System.out.println("you application is still pending");
                            break;
                        }
                    }
                } catch (NullPointerException e) {
                    System.out.println("application is still pending");
                    break;
                }

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void loginfailure() throws SQLException {
        byte option = 0;
        System.out.println("1) Register new user");
        System.out.println("2) Relogin");
        System.out.println("Enter the option");
        while (true) {
            try {
                option = input.nextByte();
            } catch (InputMismatchException e) {
                System.out.println("please enter valid option");
            }
            switch (option) {
                case 1:
                    new CandidateRegisteration().createuser();
                    break;

                case 2:
                    login();
                    break;
                case 3:
                    break;
            }
        }

    }

}