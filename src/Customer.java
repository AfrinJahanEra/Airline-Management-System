public class Customer extends User {
    private String name;
    private String email;
    private String phone;
    private String address;
    private int age;

    public Customer(String userId, String username, String password, 
                   String name, String email, String phone, 
                   String address, int age, AccountManager accountManager) {
        super(userId, username, password, UserType.CUSTOMER, accountManager);
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.age = age;
    }

    @Override
    public void showDashboard(UtilityExtract ui, AirlineSystemManager manager) {
        while (true) {
            ui.displayCustomerMenu(username);
            int choice = ui.getCustomerMenuChoice();
            
            switch (choice) {
                case 1: manager.getBookingManager().bookFlight(this, ui); break;
                case 2: manager.getCustomerManager().updateCustomerInfo(this, ui); break;
                case 3: manager.getAccountManager().deleteAccount(this, ui, manager); break;
                case 4: manager.getFlightManager().displayFlightSchedule(ui); break;
                case 5: manager.getBookingManager().cancelFlight(this, ui); break;
                case 6: manager.getBookingManager().displayCustomerBookings(this, ui); break;
                case 0: 
                    manager.logout(); 
                    return;
                default: ui.displayError("Invalid choice");
            }
        }
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}