public class UserFactory {
    public static User createUser(UserType type, String userId, String username, String password, 
                                AccountManager accountManager, Object... additionalArgs) {
        switch (type) {
            case ADMIN:
                return new Admin(userId, username, password, accountManager);
            case CUSTOMER:
                if (additionalArgs.length >= 5) {
                    return new Customer(userId, username, password, 
                                      (String) additionalArgs[0], // name
                                      (String) additionalArgs[1], // email
                                      (String) additionalArgs[2], // phone
                                      (String) additionalArgs[3], // address
                                      (int) additionalArgs[4],   // age
                                      accountManager);
                }
                throw new IllegalArgumentException("Not enough arguments for Customer creation");
            default:
                throw new IllegalArgumentException("Invalid user type");
        }
    }
}