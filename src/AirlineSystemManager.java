public class AirlineSystemManager {
    private final AccountManager accountManager;
    private final CustomerManager customerManager;
    private final FlightManager flightManager;
    private final BookingManager bookingManager;
    private final UtilityExtract ui;
    private User currentUser;

    public AirlineSystemManager() {
        this.ui = new UtilityExtract();
        this.accountManager = new AccountManager();
        this.flightManager = new FlightManager();
        this.bookingManager = new BookingManager(flightManager, accountManager);
        this.customerManager = new CustomerManager(accountManager, bookingManager);
    }

    public void start() {
        boolean running = true;
        while (running) {
            if (currentUser == null) {
                showMainMenu();
            } else {
                currentUser.showDashboard(ui, this);
            }
        }
    }
    private void showMainMenu() {
        ui.displayMainMenu();
        int choice = ui.getMainMenuChoice();
        
        switch (choice) {
            case 1: currentUser = accountManager.handleAdminLogin(ui); break;
            case 2: currentUser = accountManager.handleAdminRegistration(ui); break;
            case 3: currentUser = accountManager.handleCustomerLogin(ui); break;
            case 4: currentUser = accountManager.handleCustomerRegistration(ui); break;
            case 5: displayManualInstructions(); break;
            case 0: System.exit(0);
        }
    }

    private void displayManualInstructions() {
        System.out.println("\nUser Manual:");
        System.out.println("1. Admins can manage all customer accounts and flights");
        System.out.println("2. Customers can book flights and manage their own accounts");
        System.out.println("3. Default admin credentials: root/root");
        System.out.println("4. Flight bookings are limited to 10 tickets per transaction");
    }

    public CustomerManager getCustomerManager() { return customerManager; }
    public FlightManager getFlightManager() { return flightManager; }
    public BookingManager getBookingManager() { return bookingManager; }
    public AccountManager getAccountManager() { return accountManager; }
    
    public void logout() {
        currentUser = null;
        ui.displaySuccess("Logged out successfully");
    }
}
