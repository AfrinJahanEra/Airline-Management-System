import java.util.Arrays;
import java.util.Random;

public class RandomGenerator {

    private String randomNum;
    /*
     * City name is at the 0-index, its latitude is on the 1-index and longitude on
     * the 2-index
     */
    private static final String[][] DESTINATIONS = {
            { "Karachi", "24.871940", "66.988060" }, { "Bangkok", "13.921430", "100.595337" },
            { "Jakarta", "-6.174760", "106.827072" },
            { "Islamabad", "33.607587", "73.100316" }, { "New York City", "40.642422", "-73.781749" },
            { "Lahore", "31.521139", "74.406519" },
            { "Gilgit Baltistan", "35.919108", "74.332838" }, { "Jeddah", "21.683647", "39.152862" },
            { "Riyadh", "24.977080", "46.688942" }, { "New Delhi", "28.555764", "77.096520" },
            { "Hong Kong", "22.285005", "114.158339" }, { "Beijing", "40.052121", "116.609609" },
            { "Tokyo", "35.550899", "139.780683" }, { "Kuala Lumpur", "2.749914", "101.707160" },
            { "Sydney", "-33.942028", "151.174304" }, { "Melbourne", "-37.671812", "144.846079" },
            { "Cape Town", "-33.968879", "18.596982" }, { "Madrid", "40.476938", "-3.569428" },
            { "Dublin", "53.424077", "-6.256792" }, { "Johannesburg", "25.936834", "27.925890" },
            { "London", "51.504473", "0.052271" }, { "Los Angeles", "33.942912", "-118.406829" },
            { "Brisbane", "-27.388925", "153.116751" }, { "Amsterdam", "52.308100", "4.764170" },
            { "Stockholm", "59.651236", "17.924793" }, { "Frankfurt", "50.050085", "8.571911" },
            { "New Taipei City", "25.066471", "121.551638" }, { "Rio de Janeiro", "-22.812160", "-43.248636" },
            { "Seoul", "37.558773", "126.802822" }, { "Yokohama", "35.462819", "139.637008" },
            { "Ankara", "39.951898", "32.688792" }, { "Casablanca", "33.368202", "-7.580998" },
            { "Shenzhen", "22.633977", "113.809360" }, { "Baghdad", "33.264824", "44.232014" },
            { "Alexandria", "40.232302", "-85.637150" }, { "Pune", "18.579019", "73.908572" },
            { "Shanghai", "31.145326", "121.804512" }, { "Istanbul", "41.289143", "41.261401", "28.742376" },
            { "Bhutan", "22.648322", "88.443152" }, { "Dhaka", "23.847177", "90.404133" },
            { "Munich", "48.354327", "11.788680" }, { "Perth", "56.435749", "-3.371675" },
            { "Mexico", "21.038103", "-86.875259" }, { "California", "32.733089", "-117.194514" },
            { "Kabul", "34.564296", "69.211574" }, { "Yangon", "47.604505", "-122.330604" },
            { "Lagos", "17.981829", "102.565684" }, { "Santiago", "-33.394795", "-70.790183" },
            { "Kuwait", "29.239250", "47.971575" }, { "Nairobi", "39.958361", "41.174310" },
            { "Tehran", "35.696000", "51.401000" }, { "Saint Petersburg", "60.013492", "29.722189" },
            { "Hanoi", "21.219185", "105.803967" }, { "Sialkot", "32.328361", "74.215310" },
            { "Berlin", "52.554316", "13.291213" }, { "Paris", "48.999560", "2.539274" },
            { "Dubai", "25.249869", "55.366483" }
    };

    public String[][] randomDestinations() {
        Random rand = new Random();
        int city1 = rand.nextInt(DESTINATIONS.length);
        int city2;
        do {
            city2 = rand.nextInt(DESTINATIONS.length);
        } while (city2 == city1);

        return new String[][] {
                Arrays.copyOf(DESTINATIONS[city1], 3),
                Arrays.copyOf(DESTINATIONS[city2], 3)
        };
    }

    public int randomNumOfSeats() {
        int seats;
        do {
            seats = new Random().nextInt(500);
        } while (seats < 75);
        return seats;
    }

    public String randomFlightNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            sb.append((char) (random.nextInt(26) + 'a'));
        }
        sb.append("-").append(randomNumOfSeats() / 1);
        return sb.toString();
    }

    public String randomGate() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append((char) (random.nextInt(26) + 'A'));
        sb.append(random.nextInt(30) + 1);
        return sb.toString();
    }
}
