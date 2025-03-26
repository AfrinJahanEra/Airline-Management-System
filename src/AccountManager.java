// AccountManager.java

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class AccountManager {
    private final Map<String, User> users; // username -> User
    private final UserIdGenerator idGenerator;

    public AccountManager() {
        this.users = new HashMap<>();
        this.idGenerator = new UserIdGenerator();
        // Initialize with default admin
        registerAdmin("root", "root");
    }

    public User handleAdminLogin(UtilityExtract ui) {
        String[] credentials = ui.promptForCredentials();
        User user = login(credentials[0], credentials[1]);

        if (user == null || !(user instanceof Admin)) {
            ui.displayError("Invalid admin credentials");
            return null;
        }
        return user;
    }

    public User handleAdminRegistration(UtilityExtract ui) {
        String[] credentials = ui.promptForCredentials();
        try {
            User admin = registerAdmin(credentials[0], credentials[1]);
            ui.displaySuccess("Admin registered successfully");
            return admin;
        } catch (IllegalArgumentException e) {
            ui.displayError(e.getMessage());
            return null;
        }
    }

    public User handleCustomerLogin(UtilityExtract ui) {
        String[] credentials = ui.promptForCredentials();
        User user = login(credentials[0], credentials[1]);

        if (user == null || !(user instanceof Customer)) {
            ui.displayError("Invalid customer credentials");
            return null;
        }
        return user;
    }

    public User handleCustomerRegistration(UtilityExtract ui) {
        CustomerRegistrationData data = ui.promptForCustomerRegistration();
        try {
            User customer = registerCustomer(
                    data.username(),
                    data.password(),
                    data.name(),
                    data.email(),
                    data.phone(),
                    data.address(),
                    data.age());
            ui.displaySuccess("Customer registered successfully");
            return customer;
        } catch (IllegalArgumentException e) {
            ui.displayError(e.getMessage());
            return null;
        }
    }

    public User registerAdmin(String username, String password) {
        if (users.containsKey(username)) {
            throw new IllegalArgumentException("Username already exists");
        }
        String userId = idGenerator.generate();
        User admin = UserFactory.createUser(UserType.ADMIN, userId, username, password, this);
        users.put(username, admin);
        return admin;
    }

    public User registerCustomer(String username, String password,
            String name, String email,
            String phone, String address, int age) {
        if (users.containsKey(username)) {
            throw new IllegalArgumentException("Username already exists");
        }
        String userId = idGenerator.generate();
        User customer = UserFactory.createUser(UserType.CUSTOMER, userId, username, password, 
                                             this, name, email, phone, address, age);
        users.put(username, customer);
        return customer;
    }

    public User login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public boolean deleteAccount(String userId) {
        Optional<User> userToRemove = users.values().stream()
                .filter(u -> u.getUserId().equals(userId))
                .findFirst();

        if (userToRemove.isPresent()) {
            users.remove(userToRemove.get().getUsername());
            return true;
        }
        return false;
    }

    public Customer findCustomerById(String userId) {
        return users.values().stream()
                .filter(u -> u instanceof Customer)
                .map(u -> (Customer) u)
                .filter(c -> c.getUserId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    public List<Customer> getAllCustomers() {
        return users.values().stream()
                .filter(u -> u instanceof Customer)
                .map(u -> (Customer) u)
                .collect(Collectors.toList());
    }

    public void deleteAccount(Customer customer, UtilityExtract ui, AirlineSystemManager manager) {
        if (ui.confirmAction("Are you sure you want to delete your account?")) {
            if (deleteAccount(customer.getUserId())) {
                ui.displaySuccess("Account deleted successfully");
                manager.logout();
            } else {
                ui.displayError("Failed to delete account");
            }
        }
    }
}

