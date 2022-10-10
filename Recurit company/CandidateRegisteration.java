import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class CandidateRegisteration {

    Scanner s = new Scanner(System.in);
    static ArrayList<String> degrees = new ArrayList<>(Arrays.asList("B.SC", "B.E", "M.SC", "M.E"));
    static ArrayList<String> jobroles = new ArrayList<>(
            Arrays.asList("PROGRAMMER", "WEB-DEVELOPER", "ANALYST", "DESIGNER"));
    static ArrayList<CandidateRegisteration> candidatelist = new ArrayList<>();
    private static String sql = "INSERT INTO candidateslist"
            + "(name,age,gender,graduated_year,degreegraudate,roles,email,passwords) values "
            + "(?,?,?,?,?,?,?,?);";
    private String name;
    private byte age;
    private String gender;
    private int graduated_year;
    private String degreegraudate;
    private String roles;
    private String email;
    private String passwords;

    public void createuser() throws SQLException {
        System.out.println("Enter your name");
        name = validname();
        System.out.println("Enter your age");
        age = validage();
        System.out.println("Enter the gender");
        gender = validgender();
        System.out.println("Enter the year of gradution");
        graduated_year = validyear();
        System.out.println("Choose the degree");
        degreegraudate = degrees();
        System.out.println("Choose the roles");
        roles = roles();
        System.out.println("Enter the Email");
        email = isValidmail();
        System.out.println("Enter the password");
        passwords = s.next();
        candidatelist.add(
                new CandidateRegisteration(name, age, gender, graduated_year, degreegraudate, roles, email, passwords));
        updatetodatabase();
        System.out.println(" you are successfully registered ");
        System.out.println("Return to login page");
        new Manageusers().login();
    }

    public CandidateRegisteration() {
    }

    public CandidateRegisteration(String name, byte age, String gender, int graduated_year, String degree, String role,
            String email, String password) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.graduated_year = graduated_year;
        this.degreegraudate = degree;
        this.roles = role;
        this.email = email;
        this.passwords = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDegree() {
        return degreegraudate;
    }

    public void setDegree(String degree) {
        this.degreegraudate = degree;
    }

    public int getGraduated_year() {
        return graduated_year;
    }

    public void setGraduated_year(int graduated_year) {
        this.graduated_year = graduated_year;
    }

    public String getRole() {
        return roles;
    }

    public void setRole(String role) {
        this.roles = role;
    }

    public String getPassword() {
        return passwords;
    }

    public void setPassword(String password) {
        this.passwords = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public String validname(){
        while (true) {
            name = s.next();
            String regex = "[a-zA-Z]+\\.?";
            if (name.matches(regex)) {
                return name;
            } else {
                System.out.println("Enter the valid username");
            }
        }
       
    }

    public String isValidmail() {

        while (true) {
            String email = s.next();
            String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
            if (email.matches(regex)) {
                return email;
            } else {
                System.out.println("Enter the valid mail");
            }
        }
    }

    public String validgender() {
        System.out.println("Choose the given below");
        System.out.println("1) Male");
        System.out.println("2) Female");
        while (true) {
            byte option=0;
            try {
                option=s.nextByte();
            } catch (InputMismatchException e) {
               
                System.out.println("Please enter numeric values");
                s.nextLine();
            }
            if (option == 1) {
                return "male";
            } else if (option == 2) {
                return "female";
            } else {
                System.out.println("----------------------");
                System.out.println("Enter the valid option");
                System.out.println("----------------------");
               
            }
        }

    }
    public Byte validage() throws SQLException{
        byte age;
        WHILE_LABEL:

        while (true) {
            try {
                age = s.nextByte();
                if (age >19 && age < 35){
                    break WHILE_LABEL;}
                else{
                    System.out.println("You not eligible for this application");
                    System.out.println("Retrun to home page");
                    createuser();
                }
            } catch (InputMismatchException e) {
                System.out.println("Non-valid input");
                s.nextLine();
            }
        }
        return age;
    }

    public int validyear() throws SQLException{
       
     while (true) {
           int year=0;
      try {
            year=s.nextInt();
      }
      catch(InputMismatchException e){
      
        s.nextLine();
        System.out.println("please enter valid option");
      }
      if(year>2017 && year<=2022 ){
        return year;
        
      }
     else{
        System.out.println("you are eligible for this application");
        System.out.println("Return to home page");
        createuser();
     }
    }
    
}
    public String degrees() {
        int j = 1;
        byte option=0;
        for (String i : degrees) {

            System.out.println(j + ")" + " " + i);
            j++;
        }
        while (true) {
            
        
        try{
             option = s.nextByte();
        }
        catch(InputMismatchException e){
            System.out.println("please enter valid option");
            s.nextLine();
        }
        switch (option) {
            case 1:
                return degrees.get(1);

            case 2:
                return degrees.get(2);
            case 3:
                return degrees.get(3);
            case 4:
                return degrees.get(4);
            default:
            System.out.println("Enter valid  option");
         
        }
          
    }
    }

    public String roles() {
        byte option=0;
        int j = 1;
        for (String i : jobroles) {

            System.out.println(j + ")" + " " + i);
            j++;

        }
        while (true) {
            
        try {
            option=s.nextByte();
         } catch (InputMismatchException e) {
            System.out.println("please enter numeric values");
            s.nextLine();
         }
        switch (option) {
            case 1:
                return jobroles.get(0);

            case 2:
                return jobroles.get(1);
            case 3:
                return jobroles.get(2);
            case 4:
                return jobroles.get(3);
        }
        System.out.println("Enter valid option");
    }
    }
    public void updatetodatabase() {
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/candidatetable", "root",
                        "Chrisevans@2309");
                PreparedStatement preparedstatement = connection.prepareStatement(sql)) {

            for (Iterator<CandidateRegisteration> iterator = candidatelist.iterator(); iterator.hasNext();) {
                CandidateRegisteration candidate = (CandidateRegisteration) iterator.next();
                preparedstatement.setString(1, candidate.getName());
                preparedstatement.setByte(2, candidate.getAge());
                preparedstatement.setString(3, candidate.getGender());
                preparedstatement.setInt(4, candidate.getGraduated_year());
                preparedstatement.setString(5, candidate.getDegree());
                preparedstatement.setString(6, candidate.getRole());
                preparedstatement.setString(7, candidate.getEmail());
                preparedstatement.setString(8, candidate.getPassword());
                preparedstatement.addBatch();
            }
            int[] count = preparedstatement.executeBatch();
            System.out.println(Arrays.toString(count));
        } catch (BatchUpdateException batchUpdateException) {
            printBatchupdateException(batchUpdateException);
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    private static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("Sqlstate" + ((SQLException) e).getSQLState());
                System.err.println("Error code " + ((SQLException) e).getErrorCode());
                System.err.println("Message" + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause" + t);
                    t = t.getCause();
                }
            }
        }
    }

    public static void printBatchupdateException(BatchUpdateException b) {
        System.err.println("sqlstate" + b.getSQLState());
        System.err.println("Message" + b.getMessage());
        System.err.println("Vendor" + b.getErrorCode());
        System.err.println("Update counts: ");

    }

}
