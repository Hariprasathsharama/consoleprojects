package Moviebooking;

public class Moviedetails {
    private byte movieid;
    private String moviename;
    private int ticketcost;
    private int seat;
    
    public Moviedetails() {

    }

    public byte getMovieid() {
        return movieid;
    }

    public void setMovieid(byte movieid) {
        this.movieid = movieid;
    }

    public String getMoviename() {
        return moviename;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }

    public int getTicketcost() {
        return ticketcost;
    }

    public void setTicketcost(int ticketcost) {
        this.ticketcost = ticketcost;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public Moviedetails(byte movieid, String moviename, int ticketcost, int seat) {
        this.movieid = movieid;
        this.moviename = moviename;
        this.ticketcost = ticketcost;
        this.seat = seat;
    }

}
