public class Admin extends User {
    public Admin(String userId, String username, String password, AccountManager accountManager) {
        super(userId, username, password, UserType.ADMIN, accountManager);
    }

    @Override
    public void showDashboard(UtilityExtract ui, AirlineSystemManager manager) {
        while (true) {
            ui.displayAdminMenu(username);
            int choice = ui.getAdminMenuChoice();
            
            switch (choice) {
                case 1: manager.getCustomerManager().addNewCustomer(ui); break;
                case 2: manager.getCustomerManager().searchCustomer(ui); break;
                case 3: manager.getCustomerManager().updateCustomer(ui); break;
                case 4: manager.getCustomerManager().deleteCustomer(ui); break;
                case 5: manager.getCustomerManager().displayAllCustomers(ui); break;
                case 6: manager.getCustomerManager().displayCustomerFlights(ui); break;
                case 7: manager.getFlightManager().displayFlightPassengers(ui); break;
                case 8: manager.getFlightManager().deleteFlight(ui); break;
                case 0: 
                    manager.logout(); 
                    return;
                default: ui.displayError("Invalid choice");
            }
        }
    }
}