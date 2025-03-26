import java.time.LocalDateTime;

public class FlightBooking {
    private final String bookingId;
    private final Customer customer;
    private final Flight flight;
    private int numTickets;
    private final LocalDateTime bookingTime;

    public FlightBooking(String bookingId, Customer customer, Flight flight, 
                        int numTickets, LocalDateTime bookingTime) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.flight = flight;
        this.numTickets = numTickets;
        this.bookingTime = bookingTime;
    }

    // Getters and setter
    public String getBookingId() { return bookingId; }
    public Customer getCustomer() { return customer; }
    public Flight getFlight() { return flight; }
    public int getNumTickets() { return numTickets; }
    public LocalDateTime getBookingTime() { return bookingTime; }
    public void setNumTickets(int numTickets) { this.numTickets = numTickets; }
}