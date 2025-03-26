import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BookingManager {
    private final FlightManager flightManager;
    private final AccountManager accountManager;
    private final List<FlightBooking> bookings;

    public BookingManager(FlightManager flightManager, AccountManager accountManager) {
        this.flightManager = flightManager;
        this.accountManager = accountManager;
        this.bookings = new ArrayList<>();
    }

    public void bookFlight(Customer customer, ConsoleInterface ui) {
        flightManager.displayFlightSchedule(ui);
        String flightNumber = ui.promptForFlightNumber();
        int numTickets = ui.promptForNumberOfTickets();
        
        if (numTickets > 10) {
            ui.displayError("Cannot book more than 10 tickets at once");
            return;
        }

        Flight flight = flightManager.findFlightByNumber(flightNumber);
        if (flight == null) {
            ui.displayError("Flight not found");
            return;
        }

        if (!flight.addPassenger(customer, numTickets)) {
            ui.displayError("Not enough seats available");
            return;
        }

        bookings.add(new FlightBooking(
            UUID.randomUUID().toString(),
            customer,
            flight,
            numTickets,
            LocalDateTime.now()
        ));
        ui.displaySuccess("Flight booked successfully");
    }

    public void cancelFlight(Customer customer, ConsoleInterface ui) {
        List<FlightBooking> customerBookings = getCustomerBookings(customer.getUserId());
        if (customerBookings.isEmpty()) {
            ui.displayError("No bookings found");
            return;
        }
        
        ui.displayCustomerBookings(customerBookings);
        String flightNumber = ui.promptForFlightNumber();
        int numTickets = ui.promptForNumberOfTickets();
        
        FlightBooking booking = findBooking(customer, flightNumber);
        if (booking == null) {
            ui.displayError("Booking not found");
            return;
        }

        if (numTickets > booking.getNumTickets()) {
            ui.displayError("Cannot cancel more tickets than booked");
            return;
        }

        if (numTickets == booking.getNumTickets()) {
            bookings.remove(booking);
            booking.getFlight().removePassenger(customer, numTickets);
        } else {
            booking.setNumTickets(booking.getNumTickets() - numTickets);
            booking.getFlight().removePassenger(customer, numTickets);
        }
        ui.displaySuccess("Booking cancelled successfully");
    }

    public void displayCustomerBookings(Customer customer, ConsoleInterface ui) {
        List<FlightBooking> bookings = getCustomerBookings(customer.getUserId());
        ui.displayCustomerBookings(bookings);
    }

    public List<FlightBooking> getCustomerBookings(String customerId) {
        return bookings.stream()
                .filter(b -> b.getCustomer().getUserId().equals(customerId))
                .collect(Collectors.toList());
    }

    private FlightBooking findBooking(Customer customer, String flightNumber) {
        return bookings.stream()
                .filter(b -> b.getCustomer().equals(customer) && 
                           b.getFlight().getNumber().equalsIgnoreCase(flightNumber))
                .findFirst()
                .orElse(null);
    }
}