
public class Audience {
    String name;
    String moviechoice;
    byte number_of_ticket;
    int ticketcost;

    public Audience(String name, String moviechoice, byte number_of_ticket, int ticketcost) {
        this.name = name;
        this.moviechoice = moviechoice;
        this.number_of_ticket = number_of_ticket;
        this.ticketcost = ticketcost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoviechoice() {
        return moviechoice;
    }

    public void setMoviechoice(String moviechoice) {
        this.moviechoice = moviechoice;
    }

    public byte getNumber_of_ticket() {
        return number_of_ticket;
    }

    public void setNumber_of_ticket(byte number_of_ticket) {
        this.number_of_ticket = number_of_ticket;
    }

    public int getTicketcost() {
        return ticketcost;
    }

    public void setTicketcost(int ticketcost) {
        this.ticketcost = ticketcost;
    }

}
