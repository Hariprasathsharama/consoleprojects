package Moviebooking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class TheatreAdmin {
    String sql = "INSERT INTO moviesdetails" + "(Id,moviename,ticketcost,seat) values " + "(?,?,?,?)";
    ArrayList<Moviedetails> movielist = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        TheatreAdmin admin = new TheatreAdmin();
        System.out.println("IN ADMIN PAGE");
        System.out.println("1) CREATE MOVIES");
        System.out.println("2) DELETE MOVIES ");
        admin.selectchoice();

    }

    public void selectchoice() {
        byte option = 0;
        while (true) {
            System.out.println("Enter the option");
            try {
                option = input.nextByte();
            } catch (InputMismatchException e) {
                System.out.println("Enter the numeric value");
            }
            switch (option) {
                case 1:
                    System.out.println("Enter how many movies to add in theater");
                    int noofmovie = input.nextInt();
                    for (int i = 0; i < noofmovie; i++) {
                        createmovies();
                    }
                    return;
                case 2:
                    deletemovies();
                    break;
                case 3:
                    break;

            }
        }
    }

    public void createmovies() {
        System.out.println("Enter the movie ID");
        Byte movieid = input.nextByte();
        input.nextLine();
        System.out.println("Enter the Movie name");
        String moviename = input.nextLine();
        System.out.println("Enter the ticket cost of the movie");
        int ticketcost = input.nextInt();
        System.out.println("Enter the seat availablity");
        int seat = input.nextInt();
        movielist.add(new Moviedetails(movieid, moviename, ticketcost, seat));
        updatetodatabase();
    }

    public void updatetodatabase() {
       
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/ticketbooking", "root",
                "Chrisevans@2309")) {
            PreparedStatement preparedstatement = connection.prepareStatement(sql);
            for (Iterator<Moviedetails> iterator = movielist.iterator(); iterator.hasNext();) {
                Moviedetails movies = (Moviedetails) iterator.next();
                preparedstatement.setByte(1, movies.getMovieid());
                preparedstatement.setString(2, movies.getMoviename());
                preparedstatement.setInt(3, movies.getTicketcost());
                preparedstatement.setInt(4, movies.getSeat());
                preparedstatement.addBatch();
            }
            int[] count = preparedstatement.executeBatch();
            System.out.println(Arrays.toString(count));
        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public void deletemovies() {

    }

    
}
