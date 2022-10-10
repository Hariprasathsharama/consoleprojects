
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Customer {
    private String name;
    private String passwords;
    private int accountnumber;
    private int balance;
    private ArrayList<Customer> customerlist = new ArrayList<>();
    private static String sql = "insert into bank" + "(name,passwords,accountnumber,balance) values" + "(?,?,?,?)";
    Scanner input = new Scanner(System.in);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public int getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(int accountnumber) {
        this.accountnumber = accountnumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Customer() {
    }

    public Customer(String name, String passwords) {
        this.name = name;
        this.passwords = passwords;
    }

    public Customer(String name, String passwords, int accountnumber, int balance) {
        this.name = name;
        this.passwords = passwords;
        this.accountnumber = accountnumber;
        this.balance = balance;

    }

    public void addcustomer() throws SQLException {
        System.out.println("Enter the name");
        name = input.next();
        System.out.println("Enter he password");
        passwords = input.next();
        while (true) {
            System.out.println("Enter the account number");
            try {
                accountnumber = input.nextInt();
                break;

            } catch (InputMismatchException e) {

                System.out.println("Enter only numeric values");
                input.nextLine();
            }
        }
        balance = 1000;
        customerlist.add(new Customer(name, passwords, accountnumber, balance));
        updatetodatabase();// insert the list into the database""
        System.out.println("---------------------------------------------------------");
        System.out.println("Hi !" + name + " " + " your account created succesfully");
        System.out.println("---------------------------------------------------------");
        accountverify();// verify the user info

    }

    public void accountverify() {
        System.out.println("```````````````````````````````");
        System.out.println("LOGIN PAGE");
        System.out.println("```````````````````````````````");
        System.out.println("Please enter your login details:");
        System.out.println("Enter your username: ");
        name = input.next();
        System.out.println("Enter your password");
        passwords = input.next();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Bankapplication", "root", "Chrisevans@2309");
            java.sql.Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery("select * from bank");
            int flag = 0;
            while (resultset.next()) {
                if (resultset.getString(1).equals(name) && resultset.getString(2).equals(passwords)) {
                    flag = 1;
                    break;

                }
            }
            if (flag == 1) {
                System.out.println("LOGGED IN SUCCESFULLY");
                bankprocess();
            } else {
                System.out.println("------------------------------");
                System.out.println("USERNAME OR PASSWORD IS WRONG");
                System.out.println("------------------------------");
                System.out.println("Enter your Details Correctly");
                accountverify();
            }

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void bankprocess() throws SQLException {
        byte selection = 0;
        System.out.println("Please select an option");
        System.out.println("1) Balance Enquiry");
        System.out.println("2) WITHDRAWAL");
        System.out.println("3) Deposit");
        System.out.println("4) Exit");
        System.out.print("Your selection: ");
        while (true) {
            
        
        try {
            Scanner input = new Scanner(System.in);
            selection = input.nextByte();
        } catch (InputMismatchException e) {
            System.out.println("Enter valid option");

        }

        switch (selection) {
            case 1: {
                checkBalance();
                break;
            }
            case 2: {
                withdraw();
                break;
            }
            case 3: {
                deposit();
                break;
            }
            case 4: {
                return;
            }
            default:
                System.out.println("Enter the correct option");
        }
    }
    }

    public void checkBalance() throws SQLException {

        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Bankapplication", "root", "Chrisevans@2309");
        java.sql.Statement stmt = con.createStatement();
        ResultSet resultset = stmt.executeQuery("select * from bank");
        System.out.println("Enter your account numebr");
        accountnumber = input.nextInt();
        while (resultset.next()) {

           

            if (resultset.getInt(3) == (accountnumber)) {
                System.out.println("Your balance is " + resultset.getString(4));
                bankprocess();
            }

        }
    }

    public void withdraw() {
        System.out.println("Enter your username");
        name = input.next();
        System.out.println("Enter your account number");
        try {
            accountnumber = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("please enter numeric value");
            input.nextLine();
            withdraw();
        }

        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Bankapplication", "root", "Chrisevans@2309");
            java.sql.Statement statement = con.createStatement();
            ResultSet resultset = statement.executeQuery("select * from bank");
            int withdrawamount = 0;
            while (resultset.next()) {

                if (accountnumber == resultset.getInt(3) && name.equals(resultset.getString(1))) {
                    System.out.print("Enter the amount to withdraw :");
                    int amount = input.nextInt();

                    int currentamount = resultset.getInt(4);

                    if (amount < currentamount) {
                        withdrawamount = currentamount - amount;
                        System.out.println("Your new balance is " + withdrawamount);
                        statement.executeUpdate(
                                "update bank set balance=" + " " + withdrawamount + " " + "where accountnumber="
                                        + accountnumber + " ");
                        bankprocess();
                        return;
                    } else {
                        System.out.println("Insufficient balance");
                        bankprocess();
                    }
                }
            }

            System.out.println("No account found");
            withdraw();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void deposit() {
        System.out.println("Enter your username");
        name = input.next();
        System.out.println("Enter your account number");
        try {
            accountnumber = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("please enter numeric value");
            input.nextLine();
            withdraw();
        }

        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Bankapplication", "root", "Chrisevans@2309");
            java.sql.Statement stmt = con.createStatement();
            ResultSet resultset = stmt.executeQuery("select * from bank");
            int exsistedamount = 0;
            int newamount = 0;
            while (resultset.next()) {
                if (accountnumber == resultset.getInt(3) && name.equals(resultset.getString(1))) {
                    System.out.print("Enter the amount to deposit :");
                    int amount = input.nextInt();
                    exsistedamount = resultset.getInt(4);
                    newamount = exsistedamount + amount;
                    System.out.println(newamount + "  rupees " + "deposited successfully");
                    stmt.executeUpdate(
                            "update bank set balance=" + newamount + " where accountnumber = " + accountnumber + " ");
                    bankprocess();
                }

            }
            System.out.println("No account found");
            deposit();

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void updatetodatabase() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/Bankapplication", "root",
                "Chrisevans@2309");
        PreparedStatement preparedstatement = connection.prepareStatement(sql);
        preparedstatement.setString(1, this.getName());
        preparedstatement.setString(2, this.getPasswords());
        preparedstatement.setInt(3, this.getAccountnumber());
        preparedstatement.setInt(4, this.getBalance());
        preparedstatement.addBatch();

        int[] count = preparedstatement.executeBatch();
        System.out.println(Arrays.toString(count));
    }

}
