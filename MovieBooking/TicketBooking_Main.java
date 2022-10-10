
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TicketBooking_Main {
    TheatreAdmin movies = new TheatreAdmin();
    ArrayList<Moviedetails> updatedmovies = new ArrayList<>();
    ArrayList<Audience> audience_record = new ArrayList<>();
    Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        TicketBooking_Main booking = new TicketBooking_Main();
        booking.movielist();
        booking.showmovies();
        booking.bookmovie();

    }

    public void bookmovie() {
        System.out.println("1) Book the Movie");
        System.out.println("2) Exit");
        byte option = 0;
        System.out.println("Enter the option");
        while (true) {
            try {
                option = input.nextByte();

            } catch (InputMismatchException e) {
                System.out.println("Enter numeric values");
                input.next();
            }
            switch (option) {
                case 1:
                    audiencebooking();
                    break;

                case 2:
                    return;
                default:
                    System.out.println("Enter only given choice");
            }

        }
    }

    public void audiencebooking() {
        for (Moviedetails movie : updatedmovies) {
            System.out.println(
                    "Movieid->" + "  " + movie.getMovieid() + "-----" + " " + "Moviename->" + "  "
                            + movie.getMoviename());

        }
        System.out.println("Choose which movie?");
        byte choosemovie = 0;
        while (true) {
            try {
                choosemovie = input.nextByte();
            } catch (Exception e) {
                System.out.println("Enter numeric values!");
            }
            if (choosemovie <= updatedmovies.size()) {
                break;
            }
        }
        input.nextLine();
        System.out.println("Enter your name");
        String name = input.next();
        System.out.println("Enter how many tickets");
        byte number_of_ticket = input.nextByte();

        String moviename = "";
        int ticketcost = 0;
        for (Moviedetails movie : updatedmovies) {
            if (movie.getMovieid() == choosemovie) {
                if (number_of_ticket < movie.getSeat()) {
                    ticketcost = movie.getTicketcost() * number_of_ticket;
                    moviename = movie.getMoviename();
                    audience_record.add(new Audience(name, moviename, number_of_ticket, ticketcost));
                    int Availableseat = movie.getSeat() - number_of_ticket;
                    updateseat(moviename, Availableseat);
                } else {
                    System.out.println("Sorry ,ticket not available for this movie!");
                    audiencebooking();
                }
            }
        }

        System.out.println("Movie Name" + "  ->  " + moviename);
        System.out.println("Name ->    " + name);
        System.out.println("Number of Tickets ->   " + number_of_ticket);
        System.out.println("-------------------------------");
        System.out.println("Cost of total Tickets ->   " + ticketcost);
        System.out.println("-------------------------------");
        bookmovie();
    }

    public void showmovies() {
        for (Moviedetails movie : updatedmovies) {
            System.out.println(movie.getMovieid() + " | " + "MovieName-> " + movie.getMoviename() + " | "
                    + " | " + "TicketCost-> " + movie.getTicketcost() + " | "
                    + "Availableseat-> " + movie.getSeat());
        }

    }

    public void movielist() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/ticketbooking", "root",
                "Chrisevans@2309")) {
            java.sql.Statement statement = connection.createStatement();
            ResultSet resultset = ((java.sql.Statement) statement).executeQuery("select * from moviesdetails");
            while (resultset.next()) {
                updatedmovies.add(new Moviedetails(resultset.getByte(1), resultset.getString(2), resultset.getInt(3),
                        resultset.getInt(4)));

            }
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateseat(String moviesname, int seats) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/ticketbooking", "root",
                "Chrisevans@2309")) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(
                    "update moviesdetails set seat=" + seats + " where moviename = '" + moviesname + "';");
        } catch (SQLException e) {

            e.printStackTrace();
        }

    }
}