public abstract class User {
    protected final String userId;
    protected final String username;
    protected final String password;
    protected final UserType type;
    protected final AccountManager accountManager;

    public User(String userId, String username, String password, 
               UserType type, AccountManager accountManager) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.type = type;
        this.accountManager = accountManager;
    }

    public abstract void showDashboard(UtilityExtract ui, AirlineSystemManager manager);

    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public UserType getType() { return type; }
}