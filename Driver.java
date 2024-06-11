import java.io.*;
import java.util.List;
import java.util.Random;

public class Driver {

    private static final int[] SEQ_NUMS = { 3000, 2000, 5000, 2500, 2500, 1500, 3000 };
    private static final String filePath = "output.csv";

    public static boolean save(Simulation sim) {
        File file = new File(filePath);
        int[][] arr = sim.getResults();
        String[] labels = sim.getLabels();
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
            writer.print("");
            for (int r = 0; r < sim.NUM_OF_SEQ; r++) {
                writer.print(labels[r] + ",");
                for (int c = 0; c < sim.SEQ_LEN; c++)
                    writer.print(arr[r][c] + ",");
                writer.println();
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] arg) {
        for (int i = 1; i <= 7; i++) {
            Simulation sim = new Simulation(SEQ_NUMS[i - 1]);
            sim.ZONE = i;
            sim.generateBehavior();
            save(sim);
        }

    }
}
