// CustomerManager.java

import java.util.List;

public class CustomerManager {
    private final AccountManager accountManager;
    private final BookingManager bookingManager;

    public CustomerManager(AccountManager accountManager, BookingManager bookingManager) {
        this.accountManager = accountManager;
        this.bookingManager = bookingManager;
    }

    public void addNewCustomer(UtilityExtract ui) {
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

    public void searchCustomer(UtilityExtract ui) {
        String customerId = ui.promptForCustomerId();
        Customer customer = accountManager.findCustomerById(customerId);
        if (customer != null) {
            ui.displayCustomerDetails(customer);
        } else {
            ui.displayError("Customer not found");
        }
    }

    public void updateCustomer(UtilityExtract ui) {
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

    public void deleteCustomer(UtilityExtract ui) {
        String customerId = ui.promptForCustomerId();
        if (accountManager.deleteAccount(customerId)) {
            ui.displaySuccess("Customer deleted successfully");
        } else {
            ui.displayError("Customer not found");
        }
    }

    public void displayAllCustomers(UtilityExtract ui) {
        List<Customer> customers = accountManager.getAllCustomers();
        ui.displayAllCustomers(customers);
    }

    public void displayCustomerFlights(UtilityExtract ui) {
        String customerId = ui.promptForCustomerId();
        List<FlightBooking> bookings = bookingManager.getCustomerBookings(customerId);
        ui.displayCustomerBookings(bookings);
    }

    public void updateCustomerInfo(Customer customer, UtilityExtract ui) {
        CustomerRegistrationData data = ui.promptForCustomerUpdate();
        customer.setName(data.name());
        customer.setEmail(data.email());
        customer.setPhone(data.phone());
        customer.setAddress(data.address());
        customer.setAge(data.age());
        ui.displaySuccess("Your information updated successfully");
    }
}