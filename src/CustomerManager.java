// CustomerManager.java

import java.util.List;

public class CustomerManager {
    private final AccountManager accountManager;
    private final BookingManager bookingManager;

    public CustomerManager(AccountManager accountManager, BookingManager bookingManager) {
        this.accountManager = accountManager;
        this.bookingManager = bookingManager;
    }

    public void addNewCustomer(ConsoleInterface ui) {
        CustomerRegistrationData data = ui.promptForCustomerRegistration();
        try {
            accountManager.registerCustomer(
                data.username(), 
                data.password(),
                data.name(),
                data.email(),
                data.phone(),
                data.address(),
                data.age()
            );
            ui.displaySuccess("Customer added successfully");
        } catch (IllegalArgumentException e) {
            ui.displayError(e.getMessage());
        }
    }

    public void searchCustomer(ConsoleInterface ui) {
        String customerId = ui.promptForCustomerId();
        Customer customer = accountManager.findCustomerById(customerId);
        if (customer != null) {
            ui.displayCustomerDetails(customer);
        } else {
            ui.displayError("Customer not found");
        }
    }

    public void updateCustomer(ConsoleInterface ui) {
        String customerId = ui.promptForCustomerId();
        Customer customer = accountManager.findCustomerById(customerId);
        if (customer != null) {
            CustomerRegistrationData data = ui.promptForCustomerUpdate();
            customer.setName(data.name());
            customer.setEmail(data.email());
            customer.setPhone(data.phone());
            customer.setAddress(data.address());
            customer.setAge(data.age());
            ui.displaySuccess("Customer updated successfully");
        } else {
            ui.displayError("Customer not found");
        }
    }

    public void deleteCustomer(ConsoleInterface ui) {
        String customerId = ui.promptForCustomerId();
        if (accountManager.deleteAccount(customerId)) {
            ui.displaySuccess("Customer deleted successfully");
        } else {
            ui.displayError("Customer not found");
        }
    }

    public void displayAllCustomers(ConsoleInterface ui) {
        List<Customer> customers = accountManager.getAllCustomers();
        ui.displayAllCustomers(customers);
    }

    public void displayCustomerFlights(ConsoleInterface ui) {
        String customerId = ui.promptForCustomerId();
        List<FlightBooking> bookings = bookingManager.getCustomerBookings(customerId);
        ui.displayCustomerBookings(bookings);
    }

    public void updateCustomerInfo(Customer customer, ConsoleInterface ui) {
        CustomerRegistrationData data = ui.promptForCustomerUpdate();
        customer.setName(data.name());
        customer.setEmail(data.email());
        customer.setPhone(data.phone());
        customer.setAddress(data.address());
        customer.setAge(data.age());
        ui.displaySuccess("Your information updated successfully");
    }
}