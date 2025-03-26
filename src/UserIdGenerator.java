import java.util.Random;

public class UserIdGenerator {
    public String generate() {
        Random rand = new Random();
        String randomID = Integer.toString(rand.nextInt(1000000));
        while (Integer.parseInt(randomID) < 20000) {
            randomID = Integer.toString(rand.nextInt(1000000));
        }
        return randomID;
    }
}
