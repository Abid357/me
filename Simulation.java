import java.util.Random;

public class Simulation {

    public static int SEED = 2;
    public static int ZONE = 7;
    public static int SEQ_LEN = 30;
    public static int NUM_OF_SEQ = 10;
    public static int PERC_RATE_OF_ZONE = 3;
    public static int PERC_RATE_OF_NON_ZONE = 6;
    public static int PERC_NOISE = 10;
    public static String[] ZONES = {"Use the bookshelf", "Gaming section", "Take-away", "Side Table 1", "Side Table 2"
            , "Business seats", "Corner"};

    private Random randomZone = new Random(SEED);
    private Random random = new Random();
    private int PERCENTAGE = 100;
    private int[][] arr;
    private String[] labels;

    public Simulation(int sequenceNumber) {
        NUM_OF_SEQ = sequenceNumber;
        arr = new int[NUM_OF_SEQ][SEQ_LEN];
        labels = new String[NUM_OF_SEQ];
    }

    public String[] getLabels() {
        return labels;
    }

    public int[][] getResults() {
        return arr;
    }

    public int generateSequences(int currentZone) {
        if (randomZone.nextInt(100) < PERCENTAGE) {
            if (currentZone == ZONE)
                PERCENTAGE -= PERC_RATE_OF_ZONE;
            else
                PERCENTAGE -= PERC_RATE_OF_NON_ZONE;
            return currentZone;
        } else {
            PERCENTAGE = 100;
            if (currentZone != ZONE)
                return ZONE;
            else {
                int x = random.nextInt(7 - 1 + 1) + 1;
                while (x == currentZone)
                    x = random.nextInt(7 - 1 + 1) + 1;
                return x;
            }
        }
    }

    public void generateBehavior() {
        int currentZone = ZONE;
        for (int row = 0; row < NUM_OF_SEQ; row++) {

            if (random.nextInt(100) < PERC_NOISE) {
                labels[row] = "Unknown";
                for (int col = 0; col < SEQ_LEN; col++) {
                    int x = random.nextInt(7 - 1 + 1) + 1;
                    arr[row][col] = x;
                }
            } else {
                labels[row] = ZONES[ZONE - 1];
                for (int col = 0; col < SEQ_LEN; col++) {
                    currentZone = generateSequences(currentZone);
                    arr[row][col] = currentZone;
                }
            }
        }
    }
}
